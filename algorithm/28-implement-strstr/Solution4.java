public class Solution {
    int[] d;
    void prebuild(String needle) {
        int i=0,j=-1;
        d[0]=-1;
        char[] arrs = needle.toCharArray();
        while(i<needle.length()){
            while(j>=0 && arrs[i] != arrs[j]) j = d[j];
            i++;
            j++;
            d[i] = j;
        }
    }
    int find(String haystack, String needle) {
        int j = 0, i = 0; 
        char[] arrs1 = haystack.toCharArray();
        char[] arrs2 = needle.toCharArray();
        while(i < haystack.length()) {
            while(j>=0 &&arrs1[i] != arrs2[j] ) j = d[j];
            i++;
            j++;
            if(j == needle.length()) {
                return i-j;
            }
        }
        return -1;
    }
            
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        d = new int[needle.length()+1];
        prebuild(needle);
        return find(haystack, needle);
    }
}
