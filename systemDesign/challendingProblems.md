# Challenging Problems
# Technical Challenges Analysis for Each Role

## M

1. **Ads Format Optimization**
   * multiple features concurrent development and A/B testing upon same set of sellers and buyers, causing a fraction of fisibility issues
      * Backend:
         * 1. Create a single interface to check for all features
         * 2. Consolidated feature flag checking in the backend
      * Client:
         * 1. Consume the single interface to check for all features
2. **Messenger Shop Initiative**
   - Creating a commerce platform within a messaging ecosystem that wasn't originally designed for it
   - Handling real-time inventory synchronization between advertisers and customers
   - Implementing secure payment processing within the messaging flow
   - Ensuring low latency for millions of concurrent users

3. **Mobile Sheriff Rotation**
   - Managing code health across massive Android repositories with thousands of contributors
   - Implementing automated checks that catch issues without creating false positives
   - Balancing strict code quality requirements with rapid development cycles
### Ads Format Optimization Deep dive: The Challenge: Feature Flag Management at Scale

One of the most challenging problems I faced was at M, where we were developing multiple concurrent features for our ads platform targeting the same set of sellers and buyers in Southeast Asia. This created significant feasibility issues where features would interact in unexpected ways, causing inconsistent user experiences and making it difficult to measure the true impact of each feature.

#### Technical Complexity

The core challenge was multi-dimensional:

1. **Feature Interaction Complexity**: With 10+ teams simultaneously developing features that affected the same user journey, we encountered unexpected interactions. For example, a pricing optimization feature would conflict with a discount feature, resulting in incorrect final prices being shown to users.

2. **Experimentation Integrity**: Our A/B testing framework became compromised because users were being placed into multiple experiment buckets simultaneously, making it impossible to isolate the impact of individual features.

3. **System Performance**: Each feature required its own GraphQL endpoint with similar but slightly different logic, creating significant technical debt. This redundancy not only increased maintenance complexity but also introduced subtle race conditions and inconsistent behavior that resulted in a 35% increase in production incidents.

4. **Developer Experience**: Teams were stepping on each other's toes, with changes to one feature flag system breaking others, leading to emergency rollbacks and delayed launches.

#### My Approach

I led a cross-functional initiative to solve this problem:

1. **Architecture Design**: I designed a unified feature flag evaluation service that consolidated all flag checking logic into a single, high-performance backend service with consistent caching strategies.

2. **Implementation Strategy**: 
   - Created a single GraphQL interface that could evaluate all feature flags in one request
   - Implemented a rule-based conflict resolution system that could deterministically resolve feature interactions
   - Built a distributed caching layer to ensure sub-5ms response times even with hundreds of feature flags

3. **Rollout and Adoption**:
   - Developed client SDKs for our web and mobile platforms that made integration simple
   - Created migration tools to help teams transition from their custom implementations
   - Established governance processes for feature flag lifecycle management

#### Results and Impact

This solution had significant measurable impact:

1. **Performance**: Reduced feature flag evaluation latency by 95%, from ~50ms to ~2.5ms
2. **Reliability**: Decreased feature flag related incidents from 2-3 per week to zero in the quarter following implementation
3. **Experimentation Accuracy**: Improved our ability to measure true feature impact, which directly contributed to the $40M/year incremental revenue from our ads format optimization
4. **Developer Productivity**: Reduced feature development cycle time by 30% by eliminating cross-team dependencies and conflicts

#### Key Learnings

This challenge taught me several important lessons:

1. **System thinking is crucial**: Looking at the problem holistically rather than as isolated feature flags was key to finding a scalable solution.

2. **Technical debt compounds quickly**: What started as a simple feature flag implementation became a major problem as we scaled, showing how important it is to design for future growth.

3. **Developer experience matters**: By focusing on making the system easy to use for other developers, we achieved much faster adoption than previous infrastructure initiatives.

This experience fundamentally shaped how I approach platform and infrastructure problems, emphasizing the importance of designing systems that can scale not just technically but also organizationally."

## C (IoT)

1. **IoT System Architecture**
   - old IoT system was a c++ monoapp which hard to maintain and upgrade, easy to breakdown, expansive for operation, and difficult to add new features
   - Designing a reliable IoT system(android based) that fix all previous issues
   - Managing firmware updates across distributed devices without disrupting service
   - Implementing secure device authentication and data transmission
   - Handling large volumes of sensor data efficiently

2. **CI/CD for IoT Devices**
   - Creating deployment pipelines that can safely update devices in the field
   - Implementing rollback mechanisms for failed updates
   - Testing across various hardware configurations and Android versions

3. **Support Dashboard**
   - Real-time visualization of device status across a large fleet
   - Implementing efficient data aggregation for operational insights
   - Creating secure remote configuration capabilities
### IoT System Architecture Deep Dive: Modernizing a Legacy IoT System

One of the most challenging problems I faced was leading the complete redesign of our industrial IoT platform that controlled manufacturing equipment across 12 factories in Asia. The existing system was a monolithic C++ application that had evolved over 8 years, becoming increasingly unstable and costly to maintain.

#### Technical Complexity

The challenge was multi-faceted and required solving several critical problems simultaneously:

1. **Legacy System Constraints**: The original monolith had over 500,000 lines of C++ code with minimal documentation, tight coupling between components, and no clear separation of concerns. Any change to one part of the system risked breaking others, leading to production outages that cost $25,000 per hour.

2. **Continuous Operation Requirements**: The system controlled critical manufacturing equipment that operated 24/7. We needed to migrate to the new architecture without disrupting ongoing operations, essentially performing a "heart transplant" while the patient was running a marathon.

### My Approach

I led a cross-functional team of 15 engineers to tackle this challenge:

1. **Architecture Design**: 
   - Designed a modular, microservices-based architecture using Android as the device OS
   - Created a clear separation between device control, data collection, and business logic layers
   - Implemented a message-based communication system using MQTT for resilience during connectivity issues
   - Developed an edge computing framework that allowed critical operations to continue offline

2. **Implementation Strategy**:
   - Implemented a robust OTA update system with automatic rollback capabilities
   - Developed a data synchronization protocol that handled intermittent connectivity gracefully
   - Built comprehensive monitoring and remote diagnostics capabilities

3. **Migration Approach**:
   - Developed a phased migration strategy that allowed parallel operation of old and new systems
   - Implemented extensive automated testing with 85% code coverage

### Results and Impact

The solution delivered significant measurable impact:

1. **Reliability**: Reduced system failures by 94%, from 3-4 per month to 2-3 per year
2. **Operational Costs**: Decreased maintenance costs by 68% ($1.2M annually)
3. **Development Velocity**: Reduced new feature development time from 6-8 weeks to 1-2 weeks
4. **Security**: Eliminated all critical and high-severity vulnerabilities identified in security audits
5. **Scalability**: Successfully scaled from 5,000 to 12,000 devices without architecture changes
6. **Business Impact**: Enabled $8M in new revenue through capabilities that weren't possible with the legacy system

### Key Learnings

This challenge taught me several important lessons:

2. **Architecture flexibility pays dividends**: By designing for heterogeneity from the start, we created a system that could adapt to new requirements and hardware without major rework.

3. **Security must be foundational**: Building security into the architecture from day one proved much more effective than trying to add it later.

4. **Invest in observability**: The comprehensive monitoring we built saved countless hours in troubleshooting and allowed us to be proactive rather than reactive.

This experience fundamentally shaped my approach to large-scale system modernization, emphasizing the importance of balancing technical excellence with practical business constraints and careful risk management.

## C (VOIP)

1. **VOIP Implementation**
   - Ensuring call quality across varying network conditions
   - Minimizing latency and handling packet loss gracefully
   - Implementing efficient audio codecs and processing
   - Battery optimization for mobile VOIP applications

2. **Cross-platform Architecture**
   - Maintaining consistent behavior across Android, desktop, and web platforms
   - Implementing shared business logic while optimizing for each platform

3. **State Management Refactoring**
   - Migrating existing applications to Redux without disrupting users
   - Designing a state management system that works efficiently across complex VOIP scenarios

## S (E-commerce)

1. **Scaling Shop App**
   - Building systems that could handle millions of concurrent users
   - Implementing efficient caching strategies for product catalogs
   - Optimizing network usage for regions with limited bandwidth

2. **Order System Complexity**
   - Managing complex order states across different fulfillment methods
   - Ensuring data consistency during high-volume shopping events
   - Implementing reliable payment processing with various payment methods

3. **Internationalization**
   - Supporting RTL layouts and multiple languages
   - Adapting to different regional requirements and regulations
   - Optimizing app performance for diverse device ecosystems in different markets

4. **Protobuf Optimization**
   - Creating an automated system to convert between protobuf and Java classes
   - Ensuring type safety and performance in the conversion process

## Game Startup

1. **Game Performance Optimization**
   - Achieving smooth gameplay on limited mobile hardware
   - Optimizing rendering and physics calculations
   - Managing memory efficiently to prevent crashes

2. **Cross-platform Development**
   - Maintaining consistent gameplay between iOS and various Android devices
   - Adapting to different screen sizes and hardware capabilities

3. **Backend Infrastructure on Limited Budget**
   - Creating scalable backend services with limited resources
   - Implementing efficient user authentication and data storage

The most technically challenging problems across all roles likely involved:

1. **Scaling systems to handle millions of users** - particularly in the Shopee and Facebook experiences
2. **Real-time data processing** - especially in IoT and VOIP applications
3. **Cross-platform consistency** - maintaining quality across different devices and platforms
4. **Performance optimization** - ensuring responsive applications despite network or hardware limitations
5. **Complex state management** - particularly in order systems and VOIP applications

# Technical Difficulty

## 1. 技术难题

常见的技术难题和解决方案有以下这些：

### 高并发请求问题

在一个短时间内有大量的用户同时访问服务器或应用程序，导致服务器负载急剧增加，可能会出现响应延迟、系统崩溃等情况。

**解决方案**：
- **添加缓存**：使用缓存来存储热点数据，减少对后端数据库的访问。
- **限流和降级**：使用令牌桶或漏桶算法来限制单位时间内请求的数量；当检测到某个服务出现异常时，自动切断与该服务的连接，防止故障扩散。
- **异步处理**：使用消息队列（如 RocketMQ、Kafka 等）来异步处理任务，缓解即时处理的压力。
- **数据库优化**：可以使用读写分离、分库分表、分布式数据库等方案来解决。

### 数据和缓存一致性问题

在程序运行期间，当数据库的数据发生修改之后，导致缓存中的数据和数据库数据不一致性的问题。

**解决方案**：
- 使用延迟双删和 MQ 来解决数据一致性问题。
- 使用 Canal 监听 MySQL Binlog，再将数据库更新到 MQ（如 Kafka）中，再通过监听消息更新 Redis 缓存。

### 消息丢失/消息积压等问题

消息丢失是指在消息传递过程中，消息未能到达目的地，可能是由于网络问题、系统故障等原因造成的。消息积压是指消息队列中累积了大量的未处理消息，通常是由于消息产生的速度超过了消费者的处理速度。

**解决方案**：
- **消息丢失解决方案**：使用消息确认机制（生产者消息确认和消费者消息确认）、持久化、多机部署等手段来解决。
- **消息积压解决方案**：可以使用扩展消费者实例、优化消费者代码、限制生产者生产速度等手段来解决。

## 2. 线上调试难题

常见的线上调试问题以及解决方案有以下这些：

### 间歇性问题

在生产环境运行中，偶尔出现的某些问题，例如以下这些：

#### 间隙性图片覆盖问题

A 用户生成图片时，发现间歇性生成的是 B 用户的图片。

**解决方案**：图片名称生成规则问题，可以是使用时间戳来命名的，并发环境中，可能会出现数据覆盖问题。

#### 间歇性查询效率低问题

随机一段时间，执行某个操作效率低的问题。

**解决方案**：设置报警和监控，在出现问题时，第一时间查看日志和分析系统资源确定问题。这些问题可能是数据库资源枯竭排队问题、也可能是内存资源被占用完导致运行效率低的问题，还有可能是 CPU 突发资源占用等问题，所以需要根据日志确定问题之后再进行相应的优化。

#### 间歇性 OOM 问题

项目上线之后，每隔一段时间（时间可能不固定）会导致 OOM（Out Of Memory）内存溢出问题。

**解决方案**：导致 OOM 问题的原因有很多，所以解决 OOM 问题的常见思路和步骤如下：
- **诊断 OOM 问题**：使用工具如 VisualVM、JProfiler 或 MAT 生成堆转储文件（Heap Dump），分析内存使用情况，确定导致 OOM 问题的原因。
- **优化代码**：根据诊断的 OOM 问题，优化对应的代码。
- **调整 JVM 参数**：调整堆空间、新生代占比、垃圾回收器等预防一些 OOM 问题的发生。

### 某些复杂问题

复杂问题有很多，这里列举一些：
- MySQL 和 Redis 的分布式事务问题。
- 大批量数据导入和导出效率低和 OOM 问题。

## 3. 性能问题

常见的性能问题有以下这些：

### 程序性能问题

在程序运行时表现出的效率低下、响应迟缓、资源消耗过高或无法满足预期的处理速度和吞吐量等情况。常见的程序性能问题包括以下这些：

- **高 CPU 使用率**：程序中的某些计算或逻辑导致 CPU 长时间处于高负荷状态。
- **内存泄漏**：未正确释放不再使用的内存，导致可用内存逐渐减少。
- **频繁的 I/O 操作**：如大量的文件读写、网络请求等，造成程序阻塞。
- **数据库查询性能差**：不合理的 SQL 查询、缺少索引等导致数据库操作缓慢。
- **算法和数据结构选择不当**：例如使用了低效率的算法或不适合当前场景的数据结构。
- **线程竞争和死锁**：多线程环境中线程之间的资源竞争和死锁会影响程序执行效率。

**解决方案**：
- **性能分析**：使用如 JProfiler（Java）、VTune（通用）等工具来监测程序的性能指标，找出性能瓶颈所在。
- **代码优化**：优化算法和数据结构，选择更高效的实现方式；减少不必要的计算和重复计算。
- **内存管理**：及时释放不再使用的内存资源，避免内存泄漏；合理使用缓存，避免过度占用内存。
- **I/O 优化**：采用异步 I/O 操作，避免阻塞；对文件读写和网络请求进行批量处理。
- **数据库优化**：优化 SQL 查询语句，添加合适的索引；可以使用读写分离、分库分表、分布式数据库等方案来解决。
- **多线程优化**：避免过度的线程同步，减少锁竞争；检查并解决死锁问题。

### 数据库性能问题

在数据库处理数据操作（如查询、插入、更新、删除等）时表现出的响应速度慢、资源利用率高、吞吐量低等不良情况，影响了系统的整体性能和用户体验。

**解决方案**：
- **优化查询语句**：避免使用不必要的子查询和复杂的函数；确保查询条件使用了合适的索引。
- **建立和优化索引**：根据经常用于查询、连接和排序的字段创建索引。
- **调整数据库配置**：合理配置内存缓冲区、连接数、线程池等参数。
- **分库分表**：当数据量过大时，将表按照一定规则进行水平或垂直分表，或者进行数据库的垂直分割和水平分割功能。
- **解决锁竞争**：尽量缩短事务的执行时间，减少锁的持有时间；采用合适的锁级别，如行锁而不是表锁。
- **监控和分析**：使用数据库自带的性能监控工具或第三方工具，定期分析性能指标，发现问题及时解决。
