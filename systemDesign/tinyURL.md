# 短链接服务
## 需求
### 功能性
* 创建
* 访问
* 定制
* 失效
### 非功能性
* 可用性
* 低延迟
* 扩展性
    * RESTful API

## 估算
### 流量估算
* MAU: 250M
* 每个用户平均创建2个新URL
* 每月新URL总数: 250M × 2 = 500M

### 读写比例
* 假设读写比为100:1
* 每月读请求: 500M × 100 = 50B

### QPS计算
* 写QPS: 500M / (30天 × 24小时 × 3600秒) ≈ 200 URL/秒
* 读QPS: 200 URL/秒 × 100 = 20K URL/秒

![500M新链接估算过成](./graphs/500MTinyURLEstimate.drawio.svg)

## Databae
Read only, so no sql is better

## Basic design and Algorithm


### encoding the URL
我们需要将长URL转换为短URL的唯一标识符。主要步骤如下：

1. **哈希处理**：使用MD5或SHA256等哈希算法将长URL转换为固定长度的哈希值
   - MD5生成128位(16字节)的哈希值，通常表示为32个十六进制字符
   - SHA256生成256位(32字节)的哈希值，通常表示为64个十六进制字符

2. **Base64编码**：将哈希值编码为可读字符
   - Base64使用64个可打印字符表示二进制数据
   - 每个Base64字符编码6位数据
   - 128位的MD5哈希值经过Base64编码后得到约21-22个字符

3. **截取短码**：从Base64编码结果中截取前6-8个字符作为短URL的唯一标识符
   - 6个字符的空间：64^6 ≈ 68.7亿种可能组合
   - 7个字符的空间：64^7 ≈ 4.4万亿种可能组合
   - 8个字符的空间：64^8 ≈ 281万亿种可能组合

这种方法可能导致哈希冲突（不同的长URL产生相同的短码）。为了解决这个问题，我们可以：
- 在发现冲突时尝试使用哈希值的不同部分
- 或者采用下面将要讨论的离线密钥生成服务(KGS)
Hash(md5/SHA256) --> encode(Bas64)

for base64, 64^6 ~= 68.7 billion, 64^7 ~= 281 trillion, so that's enough letters.

String ---Hash---> 128 bit hash bytes value --base64 encode----> 21+ characters ----6,8 letters ----> keys

* Hash
    
    MD5 produces a 128bit hash value.

* base64

    each bas64 character encodes 6 bits

We got 21 characters. We chose the first 6-8 letters for keys.


### Generationg keys offline

A standalone Key Generation Service(KGS) generates random 6-8 letters before hand and store them in a database.

![KGS](./graphs/KGS.drawio.svg)


### Partitioning and Replication

* Hash based partitioning
根据Hash的结果来随机的分配URL到不同的server，仍然会出现非常不平均的分配。此处，我们可以用Consistent Hashing

![](./graphs/ConsistentHashing.drawio.svg)

### Cache

(Hash,key) in memory or MemCached

* How much cache we hold
20% daily traiffice

LRU

~[shortUrl cache](./graphs/shortenUrlCaches.drawio.svg)


## Purging or DB cleanup
怎么处理 Expirated Data，
* 访问并删除
* clean up service

![clean up service](./graphs/cleanupService.drawio.svg)
    