# Instagram

## 需求
### 功能性需求
* 上传下载照片
* 搜索标题
* 关注其他用户
* 更新流，该用户的top photos

### 非功能性需求
* 可用性
* 更新流低延迟，200ms
* 可靠性，不能丢照片和数据

不考虑的，加标签，搜索标签，评论，等等。

## 系统的考虑
* 多读少写
* 低延迟
* 高可靠

## 估算
* 500M user, 1M daily active users
* 2M photoes per day, 23 Photos per second
* 2M photo, 1M per photos
* 一天照片
    * 2M * 1000K => 2000KM = 2000G = 2T
* 一年的照片
    * 2T * 364 * 10 = 7300T
### Traffic

？？？ 为什么feed类不考虑triffic

### Storage

### Memory
？？？为什么feed类不考虑memory


## 系统设计

{client upload, client view/search} => Image Hosting System => {Image Storage, Image Metadata}

## 模型
* Photo
```
{
    id: string,
    url: string,
    owner: string
}
```

* user
```
{
    id: string,
    name: string,
    email string

}
```

* userfollow

```
{
    user1: string
    user2: string
}
```
### Photo save
HDFS OR S3

### schema
SQL or Key-value

* If we use SQL, scale challenge
* If we use No-SQL
    * 一个表存储用户照片关系 ‘UserPhoto'
    * 一个表存储关注关系 'UserFollow'
    * 都可以用列数据库Cassandra.
### Wide-column datastore
对UserPhoto来说，我们用UserId做key，value就是一些列的PhotoIds, 存在不同的列里边。
'UserFollow'相同情况




## 详细设计
* 读写分离
* 写多分配几台机器

## Data sharding
### 根据用户分区
UserID%10,然后所有的用户数据都放在这个分区

要面临的问题
* 有的user非常受欢迎，很多用户要看他/她的照片
* 有的用户照片多，一个sharding装不完
* 一个一个用户的数据都在一个sharding，那个这个sharding挂了，用户的照片基本看不了了

### 根据照片ID来分区
可以解决以上问题

### 照片ID怎么生成
一个Key Gernerate Server

如果担心single point failure，就有两台，就像design  paste bin一样的设计
 
###  Ranking 和 媒体流
给某个User创建更新流的时候。为了那最近的100张照片，我们的application server可以做以下操作
* 拿到关注的人列表
* 拿这些user列表的最新100张照片信息
* 我们算法排序，找出一百张（比如最近，亲密度)

#### 预生成
专门的server，生成'UserNewsFeed`表。

当用户请求`UserNewsFeed`表，返回数据，记录记录的最后更新时间，然后把生成改时间到当钱时间的feed。


#### 数据请求
1. 拉
    * 用户定时唿喇
    * 问题
        * 数据过时
        * 空请求(没有新数据)
2. 推
    * server把新数据发给用户。
    * 问题
        * 对follow用户多的用户，server不停的推数据
3. 混合模式
    * 关着者非常多的用户，用拉
    * 小于几百个关注者的，server 推
    * server限定频率push，但是对关注者多的可以更经常的被推送

## cache
CDN

LRU

80/20 rule
