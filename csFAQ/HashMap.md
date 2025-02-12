# HashMap
## HashMap实现原理
### 存储结构
* 红黑树， 
![图示](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/3/15/170ddda186409662~tplv-t2oaga2asx-jj-mark:3024:0:0:0:q75.awebp#?w=1372&h=500&s=91501&e=png&b=fdfdfd)
###
### 初识容量
* 默认大小16
* 负载因子0.75
* 传k的话，那么初始化为 大于k的2^n, 
    * 比如初始值10，那么初始化为16
```
// 如何得到比初始值大的那个2^n
static final int tableSizeFor(int cap) {
  int n = cap - 1;
  n |= n >>> 1;
  n |= n >>> 2;
  n |= n >>> 4;
  n |= n >>> 8;
  n |= n >>> 16;
  return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}

// 比如初值传50，算法就是让初始二进制右移1，2，4，8，16位，分别与自己异或，把高位第一个为1的数通过不断右移，把高位为1的后几位全变为1，111111 + 1 = 1000000 = 2^6（符合大于50并且是2的整数次幂 ）

```    
### Hash值计算/索引计算

**Hash函数如何设计？索引如何计算**

![图示](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/3/15/170ddda189ee807a~tplv-t2oaga2asx-jj-mark:3024:0:0:0:q75.awebp#?w=586&h=336&s=17244&e=jpg)
拿key到hashcode（32位int值），高16位和低16位进行抑或运算

```
// jdk1.8
static final int hash(Object key) {   
     int h;
     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    /* 
     h = key.hashCode() 为第一步：取hashCode值
     h ^ (h >>> 16)  为第二步：高位参与运算
    */
}

// index
bucketIndex = indexFor(hash, table.length);

static int indexFor(int h, int length) {
     return h & (length-1);
}
```
#### 为什么Hash函数这么设计

**扰动函数**

高bit低bit都参与hash计算，降低**碰撞**。

* 为什么能降低碰撞？
    1. hash值处在[-2^32, 2^32-1]，直接用hashcode也可以，使用此扰动函数，是为了之后的索引计算
    2. 索引计算，一般是 hash%length
    3. hash % length，根据散列值的设计（hashcode），碰撞会很严重。如果散列值设计的不好，最后几位出现规律，更多碰撞。
    4. 扰动函数的价值，高bit低bit都参与hash计算，降低**碰撞**。


2. 位运算，开销不大

## 关于 hashCode() 和 equals()
## 哈希冲突详解
## 扩容机制
## HashMap 基本操作
### put方法/插入数据(你知道HashMap的数据插入原理吗?)
![图示](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/3/18/170ec298770f534d~tplv-t2oaga2asx-jj-mark:3024:0:0:0:q75.awebp#?w=1920&h=1232&s=286114&e=png&b=fdfdfd)
1. 判断数组是否为空，为空进行初始化;
2. 不为空，计算 k 的 hash 值，通过(n - 1) & hash计算应当存放在数组中的下标 index;
3. 查看 table[index] 是否存在数据，没有数据就构造一个Node节点存放在 table[index] 中；
4. 存在数据，说明发生了hash冲突(存在二个节点key的hash值一样), 继续判断key是否相等，相等，用新的value替换原数据(onlyIfAbsent为false)；
5. 如果不相等，判断当前节点类型是不是树型节点，如果是树型节点，创造树型节点插入红黑树中；
6. 如果不是树型节点，创建普通Node加入链表中；判断链表长度是否大于 8， 大于的话链表转换为红黑树；
7. 插入完成之后判断当前节点数是否大于阈值，如果大于开始扩容为原数组的二倍。

作者：安琪拉的博客
链接：https://juejin.cn/post/6844904090711900168
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
#### 特殊key
#### hash值计算
#### bucket遍历
### get方法
### Map的迭代器iterator
## hashmpa线程安全
### 数据覆盖
### 扩容时死循环
### 如何线程安全



## Reference
* [安琪拉的博客](https://juejin.cn/post/6844904090711900168)
