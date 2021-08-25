# mobileSystemDesign 移动系统设计
移动端的系统设计也许要follow这些步骤，虽然另有侧重
* 厘清需求
* 估算
* 接口设计
* 数据模型
* high level的设计
* 详细设计
* 瓶颈识别+解决


## 共通问题
* 模块化
* 离线
* Scalability
* Picture-in-Picture

## android 安卓相关
* 架构相关: 
    * MVP, MVVM, MVC, MVI, VIPER, MV*, RIBS,MV-Flow
    * 能说出优缺点
* MV-Flow
    * The library introduces few - if any - new concepts outside MVI, Kotlin coroutines, and Kotlin flows.
        * Any MVI library needs to accomplish the following tasks:
            * Manage the current state (Model in MVI)
            * Call a render function when the state changes (View in MVI)
            * Detect user interactions (Intent in MVI)
            * Process user intents, potentially mutating the current state
    * https://pedroql.github.io/mvflow/#objectives
    * lifecycle components, nav-graph of course, view-models may be, but lifecycleScope certainly, and all data exchange between ui-domain is observables of 'events' and 'states'. either a lifecycle-event, or a user-generated event such as on-click, triggers an appropriate 'event' posted to the domain-layer
    * domain and data layers communicate via flow-data components
    * domain exposes ui-contract and data-contract
        * repo/db/network/
            * retrofit
            * room-orm
* unit-testing
    * mock components, mockk
    * mock ui-contract, mock data-contract
    * mock webserver/mock web response

* UI相关
    * RecyclerView 
        * recyclerview-adapter, view-holder
    * 图片缓存(how Glide/Picasso works basically  
    * Activity/Fragment/Navigation
* Kotlin相关
    * lifecycleScope or viewModelScope 



## APP分类

设计的APP一般可以分为两大类

* 获取数据，显示数据，from a simple fetch data and show lists of items on the device screen
* 聊天/Messaging/数据备份 to build this IOT or real-time chat-app ( WRT - web realtime messaging ) kind of an application    