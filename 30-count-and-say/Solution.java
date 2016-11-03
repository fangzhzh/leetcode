public class Solution {
	public String countAndSay(int n) {

		int idx = 1;
		String s = "1";
		while(idx < n){
			StringBuilder re = new StringBuilder();
			for(int i=0; i < s.length(); ++i) {
				char c = s.charAt(i);
				char nt = (i < s.length()-1)?s.charAt(i+1):'0';
				int cnt = 1;
				while(c==nt) {
					cnt++;
					++i;
					nt = (i < s.length()-1)?s.charAt(i+1):'0';
				}

				re.append(cnt);
				re.append(c);
			}
			s=re.toString();

			idx++;
		}
		return s;
	}
}
