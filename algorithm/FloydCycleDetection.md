
# Floyd Cycle Detection Floyd 判圈算法


![Floyd判圈算法](./graphs/floydCycleDection.drawio.svg)
## 判圈算法
使用两个指针slow和fast。两个指针开始时均在头节点处(SS点)，slow指针（龟）一次向后移动一个一步，fast指针（兔）一次向后移动两步。若存在环，则slow和fast必能相遇；反之若slow到达链表尾时两个指针仍不能相遇，则不存在环。
证明
设头节点SS与循环节起始点AA之间举例|SA|=m|SA|=m。两个指针在BB点相遇，|AB|=n|AB|=n。可知环中的点满足xi=xi+klxi=xi+kl，其中ll为循环节的长度，也就是说fast比slow多走了整数圈。当i=kli=kl时，满足xi=x2ixi=x2i，这样的ii一定存在，得证。

## 圈内移动
首先，让我们确认一个事实：两个人在环形跑道上同向而行，一前一后，速度不等，则快的那个一定能追上慢的那个。
设两人相距xx，跑道周长为CC，令快者的速率（2v2v）等于慢者（vv）的两倍，则追及时间t=x2v−v=xvt=x2v−v=xv，由于vt=x≤Cvt=x≤C，从开始计时到第一次相遇，慢者至多跑一圈。


## 计算环的长度

## 寻找环的起点

## 应用

