public class Solution {a
	public int strStr(String haystack, String needle) {
		for(int i=0; i<heystack.length(); ++i) {
			if(heystack.charAt(i) == needle.charAt(0)) {
				int idx = 0;
				int j = 0;
				while(j<needle.length()
					&& idx < heystack.length()
					&& heystack.charAt(idx) == needle.charAt(j) ) {
						++idx;
						++j;
					}
				if(j == needle.length()) return j;
			}
		}
	}
}
