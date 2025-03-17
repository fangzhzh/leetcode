# Android Activity and Fragment and life cycle

## Activity Lifecycle

### Lifecycle States
1. **onCreate()**
   - Called when Activity is first created
   - Initialize essential components
   - Set content view
   - One-time setup

2. **onStart()**
   - Activity becomes visible
   - Not yet interactive

3. **onResume()**
   - Activity is in foreground
   - User can interact
   - Start animations/camera/location updates

4. **onPause()**
   - Activity partially visible
   - Stop animations/updates
   - Save light-weight UI state
   - Quick execution required

5. **onStop()**
   - Activity no longer visible
   - Save persistent data
   - Release heavy resources

6. **onDestroy()**
   - Activity is being destroyed
   - Cleanup resources
   - Called once or never

### Common Lifecycle Issues
- Memory leaks
- Configuration changes
- Process death
- Background resource management
#### **Activity生命周期**
1. **Activity生命周期方法及其调用顺序**
   - `onCreate()`：Activity创建时调用，用于初始化
   - `onStart()`：Activity可见但不可交互
   - `onResume()`：Activity进入前台，可交互
   - `onPause()`：Activity部分可见，失去焦点
   - `onStop()`：Activity完全不可见
   - `onDestroy()`：Activity销毁前调用
   - 正常启动顺序：onCreate → onStart → onResume
   - 正常退出顺序：onPause → onStop → onDestroy
2. **调用`onDestroy()`方法的情况**
   - 用户主动调用`finish()`方法
   - 系统因内存不足销毁Activity
   - 配置改变（如屏幕旋转）导致Activity重建
   - 用户按下返回键退出Activity
   - 调用`ActivityCompat.finishAfterTransition()`
3. **`onPause()`和`onStop()`的区别**
   - `onPause()`：
     - Activity部分可见（如被透明Activity覆盖）
     - 快速执行，不能执行耗时操作
     - 保存轻量级UI状态
     - 释放系统资源（如相机）
   - `onStop()`：
     - Activity完全不可见
     - 可以执行较长时间的操作
     - 保存持久化数据
     - 释放重量级资源（如数据库连接）
   - 主要区别：Activity的可见程度不同，执行时间限制不同，资源释放的优先级不同


##### **Activity启动模式**
1. **Android中的四种启动模式**

   - `standard`（标准模式）：
     - 默认启动模式
     - 每次启动都会创建新的Activity实例
     - 允许同一个Activity多次实例化
     - 适用于大多数普通场景

   - `singleTop`（栈顶复用模式）：
     - 如果目标Activity已在栈顶，则复用该实例
     - 不在栈顶时创建新实例
     - 适用于防止重复创建相同Activity的场景
     - 示例：通知点击打开同一个Activity

   - `singleTask`（栈内复用模式）：
     - 在任务栈中只存在一个实例
     - 如果已存在，则清除该实例之上的所有Activity
     - 系统会调用`onNewIntent()`方法
     - 适用于主界面或需要唯一实例的场景

   - `singleInstance`（单实例模式）：
     - 独占一个任务栈
     - 整个系统中只存在一个实例
     - 其他Activity不能进入该任务栈
     - 适用于需要完全独立运行的场景（如来电界面）

2. **使用`singleTask`启动模式的场景**

   - 应用的主界面（MainActivity）
   - 需要保持唯一实例的全局性Activity
   - 需要清理返回栈的场景
   - 需要确保特定Activity只存在一个实例的场景
   - 需要处理深层链接（Deep Link）的Activity
   - 需要作为任务入口点的Activity

   典型应用场景：
   - 电商应用的主页
   - 即时通讯应用的聊天界面
   - 需要保持登录状态的用户中心
   - 需要处理特定业务逻辑的核心页面   

#### **Activity数据传递**
1. Activity之间怎么传输数据
    * Intent
    * Bundle `intent.putExtras(bundle)`
    * ViewModel, activity间共享
    * OnActivityResult

2. **`Intent`和`Bundle`的区别**
   - `Intent`：用于启动Activity/Service，包含目标组件、操作和数据
   - `Bundle`：仅用于存储和传递数据，不包含目标信息
   - `Intent`内部使用`Bundle`存储数据

3. **传递大量数据或复杂对象**
   - 使用`Parcelable`或`Serializable`接口序列化对象
   - 通过`ViewModel`共享数据
   - 使用持久化存储（如Room数据库）
   - 通过`Application`类存储全局数据
   - 使用`LiveData`或`Flow`进行数据共享


| Aspect         | `Serializable`                          | `Parcelable`                          |
|----------------|-----------------------------------------|---------------------------------------|
| **Implementation** | Java built-in interface (marker interface) | Android-specific interface            |
| **Complexity** | Simple (just implement the interface)    | More complex (manual implementation)  |
| **Performance**| Uses I/O and reflection, slower, GC prone         | Direct memory operations, faster      |
| **Use Cases**  | Object persistence to disk, Network transmission | In-memory data transfer, Activity communication |
| **Recommendation** | Use for persistence/network scenarios | in-memory data transfer in Android |

综上所述，在Android开发中，如果需要在内存中传递数据，推荐使用`Parcelable`；如果需要将数据持久化或进行网络传输，建议使用`Serializable`。 

####  **Activity与Fragment通信**
    - 如何实现Activity与Fragment之间的通信？
    - 使用 ViewModel 共享数据
    - 通过 FragmentResultAPI 传递结果
    - 定义接口回调
    - 直接通过 FragmentManager 访问
- 使用`ViewModel`和`LiveData`进行通信的优势是什么？
    - 生命周期感知，自动管理资源
    - 数据持久化，避免配置改变导致数据丢失
    - 解耦UI和业务逻辑
    - 线程安全，确保UI更新在主线程
    - 便于测试和维护

5. **Activity重建**
   - 当Activity因配置更改（如屏幕旋转）而重建时，如何保存和恢复数据？
   - `onSaveInstanceState()`和`onRestoreInstanceState()`的作用是什么？

## Fragment Fundamentals

### Lifecycle
1. **onAttach()**
   - Fragment attached to Activity

2. **onCreate()**
   - Fragment instance initialized

3. **onCreateView()**
   - Inflate/create view hierarchy
   - Return View or null

4. **onViewCreated()**
   - View setup
   - Initialize UI components

5. **onStart()**
   - Fragment visible

6. **onResume()**
   - Fragment interactive

7. **onPause()**
   - Fragment partially visible

8. **onStop()**
   - Fragment not visible

9. **onDestroyView()**
   - View hierarchy destroyed

10. **onDestroy()**
    - Fragment destroyed

11. **onDetach()**
    - Fragment detached from Activity

### Fragment 事务管理
#### 1. 什么是Fragment事务？
Fragment事务是对Fragment进行添加、删除、替换等操作的一系列原子操作。类似数据库事务，要么全部执行成功，要么全部回滚。

#### 2. 基本操作示例
```java
// 基本的Fragment添加操作
supportFragmentManager.beginTransaction()
    .add(R.id.container, MyFragment())
    .commit()

// 替换Fragment
supportFragmentManager.beginTransaction()
    .replace(R.id.container, NewFragment())
    .commit()

// 删除Fragment
supportFragmentManager.beginTransaction()
    .remove(fragmentToRemove)
    .commit()
```

### 3. Fragment事务的提交方式
- commit() : Standard, fails after onSaveInstanceState
- commitAllowingStateLoss() : Works after onSaveInstanceState, may lose state
- commitNow() : Executes immediately, cannot use with back stack

### 4. 回退栈管理
```java
// 添加到回退栈
supportFragmentManager.beginTransaction()
    .replace(R.id.container, NewFragment())
    .addToBackStack("fragment_tag")  // 可以给回退栈加标签
    .commit()

// 弹出回退栈
supportFragmentManager.popBackStack()

// 弹出到指定标签
supportFragmentManager.popBackStack("fragment_tag", FragmentManager.POP_BACK_STACK_INCLUSIVE)
```

### 5. Fragment动画
```java
// 使用系统预定义动画
supportFragmentManager.beginTransaction()
    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    .replace(R.id.container, NewFragment())
    .commit()

// 使用自定义动画
supportFragmentManager.beginTransaction()
    .setCustomAnimations(
        R.anim.enter_from_right,  // 新Fragment进入动画
        R.anim.exit_to_left,      // 旧Fragment退出动画
        R.anim.enter_from_left,   // 返回时新Fragment进入动画
        R.anim.exit_to_right      // 返回时旧Fragment退出动画
    )
    .replace(R.id.container, NewFragment())
    .commit()
```

自定义动画文件示例（`enter_from_right.xml`）：
```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="300"
        android:fromXDelta="100%"
        android:toXDelta="0%" />
</set>
```

### 6. 状态保存和恢复
```java
class MyFragment : Fragment() {
    private var myData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 恢复保存的状态
        savedInstanceState?.let {
            myData = it.getString("my_data")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 保存状态
        outState.putString("my_data", myData)
    }
}
```

###  Fragment Communication
1. Fragment to Activity Communication
   
   - Interface callbacks
   - ViewModel sharing
   - Direct method calls (not recommended)
2. Fragment to Fragment Communication
   
   - Shared ViewModel (recommended)
   - Fragment Result API
   - Interface callbacks through Activity
   - FragmentManager direct access
3. ViewModel Communication Benefits
   
   - Lifecycle awareness
   - Data persistence through configuration changes
   - Decoupled UI and business logic
   - Thread safety
   - Testability

### Advanced pattern 
#### **ViewPager与Fragment**
- 如何在`ViewPager`中使用Fragment？
- `FragmentPagerAdapter`和`FragmentStatePagerAdapter`的区别是什么？
在Android中，`ViewPager`与`Fragment`的结合使用以及`FragmentPagerAdapter`和`FragmentStatePagerAdapter`的区别是常见的面试问题。以下是详细解答：

##### 如何在`ViewPager`中使用Fragment？

1. **基本使用步骤**：
```java
class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getCount(): Int = 2
}

// 在Activity中使用
val viewPager = findViewById<ViewPager>(R.id.viewPager)
viewPager.adapter = MyPagerAdapter(supportFragmentManager)
```

| 特性 | `FragmentPagerAdapter` | `FragmentStatePagerAdapter` |
|------|------------------------|-----------------------------|
| **Fragment生命周期** | 保持所有Fragment在内存中 | 销毁不可见的Fragment |
| **内存使用** | 占用更多内存 | 更节省内存 |
| **适用场景** | 少量固定Fragment | 大量或动态变化的Fragment |
| **状态保存** | 自动保存Fragment状态 | 需要手动保存Fragment状态 |
| **性能** | 切换更快 | 切换稍慢（需要重新创建Fragment） |
| **使用建议** | 适用于固定且少量的Fragment（如3-5个） | 适用于大量Fragment或动态变化的场景 |

##### 最佳实践

1. **使用`FragmentStatePagerAdapter`时**：
   - 实现`saveState()`和`restoreState()`方法
   - 在`instantiateItem()`中正确处理Fragment状态
   - 使用`BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT`标志

2. **性能优化**：
   - 使用`ViewPager2`替代`ViewPager`
   - 考虑使用`RecyclerView.Adapter`
   - 实现Fragment的懒加载
   - 合理管理Fragment的生命周期

3. **现代替代方案**：
   - 使用`ViewPager2` + `FragmentStateAdapter`
   - 考虑使用`Navigation Component` + `BottomNavigationView`
   - 对于复杂场景，可以使用`ViewPager2` + `RecyclerView.Adapter`

```java
// 1. 在布局文件中添加 ViewPager2
// activity_main.xml
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

// 2. 创建 FragmentStateAdapter
class ViewPager2Adapter(fragmentActivity: FragmentActivity) : 
    FragmentStateAdapter(fragmentActivity) {
    
    // 页面数量
    override fun getItemCount(): Int = 3
    
    // 创建Fragment
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> DashboardFragment()
            2 -> NotificationsFragment()
            else -> throw IndexOutOfBoundsException()
        }
    }
}

// 3. 在Activity中设置适配器
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPager2Adapter(this)
        
        // 可选：设置页面切换动画
        viewPager.setPageTransformer(MarginPageTransformer(50))
        
        // 可选：设置方向（默认水平）
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        
        // 可选：预加载页面数量
        viewPager.offscreenPageLimit = 1
    }
}
```

####  **Fragment懒加载**
   - 如何实现Fragment的懒加载？
   - 为什么需要懒加载？懒加载的实现原理是什么？
Fragment懒加载是一种优化技术，主要用于解决ViewPager中Fragment预加载导致的性能问题。以下是详细解释：

### 为什么需要懒加载？
   - ViewPager默认会预加载相邻的Fragment
   - 避免在Fragment不可见时加载数据，减少不必要的网络请求，节省系统资源

#### 懒加载的实现原理

1. **生命周期监听**：
   - 利用Fragment的生命周期方法
   - 在Fragment真正可见时才加载数据
   - 旧：使用`setUserVisibleHint()`或`onHiddenChanged()`方法
   - 新：onResume
   - 更新： ViewPager2 + onViewCreated


#### 实现方式

1. **现代实现（推荐）**：
```java
class LazyFragment : Fragment() {
    private var isDataLoaded = false
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup views but don't load data yet
    }
    // onViewCreated is not realiable, 在 onResume() 方法中加载数据
    override fun onResume() {
        super.onResume()
        if (!isDataLoaded) {
            loadData()
            isDataLoaded = true
        }
    }

    private fun loadData() {
        // 加载数据
    }
}
```

3. **结合ViewPager2**：
```java
class LazyFragment : Fragment() {
    private var isDataLoaded = false
    // 在ViewPager2中， onViewCreated() 会在Fragment视图创建完成后调
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                if (!isDataLoaded) {
                    loadData()
                    isDataLoaded = true
                }
            }
        })
    }

    private fun loadData() {
        // 加载数据
    }
}
```

#### ViewPager2的改进
1. 更好的生命周期管理 ：
   - ViewPager2基于RecyclerView实现
   - 提供了更准确的生命周期管理
   - Fragment的可见性变化会触发标准的生命周期方法
2. onViewCreated() 的可靠性 ：
   - 在ViewPager2中， onViewCreated() 会在Fragment视图创建完成后调用
   - 可以安全地在这里进行视图初始化和数据加载
   - 结合 viewLifecycleOwner 可以更好地管理生命周期
3. FragmentStateAdapter 的支持 ：
   - 提供了 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 标志
   - 确保只有当前可见的Fragment会进入 RESUMED 状态
   - 简化了懒加载的实现



## Lifecyle Integration
### Lifecycle binding Activity and Fragment
```
# Creation Sequence
Activity onCreate() → Fragment onAttach() → Fragment onCreate() → Fragment onCreateView() → Fragment onViewCreated() → Fragment onStart() → Activity onStart() → Fragment onResume() → Activity onResume()

# Destruction Sequence
Fragment onPause() → Activity onPause() → Fragment onStop() → Activity onStop() → Fragment onDestroyView() → Fragment onDestroy() → Fragment onDetach()
```

###  Configuration Changes and State Preservation
1. Activity State Preservation
   
   - onSaveInstanceState/onRestoreInstanceState
   - ViewModel
   - Handling configuration changes manually
2. Fragment State Preservation
   
   - Bundle savedInstanceState(above, onCreate and onSaveInstanceState)
   - setRetainInstance (deprecated)
   - ViewModel with SavedStateHandle(details below)

#### 触发时机
ctivity 中的 onSaveInstanceState() 在以下情况会被触发：

1. 用户按下 Home 键（Activity 进入后台）
2. 用户按下电源键（屏幕关闭）
3. 用户切换到其他应用
4. 屏幕旋转等配置变化
5. 系统内存不足，可能被回收前
6. 启动新的 Activity

#### 恢复流程与生命周期方法
当 Activity 被重新创建时，恢复流程会触发以下生命周期方法（按顺序）：

1. onCreate(Bundle savedInstanceState) - 传入保存的状态
2. onStart()
3. onRestoreInstanceState(Bundle savedInstanceState) - 在 onStart() 之后， onResume() 之前调用
4. onResume()
onRestoreInstanceState() 只有在有状态需要恢复时才会被调用（即 savedInstanceState 不为 null）。

#### 当 Fragment 被重新创建时，恢复流程会触发以下生命周期方法（按顺序）：

1. onAttach(Context) - Fragment 附加到 Activity
2. onCreate(Bundle savedInstanceState) - 传入保存的状态
3. onCreateView(LayoutInflater, ViewGroup, Bundle) - 创建视图
4. onViewCreated(View, Bundle) - 视图创建完成
5. onActivityCreated(Bundle) (已弃用，但在旧代码中可能存在)
6. onViewStateRestored(Bundle) - 在 onStart() 之前调用，用于恢复视图状态
7. onStart()
8. onResume()
注意 ：Fragment 没有单独的 onRestoreInstanceState() 方法，状态恢复主要在 onCreate() 和 onViewStateRestored() 中处理。

## Android Architecture Components

[Architecture Component and Jetpack](./androidLifeCycleGoogleEffort.md)

### 1. ViewModel

```java
class MyViewModel : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun updateData(newData: String) {
        _data.value = newData
    }
}
```

- Survives configuration changes
- Stores UI-related data
- Cleared when Activity/Fragment destroyed

### 2. LiveData
```java
class MyActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.data.observe(this) { data ->
            // Update UI
        }
    }
}
```
- Lifecycle-aware
- Automatically manages subscriptions
- Ensures UI consistency
### 3 Fragment with ViewModel
```java
class MyFragment : Fragment() {
    // Fragment-scoped ViewModel
    private val viewModel: MyViewModel by viewModels()
    
    // Activity-scoped ViewModel (shared)
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Always use viewLifecycleOwner for view-related observations
        viewModel.data.observe(viewLifecycleOwner) { data ->
            // Update UI
        }
    }
}
 ```


### 3. Lifecycle
```java
class MyObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        // Handle resume
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        // Handle pause
    }
}
```

### 4. SavedStateHandle
```java
class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var state: String
        get() = savedStateHandle.get<String>("key") ?: ""
        set(value) = savedStateHandle.set("key", value)
}
```
- Survives process death
- Automatic state saving/restoration

## Modern Solutions for Lifecycle Issues
### 3. Jetpack 
```java
@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state by viewModel.stateFlow.collectAsState()
    
    LaunchedEffect(Unit) {
        // Side effects
    }
}
```
- Declarative UI
- Built-in lifecycle management
- Simplified state handling

### 1. Coroutines with Lifecycle
```java
class MyFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            // Automatically cancelled when view destroyed
        }
    }
}
```

### 2. Flow with Lifecycle
```java
class MyViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow<UiState>()
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            // Handle state updates
        }
    }
}
```


## 6. Common Issues and Best Practices
### 6.1 Common Issues
1. Fragment Overlap Issues
   
   - Causes: Activity recreation, async operations, transaction issues
   - Solutions: Proper transaction management, unique container IDs
2. Memory Leaks
   
   - Causes: Static references, inner classes, background tasks
   - Solutions: WeakReferences, proper lifecycle management, viewLifecycleOwner
3. Configuration Changes
   
   - Causes: Screen rotation, language change, theme change
   - Solutions: ViewModel, savedInstanceState, onSaveInstanceState
4. Process Death
   
   - Causes: System kills app in background
   - Solutions: SavedStateHandle, persistent storage, proper state restoration