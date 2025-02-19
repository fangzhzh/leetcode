# Synchronized关键字

## 1. Synchronized特性: 内存可见性与有序性
### 1.0 原子性
### 1.1 内存可见性
同步块的操作保证内存可见性

```java
public class SynchronizedVisibility {
    private int value = 0;
    private final Object lock = new Object();
    
    public void setValue(int value) {
        synchronized(lock) {
            this.value = value;    // 写入操作
        }                         // 退出时刷新到主内存
    }
    
    public int getValue() {
        synchronized(lock) {       // 进入时从主内存读取
            return value;         // 读取操作
        }
    }
}
```

### 1.2 有序性保证
* **happens-before关系**：同步块的所有操作与其他线程的同步块形成happens-before关系

JMM可以通过happens-before关系向程序员提供跨线程的内存可见性保证（如果A线程的写操作a与B线程的读操作b之间存在happens-before关系，尽管a操作和b操作在不同的线程中执行，但JMM向程序员保证a操作将对b操作可见）。

```java
public class SynchronizedOrdering {
    private int a = 0;
    private boolean flag = false;
    private final Object lock = new Object();
    
    public void writer() {
        synchronized(lock) {
            a = 1;            // 1
            flag = true;      // 2
        }
    }
    
    public void reader() {
        synchronized(lock) {
            if (flag) {       // 3
                int i = a;    // 4 保证能读取到1
            }
        }
    }
}
```
## 2. 底层实现原理

### 2.1 对象头结构
```
|内容                    | 说明                          |
|--------------------------|-------------------------------|
|Mark Word (32/64 bits)    |存储对象的hashCode、锁信息等|
|Class Metadata Address    |存储到对象类型数据的指针  |
|Array length(if array)    |数组的长度（如果是数组）     |
```

### 2.2 Mark Word与ObjectMonitor的关系

#### 2.2.1 Mark Word结构
```
|锁状态   | Mark Word内容                        | 说明                  |
|---------|-----------------------------------|----------------------|
|无锁     | 对象hashCode、分代年龄          | 正常对象              |
|偏向锁   | 线程ID、epoch、分代年龄        | 单线程重复访问         |
|轻量级锁 | 指向栈中锁记录的指针          | 多线程竞争轻度        |
|重量级锁 | 指向ObjectMonitor的指针        | 多线程竞争激烈        |
```

#### 2.2.2 Mark Word与ObjectMonitor的关系
* Mark Word是对象头中的一部分，存储在对象自身内存中
* ObjectMonitor是一个独立的数据结构，在JVM中管理

```java
// 当锁升级为重量级锁时
 class Object {
    // Mark Word中的重量级锁状态
    markword = ptr_to_heavyweight_monitor;
    
    // 指向对应的ObjectMonitor对象
    ObjectMonitor* monitor = markword->monitor();
}
```

### 2.3 锁升级过程

#### 2.3.1 为什么需要锁升级？
* 线程竞争的概率是非常低的
* 大部分情况下，锁总是由同一个线程获得
* 通过锁升级的方式，减少了锁竞争带来的性能开销

#### 2.3.2 锁的三种状态
```java
public class LockExample {
    private static Object lock = new Object();
    
    public void method() {
        synchronized(lock) {
            // 锁会经历以下状态：
            // 1. 偏向锁 (Biased Lock)：单线程重复访问
            // 2. 轻量级锁 (Lightweight Lock)：多线程竞争，自旋 (Spinning) 等待
            // 3. 重量级锁 (Heavyweight Lock)：竞争激烈，阻塞 (Blocking) 等待
        }
    }
}
```

#### 2.3.3 各种锁的详细解析

1. **偏向锁（Biased Locking）**
   * 什么是偏向锁？
     ```java
     public class BiasedLockingExample {
         private static Object obj = new Object();
         
         public void methodA() {
             synchronized(obj) {
                 // 第一次获取锁，对象头记录线程ID
                 // 之后这个线程再次获取锁，只需要对比ID
             }
         }
     }
     ```
   * 为什么需要偏向锁？
     - 大部分情况下，锁总是由同一个线程获得
     - 避免了每次都要获取锁的开销

2. **轻量级锁（Lightweight Locking）**
   * 什么是自旋(Spinning)？
     ```java
     // 自旋的伪代码实现
     while(!获取到锁) {
         // CPU空转，不释放CPU时间片
         // 适用于锁竞争不激烈的情况
     }
     ```
   * 为什么需要自旋？
     - 避免线程切换的开销
     - 适用于锁竞争不激烈的情况

3. **重量级锁（Heavyweight Locking）**
   * 重量级锁的实现原理：
     ```java
     // 每个Java对象都有一个关联的monitor
     public class ObjectMonitor {
         // 记录持有锁的线程
         private Thread owner;
         // 等待队列，存放被阻塞的线程
         private Queue<Thread> entryList;
         // 等待和通知机制的等待队列
         private Queue<Thread> waitSet;
         // 记录该线程获得锁的次数（可重入）
         private int recursions;
     }
     ```

   * 重量级锁的工作流程：
     ```java
     public class HeavyweightLockExample {
         private static final Object lock = new Object();
         
         public static void main(String[] args) {
             // 创建多个线程竞争锁
             for(int i = 0; i < 3; i++) {
                 new Thread(() -> {
                     synchronized(lock) {
                         // 1. 线程尝试获取锁
                         // 2. 如果锁被占用，进入entryList队列
                         // 3. 线程被阻塞，进入BLOCKED状态
                         // 4. 当前线程释放锁时，从队列中唤醒一个线程
                         try {
                             Thread.sleep(100); // 模拟业务处理
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }, "Thread-" + i).start();
             }
         }
     }
     ```

   * 重量级锁的内部机制：
     1. **锁的获取过程**
        - 检查owner是否为空
        - 如果为空，尝试获取锁（CAS设置owner）
        - 如果不为空，检查是否为当前线程（可重入锁）
        - 如果都不是，将线程加入entryList

     2. **等待队列管理**
        ```java
        // 等待队列的状态转换
        public void wait() throws InterruptedException {
            // 1. 释放当前持有的锁
            // 2. 将线程加入waitSet
            // 3. 等待被通知（notify）
            // 4. 重新竞争锁
        }
        ```

     3. **锁的释放过程**
        - 如果是可重入锁，递减recursions
        - 当recursions为0时，清空owner
        - 从等待队列中选择一个线程唤醒

   * 重量级锁的特点：
     1. **实现机制**
        - 基于操作系统的Mutex Lock实现
        - 通过操作系统内核来完成线程的阻塞和唤醒
     
     2. **性能影响**
        - 涉及用户态和内核态的切换
        - 线程阻塞和唤醒的开销大
        - 适用于锁竞争激烈的情况
     
     3. **优缺点**
        - 优点：不消耗CPU（相比自旋）
        - 缺点：线程需要操作系统从用户态切换到内核态

#### 2.3.4 锁升级的具体过程

1. **无锁 (Non-locked) → 偏向锁 (Biased Lock)**
   * 线程第一次访问同步块
   * 在对象头Mark Word中记录线程ID
   * 下次相同线程加锁时，只需比对线程ID
   * 如果ID一致，说明是同一个线程，直接获得锁

2. **偏向锁 (Biased Lock) → 轻量级锁 (Lightweight Lock)**
   * 当有其他线程竞争锁时
   * 首先撤销偏向锁，标记为无锁状态
   * 线程在自己的栈帧 (Stack Frame) 中创建锁记录 (Lock Record)
   * 通过CAS (Compare-And-Swap) 操作尝试在对象头中设置指向锁记录的指针

3. **轻量级锁 (Lightweight Lock) → 重量级锁 (Heavyweight Lock)**
   * 当线程自旋一定次数（默认10次）仍未获得锁
   * JVM会将锁升级为重量级锁
   * 未获得锁的线程进入阻塞队列 (Monitor's EntryList)
   * 等待操作系统来调度
## 3. 锁的优化
### 3.1 锁消除
```java
public class LockElimination {
    public String concatString(String s1, String s2) {
        // JVM可以分析到这里的StringBuilder不会被其他线程访问
        // 自动消除synchronized
        return new StringBuilder().append(s1).append(s2).toString();
    }
}
```

### 3.2 锁粗化
```java
public class LockCoarsening {
    public void method() {
        // JVM会将这三个同步块合并
        synchronized(this) {
            // 操作1
        }
        synchronized(this) {
            // 操作2
        }
        synchronized(this) {
            // 操作3
        }
    }
}
```
## 4. 使用场景

### 4.1 复合操作原子性
```java
public class BankAccount {
    private int balance;
    
    public synchronized void transfer(BankAccount to, int amount) {
        if (balance >= amount) {
            balance -= amount;
            to.balance += amount;
        }
    }
}
```

### 4.2 对象状态一致性
```java
public class Counter {
    private int count;
    private int total;
    
    public synchronized void increment() {
        count++;
        total += count;
    }
}
```

### 4.3 类级别的线程安全
```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```


## 5. Synchronized vs Lock

### 2.1 使用方式
```java
// Synchronized
public synchronized void method() {
    // 业务代码
}

// Lock
Lock lock = new ReentrantLock();
public void method() {
    lock.lock();
    try {
        // 业务代码
    } finally {
        lock.unlock();
    }
}
```

### 2.2 对比
| 特性 | Synchronized | Lock |
|---------|--------------|------|
| 锁的实现 | JVM层面，关键字 | API层面，JDK实现 |
| 灵活性 | 差，自动获取释放 | 好，手动控制 |
| 公平性 | 不公平 | 可以设置公平性 |
| 结构简洁性 | 好 | 差 |
| 线程等待 | 不可中断 | 可中断 |
| 锁粒度 | 固定 | 灵活 |
| 性能 | 升级后还可以 | 稳定好 |

### 2.3 Lock独有特性
1. **可中断获取锁**
```java
lock.lockInterruptibly();
```

2. **超时获取锁**
```java
if (lock.tryLock(5, TimeUnit.SECONDS)) {
    try {
        // 业务代码
    } finally {
        lock.unlock();
    }
}
```

3. **公平锁**
```java
ReentrantLock fairLock = new ReentrantLock(true);
```

4. **多条件变量**
```java
ReentrantLock lock = new ReentrantLock();
Condition condition1 = lock.newCondition();
Condition condition2 = lock.newCondition();
```

## 3. 使用建议

1. **简单同步场景**
   * 使用synchronized
   * 代码简洁，不容易出错

2. **复杂同步场景**
   * 使用Lock
   * 需要公平性、可中断、超时等特性

3. **注意事项**
   * Lock必须在finally中释放
   * 尽量缩小同步范围
   * 避免死锁
