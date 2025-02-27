# Volatile关键字详解

## 1. 什么是Volatile
Volatile是Java提供的一种轻量级的同步机制。它保证了变量的"可见性"，但不保证"原子性"。

### 1.1 JVM内存模型
[JVM Memory model](./JVMMemoryModel.md)
```
+------------------+     +------------------+
|     Thread 1     |     |     Thread 2     |
|  +------------+  |     |  +------------+  |
|  | 工作内存    |  |     |  | 工作内存    |  |
|  | (CPU Cache)  |  |     |  | (CPU Cache)  |  |
|  +------------+  |     |  +------------+  |
+--------↕--------+     +--------↕--------+
         ↕                      ↕
+--------------------------------------------------+
|                    主内存                      |
|                 Main Memory                      |
+--------------------------------------------------+
```

1. **为什么不能所有数据都放在主内存**
   ```
   a. 性能差异：
   - 寄存器: ~1个时钟周期
   - L1缓存: ~3-4个时钟周期
   - L2缓存: ~10个时钟周期
   - 主内存: ~100个时钟周期
   
   ```

2. **可见性问题示例**
   ```java
   public class VisibilityProblem {
       private int number = 0;  // 非volatile变量
       
       // 线程A
       public void write() {
           number = 42;  // 只写入到CPU缓存，不保证何时刷新到主内存
       }
       
       // 线程B
       public void read() {
           // 从自己的CPU缓存读取，可能读不到最新值
           System.out.println(number);
       }
   }
   ```

### 1.2 Volatile的工作原理

#### 1.2.1 缓存失效的过程
```java
public class CacheInvalidationProcess {
    private volatile int value;
    
    // CPU1上的线程
    public void writeOnCPU1() {
        value = 42;
        // 1. CPU!写入前发出占用总线信号（Bus Lock）
        // 2. CPU1将缓存行状态改为Modified
        // 3. 通过总线发出广播信号
        // 4. 其他CPU收到信号，将相应缓存行标记为Invalid
        // 5. 写入前发出占用总线信号（Bus Lock）
    }
    
    // CPU2上的线程
    public void readOnCPU2() {
        // 1. CPU2发现缓存行是Invalid
        // 2. 从主内存重新读取数据
        // 3. 将新数据加载到缓存，状态改为Shared
        System.out.println(value);
    }
}
```

## 2. Volatile的三大特性

### 2.1 可见性
```java
public class VisibilityExample {
    private volatile int value = 0;
    
    public void setValue(int value) {
        this.value = value;  // 对其他线程立即可见
    }
    
    public int getValue() {
        return value;  // 总是获取最新值
    }
}
```

### 2.2 有序性（禁止重排序）
通过内存屏障保证有序性
```java
public class OrderingExample {
    private int a = 0;
    private volatile boolean flag = false;
    
    public void writer() {
        a = 1;                // Store1 (normal write)
        // StoreStore barrier - ensures a=1 is visible before flag=true
        flag = true;          // Store2 (volatile write)
        // StoreLoad barrier - ensures all stores complete before any subsequent loads
    }
    
    public void reader() {
        if (flag) {           // Load1 (volatile read)
            // LoadLoad barrier - ensures subsequent loads happen after flag read
            // LoadStore barrier - ensures subsequent stores happen after flag read
            int i = a;        // Load2 (normal read)
        }
    }
}
```

[详细内存屏障](./JVMMemoryModel.md)

### 2.3 单次读/写的原子性
```java
public class AtomicityExample {
    private volatile long value = 0L;  // 64位数据类型
    
    // 读取操作是原子的
    public long getValue() {
        return value;
    }
    
    // 写入操作是原子的
    public void setValue(long value) {
        this.value = value;
    }
    
    // 复合操作不保证原子性
    public void increment() {
        value++;  // 非原子操作！
    }
}
```

## 3. Volatile的内存屏障
[详细内存屏障](./JVMMemoryModel.md)

## 4 应用场景
### 4.1 双重检查单例模式
```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) { // synchronized 类/instance/object filed
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
注:
[synchronized 类/instance/object filed](./synchronizedAndLock.md)

### 4.2 独立观察（一写多读）
```java
public class Configuration {
    private volatile String config;
    
    public void updateConfig(String newConfig) {
        this.config = newConfig;  // 一个线程写入
    }
    
    public String getConfig() {
        return config;  // 多个线程读取
    }
}
```

## 5. 使用注意事项

### 5.1 不能保证原子性
```java
public class Counter {
    private volatile int count = 0;
    
    // 错误使用！不能保证原子性
    public void increment() {
        count++;  // 读取-修改-写入，是三个操作
    }
    
    // 正确使用：需要使用synchronized或AtomicInteger
    public synchronized void incrementRight() {
        count++;
    }
}
```

### 5.2 性能考虑
1. **内存屏障开销**：
   - 会插入内存屏障指令
   - 可能会导致缓存失效

2. **使用建议**：
   - 仅在必要时使用volatile
   - 优先考虑其他并发工具类
   - 注意变量的读写频率


| 特性     | Volatile      | Synchronized  |
|---------|--------------|---------------|
| 原理     | 内存屏障       | 锁机制         |
| 线程阻塞  | 不会          | 会            |
| 性能开销  | 较小          | 较大          |
| 原子性    | 单个操作      | 代码块         |
| 可见性    | 立即可见      | 退出时可见      |