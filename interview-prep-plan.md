# Fang's Interview Prep Plan — 恢复状态 + 升级

> Based on deep scan of https://github.com/fangzhzh/leetcode
> 267 problem dirs, 52 study notes, ~245 unique problems, primarily Java
> 三条线并行：Coding + System Design + Leadership

---

## 📊 强弱项诊断

### 🟢 Coding 强项
| 类别 | 信心 | 证据 |
|------|------|------|
| DP 动态规划 | ⭐⭐⭐⭐⭐ | 255行笔记 + 状态转移方程分类 + 30+题 |
| 二叉树 | ⭐⭐⭐⭐⭐ | 构造/遍历/LCA全覆盖 |
| 链表 | ⭐⭐⭐⭐⭐ | 216行笔记 + API模板化 |
| DFS/回溯 | ⭐⭐⭐⭐ | 4篇深度笔记(270+273行) |
| 滑动窗口 | ⭐⭐⭐⭐ | 448行笔记！理论很强 |
| 并查集 | ⭐⭐⭐⭐ | 469行笔记，阿克曼函数级别 |
| 面试方法论 | ⭐⭐⭐⭐⭐ | ACT + STAR + SEE-I + 输入→解法映射表 |

### 🟡 Coding 需复习
单调栈、图BFS、贪心、前缀和 — 有笔记但题少

### 🔴 Coding 弱项
Hard题少、BFS图题少、Python弱、近年新题缺

### 🔴 System Design 弱项
- 有21篇SD笔记，但停在**Senior级别**
- 缺少：trade-off深度讨论、failure mode分析、Staff级别的模糊需求处理
- 缺6个常见SD题：Chat System, Notification, Web Crawler, Rate Limiter, News Feed, ML System

### 🔴 Leadership 弱项
- 有经验（OKX Tech Lead, Meta IC cross-team），但**不习惯用"I"来讲故事**
- 倾向"成全别人"而非"推动决策"，面试需要反过来

---

## 🗓️ 3周计划 — 三线并行

**每天节奏（2.5小时）：**
```
30min  自己的笔记.md（Coding唤醒）
30min  BBG System Design 视频/文章
60min  做题（1旧+1新）
30min  Leadership故事写/练
```

### Week 1：唤醒 + 建基础

| Day | Coding笔记→做题 | BBG System Design | Leadership |
|-----|-----------------|-------------------|------------|
| Mon | `codingInterviewACT.md` → 1✅, 15✅ | Scalability, Load Balancer | 列出OKX 5个最难决策（草稿） |
| Tue | `slidingWindow.md` → 76✅, 239(新) | **Rate Limiter** → 写笔记 | 写STAR故事#1：技术方案争论 |
| Wed | `dynamicProgramming.md` → 322✅, 300✅ | CAP Theorem, Consistency | 写STAR故事#2：跨团队推动 |
| Thu | `dfsBacktracking.md` → 200✅, 79✅ | Database Sharding | 写STAR故事#3：紧急救火/线上问题 |
| Fri | `binarySearch.md` → 236✅, 297✅ | Caching (Redis, CDN) | 写STAR故事#4：技术选型决策 |
| Sat | `LinkedList.md` → 146✅, 460(新Hard) | Message Queue (Kafka) | 写STAR故事#5：mentor/带人 |
| Sun | `monotoneStack.md` → 42✅, 84(新) | 闭卷画Rate Limiter | 5个故事对镜子各讲3min |

**Week 1 SD检查点：** 能闭卷画出Rate Limiter（Token Bucket vs Sliding Window）
**Week 1 Leadership检查点：** 5个STAR故事初稿完成

### Week 2：补短板 + SD深度 + Leadership打磨

| Day | Coding（限时45min/题） | BBG System Design | Leadership |
|-----|----------------------|-------------------|------------|
| Mon | 图BFS: 994, 752 | **Design URL Shortener** → 对比`tinyURL.md` | 改故事#1：所有"we"→"I" |
| Tue | 并查集: 684, 1584 | **Design Notification System** → 写笔记 | 改故事#2：加具体数据/metrics |
| Wed | 前缀和: 525, 974 | **Design News Feed** → 写笔记 | 改故事#3：强调你如何识别risk |
| Thu | DP Hard: 72✅, 312(新) | **Design Chat System** → 写笔记 | 改故事#4：讲清trade-off过程 |
| Fri | 贪心: 435, 621✅ | **Design YouTube** → 对比`facebookLive.md` | 改故事#5：讲impact和multiplier effect |
| Sat | 字符串Hard: 68, 273 | **Design Payment** → 对比`highTPSStablecoinTransferSystem.md` | 录音回听全部5个故事 |
| Sun | 模拟coding 2题90min | 闭卷画Chat System + Payment(各15min) | 准备7个BQ问题的答案 |

**Week 2 SD检查点：**
- [ ] 闭卷画Chat System（WebSocket, MQ, 存储, 在线状态）
- [ ] 闭卷画Payment System（idempotency, 分布式事务, 对账）
- [ ] 每个组件能解释"为什么选这个"

**Week 2 Leadership检查点：** 能流畅讲出5个故事，每个≤3min，全用"I"开头

### Week 3：实战模拟

| Day | Coding | System Design | Leadership |
|-----|--------|---------------|------------|
| Mon | `FB-Meta-面经.md` 每Tag做1题 | BBG: **Design Instagram** → 对比你的笔记 | 复习7个BQ问题 |
| Tue | 模拟2题45min | 闭卷45min设计一个系统（SD模拟） | - |
| Wed | - | - | Leadership模拟：回答5个BQ问题（找人mock或讲给我） |
| Thu | 模拟2题45min | BBG: **ML System Design**（Mistral相关） | - |
| Fri | - | 闭卷SD 45min（Staff级：模糊需求→定义scope→设计→failure mode） | - |
| Sat | **全真模拟：Coding 45min + SD 45min + BQ 30min = 2小时** | | |
| Sun | 休息 + 总复习 | | |

---

## 🎯 System Design 升级路径

### 你已有 → BBG补充

| BBG话题 | 你的笔记 | 行动 |
|---------|---------|------|
| URL Shortener | `tinyURL.md` ✅ | 对比BBG，补trade-off讨论 |
| Instagram | `designInstagram.md` ✅ | 对比BBG，补failure mode |
| Facebook Live | `facebookLive.md` ✅ | 对比BBG YouTube，补CDN |
| Payment/Crypto | `highTPSStablecoinTransferSystem.md` ✅ | **你的独家优势！** |
| PasteBin | `designPasteBin.md` ✅ | 对比更新 |
| eCommerce | `eCommerce.md` ✅ | 对比更新 |
| Rate Limiter | ❌ | Week 1 BBG学+写笔记 |
| Chat System | ❌ | Week 2 BBG学+写笔记 |
| Notification | ❌ | Week 2 BBG学+写笔记 |
| News Feed | ❌ | Week 2 BBG学+写笔记 |
| Web Crawler | ❌ | Week 2 BBG学+写笔记 |
| ML System | ❌ | Week 3 如果面Mistral |

### Staff/Principal级别SD面试框架

每个SD题用这个流程（45min）：
```
0-5min   需求澄清（问3-5个关键问题，定义scope）
5-10min  估算（QPS, storage, bandwidth）
10-25min 高层设计（画架构图，核心组件）
25-35min 深入设计（数据模型, API, 关键算法）
35-40min Failure mode + 扩展性讨论
40-45min 总结 + trade-off回顾
```

**Staff级加分：**
- "If I had to pick the riskiest part of this design, it would be..."
- "In 6 months when traffic 10x, the first bottleneck would be..."
- "An alternative approach would be X, but I chose Y because..."

---

## 🎯 Leadership 专项

### STAR故事模板（每个≤300字）

```
## Story #N: [标题]
**Situation**: 背景+挑战
**Task**: 我的角色+期望
**Action**: 我做了什么（"I"开头）
  - 行动1
  - 行动2
  - 行动3
**Result**: 结果（用数据）
**可回答的BQ问题**: [列出]
```

### 素材来源（OKX经历）

| 场景 | 怎么讲 |
|------|--------|
| 技术方案争论 | "I analyzed both approaches, presented data showing X was 3x more efficient, and drove the team to adopt it" |
| 线上紧急问题 | "I led the incident response, identified root cause in 30min, coordinated 3 teams, reduced MTTR by X%" |
| 跨团队协调 | "I initiated weekly syncs between wallet and trading teams, reducing integration issues by X%" |
| 技术选型 | "I evaluated 3 solutions, built a comparison matrix, presented to stakeholders, got alignment" |
| Mentor带人 | "I established code review guidelines, paired with 2 junior engineers weekly, they shipped feature X independently in 3 months" |
| Push back | "PM requested feature X in 2 weeks, I presented technical constraints and proposed phased delivery" |
| 从失败学习 | "I made decision X which caused Y, I conducted post-mortem, implemented Z to prevent recurrence" |

### 必准备的7个BQ问题

1. **Influence without authority** — 用故事#2
2. **Disagreement with manager/team** — 用故事#1
3. **Ambiguity/incomplete info** — 用故事#4
4. **Drove technical direction** — 用故事#4
5. **Mentorship** — 用故事#5
6. **Failure & learning** — 用故事#3或新故事
7. **Speed vs quality trade-off** — 用故事#6(push back)

### 表达升级

| ❌ 不要说 | ✅ 要说 |
|----------|--------|
| 我们做了... | **I identified... I proposed... I drove...** |
| 团队决定... | **I recommended X because Y, the team aligned** |
| 出了问题修了 | **I diagnosed root cause, coordinated 3 teams, implemented fix reducing incidents by X%** |
| 帮新人看代码 | **I established review guidelines improving code quality, measured by X** |

---

## 📌 你的独特优势

1. **ACT框架 + 输入→解法映射表** — 面试破题利器
2. **52篇系统化笔记** — 有体系的学习，不是随便刷的
3. **Crypto SD经验** — `highTPSStablecoinTransferSystem.md` 别人没有
4. **Meta + OKX双大厂** — FAANG + Crypto 跨领域视角
5. **SEE-I表达法** — 沟通框架

---

## ⚡ 每日Quick Start

```
1. 笔记.md 20min → 回忆框架
2. BBG视频 20min → 视觉化补充
3. 旧题1道 20min → 不看答案
4. 新题1道 30min → ACT框架限时
5. Leadership故事 20min → 写/练/改
6. 超时的题 → 记录为什么卡住
```

---

## 📺 BBG使用技巧

1. **先看BBG视频 → 再读自己笔记** — 图帮回忆，笔记帮深入
2. **看完就画** — 关掉BBG闭卷画架构图
3. **对比你和BBG的版本** — 补你遗漏的点
4. **BBG Newsletter** — 通勤碎片时间看infographic
5. **重点看"Key Decisions"** — 面试考的是为什么，不是是什么

---

*Last updated: 2026-04-03*
*三条线：Coding恢复手感 + SD升到Staff级 + Leadership讲好"I"的故事*
