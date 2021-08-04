# 一些常用模板

## 快速乘法

采用的是倍增思想

```java
// 10*15
// ans, a, k
// 0, 10, 15
// 10, 20, 7,
// 30, 40, 3
// 70, 80, 1
// 150, 160, 0 ==> End


// 10 * 8
// ans, a, k
// 0, 10, 8
// 0, 20, 4
// 0, 40, 2
// 0, 80, 1
// 80, 160, 0 ==> End
long mul(long a, long k) {
    long ans = 0;
    while (k > 0) {
        if ((k & 1) == 1) ans += a;
        k >>= 1;
        a += a;
    }
    return ans;
}

// 链接：https://leetcode-cn.com/problems/divide-two-integers/solution/shua-chuan-lc-er-fen-bei-zeng-cheng-fa-j-m73b/

```


## 二分模板

二分的思想是把[left, right)分为[left, mid), [mid+1, right)两部分

所以`lower_bound`是`nums[mid]>=target`时，`right = mid`,`uppder_bound`是`nums[mid]<=target`时，`left=mid+1`