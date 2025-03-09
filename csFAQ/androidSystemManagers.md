# Android System Managers: AMS, WMS, and PMS

Based on the article you referenced, here's an explanation of three critical system services in Android:

## ActivityManagerService (AMS)

ActivityManagerService is one of the most important system services in Android, responsible for managing the lifecycle and running state of applications.

Key responsibilities:
- Managing the lifecycle of Activities, Services, and Processes
- Handling application startup and shutdown
- Managing the back stack of activities
- Coordinating process priorities and memory allocation
- Handling Intent routing between components
- Managing application crashes and ANRs (Application Not Responding)

AMS works closely with the Zygote process to spawn new application processes and maintains a record of all running applications and their states.

## WindowManagerService (WMS)

WindowManagerService manages all windows and their visual presentation on the device screen.

Key responsibilities:
- Managing the window hierarchy and z-order
- Handling window focus and input events
- Coordinating window animations and transitions
- Managing screen orientation changes
- Handling display metrics and configurations
- Working with SurfaceFlinger for actual rendering

WMS determines which windows are visible, how they're positioned, and how input events are routed to the appropriate windows. It's essential for the UI rendering pipeline and works closely with AMS to manage activity transitions.

## PackageManagerService (PMS)

PackageManagerService is responsible for managing installed applications and their associated information.

Key responsibilities:
- Scanning and installing application packages (APKs)
- Maintaining application metadata (permissions, activities, services, etc.)
- Handling application updates and uninstallations
- Managing component resolution for Intents
- Enforcing permission checks
- Tracking application signatures and certificates

PMS maintains a database of all installed applications and their components, which is crucial for the Intent resolution system to work properly.
## 简单使用实例
### AMS启动程序
```java
Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.myapp"); // 获取启动应用程序的意图
if (launchIntent != null) {
    startActivity(launchIntent); // 启动应用程序
} else {
    Log.e(TAG, "Unable to launch the app");
}

```
### 使用PMS请求权限：
```java
private static final int PERMISSIONS_REQUEST_CODE = 123;
​
private void requestPermissionsIfNeeded() {
    int checkPermissionResult = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA); // 检查是否已经获取摄像头权限
    if (checkPermissionResult != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CODE); // 请求摄像头权限
    } else {
        // 已经获得了摄像头权限
    }
}
​
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == PERMISSIONS_REQUEST_CODE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 用户已经授权摄像头权限
        } else {
            // 用户拒绝授权摄像头权限
        }
    }
}


```
### 使用WMS管理应用程序窗口
```java
// 初始化WindowManager
WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
​
// 设置LayoutParams参数
WindowManager.LayoutParams params = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // 指定窗口需要显示在最上层
        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
        PixelFormat.TRANSLUCENT);
​
// 创建View并添加到WindowManager中
View myView = LayoutInflater.from(this).inflate(R.layout.my_view, null);
wm.addView(myView, params);

```
## Relationship Between These Services

These three services work together closely:
- When an application is launched, AMS requests PMS for application information
- AMS then coordinates with WMS to create and manage the application's windows
- When activities transition, AMS notifies WMS to handle the window animations
- Permission checks involve both AMS and PMS to ensure security

Together, these system services form the backbone of Android's application management system, enabling the multi-tasking, component-based architecture that defines the Android platform.