//import java.util.*;
public class Solution {

    String[] dic = {"0","1","abc", "def", "ghi", "jkl", "mno", 
    "pqrs", "tuv", "wxyz"};
	public List<String> letterCombinations(String digits) {
		List<String> list = new ArrayList<>();
        if(digits.length() > 0) {
            backtrack(list,new StringBuilder() , digits, 0);
        }
		return list;
	}

	void backtrack(List<String> list, StringBuilder b, String digits, int start) {
		if(b.length() == digits.length()) {
            list.add(new String(b.toString()));
            return;
		}
        for(int i = start; i < digits.length(); i++) {
            // System.out.println(String.format("%d of digits", i));
            String str = dic[Integer.valueOf(digits.charAt(i)-'0')];
            for(int j = 0; j < str.length(); j++) {
                b.append(str.charAt(j));
                backtrack(list, b, digits, i+1);
                b.setLength(b.length()-1);
            }
        }
	}
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.letterCombinations("232"));
        return ;
    }
}
