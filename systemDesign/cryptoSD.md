# System Design Interview Questions (with crypto angle)
**system design interview questions** that combine:

* High-performance system thinking (scaling, reliability)
* Blockchain integration
* Crypto-native constraints (signing, nonce ordering, mempool, etc.)

## 🔍 System Design Interview Prompts (with crypto angle)

### 1. **Design a High-TPS Stablecoin Transfer System**

> **Prompt:**
> Design a backend architecture that allows users to transfer stablecoins with high throughput (e.g., 10k TPS) while ensuring nonce correctness, minimizing stuck transactions, and optimizing gas usage.

**Expected Discussion Areas:**

* Wallet abstraction (per-user or pooled wallets?)
* Nonce management under concurrent requests
* Transaction queueing (custom mempool buffer?)
* Backpressure strategies when gas spikes
* RPC provider scaling (e.g., QuickNode, Alchemy)

---

### 2. **Design a Wallet Management Service for Multi-chain Support**

> **Prompt:**
> How would you design a service to manage user wallets across multiple EVM-compatible chains (e.g., Ethereum, Polygon, Arbitrum), while supporting fast balance checks, transfers, and nonce-aware queuing?

**Expected Discussion Areas:**

* Sharded nonce manager per user-chain pair
* Multi-RPC failover and redundancy
* Caching wallet balances (invalidate on transfer?)
* Chain-specific fee estimation strategies
* Signing service with private key isolation

---

### 3. **Design a Reliable Transaction Broadcaster**

> **Prompt:**
> You're tasked with designing a component that can reliably broadcast signed transactions to the blockchain network and handle retries for failed txns. The system must avoid duplicate nonce errors and manage gas price bumping.

**Expected Discussion Areas:**

* Transaction deduplication (based on hash/nonce)
* Mempool TTL awareness
* Gas bump strategies (EIP-1559 fee mechanics)
* Exponential backoff with override logic
* Monitoring stuck txns (via WebSocket or polling RPC)

---

### 4. **Design a Webhook Notification System for On-chain Events**

> **Prompt:**
> Design a service that allows clients to subscribe to events (e.g., stablecoin received) on-chain and reliably notifies them within 1s of confirmation.

**Expected Discussion Areas:**

* RPC WebSocket vs polling tradeoffs
* Filtering + deduplication of events
* Replay window support for reorgs
* At-least-once vs exactly-once guarantees
* Horizontal scaling of subscriptions

---

### 5. **Design a Fraud Detection Layer on Stablecoin Transfers**

> **Prompt:**
> Design a service that flags suspicious transfers in real-time (e.g., same wallet sending funds rapidly across multiple chains). It must scale and work under low latency.

**Expected Discussion Areas:**

* Stream processing (e.g., Kafka + Flink)
* Feature extraction: transfer volume, frequency, gas patterns
* Labeling/feedback loop design
* Integration with on-chain analytics tools
* Rate limiting + IP/device fingerprinting

---

### 6. **Build a “Meta-Send” API for Wallet-to-Wallet Transfer**

> **Prompt:**
> How would you design a single API endpoint that lets a user send stablecoins from their wallet, abstracting signing, gas estimation, chain selection, and status tracking?

**Expected Discussion Areas:**

* Meta-transactions (abstracting gas from user)
* Private key custody + HSM usage
* Internal nonce & status manager
* Unified transfer pipeline with queueing
* Chain failover (e.g., fallback from Ethereum to L2)

---

### 7. **Design a Transaction Status Tracker (High-Scale)**

> **Prompt:**
> Design a system to track the confirmation status of millions of blockchain transactions in near real-time with guaranteed update delivery to clients.

**Expected Discussion Areas:**

* WebSocket subscription sharding
* Event-driven architecture
* Confirmation thresholds (N blocks)
* Retry after drop (using tx hash query)
* Multi-tenant client architecture

---

## Bonus Concepts You Can Probe for Crypto-Awareness

* What happens if a nonce gap occurs?
* What’s the impact of mempool eviction?
* How would you handle gas spikes during market volatility?
* How to avoid replay attacks?
* Multi-sig vs MPC in signing?


## 🔍 System Design Interview Example

### 1. **Design a High-TPS Stablecoin Transfer System**

[[highTPSStablecoinTransferSystem.md]]



