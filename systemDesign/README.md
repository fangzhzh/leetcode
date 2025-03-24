# System Design 知识图谱
## 1. 核心设计原则

## Term
### 1.1 AS STAR 方法论
- **A**vailability（可用性）
- **S**calability（可扩展性）
- **S**ecurity（安全性）: p2p/server
- **T**estability（可测试性） : CI/CD
- **A**ccessibility（可访问性）: accessibility engineer
- **R**esource（资源管理）

### 1.2 三支柱原则
#### Reliability
The system should continue to work correctly (performing the correct function at the desired level of performance) even in the face of adversity (hardware or software faults, and even human error). 

#### Scalability
As the system grows (in data volume, traffic volume, or complexity), there should be reasonable ways of dealing with that growth.

#### Maintainability
Over time, many different people will work on the system (engineering and operations, both maintaining current behavior and adapting the system to new use cases), and they should all be able to work on it productively.

## 2. 系统设计维度
### 2.1 基础组件
- 数据存储（数据库）
- 缓存策略
- 搜索索引
- 消息队列
    * Send a message to another process, to be handled asynchronously (stream processing)
- 批处理系统
    * Periodically crunch a large amount of accumulated data (batch processing)

### 2.2 服务特性
- 高可用设计（24/7）
- 实时响应（低延迟）
- 容错机制
- 数据一致性

## 3 设计方法论
1. 需求澄清（AS STAR）
2. 容量估算（QPS/存储）
3. API设计（REST/RPC）
4. 数据模型（Schema）
5. 高层架构（模块划分）
6. 详细设计（核心流程）
7. 瓶颈优化（扩展方案）

### 3.2 架构演进模式
- 垂直/水平扩展
- 读写分离
- 分片策略
- 服务解耦

## 4. 专项领域
### 4.1 Mobile 系统设计
[Mobile/Android/iOS 系统设计](./mobileSystemDesign.md)
### 面试的时间分配模板
[面试时间模板](./SystemDesignInterviewTimeTemplate.md)
### 逐字稿
[逐字稿](./逐字稿.md)
### 典型系统案例
* [设计facebook Live](./facebookLive.md)
* [TinyURL](./tinyURL.md)
* [PasteBin](./designPasteBin.md)
* [设计Instagram](./designInstagram.md)

## 5. 技术选型
### 5.1 数据存储
- SQL vs NoSQL
- 缓存策略（LRU/LFU）
- 消息队列（Kafka/RabbitMQ）

### 5.2 编码方案
- Base36/62/64
- 哈希算法选择
- ID生成策略

### 6.1 时间分配模板
- 需求澄清（3-5min）
- 高层设计（5-8min） 
- 详细设计（10-12min）
- 扩展优化（5-8min）

### 6.2 核心考察点
- 权衡决策能力（CAP）
- 模块拆分逻辑
- 故障处理意识
- 性能估算能力

## 附录：实用工具
- 单位换算（1PB = 10^6 GB）
- 性能指标（SSD/Memory吞吐量）
- 网络带宽计算
### Size
1000 = 1K
1000K = 1M
10000M = 1G
10000G = 1T


### Latency Comparison Numbers
--------------------------
L1 cache reference                           0.5 ns
Branch mispredict                            5   ns
L2 cache reference                           7   ns                      14x L1 cache
Mutex lock/unlock                           25   ns
Main memory reference                      100   ns                      20x L2 cache, 200x L1 cache
Compress 1K bytes with Zippy            10,000   ns       10 us
Send 1 KB bytes over 1 Gbps network     10,000   ns       10 us
Read 4 KB randomly from SSD*           150,000   ns      150 us          ~1GB/sec SSD
Read 1 MB sequentially from memory     250,000   ns      250 us
Round trip within same datacenter      500,000   ns      500 us
Read 1 MB sequentially from SSD*     1,000,000   ns    1,000 us    1 ms  ~1GB/sec SSD, 4X memory
Disk seek                           10,000,000   ns   10,000 us   10 ms  20x datacenter roundtrip
Read 1 MB sequentially from 1 Gbps  10,000,000   ns   10,000 us   10 ms  40x memory, 10X SSD
Read 1 MB sequentially from disk    30,000,000   ns   30,000 us   30 ms 120x memory, 30X SSD
Send packet CA->Netherlands->CA    150,000,000   ns  150,000 us  150 ms

Notes
-----
1 ns = 10^-9 seconds
1 us = 10^-6 seconds = 1,000 ns
1 ms = 10^-3 seconds = 1,000 us = 1,000,000 ns