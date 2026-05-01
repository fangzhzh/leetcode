public class Solution {
	public int numDecodings(String s) {
		int n = s.length();
		if(n == 0) return 0;
		int[] d = new int[n+1];
		d[n]=1;
		d[n-1]=(s.charAt(n-1)=='0')?0:1;
		for(int i = n-2;i>=0;--i) {
			if(s.charAt(i) == '0') continue;
			else d[i] = (Integer.valueOf(s.substring(i,i+2))<=26)?d[i+1]+d[i+2]:d[i+1];
		}
		return d[0];
	}
}
