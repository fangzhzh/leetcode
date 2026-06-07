# Concurrency Interview Problems

> 这里收录并发相关的面试编程题（含多种解法对比）。
> 理论知识与知识点参见 → [javaConcurrency.md](javaConcurrency.md)

---

## Problem 1 — Currency Exchange Rate（货币汇率查询）

> ⚠️ **注意**：这道题的核心算法是**图的 DFS/BFS 遍历**，并非并发题。
> 它出现在并发文件中，可能是因为面试时被要求在 `CurrencyExchanger` 上追加并发安全（如多线程同时读写汇率图）。
> 纯图论版本另见 → [hashMapRealWorldProblem.md](hashMapRealWorldProblem.md)

### 题目

```
We are a currency exchange that maintains the current exchange rates between currencies.
A user can come to us with some amount in one currency and request the equivalent amount
in a different currency. Given a list of exchange rates between currencies, write a function
that calculates currency rate between any 2 currencies.

(GBP, EUR, 10)     - "1 GBP equals 10 EUR"
(EUR, USD, 1.1)    - "1 EUR equals 1.1 USD"
(USD, JPY, 108.3)
(CNY, RUB, 14.6)


```

### 解法（Kotlin — 图 + DFS）

```kotlin
data class CurrencyExchange(val source: String, val target: String, val rate: Double)

class CurrencyExchanger {
    private val currencyExchangeGraph: MutableMap<String, MutableMap<String, Double>> = HashMap()

    private fun addExchangeRate(exchangeRate: CurrencyExchange) {
        val (source, target, rate) = exchangeRate
        currencyExchangeGraph.computeIfAbsent(source) { HashMap() }[target] = rate
        currencyExchangeGraph.computeIfAbsent(target) { HashMap() }[source] = 1.0 / rate
    }

    fun currencyGraphBuild(currenciesExchanges: Array<CurrencyExchange>) {
        currenciesExchanges.forEach { addExchangeRate(it) }
    }

    fun getCurrencyRate(source: String, target: String): Double {
        val visited: MutableMap<String, Boolean> = HashMap()
        return getCurrencyRate(source, target, visited)
    }

    private fun getCurrencyRate(source: String, target: String, visited: MutableMap<String, Boolean>): Double {
        if (visited.getOrDefault(source, false)) return -1.0
        if (!currencyExchangeGraph.containsKey(source) || !currencyExchangeGraph.containsKey(target)) return -1.0
        return getCurrencyRateDFS(source, target, visited)
    }

    private fun getCurrencyRateDFS(source: String, target: String, visited: MutableMap<String, Boolean>): Double {
        visited[source] = true
        currencyExchangeGraph[source]?.let { sourceMap ->
            if (sourceMap.getOrDefault(target, -1.0) != -1.0) return sourceMap[target]!!
            for (neighbour in sourceMap.keys) {
                val neighbourRate = getCurrencyRate(neighbour, target, visited)
                if (neighbourRate != -1.0) return sourceMap[neighbour]!! * neighbourRate
            }
        }
        return -1.0
    }
}

internal object Solution {
    @JvmStatic
    fun main(args: Array<String>) {
        val exchanger = CurrencyExchanger()
        val records = arrayOf(
            CurrencyExchange("GBP", "EUR", 10.0),
            CurrencyExchange("EUR", "USD", 1.1),
            CurrencyExchange("USD", "JPY", 108.3),
            CurrencyExchange("CNY", "RUB", 104.6)
        )
        exchanger.currencyGraphBuild(records)
        println("GBP->EUR: ${exchanger.getCurrencyRate("GBP", "EUR")}")
        println("GBP->USD: ${exchanger.getCurrencyRate("GBP", "USD")}")
        println("GBP->JPY: ${exchanger.getCurrencyRate("GBP", "JPY")}")
        println("GBP->CNY: ${exchanger.getCurrencyRate("GBP", "CNY")}")
    }
}
```

### 并发 Follow-up：如何让 CurrencyExchanger 线程安全？

如果汇率数据会被多线程并发读取/更新，需要：
- 用 `ReentrantReadWriteLock` 保护 `currencyExchangeGraph`（读多写少场景）
- 或将内部 Map 改为 `ConcurrentHashMap`（但要注意复合操作原子性）
- 核心思路 → 见 [javaConcurrency.md § Read-Write Conflicts](javaConcurrency.md)

---

## Problem 2 — Operator Assignment（任务调度器）

### 题目

```
When assigning a task to an operator, assign it to:
1. The operator with the fewest tasks
2. Tie-break: operator with the most idle time
3. Tie-break: alphabetically by name

API:
- TaskAssigner(operatorNames: List<String>)  — initialize
- set_limit(operator_name, n)                — set operator's task capacity
- assign(conversation_id): String?           — assign next conversation
- get_assignment_queue(n): List<String>      — preview next n assignments (no side effect)
```

### 核心并发知识点
- `PriorityQueue` 维护优先级排序
- 每次修改 operator 状态时需要先 remove 再 add（PQ 不会自动重排）
- 如需线程安全：`PriorityBlockingQueue` + 外层锁保护复合操作

### 解法（Kotlin）

```kotlin
import java.util.PriorityQueue

data class Operator(
    val name: String,
    var capability: Int = 0,
    var workLoad: Int = 0,
    var lastOpTime: Long = 0L
) : Comparable<Operator> {
    override fun compareTo(other: Operator): Int {
        if (this.workLoad != other.workLoad) return this.workLoad - other.workLoad
        if (this.lastOpTime != other.lastOpTime) return (this.lastOpTime - other.lastOpTime).toInt()
        return this.name.compareTo(other.name)
    }
}

class TaskAssigner(operatorNames: List<String>) {
    private val operators: MutableMap<String, Operator> = HashMap()
    private val availableOperators: PriorityQueue<Operator> = PriorityQueue()

    init {
        operatorNames.forEach {
            val op = Operator(name = it)
            operators[it] = op
            availableOperators.add(op)
        }
    }

    fun set_limit(operator_name: String, n: Int) {
        operators[operator_name]?.let {
            availableOperators.remove(it)
            it.capability = n
            availableOperators.add(it)
        }
    }

    fun assign(conversation_id: String): String? {
        val tmpQueue = PriorityQueue<Operator>()
        var bestOp: Operator? = null

        while (availableOperators.isNotEmpty()) {
            val op = availableOperators.poll()
            if (op.workLoad < op.capability) {
                bestOp = op
                break
            }
            tmpQueue.add(op)
        }
        availableOperators.addAll(tmpQueue)

        if (bestOp == null) return null

        availableOperators.remove(bestOp)
        bestOp.lastOpTime = System.currentTimeMillis()
        bestOp.workLoad++
        availableOperators.add(bestOp)
        return bestOp.name
    }

    fun get_assignment_queue(n: Int): List<String> {
        val res = ArrayList<String>()
        // 使用快照，不影响真实状态
        val tmpOperators = operators.mapValues { it.value.copy() }
        val tmpQueue = PriorityQueue<Operator>(tmpOperators.values)

        for (i in 0 until n) {
            val op = tmpQueue.poll() ?: break
            if (op.workLoad >= op.capability) continue
            res.add(op.name)
            tmpQueue.remove(op)
            op.workLoad++
            op.lastOpTime = System.currentTimeMillis()
            tmpQueue.add(op)
        }
        return res
    }
}
```

---

## Problem 3 — Event Uploader（事件批量上传）

### 题目

```
Design a concurrent event uploader with:
- Ordered processing through sequence numbers
- No event loss during failures (retry with exponential backoff)
- Strict batch size of 100 (except final batch)
```

### 并发知识点
- **Producer-Consumer 模式**：addEvent 是 producer，upload worker 是 consumer
- **指数退避重试**：失败后等待时间指数增长，避免热点重试
- **有序性保证**：seq 字段保证批次内顺序；失败重放时需把失败批次插回队头

### 解法 1 — Kotlin Coroutines + Channel（推荐）

```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

data class Event(val content: String, val seq: Int)

object Network {
    fun post(events: List<Event>, onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit) {
        GlobalScope.launch {
            try {
                delay(50)
                if (Random.nextInt() % 2 == 0) onSuccess("Uploaded ${events.size} events")
                else onFailure(Exception("network error"))
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }
}

class UploadManagerChannelImproved {
    private val channel = Channel<List<Event>>(capacity = Channel.UNLIMITED)
    private val scope = CoroutineScope(newSingleThreadContext("Single"))
    private val events = mutableListOf<Event>()
    private val mutex = Mutex()
    private var sequence = 0

    init {
        // Consumer: 消费 channel 中的批次
        scope.launch {
            for (batch in channel) {
                processEvent(batch)
            }
        }

        // Batcher: 定时检查是否满 100，满则发送到 channel
        scope.launch {
            while (true) {
                mutex.withLock {
                    if (events.size >= 100) {
                        val batch = events.take(100).toMutableList()
                        repeat(100) { events.removeAt(0) }
                        channel.send(batch)
                    }
                }
                delay(100)
            }
        }
    }

    suspend fun addEvent(event: String) {
        mutex.withLock {
            events.add(Event(content = event, seq = sequence++))
        }
    }

    private suspend fun processEvent(batch: List<Event>) {
        var retried = 0
        var exponential = 100L
        while (retried < 3) {
            try {
                upload(batch)
                return
            } catch (e: Throwable) {
                retried++
                if (retried >= 3) {
                    // 失败后重新插入，保序
                    scope.launch {
                        mutex.withLock {
                            events.addAll(batch)
                            val sorted = events.sortedBy { it.seq }
                            events.clear()
                            events.addAll(sorted)
                        }
                    }
                }
                delay(exponential)
                exponential *= 2
            }
        }
    }

    private suspend fun upload(batch: List<Event>) {
        val sorted = batch.sortedBy { it.seq }
        Network.post(sorted,
            onSuccess = { println("$it success") },
            onFailure = { throw it }
        )
    }

    fun shutdown() {
        runBlocking {
            mutex.withLock { channel.send(events.toList()) }
            delay(1000)
            channel.close()
            scope.cancel()
        }
    }
}
```

### 解法 2 — Java Thread + synchronized（无 Coroutines 依赖）

```kotlin
import java.lang.Thread.sleep

class UploadManagerThread {
    private val events = mutableListOf<Event>()
    private val lock = Object()
    private var sequence = 0

    fun addEvent(name: String) {
        synchronized(lock) {
            events.add(Event(name, seq = sequence++))
        }
    }

    fun uploadBatch() {
        val batch: List<Event>
        synchronized(lock) {
            batch = events.take(100).toList()
            val remaining = events.drop(100)
            events.clear()
            events.addAll(remaining)
        }

        var retries = 0
        var delay = 100
        while (retries < 3) {
            try {
                upload(batch)
                return
            } catch (e: Throwable) {
                retries++
                if (retries >= 3) {
                    synchronized(lock) { events.addAll(0, batch) }
                    return
                }
                sleep(delay.toLong())
                delay *= 2
            }
        }
    }

    private fun upload(batch: List<Event>) {
        Network.post(batch,
            onSuccess = { println("$it success") },
            onFailure = { throw it }
        )
    }

    fun shutdown() {
        synchronized(lock) {
            if (events.isNotEmpty()) upload(events)
        }
    }
}
```

### 解法对比

| | Coroutines + Channel | Thread + synchronized |
|--|---------------------|-----------------------|
| **线程开销** | 低（协程轻量） | 较高 |
| **代码可读性** | 高（结构化并发） | 中 |
| **依赖** | `kotlinx.coroutines` | 无额外依赖 |
| **适合场景** | Android / 高并发服务 | 简单后台服务 |
