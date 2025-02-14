# HashMap
![类关系图](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/f7fe16a2.png)
## HashMap实现原理
### 存储结构
* 链表 + 红黑树， 
![图示](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/3/15/170ddda186409662~tplv-t2oaga2asx-jj-mark:3024:0:0:0:q75.awebp#?w=1372&h=500&s=91501&e=png&b=fdfdfd)
#### 为什么链表+红黑树，而不是？
只要**红黑树？**？

红黑树需要更多存储空间，并且要进行左旋，右旋，变色保持平衡；单链表不需要。
* 元素数小于8，单链表满足性能。O(n)
* 大于8，红黑树O(lngn)

只用**二叉查找树?**
可以，但是特殊情况下，会变成一条线形链表，深遍历。

### **红黑树阈值 8？**
**红黑树阈值 8？**
1. 泊松分布，8以上的概率很小。
2. 元素较少时，链表有更高的效率。

## 加载因子0.75， why not 0.6 or 0.8？

```
     int threshold;             // 容纳键值对的最大值
     final float loadFactor;    // 负载因子
     int modCount;  
     int size;  

     threshold = size * loadFactor;
```
举个例子，加载因子0.75, 初始值16，当16*0.75=12，就会触发扩容机制。

| loadFactor｜ pros ｜cons ｜ 
|---|---|---|
| bigger | 数据多，空间利用率高 | 哈希冲突大，查找成本高 |
| smaller | 哈希冲突小，查找快 | 浪费空间, 提高扩容触发几率 | 



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
* 位运算，开销不大
* 下标稳定(refer indexFor)
    * 取下标是 散列值 & 数组长度-1
    * 或者散列值 % 数组长度
    * 位运算 远快于 模运算
* 此处也解释了，为什么前边，HashMap的数组长度取2^n, 因为这样(length-1)正好就是数组长度取模.
    * 以16为例，
    * 16        => 0001 0000
    * 16 - 1=15 => 0000 1111
    * h & (length-1) 就是数组中有效下标





## 哈希冲突详解
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

## 扩容机制
JDK1.7: 扩容有两步，resize(double), 和transfer。如下图所示

```java
void resize(int newCapacity) {   //传入新的容量
    Entry[] oldTable = table;    //引用扩容前的Entry数组
    int oldCapacity = oldTable.length;         
    if (oldCapacity == MAXIMUM_CAPACITY) {  //扩容前的数组大小如果已经达到最大(2^30)
        threshold = Integer.MAX_VALUE; //修改阈值为int的最大值(2^31-1)，这样以后就不扩容了
        return;
    }
 
    Entry[] newTable = new Entry[newCapacity];  //初始化一个新的Entry数组
    transfer(newTable);                         //！！将数据转移到新的Entry数组里
    table = newTable;                           //HashMap的table属性引用新的Entry组
    threshold = (int)(newCapacity * loadFactor);//修改阈值
}

void transfer(Entry[] newTable) {
    Entry[] src = table;                   //src引用了旧的Entry数组
    int newCapacity = newTable.length;
    for (int j = 0; j < src.length; j++) { //遍历旧的Entry数组
        Entry<K,V> e = src[j];             //取得旧Entry数组的每个元素
        if (e != null) {
            src[j] = null;//释放旧Entry数组的对象引用（for循环后，旧的Entry数组不再引任何对象）
            do {
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash, newCapacity); //！！重新计算每个元素在数组的位置
                e.next = newTable[i]; //标记[1]
                newTable[i] = e;      //将元素放在数组上
                e = next;             //访问下一个Entry链上的元素
            } while (e != null);
        }
    }
} 

```

![java 7扩容](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/b2330062.png)


### JDK 1.8
![不需重新计算hash，保持原索引，或者原索引+oldcap](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2016/3cc9813a.png)

## hashmpa线程安全
* 多线程下扩容会死循环
* 多线程下 put 会导致元素丢失
* put 和 get 并发时会导致 get 到 null
### 扩容时死循环
JDK7, 头部插入
![JDK7多线程扩容引起死循环](https://cdn.tobebetterjavaer.com/stutymore/hashmap-20241120144833.png)
### 多线程下 put 会导致元素丢失
### put 和 get 并发时会导致 get 到 null
## 如何线程安全
* 锁
* Collections.lock
* ConcurrentHashMap,分段锁
## 有序HashMap
TreeMap

## Reference
* [安琪拉的博客](https://juejin.cn/post/6844904090711900168)
* [Java 8系列之重新认识HashMap](https://tech.meituan.com/2016/06/24/java-hashmap.html)
* [Java HashMap详解：源码分析、hash 原理、扩容机制、加载因子、线程不安全](https://javabetter.cn/collection/hashmap.html#_04%E3%80%81%E7%BA%BF%E7%A8%8B%E4%B8%8D%E5%AE%89%E5%85%A8)
