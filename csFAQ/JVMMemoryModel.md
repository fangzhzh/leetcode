# Java内存模型（JMM）详解

## 1. JVM内存结构

### 1.1 整体架构
```
+------------------+     +------------------+     +------------------+
|     Thread 1     |     |     Thread 2     |     |     Thread N     |
|  +------------+  |     |  +------------+  |     |  +------------+  |
|  | 工作内存    |  |     |  | 工作内存    |  |     |  | 工作内存    |  |
|  | (CPU缓存)   |  |     |  | (CPU缓存)   |  |     |  | (CPU缓存)   |  |
|  +------------+  |     |  +------------+  |     |  +------------+  |
+--------↕--------+     +--------↕--------+     +--------↕--------+
         ↕                      ↕                       ↕
+--------------------------------------------------+
|                    主内存（堆内存）                  |
|                    Main Memory                     |
+--------------------------------------------------+
```

### 1.2 为什么需要CPU缓存
1. **性能考虑**
   ```
   CPU访问速度：寄存器 > L1缓存 > L2缓存 > 主内存
   - 寄存器访问：1个时钟周期
   - L1缓存访问：~3-4个时钟周期
   - L2缓存访问：~10个时钟周期
   - 主内存访问：~100个时钟周期
   ```

2. **局部性原理**
   ```java
   // 时间局部性
   for (int i = 0; i < 10000; i++) {
       counter++;  // counter会被缓存在CPU缓存中
   }
   
   // 空间局部性
   int[] array = new int[10000];
   for (int i = 0; i < array.length; i++) {
       array[i] = i;  // 连续的内存访问
   }
   ```

## 2. 内存交互操作

### 2.1 八种原子操作
```
1. lock（锁定）：作用于主内存，将变量标识为线程独占
2. unlock（解锁）：作用于主内存，将变量释放出来
3. read（读取）：作用于主内存，将变量值读到工作内存
4. load（载入）：作用于工作内存，将read的值载入变量副本
5. use（使用）：作用于工作内存，读取变量的值
6. assign（赋值）：作用于工作内存，将值赋给变量
7. store（存储）：作用于工作内存，将变量值传送到主内存
8. write（写入）：作用于主内存，将store的值写入变量
```

### 2.2 变量的操作过程
```java
public class MemoryOperationExample {
    private int value = 0;  // 主内存中的变量
    
    public void setValue(int v) {
        // 1. read: 从主内存读取value的值
        // 2. load: 将value载入工作内存
        // 3. assign: 将v赋值给工作内存中的value
        // 4. store: 将修改后的value传送到主内存
        // 5. write: 将value的值写入主内存
        this.value = v;
    }
    
    public int getValue() {
        // 1. read: 从主内存读取value的值
        // 2. load: 将value载入工作内存
        // 3. use: 从工作内存读取value的值
        return value;
    }
}
```

## 3. 内存可见性问题

### 3.1 可见性问题的产生
```java
public class VisibilityProblem {
    private int value = 0;
    
    // 线程A
    public void write() {
        value = 1;  // 写入工作内存，不一定立即刷新到主内存
    }
    
    // 线程B
    public void read() {
        // 从工作内存读取，可能读不到最新值
        System.out.println(value);
    }
}
```

### 3.2 缓存一致性协议（MESI）
```
+-------+     +-------+     +-------+
| CPU1  |     | CPU2  |     | CPU3  |
| Cache |     | Cache |     | Cache |
+-------+     +-------+     +-------+
    ↓            ↓            ↓
+-----------------------------------+
|           系统总线（Bus）           |
+-----------------------------------+
    ↑
+-------+
| 主内存  |
+-------+

缓存状态：
- Modified（修改）：该CPU已修改缓存行
- Exclusive（独占）：缓存行只在该CPU中
- Shared（共享）：缓存行可能在其他CPU中
- Invalid（无效）：缓存行已失效
```

## 4. JMM内存屏障

### 4.1 四种屏障类型
```
LoadLoad屏障：确保Load1数据的装载先于Load2及后续装载指令完成
StoreStore屏障：确保Store1数据对其他处理器可见先于Store2及后续存储指令
LoadStore屏障：确保Load1数据装载先于Store2及后续存储指令
StoreLoad屏障：确保Store1数据对其他处理器变得可见先于Load2及后续装载指令
```

### 4.2 屏障的使用示例
```java
public class BarrierExample {
    private int a = 0;
    private volatile int v = 0;
    
    public void write() {
        a = 1;                  // Store1
        // StoreStore屏障
        v = 1;                  // Store2
        // StoreLoad屏障
    }
    
    public void read() {
        int i = v;              // Load1
        // LoadLoad屏障
        int j = a;              // Load2
        // LoadStore屏障
    }
}
```

## 5. 实际应用

### 5.1 单例模式中的内存问题
```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {  // 第一次检查
            synchronized (Singleton.class) {
                if (instance == null) {  // 第二次检查
                    // 1. 分配内存空间
                    // 2. 初始化对象
                    // 3. 将引用指向内存空间
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### 5.2 生产者-消费者模式
```java
public class ProducerConsumer {
    private volatile boolean flag = false;
    private int data;
    
    public void produce(int d) {
        while (flag) {
            // 等待消费者消费
        }
        data = d;        // 写入数据
        flag = true;     // 通知消费者
    }
    
    public void consume() {
        while (!flag) {
            // 等待生产者生产
        }
        use(data);      // 使用数据
        flag = false;    // 通知生产者
    }
}
```
