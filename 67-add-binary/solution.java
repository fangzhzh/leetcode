
public class Solution {
	public String addBinary(String a, String b) {
		StringBuilder result = new StringBuilder();
		int len = Math.max(a.length(), b.length());
		int lenA = a.length();
		int lenB = b.length();
		int in = 0;
		int out = 0;
		for(int i = 0; i < len; ++i){
			char ca = (i < lenA) ? a.charAt(lenA-i-1) : '0';
			char cb = (i < lenB) ? b.charAt(lenB-i-1) : '0';
			char tmp;
			if(ca == '0'){
				if(cb == '0'){
					tmp = '0';
					out = 0;
				} else {
					tmp = '1';
					out = 0;
				}
			} else {
				if(cb == '0'){
					tmp = '1';
					out = 0;
				} else {
					tmp = '0';
					out = 1;
				}
			}
			if(tmp == '0'){
				if(in == 0){
					result.insert(0, '0');
				} else {
					result.insert(0, '1');
				}
			}else{
				if(in == 0){
					result.insert(0, '1');
				} else {
					result.insert(0, '0');
					out = 1;
				}
			}
			in = out;
		}
		if( in == 1 ) {
			result.insert(0, '1');
		}
		return result.toString();
	}
}
