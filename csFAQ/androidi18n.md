# Android 国际化 (i18n) 实现方法

Android 提供了完善的国际化支持机制，以下是实现 Android 应用国际化的主要方法：

## 1. 字符串资源国际化

最基本的国际化方式是通过创建不同语言的字符串资源文件：

### 基本结构

```
res/
  values/
    strings.xml (默认语言，通常是英文)
  values-zh/
    strings.xml (中文)
  values-zh-rCN/
    strings.xml (简体中文)
  values-zh-rTW/
    strings.xml (繁体中文-台湾)
  values-fr/
    strings.xml (法语)
  ...
```

### 示例内容

默认语言 (`res/values/strings.xml`):
```xml
<resources>
    <string name="app_name">My App</string>
    <string name="hello">Hello</string>
    <string name="welcome_message">Welcome to My App!</string>
</resources>
```

中文 (`res/values-zh-rCN/strings.xml`):
```xml
<resources>
    <string name="app_name">我的应用</string>
    <string name="hello">你好</string>
    <string name="welcome_message">欢迎使用我的应用！</string>
</resources>
```

### 在代码中使用

```java
// 在 Java 中
String hello = getString(R.string.hello);
textView.setText(R.string.welcome_message);

// 在 XML 中
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/welcome_message" />
```

## 2. 其他资源的国际化

除了字符串外，还可以国际化其他资源：

### 布局文件

```
res/
  layout/
    activity_main.xml (默认布局)
  layout-ar/
    activity_main.xml (阿拉伯语布局，支持从右到左)
```

### 图片资源

```
res/
  drawable/
    flag.png (默认图片)
  drawable-fr/
    flag.png (法国特定图片)
```

### 尺寸资源

```
res/
  values/
    dimens.xml (默认尺寸)
  values-sw600dp/
    dimens.xml (大屏幕设备尺寸)
```

## 3. 处理复数和格式化字符串

### 复数字符串

```xml
<resources>
    <plurals name="numberOfSongs">
        <item quantity="one">%d 首歌曲</item>
        <item quantity="other">%d 首歌曲</item>
    </plurals>
</resources>
```

使用方法：
```java
String songsText = getResources().getQuantityString(
    R.plurals.numberOfSongs, songCount, songCount);
```

### 格式化字符串

```xml
<resources>
    <string name="welcome_user">欢迎，%1$s！你有 %2$d 条新消息。</string>
</resources>
```

使用方法：
```java
String message = getString(R.string.welcome_user, userName, messageCount);
```

## 4. 日期、时间和数字格式化

```java
// 日期格式化
DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
String formattedDate = dateFormat.format(new Date());

// 数字格式化
NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
String formattedCurrency = numberFormat.format(1000.50);
```

## 5. 运行时切换语言

在 Android 7.0 之前：

```java
public void changeLanguage(String languageCode) {
    Locale locale = new Locale(languageCode);
    Locale.setDefault(locale);
    Resources resources = getResources();
    Configuration config = resources.getConfiguration();
    config.locale = locale;
    resources.updateConfiguration(config, resources.getDisplayMetrics());
    
    // 重启当前 Activity 或应用
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}
```

在 Android 7.0 及以上版本：

```java
public void changeLanguage(String languageCode) {
    Locale locale = new Locale(languageCode);
    Locale.setDefault(locale);
    Resources resources = getResources();
    Configuration config = new Configuration(resources.getConfiguration());
    config.setLocale(locale);
    resources.updateConfiguration(config, resources.getDisplayMetrics());
    
    // 重启当前 Activity 或应用
    recreate();
}
```

## 6. 使用 AppCompat 和 AndroidX 的本地化支持

```java
// 使用 AppCompatDelegate 设置默认夜间模式
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// 使用 LocaleListCompat 处理多语言
LocaleListCompat localeList = LocaleListCompat.create(locale, Locale.getDefault());
AppCompatDelegate.setApplicationLocales(localeList);
```

# Android 应用语言设置的持久化



## 语言设置的持久化方案

要使语言设置在应用重启后仍然生效，需要将语言选择保存到持久化存储中，并在应用启动时读取并应用这个设置。以下是实现方法：

### 1. 使用 SharedPreferences 保存语言设置

```java
public void changeLanguage(String languageCode) {
    // 保存语言设置到 SharedPreferences
    SharedPreferences preferences = getSharedPreferences("app_settings", MODE_PRIVATE);
    preferences.edit().putString("language", languageCode).apply();
    
    // 应用语言设置
    Locale locale = new Locale(languageCode);
    Locale.setDefault(locale);
    Resources resources = getResources();
    Configuration config = new Configuration(resources.getConfiguration());
    config.setLocale(locale);
    resources.updateConfiguration(config, resources.getDisplayMetrics());
    
    // 重启当前 Activity 或应用
    recreate();
}
```

### 2. 在应用启动时应用保存的语言设置

在 Application 类中：

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        // 读取保存的语言设置
        SharedPreferences preferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        String languageCode = preferences.getString("language", "");
        
        if (!TextUtils.isEmpty(languageCode)) {
            // 应用保存的语言设置
            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }
    }
}
```

### 3. 在 Android 13 (API 33) 及以上版本的处理

从 Android 13 开始，Google 引入了新的应用语言选择 API，这是官方推荐的语言设置方式：

```java
// 在 Android 13 及以上版本设置应用语言
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    getSystemService(LocaleManager.class).setApplicationLocales(
        LocaleList.forLanguageTags(languageCode));
} 
// 在 Android 7.0 到 12.x 版本使用 AppCompatDelegate
else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    LocaleListCompat appLocales = LocaleListCompat.forLanguageTags(languageCode);
    AppCompatDelegate.setApplicationLocales(appLocales);
}
// 在 Android 7.0 以下版本使用旧方法
else {
    // 使用前面提到的旧方法
}
```

## 系统如何决定应用使用哪个语言

系统决定应用使用哪个语言的优先级如下：

1. **应用内设置的语言**：如果应用使用上述方法设置了特定语言并持久化，则优先使用该设置
2. **Android 13+ 的应用语言设置**：在 Android 13 及以上版本，用户可以在系统设置中为每个应用单独设置语言
3. **系统语言设置**：如果应用没有特定语言设置，则使用系统语言设置

## 完整的语言管理解决方案

为了提供完整的语言管理解决方案，可以创建一个 `LocaleHelper` 类：

```java
public class LocaleHelper {
    private static final String PREF_NAME = "language_settings";
    private static final String KEY_LANGUAGE = "language_code";
    
    // 保存语言设置
    public static void setLocale(Context context, String languageCode) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply();
        updateResources(context, languageCode);
    }
    
    // 获取当前语言设置
    public static String getLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LANGUAGE, "");
    }
    
    // 应用语言设置到资源
    public static Context updateResources(Context context, String languageCode) {
        if (TextUtils.isEmpty(languageCode)) {
            return context;
        }
        
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        
        // Android 13+ (API 33+)
        if (Build.VERSION.SDK_INT >= 33) {
            try {
                context.getSystemService(LocaleManager.class)
                       .setApplicationLocales(LocaleList.forLanguageTags(languageCode));
                return context;
            } catch (Exception e) {
                // 回退到旧方法
            }
        }
        
        // Android 7.0+ (API 24+)
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleListCompat localeList = LocaleListCompat.forLanguageTags(languageCode);
            AppCompatDelegate.setApplicationLocales(localeList);
        }
        
        // 旧版本兼容
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            return context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            resources.updateConfiguration(config, resources.getDisplayMetrics());
            return context;
        }
    }
    
    // 在 Application 中初始化语言设置
    public static void init(Context context) {
        String language = getLanguage(context);
        if (!TextUtils.isEmpty(language)) {
            updateResources(context, language);
        }
    }
}
```