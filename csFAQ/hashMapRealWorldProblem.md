# Learn Java Hashmap in real world problems

## Problem 1

```java
/*
We are a currency exchange that maintains the current exchange rates between currencies. A user can come to us with some amount in one currency and request the equivalent amount in a different currency. Given a list of exchange rates between currencies, write a function that calculates currency rate between any 2 currencies.

(GBP, EUR, 10)     - read as "1 GBP equals 10 EUR"
(EUR, USD, 1.1)    - "1 EUR equals 1.1 USD"
(USD, JPY, 108.3)
(CNY, RUB, 14.6)
*/

// GBP-> EUR = 10
// GBP -> USD => GPB -> EUR -> USD

// <CUR, MAP> 
// GBP -> MAP<CUR, num> -> EUR -> 10
// GBP -> MAP<CUR, MAP<>> -> EUR<CUR, num>

// n pair currentcy,  2n currencies

class Solution {
    public static void main(String[] args) {
    }
}

```
### Follow up

1. **Concurrency:**
   - How would you handle concurrent updates to exchange rates while ensuring rate lookups remain accurate?
   - Implement a thread-safe version of your currency exchange system that allows multiple threads to read rates while updates are happening.
   - What synchronization mechanism would be most appropriate for a system where reads are much more frequent than writes?

2. **Scaling:**
   - How would your solution change if you needed to handle millions of currency pairs?
   - Design a sharding strategy for your currency exchange system to distribute the load across multiple servers.
   - How would you implement a caching layer to reduce computation for frequently requested currency conversions?

3. **Optimization:**
   - How would you optimize the path-finding algorithm to always find the most favorable exchange rate between currencies?
   - Implement a solution that minimizes the number of conversions needed to get from one currency to another.
   - How would you handle rate updates that might invalidate previously calculated paths?

## Question 2
```java
- Initializes with a set of available operators.
- set_limit(operator_name, n)
  Sets an operator’s limit.
- assign(conversation_id)
  Assigns a given conversation to the next available operator.
- get_assignment_queue(n)
  Returns the list of possible operators in the order they will be assigned to conversations, not real assignment yet.
```
### Follow up
1. **Concurrency:**
   - How would you handle race conditions when multiple requests try to assign conversations simultaneously?
   - Implement a lock-free algorithm for operator assignment that maintains fairness.
   - What data structures would you use to ensure thread safety without sacrificing performance?

2. **Scaling:**
   - How would your design change if you needed to support 10,000+ operators and millions of conversations?
   - Design a distributed version of your operator assignment system that works across multiple data centers.
   - How would you handle operator availability that changes frequently?

3. **Optimization:**
   - How would you optimize the assignment algorithm to minimize wait times for conversations?
   - Implement priority-based assignment for premium customers while maintaining fairness for regular customers.
   - How would you handle load balancing when some operators can handle more conversations than others?



## Question 3. Log Analyzer
```java
/*
Design a log analyzer that processes server logs and provides statistics:
- Most frequent IP addresses
- Count of each HTTP status code (200, 404, 500, etc.)
- Most requested URLs
- Peak traffic times

Input: List of log entries in format: "timestamp IP_address URL status_code response_time"
Example: "2023-05-15T14:22:33 192.168.1.1 /home 200 150"

Implement methods:
- void processLog(String logEntry)
- Map<String, Integer> getTopIPs(int n)
- Map<Integer, Integer> getStatusCodeCounts()
- String getMostRequestedURL()
- String getPeakHour()
*/
```
### Follow up
1. **Concurrency:**
   - How would you design the log analyzer to process logs from multiple sources concurrently?
   - What synchronization mechanisms would you use to update statistics safely?
   - Implement a producer-consumer pattern for log processing that maximizes throughput.

2. **Scaling:**
   - How would your solution change if you needed to process billions of log entries per day?
   - Design a distributed log processing system using MapReduce or a similar paradigm.
   - How would you handle time-based aggregations efficiently across a large dataset?

3. **Optimization:**
   - How would you optimize memory usage when processing very large log files?
   - Implement an approximation algorithm (like Count-Min Sketch) for tracking top IPs with bounded memory.
   - How would you design the system to provide real-time analytics while still processing historical data?

## Question 4. Product Inventory System
```java
/*
Design a product inventory system with the following operations:
- add(productId, quantity): Add products to inventory
- remove(productId, quantity): Remove products from inventory
- getAvailableQuantity(productId): Get current quantity
- getProductsInRange(minPrice, maxPrice): Get products in price range
- getLowStockProducts(threshold): Get products below threshold quantity

Products have: ID, name, price, and quantity.
Optimize for fast lookups by ID and efficient range queries.
*/
```
### Follow up
1. **Concurrency:**
   - How would you handle concurrent inventory updates to prevent overselling?
   - Implement optimistic vs. pessimistic locking strategies for inventory management.
   - Design a system that allows high concurrency for reads while ensuring consistency for writes.

2. **Scaling:**
   - How would your design change to handle millions of products across multiple warehouses?
   - Implement a partitioning strategy for product data based on access patterns.
   - How would you handle inventory synchronization across distributed data centers?

3. **Optimization:**
   - How would you optimize range queries for price ranges?
   - Implement an efficient indexing strategy for quickly finding low-stock products.
   - How would you design the system to handle seasonal spikes in certain product categories?

## Question 5. Word Frequency Counter with Synonyms
```java
/*
Implement a word frequency counter that:
- Counts occurrences of each word in a document
- Treats synonyms as the same word
- Returns the top N most frequent words

Example:
Document: "The car is fast. The automobile is red."
Synonyms: {"car", "automobile"}
Result: {"the": 2, "car": 2, "is": 2, "fast": 1, "red": 1}

Implement:
- void processSynonyms(List<List<String>> synonymGroups)
- void processDocument(String document)
- List<String> getTopNWords(int n)
*/
```

### Follow up
1. **Concurrency:**
   - How would you design the system to process multiple documents concurrently?
   - What synchronization mechanisms would you use for updating word frequencies?
   - Implement a parallel processing strategy for large documents.

2. **Scaling:**
   - How would your solution change to handle a corpus of millions of documents?
   - Design a distributed word counting system using a framework like Hadoop or Spark.
   - How would you handle synonym groups that grow over time?

3. **Optimization:**
   - How would you optimize memory usage when processing very large documents?
   - Implement an efficient algorithm for synonym resolution that minimizes lookups.
   - How would you design the system to quickly update results when new documents are added?


## Question 6. LRU Cache Implementation
```java
/*
Implement an LRU (Least Recently Used) cache with the following operations:
- put(key, value): Insert or update an item
- get(key): Get item by key (and mark as recently used)
- getMostRecentlyUsed(): Return most recently used item
- getLeastRecentlyUsed(): Return least recently used item
- evictLeastRecentlyUsed(): Remove least recently used item

The cache should have a fixed capacity and automatically evict the least recently used item when full.
Optimize for O(1) time complexity for all operations.
*/
```
### Follow up
1. **Concurrency:**
   - How would you make your LRU cache thread-safe without sacrificing performance?
   - Implement a fine-grained locking strategy that allows concurrent reads.
   - What are the trade-offs between different synchronization approaches for an LRU cache?

2. **Scaling:**
   - How would you design a distributed LRU cache across multiple machines?
   - Implement a consistent hashing scheme for cache key distribution.
   - How would you handle cache coherence in a distributed environment?

3. **Optimization:**
   - How would you optimize your LRU implementation to reduce lock contention?
   - Implement an approximation of LRU that has better concurrency properties.
   - How would you handle variable-sized cache entries efficiently?


## Question 7. Flight Booking System
```java
/*
Design a flight booking system with the following features:
- addFlight(flightId, source, destination, capacity)
- bookSeat(flightId, passengerId)
- cancelBooking(flightId, passengerId)
- getAvailableSeats(flightId)
- getFlightsFromCity(city)
- getFlightsToCity(city)
- getPassengerBookings(passengerId)

Optimize for:
- Fast lookup of flights by ID
- Quick retrieval of flights by source/destination
- Efficient booking management
*/
```
### Follow up
1. **Concurrency:**
   - How would you prevent overbooking when multiple users try to book the last seats?
   - Implement a reservation system that handles concurrent bookings with fairness.
   - What isolation level would you use for database transactions in this system?

2. **Scaling:**
   - How would your design change to handle millions of flights and passengers?
   - Design a sharding strategy for flight data based on temporal and geographic patterns.
   - How would you implement a caching layer for frequently accessed flights?

3. **Optimization:**
   - How would you optimize flight search for complex queries (multi-city, flexible dates)?
   - Implement an efficient algorithm for finding connecting flights with minimal layover time.
   - How would you design the system to handle flight schedule changes that affect bookings?
## Question 8. Social Network Friend Recommendation
```java
/*
Implement a friend recommendation system for a social network:
- addUser(userId)
- addFriendship(userId1, userId2)
- removeFriendship(userId1, userId2)
- getFriends(userId)
- getMutualFriends(userId1, userId2)
- getRecommendedFriends(userId, n): Return top n recommended friends based on mutual connections

Optimize for efficient friend lookup and recommendation generation.
*/
```
### Follow up
1. **Concurrency:**
   - How would you handle concurrent friendship requests and updates?
   - Implement a lock-free algorithm for updating the social graph.
   - What data structures would provide the best concurrency for friend lookups?

2. **Scaling:**
   - How would your design change to handle billions of users and connections?
   - Design a partitioning strategy for the social graph that minimizes cross-partition queries.
   - How would you implement friend recommendations in a distributed environment?

3. **Optimization:**
   - How would you optimize mutual friend calculations for very popular users?
   - Implement an approximation algorithm for friend recommendations that trades accuracy for speed.
   - How would you design the system to precompute recommendations while still handling graph changes?

## Question 9. Online Shopping Cart
```java
/*
Implement an online shopping cart system:
- addItem(productId, quantity)
- removeItem(productId)
- updateQuantity(productId, newQuantity)
- getCartTotal(): Calculate total price
- applyDiscount(discountCode)
- checkout(): Process the order and clear cart

Products have varying prices and some may have discounts.
Multiple discount codes may be applied with different rules.
*/
```
### Follow up
1. **Concurrency:**
   - How would you handle concurrent cart updates from multiple devices for the same user?
   - Implement a conflict resolution strategy for simultaneous cart modifications.
   - What synchronization approach would you use to ensure price consistency during checkout?

2. **Scaling:**
   - How would your design change to handle millions of active shopping carts?
   - Design a distributed cart system that maintains session affinity.
   - How would you handle cart persistence and recovery in a distributed environment?

3. **Optimization:**
   - How would you optimize discount code application for carts with many items?
   - Implement an efficient algorithm for calculating shipping costs based on complex rules.
   - How would you design the system to handle flash sales with sudden spikes in traffic?

## Question 10. Task Scheduler with Dependencies
```java
/*
Design a task scheduler that handles tasks with dependencies:
- addTask(taskId, executionTime)
- addDependency(taskId, dependsOnTaskId)
- removeDependency(taskId, dependsOnTaskId)
- getExecutionOrder(): Return valid execution order
- getEarliestExecutionTime(taskId): Calculate earliest possible start time

Tasks cannot start until all dependencies are completed.
Detect and handle circular dependencies.
*/
```
### Follow up
1. **Concurrency:**
   - How would you handle concurrent task additions and dependency updates?
   - Implement a lock-free algorithm for updating the dependency graph.
   - What synchronization approach would you use for task execution in a multi-threaded environment?

2. **Scaling:**
   - How would your design change to handle millions of tasks with complex dependencies?
   - Design a distributed task execution system that respects dependencies across nodes.
   - How would you implement fault tolerance for long-running tasks?

3. **Optimization:**
   - How would you optimize the topological sort algorithm for large dependency graphs?
   - Implement an efficient algorithm for detecting and reporting circular dependencies.
   - How would you design the system to maximize parallelism while respecting dependencies?

## Question 11. Stock Portfolio Tracker
```java
/*
Implement a stock portfolio tracker:
- buyShares(symbol, quantity, price)
- sellShares(symbol, quantity, price)
- getCurrentHoldings(): Map of stock symbols to quantities
- getAveragePurchasePrice(symbol)
- getPortfolioValue(currentPrices): Calculate total value
- getProfitLoss(currentPrices): Calculate profit/loss

Track multiple transactions per stock and handle partial sells correctly.
*/
```
### Follow up
1. **Concurrency:**
   - How would you handle concurrent buy/sell operations for the same stock?
   - Implement a thread-safe portfolio update mechanism that maintains accurate P&L.
   - What synchronization approach would you use to handle real-time price updates?

2. **Scaling:**
   - How would your design change to handle millions of portfolios and transactions?
   - Design a sharding strategy for portfolio data that allows efficient aggregation.
   - How would you implement historical performance tracking at scale?

3. **Optimization:**
   - How would you optimize P&L calculations for portfolios with many positions?
   - Implement an efficient algorithm for tax-lot optimization (FIFO, LIFO, specific lot).
   - How would you design the system to handle high-frequency trading scenarios?

## Question 12. Document Indexing System
```java
/*
Design a document indexing system for fast keyword searches:
- addDocument(docId, content)
- removeDocument(docId)
- updateDocument(docId, newContent)
- search(keyword): Return all documents containing the keyword
- searchMultiple(keywords, useAND): Search with multiple keywords
- rankResults(docIds): Rank results by relevance (keyword frequency)

Optimize for fast search operations across many documents.
*/
```
### Follow up
1. **Concurrency:**
   - How would you handle concurrent document updates and searches?
   - Implement a read-write lock strategy for the index that favors search performance.
   - What synchronization approach would you use for updating inverted indices?

2. **Scaling:**
   - How would your design change to handle billions of documents?
   - Design a distributed indexing system with partitioning and replication.
   - How would you implement efficient cross-partition searches?

3. **Optimization:**
   - How would you optimize keyword searches for very common terms?
   - Implement an efficient ranking algorithm that considers term frequency and document relevance.
   - How would you design the system to handle real-time indexing while maintaining search performance?

## Challenges
Real-world challenges including:
- Complex data modeling
- Efficient lookups and updates
- Handling relationships between entities
- Implementing business logic
- Optimizing for specific access patterns
