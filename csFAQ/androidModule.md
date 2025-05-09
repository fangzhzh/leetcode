# Android 构建流程中关于模块化


在现代 Android 开发中，模块化架构（如 Dynamic Feature Modules、App Bundles）对构建流程有以下影响：

1. **多模块项目结构**
   - 应用被拆分为 base 模块和多个功能模块
   - 每个模块可以独立编译和测试
   - 模块间依赖关系影响编译顺序

2. **Android App Bundle (AAB)**
   - 构建输出不仅是 APK，还可以是 AAB 格式
   - AAB 包含所有模块的代码和资源，但按模块分组
   - Google Play 根据用户设备动态生成优化的 APK

3. **Dynamic Feature Modules**
   - 支持按需下载功能模块
   - 基础模块包含核心功能
   - 动态特性模块可以在运行时安装

4. **资源优化**
   - 按屏幕密度、语言等分割资源
   - 每个模块的资源单独处理和优化

5. **构建变体和风味**
   - 每个模块可以有自己的构建变体和产品风味
   - 复杂的构建矩阵需要管理

修改后的构建流程：

```
多模块源代码 (.java/.kt)
        |
        v
[并行模块编译]
        |
        v
  多模块 .class 文件
        |
        v
[模块级 ProGuard]
        |
        v
[模块级 DEX 编译]
        |
        v
[资源优化与分割]
        |
        v
[App Bundle 打包器]
        |
        v
       .aab
        |
        v
[Play Store 处理] --> 设备优化的 APK 集合
```

这种模块化方法使应用能够更高效地交付，减小初始下载大小，并支持按需功能交付。