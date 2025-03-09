## High level
* [Clean architecture](./cleanArchitecture.md)

## Android Framework
- App Components
	- Activity
	- Service
	- Content Provider
	- Broadcast Receiver
- [activity and fragemmnt](./androidAcitivityFragment.md)
	* [Intent](./androidAcitivityFragment.md)
- [lifeCycle](./androidLifeCycleGoogleEffort.md)
	- [UI building](./androidLifeCycleGoogleEffort.md)
		* part of Jackpack, 70% adaoption rate
* [Dex file in Android](./dexInAndroid.md)
* [Async - Multiple Threading Android](./androidAsyncMultipleThreading.md)
* [Android ListView](./androidListView.md)
* [List View VS. Recycler View](./androidListViewVSRecyclerView.md)

## Others
- [[Custom widget in Android]](TOO DETIALS, P2)
	- Android ListView
	- List View VS. Recycler View
	+ SpannableString
	+ CardView
	+ Tabbar & viewPager
	+ Actionbar
		- [Search](https://developer.android.com/guide/topics/search/search-dialog.html)
		- [custom Search result](https://stackoverflow.com/questions/23422072/searchview-in-listview-having-a-custom-adapter)
		- [Search with voice](https://developer.android.com/guide/topics/search/search-dialog.html#VoiceSearch)
		- [Search view with adapter](https://stackoverflow.com/questions/21585326/implementing-searchview-in-action-bar)
	+ [L:ConstraintLayout](x-devonthink-item://96997753-5CA8-43C6-A76E-AEA2174710A3)
	+ [L:Tabbar](x-devonthink-item://48106CC1-26CE-4961-B7BB-61842987DAED)
	+ [TextView ellipsize not working problem](x-devonthink-item://6BF1A8EC-533A-432D-9C4D-CE99344BCEF2)
	+ [FloatActionButton](x-devonthink-item://D3D44E8D-7A5A-4C13-95AA-4FD96D87493E)
	+ Tablayout
		- [B:Android TabLayout Example using ViewPager and Fragments](https://www.simplifiedcoding.net/android-tablayout-example-using-viewpager-fragments/)
		- [B:Google Play Style Tabs using TabLayout](http://guides.codepath.com/android/Google-Play-Style-Tabs-using-TabLayout)
	+ [B:Android Sliding Sidebar (Hamburger) Menu with Navigation Drawer Icon](http://codetheory.in/android-navigation-drawer/)

- [[Choosing the Right Background Scheduler in Android]]
- [Deeplink](https://github.com/airbnb/DeepLinkDispatch)
- [Webview]
	+ [How to load external webpage inside WebView](https://stackoverflow.com/questions/7305089/how-to-load-external-webpage-inside-webview)

- [Media]
	+ [MediaPlayer](https://developer.android.com/guide/topics/media/mediaplayer.html#viacontentresolver)

- Location-Aware
	- [L:Location](https://developer.android.com/training/location/index.html)
		+ Set up Google Play Service
		+ App permissions
		+ Connect to Google Play Service
		+ Get Last Location
		+ Change accuracy setting: LocationRequest
		+ Receiving update: request, calback, stop, save state,
		+ Geographic location: GeoCoder,
		+ GeoFences: GeoFencingRequest, LocationServices.GeofencingApi, callback
    - [B:Map](https://developers.google.com/maps/documentation/android-api/start)
		+ Google Maps Android API
- Graphic
	- [9-patch](x-devonthink-item://D13FD73D-2DFA-4204-9EE5-749A0305F000)
	- [Color](x-devonthink-item://A43B0278-7DF3-4B9E-A0B4-BCA2F347A44E)
    - [Image cropping]
    - [Camera view]
    - [Image Editor]
    - [Image manipulation]

- [L: Cache](x-devonthink-item://2A8FF3AA-FC48-46A4-AFBF-1780B7834DE9)
	+ android.util.LruCache<K, V>: Manager an set of objects
		+ LruCache in support V4
		+ ```public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)```
	+ DiskLruCache : Manage files
		+ [From JB source code](https://android.googlesource.com/platform/libcore/+/android-4.1.1_r1/luni/src/main/java/libcore/io/DiskLruCache.java)
		+ [DiskLruCache from JakeWharton](https://github.com/JakeWharton/DiskLruCache)
		+ Regarding source code, they're totally different, but ideas are the same.

- UI
    - [Font](x-devonthink-item://DF48142D-A060-4191-AF0A-6FADD8A3A74B)
	- [Custom widget](x-devonthink-item://2FA09A51-8525-4323-9F32-679BA3A5227F)
    - [Image loading & cache & vector](x-devonthink-item://74D8E045-9821-4E53-A556-405C628F783D)
	- ellipsize
		+ none         0
		+ start        1      output will be : ...bccc
		+ middle       2      output will be : aa...cc
		+ end          3      output will be : aaab...
		+ marquee      4      out put will be : aaabbbccc auto sliding from right to left


- [Memory]
	+ [Object Cache](x-devonthink-item://63350A78-DC0A-4E75-A645-8D70B880DF72)
	+ [Optimize bitmap memory management](x-devonthink-item://B0D9FC19-DEFF-4636-9407-83DDB1B00F08)
- [*Sensors]
	+ Sensor: Provides methods to identify which capabilities are available for a specific sensor.
	+ SensorManager: Provides methods for registering sensor event listeners and calibrating sensors.
	+ SensorEvent: Provides raw sensor data, including information regarding accuracy.
	+ SensorEventListener: Interface that defines callback methods that will receive sensor event notifications.
	+ <uses-feature>
- [Theme]
- [Unicode]
- [Material Design](x-devonthink-item://3A1D0F68-0754-40E2-90FD-2EB19072DBD3)
- [*Serializable & Parcelable]
	+ Serializable is a standard Java interface. You simply mark a class Serializable by implementing the interface, and Java will automatically serialize it in certain situations.
	+ Parcelable is an Android specific interface where you implement the serialization yourself. It was created to be far more efficient than Serializable, and to get around some problems with the default Java serialization scheme.


## Building system
- [Gradle]
- [Maven]
- Buck

-----------
## [[Android Library]]

### develop library
- different between jar and aar
	+ https://stackoverflow.com/questions/23915619/android-archive-library-aar-vs-standard-jar
	+ aar = jar 4 android
- publish libary to maven
	+ https://stackoverflow.com/questions/26874498/publish-an-android-library-to-maven-with-aar-and-source-jar
	+ https://github.com/wupdigital/android-maven-publish
- local remote maven repository server
	+ https://www.sonatype.com/download-oss-sonatype
	+ https://help.sonatype.com/repomanager3

## Tools
### Crash reporting
- [fabric crashlytics](https://fabric.io)
	+ [setup](https://fabric.io/kits/android/crashlytics/install)
	+ [changelog](https://docs.fabric.io/android/changelog.html#fabric-gradle-plugin)
- [sentry]
	+ https://www.sonatype.com/download-oss-sonatype
	+ setup and ui: https://help.sonatype.com/repomanager3

### CI
- Jenkins
- Travis
	+ [travis signing](https://stackoverflow.com/questions/29919066/what-is-the-best-practice-to-use-keystores-to-sign-release-version-of-an-android)
	+ [travis android setup]
		- [gradle](https://www.mkyong.com/gradle/jenkins-could-not-find-gradlewrappermain/)
		- [example travis.yml](https://github.com/osmdroid/osmdroid/blob/master/.travis.yml)
		- [spead boost](https://medium.com/@bod/cache-your-android-sdk-with-travis-c816b9264708)
		- [Things not to cache](https://docs.travis-ci.com/user/caching/#Things-not-to-cache)

```
		The cache’s purpose is to make installing language-specific dependencies easy and fast, so everything related to tools like Bundler, pip, Composer, npm, Gradle, Maven, is what should go into the cache.

		Large files that are quick to install but slow to download do not benefit from caching, as they take as long to download from the cache as from the original source:

		Android SDKs
		Debian packages
		JDK packages
		Compiled binaries
		Docker images
		Docker images are not cached, because we provision a brand new virtual machine for every build.

```
- Azure pipeline

- Building Docker
	+ [building docker file](x-devonthink-item://557F3B59-A0FA-4599-8284-AA6BBAD1D42E)
	+ [building dokcer github](https://github.com/fangzhzh/dokcer4andoridBuild)


### Debug
- ADB wifi

```java
$ adb devices
$ adb tcpip 5556
##  Settings -> About phone/tablet -> Status -> IP address
$ adb connect 192.168.0.102:5556

```

## ADB
- show threads of an process
	+ adb shell ps  -t -p 8928
- start activity/service/broadcast
	+ adb shell am broadcast   -a com.coretex.corehubapp.powermanageservice.interface  -n com.coretex.corehubapp.powermanageservice/com.coretex.corehubapp.powermanageservice.PowermanageServiceInterface --es 'type' 'powerLevelNotify'
- broadcast data

- asm poem
	+ ASM is an all purpose Java bytecode manipulation and analysis framework. I
	+ [asm, agp](x-devonthink-item://78FC6AC1-0CD9-4CA5-9FDB-35FA694FA67A)


### Lint
- [B:All lint rules supported by Android Studio](http://tools.android.com/tips/lint-checks)

### 国内技术
* 高性能架构
* APM
* 组件化
* 插件化
* 热更新
* A/B实验
* 动态埋点
* 性能优化
* Gradle构建
* 字节码编程等
