# 识别和解决Android内存泄漏问题

在Android开发中，内存泄漏是一个常见的问题，即使不使用专业工具如LeakCanary或Android Profiler，我们也有一些方法可以识别和解决内存泄漏。

## 识别内存泄漏的方法

### 1. 观察应用行为和系统日志

- **应用卡顿或崩溃**：频繁的GC操作会导致应用卡顿，严重的内存泄漏会导致OOM崩溃
- **监控LogCat中的GC日志**：频繁的GC日志（特别是"GC_FOR_ALLOC"）可能表明存在内存问题
- **观察OOM错误**：`java.lang.OutOfMemoryError`错误明确指出内存不足

### 2. 使用Android内置的调试功能

- **使用Debug类**：
  ```java
  Debug.dumpHprofData("/sdcard/dump.hprof"); // 在可疑位置转储堆
  ```

- **使用Runtime监控内存使用**：
  ```java
  Runtime runtime = Runtime.getRuntime();
  long usedMemory = runtime.totalMemory() - runtime.freeMemory();
  Log.d("MemoryMonitor", "Used memory: " + (usedMemory / 1024) + " KB");
  ```

- **在Activity中重写onTrimMemory()方法**：
  ```java
  @Override
  public void onTrimMemory(int level) {
      super.onTrimMemory(level);
      if (level >= TRIM_MEMORY_MODERATE) {
          Log.w("MemoryWarning", "Memory pressure detected");
      }
  }
  ```

## 常见内存泄漏原因及解决方案

### 1. 静态变量持有Activity或Context引用

**问题**：
```java
public class SomeManager {
    private static Context sContext; // 静态变量持有Context
    
    public static void init(Context context) {
        sContext = context; // 可能导致泄漏
    }
}
```

**解决方案**：
```java
public class SomeManager {
    private static WeakReference<Context> sContextRef;
    
    public static void init(Context context) {
        // 使用应用Context而非Activity Context
        sContextRef = new WeakReference<>(context.getApplicationContext());
    }
    
    public static Context getContext() {
        return sContextRef != null ? sContextRef.get() : null;
    }
}
```

### 2. 内部类和匿名内部类引用

**问题**：
```java
public class MyActivity extends Activity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 处理消息，隐式持有外部Activity引用
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessageDelayed(0, 60000); // 延迟消息
    }
}
```

**解决方案**：
```java
public class MyActivity extends Activity {
    // 使用静态内部类
    private static class MyHandler extends Handler {
        private final WeakReference<MyActivity> activityRef;
        
        MyHandler(MyActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }
        
        @Override
        public void handleMessage(Message msg) {
            MyActivity activity = activityRef.get();
            if (activity != null) {
                // 安全地处理消息
            }
        }
    }
    
    private final MyHandler mHandler = new MyHandler(this);
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null); // 移除所有回调和消息
    }
}
```

### 3. 未注销的监听器和回调

**问题**：
```java
public class MyFragment extends Fragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SomeManager.getInstance().registerListener(listener);
    }
    
    private final SomeListener listener = new SomeListener() {
        @Override
        public void onEvent() {
            // 处理事件
        }
    };
}
```

**解决方案**：
```java
public class MyFragment extends Fragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SomeManager.getInstance().registerListener(listener);
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SomeManager.getInstance().unregisterListener(listener); // 注销监听器
    }
    
    private final SomeListener listener = new SomeListener() {
        @Override
        public void onEvent() {
            // 处理事件
        }
    };
}
```

### 4. 资源未关闭

**问题**：
```java
public void readFile() {
    InputStream is = null;
    try {
        is = new FileInputStream(file);
        // 读取文件
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 忘记关闭流
}
```

**解决方案**：
```java
public void readFile() {
    try (InputStream is = new FileInputStream(file)) {
        // 使用try-with-resources自动关闭资源
        // 读取文件
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

### 5. Bitmap未回收

**问题**：
```java
public void loadBitmap() {
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.large_image);
    imageView.setImageBitmap(bitmap);
    // 大型Bitmap未回收
}
```

**解决方案**：
```java
public void loadBitmap() {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inSampleSize = 2; // 降低采样率，减小内存占用
    
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.large_image, options);
    imageView.setImageBitmap(bitmap);
    
    // 当不再需要时
    if (bitmap != null && !bitmap.isRecycled()) {
        bitmap.recycle();
    }
}
```

## 预防内存泄漏的最佳实践

1. **避免在Activity/Fragment中使用静态变量**
2. **使用弱引用（WeakReference）处理长生命周期对象对短生命周期对象的引用**
3. **在生命周期结束时清理资源和注销监听器**
4. **使用应用Context（Application Context）而非Activity Context**
5. **避免在非静态内部类中创建长生命周期的对象**
6. **使用try-with-resources自动关闭资源**
7. **合理管理Bitmap内存**
8. **避免在异步任务中持有Activity引用**

通过这些方法，即使不使用专业工具，也能有效地识别和解决Android应用中的内存泄漏问题。