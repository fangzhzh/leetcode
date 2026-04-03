# Transaction Status Tracker System Design

## 1. Situation (Context Setting)
- **Project context**: Designing a high-scale blockchain transaction status tracking system that can monitor millions of transactions in near real-time
- **Technical landscape**: 
  - Multiple blockchain networks (Ethereum, Polygon, Arbitrum, etc.)
  - Various RPC providers (Infura, Alchemy, QuickNode)
  - Diverse client applications requiring status updates
  - Need for guaranteed delivery of status updates

## 2. Task (Challenge Definition)
- **Primary challenge**: Create a system to track confirmation status of millions of blockchain transactions in near real-time with guaranteed update delivery to clients
- **Technical complexity factors**:
  - Scale: Millions of concurrent transactions to track
  - Latency: Near real-time updates required
  - Reliability: Guaranteed delivery despite network issues
  - Multi-chain: Support for various blockchain networks
- **Constraints**:
  - Blockchain finality varies by network (ETH ~12 blocks, others vary)
  - RPC provider rate limits and reliability issues
  - Client connection stability varies widely
- **Success metrics**:
  - Update latency < 2 seconds from block confirmation
  - 99.99% delivery guarantee for status updates
  - Support for 5+ million concurrent tracked transactions
  - Ability to scale horizontally with demand

## 3. Action (Your Approach)

### Initial Analysis
- Blockchain transactions follow a lifecycle: Pending → Included → Confirmed (N blocks)
- Different clients have different confirmation threshold requirements
- Need to handle chain reorganizations (reorgs) that can change transaction status
- System must be resilient to RPC provider outages and rate limits

### Design/Architecture Decisions

#### Core Architecture: Event-Driven System
- **Event-driven architecture** with clear separation of concerns:
  - Transaction Ingestion Service
  - Blockchain Monitoring Service
  - Status Processing Service
  - Client Notification Service
  - Recovery Service

```mermaid
graph TD;
    User[User/Client] -->|Subscribe to Updates| Gateway[API Gateway]
    Gateway -->|Route Requests| LoadBalancer[Load Balancer]
    
    subgraph "Core Services"
        LoadBalancer --> TIS[Transaction Ingestion Service]
        LoadBalancer --> CNS[Client Notification Service]
        
        TIS -->|New Transaction| KafkaTxs[Kafka: New Transactions]
        
        BMS -->|Block Data| KafkaBlocks[Kafka: New Blocks]
        BMS -->|Transaction Inclusion| KafkaTxs
        
        KafkaTxs --> SPS[Status Processing Service]
        KafkaBlocks --> SPS
        
        SPS -->|Status Updates| KafkaStatus[Kafka: Status Changes]
        
        KafkaStatus --> CNS
        
        CNS -->|WebSocket Updates| WSShards[WebSocket Shards]
        CNS -->|Webhook Callbacks| WebhookService[Webhook Service]
        CNS -->|HTTP Responses| PollingAPI[HTTP Polling API]
        
        RS[Recovery Service] -->|Query Status| SPS
        RS -->|Retry Delivery| CNS
    end
    
    subgraph "Blockchain Monitoring"
        BMS[Blockchain Monitoring Service]
        BMS -->|WebSocket Subscriptions| RPCNodes[RPC Nodes]
        BMS -->|Fallback Polling| RPCNodes
    end
    
    subgraph "Data Storage"
        SPS --> RedisCluster[Redis Cluster]
        CNS --> RedisCluster
        RS --> RedisCluster
        
        RedisCluster -->|Persistence| TimescaleDB[TimescaleDB]
        
        DLQ[Dead Letter Queue] --> RS
    end
    
    subgraph "WebSocket Sharding"
        WSShards -->|Shard 1| WS1[WebSocket Server 1]
        WSShards -->|Shard 2| WS2[WebSocket Server 2]
        WSShards -->|Shard N| WSN[WebSocket Server N]
    end
    
    RPCNodes -->|Block Data| Blockchain[Blockchain Networks]
    
    WS1 --> User
    WS2 --> User
    WSN --> User
    WebhookService -->|Callbacks| ExternalSystems[External Systems]
    PollingAPI --> User
    
    style Gateway fill:#f9f,stroke:#333,stroke-width:2px
    style Blockchain fill:#bbf,stroke:#333,stroke-width:2px
    style KafkaTxs fill:#bfb,stroke:#333,stroke-width:2px
    style KafkaBlocks fill:#bfb,stroke:#333,stroke-width:2px
    style KafkaStatus fill:#bfb,stroke:#333,stroke-width:2px
    style RedisCluster fill:#fbb,stroke:#333,stroke-width:2px
```

#### Transaction Data Flow
1. Client submits transaction for tracking
2. System assigns to appropriate shard based on transaction hash
3. Blockchain monitors track new blocks and transaction inclusion
4. Status updates flow through event processing pipeline
5. Updates delivered to clients via appropriate channels

#### Sharding Strategy
- **WebSocket Subscription Sharding**:
  - Shard by transaction hash (consistent hashing)
  - Each shard responsible for subset of transactions
  - Dedicated monitoring nodes per blockchain per shard
  - Independent scaling of shards based on load

```
// Sharding logic pseudocode
shardId = hash(transactionHash) % NUMBER_OF_SHARDS
assignToShard(transaction, shardId)
```

#### Blockchain Monitoring
- **Multi-RPC Provider Strategy**:
  - Primary-secondary setup per blockchain network
  - Automatic failover between providers
  - Load balancing across provider endpoints
  - Circuit breakers to prevent cascading failures

- **Block Ingestion Methods**:
  - WebSocket subscriptions for new block headers
  - Fallback to polling when WebSockets fail
  - Dedicated services monitoring each blockchain

#### Confirmation Threshold Management
- **Configurable Confirmation Thresholds**:
  - Default thresholds per blockchain (ETH: 12, Polygon: 64, etc.)
  - Client-specific override capabilities
  - Progressive notification (1 confirmation, 3 confirmations, final)

- **Reorg Handling**:
  - Track chain reorganizations
  - Revert and reapply transactions affected by reorgs
  - Notify clients of status changes due to reorgs

#### Client Notification System
- **Multi-Channel Delivery**:
  - WebSocket for real-time clients (primary)
  - Webhook callbacks for server applications
  - HTTP polling endpoint for fallback
  - Push notifications for mobile clients

- **Guaranteed Delivery**:
  - At-least-once delivery semantics
  - Message persistence until acknowledged
  - Retry mechanism with exponential backoff
  - Dead letter queue for manual intervention

#### Multi-Tenant Architecture
- **Tenant Isolation**:
  - Logical separation of client data
  - Dedicated connection pools per tenant
  - Rate limiting and quota enforcement
  - Custom configuration per tenant

### Implementation Strategy

#### Data Storage
- **Redis Cluster**:
  - Transaction status cache (fast reads)
  - WebSocket session management
  - Rate limiting and quota tracking

- **Kafka**:
  - Event backbone for the entire system
  - Durable message storage with replication
  - Topic partitioning aligned with sharding strategy

- **PostgreSQL/TimescaleDB**:
  - Long-term transaction history
  - Client configuration and preferences
  - Audit trail and analytics

### Testing/Validation Approach
- **Load Testing**:
  - Simulate millions of concurrent transactions
  - Test with varying confirmation patterns
  - Measure end-to-end latency under load

- **Chaos Engineering**:
  - Simulate RPC provider failures
  - Test network partitions between services
  - Randomly kill and restart components

- **Reorg Simulation**:
  - Create artificial chain reorganizations
  - Verify correct status updates after reorgs
  - Test edge cases with deep reorgs

## 4. Results (Impact)

### Technical Outcomes
- **Scalability**:
  - System can track 5+ million concurrent transactions
  - Linear scaling with additional shards
  - 99.9% of updates delivered within 2 seconds of block confirmation

- **Reliability**:
  - 99.99% update delivery guarantee
  - Automatic recovery from RPC provider outages
  - Graceful handling of network issues

- **Performance**:
  - Average end-to-end latency < 500ms
  - Efficient resource utilization (CPU, memory, network)
  - Optimized for cost-effectiveness

### Business Impact
- **Enhanced User Experience**:
  - Real-time transaction status visibility
  - Reduced support tickets related to transaction status
  - Improved user confidence in blockchain transactions

- **Operational Efficiency**:
  - Reduced manual intervention for stuck transactions
  - Lower operational costs through automation
  - Better visibility into system performance

### Team Impact
- **Improved Monitoring Capabilities**:
  - Real-time dashboards for system health
  - Proactive alerts for potential issues
  - Better debugging tools for transaction issues

## 5. Learning (Reflection)

### Technical Insights
- WebSocket connections at scale require careful management
- Event-driven architecture excels for this use case
- Redis cluster is essential for high-throughput caching
- Kafka provides the necessary durability and scalability for event processing

### Process Improvements
- Start with smaller shards and scale up as needed
- Implement more granular monitoring from day one
- Develop better simulation tools for blockchain reorgs

### Forward-looking Statement
- Future enhancements could include:
  - ML-based prediction of transaction confirmation times
  - Enhanced analytics for blockchain network health
  - Support for non-EVM blockchains (Solana, etc.)

## Bonus: Crypto-Awareness Concepts

### Nonce Gap Handling
If a nonce gap occurs (e.g., transactions with nonces 1, 2, 4 but missing 3):
- Transactions after the gap won't be processed until the gap is filled
- System should detect gaps and alert users/systems
- Recovery options include submitting the missing transaction or canceling the sequence

### Mempool Eviction Impact
When transactions are evicted from mempool:
- They effectively disappear without being executed
- Our system would detect this through timeout mechanisms
- Recovery involves resubmitting with higher gas price or different nonce

### Gas Spike Handling
During market volatility:
- Implement dynamic gas price adjustment
- Provide options to users: wait, pay higher fee, or cancel
- Use gas price oracles with multiple data sources for better estimation

### Replay Attack Prevention
To prevent replay attacks:
- Ensure chain ID is included in transaction signing
- Validate that transactions are for the intended network
- Implement nonce tracking per chain to prevent cross-chain replays

### Multi-sig vs MPC Comparison
- **Multi-sig**:
  - On-chain contract requiring multiple signatures
  - Transparent and auditable on blockchain
  - Higher gas costs for complex operations
  
- **MPC (Multi-Party Computation)**:
  - Off-chain cryptographic key sharing
  - Single on-chain signature (lower gas)
  - More complex implementation but better UX

        