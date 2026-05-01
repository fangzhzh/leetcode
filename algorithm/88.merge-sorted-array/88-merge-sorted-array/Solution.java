public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = m+n-1;m>0 && n > 0 && i >= 0;i--) {
            nums1[i]=(nums1[m-1] > nums2[n-1]) ? nums1[--m] : nums2[--n];
        }
        if(n == 0) return;
        if(m==0) {
            for(int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
    }
}
