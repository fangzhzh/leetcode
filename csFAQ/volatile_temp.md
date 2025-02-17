# Volatile关键字详解

## 1. JVM内存模型和Volatile

### 1.1 为什么需要JVM内存模型

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

1. **性能原因**
   ```
   内存访问速度差异：
   - 寄存器: ~1个时钟周期
   - L1缓存: ~3-4个时钟周期
   - L2缓存: ~10个时钟周期
   - 主内存: ~100个时钟周期
   ```

2. **并发问题**
   ```java
   public class MemoryVisibilityProblem {
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

### 1.2 Volatile如何解决可见性问题
```java
public class VolatileSolution {
    private volatile int number = 0;  // volatile变量
    
    public void write() {
        // 1. 写入CPU缓存
        // 2. 立即刷新到主内存
        // 3. 使其他CPU缓存失效
        number = 42;
    }
    
    public void read() {
        // 1. 从主内存读取最新值
        // 2. 更新到当前CPU缓存
        System.out.println(number);
    }
}
```

### 1.3 为什么不能所有变量都用Volatile
1. **性能开销**
   - 每次写操作都需要立即刷新到主内存
   - 需要使其他CPU缓存失效
   - 增加内存总线的压力

2. **缓存的优势**
   - 大多数变量不需要立即可见
   - CPU缓存提供了局部性原理的性能优化
   - JVM会自动优化对频繁访问变量的缓存

### 1.4 内存语义
1. **写内存语义**：
   - 写操作会强制将修改刷新到主内存
   - 写操作会导致其他CPU缓存失效

2. **读内存语义**：
   - 读操作会强制从主内存加载
   - 保证读取到最新的值

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

[以下内容与原文件相同...]
