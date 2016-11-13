public class Solution {
	public List<String> letterCombinations(String digits) {
		List<String> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), digits, 0);
		return list;
	}

	void backtrack(List<String> list, List<String> tmp, String digits, int start) {
		if(tmp.size() == digits.length()) {
			list.add(new ArrayList<>(tmp));
		} else {
		}
	}

}
