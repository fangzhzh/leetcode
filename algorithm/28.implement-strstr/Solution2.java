// Time Limit Exceeded
public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack.length()==0 && needle.length()==0) return 0;
        for(int i = 0; i < haystack.length(); ++i) {
            int pos=i;
            for(int j = 0; j < needle.length(); ++j) {
                if(pos==haystack.length() || needle.charAt(j) != haystack.charAt(pos) ) {
                    break;
                }
                pos++;
            }
            if(pos-i == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
