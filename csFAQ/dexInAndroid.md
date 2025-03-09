# Android build process
```
Source Code (.java/.kt)
        |
        v
    [Compiler]
        |
        v
   .class files
        |
        v
    [ProGuard] -----> Shrink, Optimize, Obfuscate
        |
        v
 [DEX Compiler (dx/d8)]
        |            \
        v             v
  classes.dex    classes2.dex (if multidex)
        |            |
        v            v
    [APK Packager]
        |
        v
      .apk
```

1. **Java/Kotlin Compilation**
   - Source code (.java/.kt) → Java bytecode (.class files)

2. **ProGuard Processing** (if enabled)
   - Input: .class files
   - Performs: shrinking, optimization, obfuscation
   - Output: processed .class files

3. **DEX Compilation**
   - Input: .class files (original or ProGuard-processed)
   - dx/d8 tool converts Java bytecode → Dalvik bytecode
   - Output: .dex files (classes.dex, or multiple dex files if multidex is enabled)

4. **APK Packaging**
   - .dex files + resources + native libraries → APK
   - Signs the APK

Build flow diagram:
# what's dex
`dex` file is a file that is executed on the Dalvik VM.

Dalvik VM includes several features for performance optimization, verification, and monitoring, one of which is **Dalvik Executable (DEX)**.

## how is it generated
Java source code is compiled by the Java compiler into `.class` files. Then the `dx` (dexer) tool, part of the Android SDK processes the `.class` files into a file format called `DEX` that contains Dalvik byte code. The `dx` tool eliminates all the redundant information that is present in the classes. In `DEX` all the classes of the application are packed into one file. The following table provides comparison between code sizes for JVM jar files and the files processed by the `dex` tool.

The table compares code sizes for system libraries, web browser applications, and a general purpose application (alarm clock app). In all cases dex tool reduced size of the code by more than 50%.

| Code | Uncompressed JAR file | Compressed JAR FILE | Uncompressed dex file |
|:------|:-------------------- |:-----------------|:------------------------ |
|Common system Libraries | 100% | 50% | 48% |
| Web Brower App | 100% | 49% | 44 %|
| Alarm Clock App | 100% | 52% | 44% |


In standard Java environments each class in Java code results in one `.class` file. That means, if the Java source code file has one public class and two anonymous classes, let’s say for event handling, then the java compiler will create total three `.class` files.

The compilation step is same on the Android platform, thus resulting in multiple `.class` files. But after `.class` files are generated, the “dx” tool is used to convert all `.class` files into a single `.dex`, or Dalvik Executable, file. It is the `.dex` file that is executed on the Dalvik VM. The `.dex` file has been optimized for memory usage and the design is primarily driven by sharing of data.


## 64k problem

The Dalvik Executable specification limits the total number of methods that can be referenced within a single DEX file to 65,536—including Android framework methods, library methods, and methods in your own code.

In the context of computer science, the term Kilo, K, denotes 1024 (or 2^10). Because 65,536 is equal to 64 X 1024, this limit is referred to as the '64K reference limit'.

## Multi-dex
- after Android 5.0, use ART which natively supports Multi-dex

    ```gradle
    android {
        defaultConfig {
            ...
            minSdkVersion 21
            targetSdkVersion 25
            multiDexEnabled true
        }
        ...
    }

    ```
### application

- do not override application

```java
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.example.myapp">
        <application
                android:name="android.support.multidex.MultiDexApplication" >
            ...
        </application>
    </manifest>
```

- Override application

```java
    public class MyApplication extends MultiDexApplication { ... }
```

- do override but can't change BaseApplication

```java
    public class MyApplication extends SomeOtherApplication {
      @Override
      protected void attachBaseContext(Context base) {
         super.attachBaseContext(context);
         Multidex.install(this);
      }
    }
```

# Proguard

[Proguard Manu][https://stuff.mit.edu/afs/sipb/project/android/sdk/android-sdk-linux/tools/proguard/docs/index.html#manual/introduction.html]
ProGuard is a Java class file shrinker, optimizer, obfuscator, and preverifier:

## Main Functions

* [[Android 混淆那些事儿]]
	* 压缩(Shrink): 侦测并移除代码中⽆无⽤用的类、字段、⽅方法、和特性(Attribute)。
	* 优化(Optimize): 分析和优化字节码。
	* 混淆(Obfuscate): 使⽤用a、b、c、d这样简短⽽而⽆无意义的名称，对类、字段和⽅方 法进⾏行行重命名。
	* 上⾯面三个步骤使代码size更更⼩小，更更⾼高效，也更更难被逆向⼯工程。
	* 预检(Preveirfy): 在java平台上对处理理后的代码进⾏行行预检。
    
1. **Shrinking**
   - Detects and removes unused classes, fields, methods, and attributes
   - Reduces app size
   - Eliminates unused code

2. **Optimization**
   - Analyzes and optimizes bytecode
   - Inlines methods
   - Optimizes loops and variable allocation
   - Removes unused instructions

3. **Obfuscation**
   - Renames classes, fields, and methods using short meaningless names
   - Makes reverse engineering more difficult
   - Preserves entry points specified in configuration

4. **Preverification**
   - Adds preverification information to Java 6+ class files
   - Required for Java Micro Edition and Java 6+
   - Speeds up class loading

## Basic Usage

```gradle
android {
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'),
                         'proguard-rules.pro'
        }
    }
}
```

### Trouble shooting

Testing your app should reveal any errors caused by inappropriately removed code, but you can also inspect what code was removed by reviewing the usage.txt output file saved in <module-name>/build/outputs/mapping/release/.
