/*
 * @lc app=leetcode id=349 lang=java
 *
 * [349] Intersection of Two Arrays
 */

// @lc code=start
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        return intersectionBinary(nums1, nums2);
    }

    // sort and binary search
    // time O(mLogN) 排序 O(logM + logN), 后边遍历m，每个m，logN查找，所以mLogN
    // mLogN > logM && mLogN > logN, 所以最终 o(mLogN)
    // space O(m)
    int[] intersectionBinary(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] result = new int[nums1.length];
        int length = 0;
        for(int i = 0; i < nums1.length; i++) {
            if(i > 0 && nums1[i] == nums1[i-1]) {
                continue;
            }
            int idx = binarySearch(nums2, nums1[i]);
            if(idx != -1) {
                result[length] = nums1[i];
                length++;
            }
        }
        return Arrays.copyOfRange(result, 0, length);
    }
    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    // Time O(m+n）, num2 需要一个logN 排序，但是logN<n, 所以总体是O(m + n)
    // Space O(m) m是num1长度，num1构建的set，所以O(n),而两个数组交集最大长度也是O(m)
    int[] intersectionSet(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums2);
        for(int i = 0; i < nums2.length; i++) {
            if(i > 0 && nums2[i] == nums2[i-1]) {
                continue;
            }
            if(set.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
// @lc code=end

