/*
 * @lc app=leetcode.cn id=1588 lang=java
 *
 * [1588] 所有奇数长度子数组的和
 */

// @lc code=start
class Solution {

    public int sumOddLengthSubarrays(int[] arr) {
        return sumOddLengthSubarraysOMath(arr);
    }
    // time O(n^3) Space O(1)
    // brutal 就是按照要求去计算
    public int sumOddLengthSubarraysO3(int[] arr) {
        int n = arr.length;
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int len = 1; len + i <= n; len += 2) {
                for(int j = i; j < len+i; j++) {
                    res += arr[j];
                }
            }
        }
        return res;
    }

    // 优化，如果要优化brutal的解法，一个可以优化的地方就是第三层的循环
    // 第三层循环的作用就是计算arr[i...i+len] YES presum
    // 是的，这个就是数组前缀和
    // 空间换时间
    // Time O(n^2), Space O(n)
    public int sumOddLengthSubarraysO2(int[] arr) {
        int n = arr.length;
        int[] presum = new int[n+1];
        for(int i = 0; i < n; i++) {
            presum[i+1] = presum[i] + arr[i];
        }
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int len = 1; len + i <= n; len += 2) {
                res += presum[i+len] - presum[i];
            }
        }
        return res;
    }

    // 本题可以继续优化，对于元素arr[i],如果我们知道这个元素属于的奇数数组的个数count，
    // 我们就可以直接 arr[i]*count
    // 所以如何计算元素属于的奇数数组的个数count呢。
    // 元素arr[i]属于一个奇数数组的充要条件是：两边的元素个数相等，即同为奇数或者同为偶数
    // res += arr[i] * (leftOdd * rightOdd + leftEven * rightEven)

    // 那么问题就转化为求元素arr[i]两边的奇数的个数和偶数的个数
    // 还是从例子推导 [1,4,2,5,3]
    // 以2为例子，左右同为偶数，2个元素
    // 在左边区域里，在[0, 2]范围内，偶数数组[], [1,4], 右边[0,2]范围内，偶数数组[], [5,3] => even = count/2+1
    // 在左边区域里，在[0,2] 范围内，奇数数组[1],[1,4,2], 右边[0,2], 奇数数组 [3] => Odd = (count+1)/2
    public int sumOddLengthSubarraysOMath(int[] arr) {
        int n = arr.length;
        int res = 0;
        for(int i = 0; i < n; i++) {
            int leftCount = i;
            int rightCount = n-i-1;
            int leftOdd = (leftCount+1)/2;
            int rightOdd = (rightCount+1)/2;
            int leftEven = (leftCount/2)+1;
            int rightEven = rightCount/2 + 1;
            res += arr[i] * (leftEven*rightEven + leftOdd*rightOdd);
        }
        return res;
    }
}





// @lc code=end

