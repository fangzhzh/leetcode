# 扫描线 (Sweep Line)

## 核心思想

将二维信息 (start, end) 投影到一维时间轴上，通过从左到右扫描端点事件，把 O(n²) 的两两比较降维成 O(n log n) 的线性扫描。

```
时间轴：  ────────────────────────→
事件：    [start₁  end₁]
              [start₂     end₂]
                  [start₃ end₃]

扫描线从左往右扫，遇到 start 就"打开"，遇到 end 就"关闭"
```

## 统一模型：所有区间问题的骨架

```java
// 1. 把每个区间拆成两个事件
List<int[]> events = new ArrayList<>();
for (int[] interval : intervals) {
    events.add(new int[]{interval[0], +1});  // start = 打开
    events.add(new int[]{interval[1], -1});  // end   = 关闭
}

// 2. 按时间排序
events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

// 3. 从左到右扫描，维护当前状态
int active = 0;
for (int[] event : events) {
    active += event[1];
    // 在这里根据题目要求，统计不同的东西
}
```

不同题目，只是在扫描时**统计的东西不同**。

---

## 题目分类

### 1. 判断是否存在重叠 (LC 252 — Meeting Rooms)

> 一个人能否参加所有会议？= 是否存在两个区间重叠？

**扫描时统计**：当前打开数 > 1？→ 不能全部参加

```java
public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < intervals[i - 1][1]) return false;
    }
    return true;
}
```

---

### 2. 最大同时重叠数 (LC 253 — Meeting Rooms II)

> 最少需要几间教室？= 最大同时重叠数

**数学依据：Dilworth 定理**

> 偏序集的最小链覆盖数 = 最大反链长度

翻译成区间语言：
* **链** = 一串互不重叠的区间（可以放在同一间教室）
* **反链** = 一组互相重叠的区间（每个都需要独立教室）
* **最少教室数 = 最多有几个会议同时进行**

**扫描时统计**：当前打开数的**峰值**

```java
public int minMeetingRooms(int[][] intervals) {
    List<int[]> events = new ArrayList<>();
    for (int[] iv : intervals) {
        events.add(new int[]{iv[0], 1});   // 开始 +1
        events.add(new int[]{iv[1], -1});  // 结束 -1
    }
    events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

    int active = 0, maxActive = 0;
    for (int[] e : events) {
        active += e[1];
        maxActive = Math.max(maxActive, active);
    }
    return maxActive;
}
```

---

### 3. 最少删除使不重叠 (LC 435 — Non-overlapping Intervals)

> 删最少的区间，使剩余互不重叠 = 保留最多的互不重叠区间

**数学依据：贪心交换论证（Exchange Argument）**

> 在所有可行方案中，**结束最早**的活动一定出现在某个最优解里。

反证：假设最优解 OPT 的第一个活动 A 不是结束最早的 E。用 E 替换 A 后，E 结束更早，不会与后续冲突。方案数不变，仍然最优。

**按 end 排序 + 贪心**：

```java
public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);  // 按 end 排序
    int ans = 0, prevEnd = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < prevEnd) {  // overlap
            ans++;
            // prevEnd 不更新 = 保留 end 更小的（已按 end 排序，prevEnd 一定更小）
        } else {
            prevEnd = intervals[i][1];
        }
    }
    return ans;
}
```

---

### 4. 合并重叠区间 (LC 56 — Merge Intervals)

> 将所有重叠的区间合并成一个

**扫描时操作**：连续打开的段合并

```java
public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    List<int[]> merged = new ArrayList<>();
    merged.add(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
        int[] last = merged.get(merged.size() - 1);
        if (intervals[i][0] <= last[1]) {
            last[1] = Math.max(last[1], intervals[i][1]);  // 扩展 end
        } else {
            merged.add(intervals[i]);
        }
    }
    return merged.toArray(new int[0][]);
}
```

---

### 5. 插入新区间 (LC 57 — Insert Interval)

> 在已排序的非重叠区间中插入一个新区间，合并所有重叠

```java
public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;

    // 1. 加入所有在 newInterval 之前的（不重叠）
    while (i < intervals.length && intervals[i][1] < newInterval[0])
        result.add(intervals[i++]);

    // 2. 合并所有与 newInterval 重叠的
    while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.add(newInterval);

    // 3. 加入所有在 newInterval 之后的（不重叠）
    while (i < intervals.length)
        result.add(intervals[i++]);

    return result.toArray(new int[0][]);
}
```

---

## 排序策略选择

| 目标 | 排序方式 | 原因 |
|------|---------|------|
| 合并区间 | 按 **start** 排序 | 需要知道"从哪里开始合并" |
| 最大不重叠子集 | 按 **end** 排序 | 贪心：结束越早，留给后面空间越大 |
| 最大同时重叠数 | 拆成事件，按**时间**排序 | 需要追踪每个时刻的活跃数 |

---

## 数学本质

$$
\text{扫描线的本质：将区间集合} \{[s_i, e_i]\} \text{投影到时间轴上的有限个事件点，利用排序后的局部性消灭全局比较}
$$

> 你不需要检查每对区间是否重叠 O(n²)，只需要在关键事件点上更新状态 O(n log n)。排序是降维的代价，扫描是收割的过程。
