# System Design
## Term
### Reliability
The system should continue to work correctly (performing the correct function at the desired level of performance) even in the face of adversity (hardware or software faults, and even human error). 

### Scalability
As the system grows (in data volume, traffic volume, or complexity), there should be reasonable ways of dealing with that growth.

### Maintainability
Over time, many different people will work on the system (engineering and operations, both maintaining current behavior and adapting the system to new use cases), and they should all be able to work on it productively.

## Others concepts
* Store data so that they, or another application, can find it again later (databases)
* Remember the result of an expensive operation, to speed up reads (caches)
* Allow users to search data by keyword or filter it in various ways (search indexes)
* Send a message to another process, to be handled asynchronously (stream processing)
* Periodically crunch a large amount of accumulated data (batch processing)
* Make sure that a service is available 24/7, 365 days a year (high availability)
* Make sure that a service is always responsive, even when it's not being used (scalability)


Excerpt From
Designing Data-Intensive Applications
Kleppmann, Martin
This material may be protected by copyright.
## How to crack the System Design 
* 厘清需求
* 估算
* 接口设计
* 数据模型
* high level的设计
* 详细设计
* 瓶颈识别+解决

## Mobile 系统设计
[Mobile/Android/iOS 系统设计](./mobileSystemDesign.md)
## 面试的时间分配模板
[面试时间模板](./SystemDesignInterviewTimeTemplate.md)

## 逐字稿
[逐字稿](./逐字稿.md)
## Design Facebook Live
[设计facebook Live](./facebookLive.md)
## Design a URL Shortening service like TinyURL
[TinyURL](./tinyURL.md)
## Design a Pastebin
[PasteBin](./designPasteBin.md)

## Design Instagram
[设计Instagram](./designInstagram.md)
## Some usefull knowledge
### base encodeing

* base36 ([a-z,0-9])
* base62([a-z,A-Z,0-9])
* base64([base63 + /])

### Size
1000 = 1K
1000K = 1M
10000M = 1G
10000G = 1T