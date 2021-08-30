# mobileSystemDesign 移动系统设计
移动端的系统设计也许要follow这些步骤，虽然另有侧重
* 厘清需求
* 估算
* 接口设计
* 数据模型
* high level的设计
* 详细设计
* 瓶颈识别+解决
## 系统设计时间模板
* 时间分配
* 5 minutes - acquaintance 寒暄
* 5–10 minutes - defining the task 定义任务(问问题，理清需求)
* 25–30 minutes - solution 
    * API
    * 定义Model(根据需求)
    * 数据使用估计
    * 高层实际
    * 细节设计
    * 瓶颈
* 5 minutes - your questions to an interviewer.()

## Most important feature when designing a mobile app/feature
* reliable
* scalable
* maintainable
* power efficiency
* customizable
* cost efficiency
* secure

## Use case and technology alternative
* Internet
	* Message
		* Json
			* {type:1, timestamp:1993828239}
		* Restful API
			* HTTP HEADER from 200 bytes to over 2KB
			* Content 60B
		* Websocket
			* 2B header, 4B timestamp, 1B event = 7B per event
		* tradeoffs
			* websocket is good saving data
			* Json is good if cost is not a concern
			* Http is good when not too much requests
		* Data cost
			* 2.5G/$16 = 120M/刀
	* Media
		* Upload
			* Background upload, fire and forgot, 
				* notification of finish/error retry
			* Android Upload Service
			* Http Upload
		* Download
			* Picaso
			* Volley
			* Image Cache
* storage
	* database
		* sqlite
		* room `part of Jackpack`
	* sharedpreferences
	* file
* offline
	* store 
	* retry, exponential backoff && max retry
	* discard
* reminder
	* notification
* background/schedule tasking
	* forever
		* foreground service
	* immediate
		* Kotlin Coroutine
		* Foreground Service
		* WorkManager
	* deferred
		* DownloadManager
	* exact	
		* AlarmManager
		* WorkManager
* idle mode
* dependency injecetion
	* dagger
	* hilt
* location
	* The fused location provider
	* locationListener

	```
	var dir: Double,
    var fix: Int, accuracy
    var hdop: Double,
    var lat: Double,
    var lon: Double,
    var pSpd: Double,
    var rssi: Double?,
    var sat: Int, number of satallat
    var spd: Double, // kmph
    var timestamp: Long, // system timestamp when data created
    var gpsTime: Long // gps time of this data
	```
* end to end encryption

	When you first register on Signal, a private `identity key` is generated on your phone, as well as a bunch of public `prekeys` that are uploaded to the server and sent to your contacts whenever they initiate a new conversation with you or you initiate a new conversation with them.

	When you add a new instance of Signal Desktop to your account, the desktop client 
	1. generates a keypair, 
	2. encodes the public key as a QR code, 
	3. you scan it with your phone
	4. the phone encrypts your private 'identity key' to the desktop client’s public key and uploads the encrypted key to the Signal server, 
	5. the desktop client then downloads and decrypts your 'identity key' and uses it to generate a new set of public 'prekeys' that are uploaded to the server.		

	![Signal End to End encrytion](./graphs/end2EndEncryption.drawio.svg)
## 共通问题
* reliable
* scalable
* maintainable
* power efficiency
* customizable
* cost efficiency
* secure

* 模块化
* 离线
* Scalability
* Picture-in-Picture
* Bandwidth Consumption/Data 流量
* Battery Consumption 电池
* Persistance 存储
* Resilience
* Device Support / Api
* Memory Usage
* Multiple language

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


## 面试的时间分配模板
[面试模板](./SystemDesignInterviewTemplate.md)