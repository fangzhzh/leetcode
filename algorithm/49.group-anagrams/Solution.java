public class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		if(strs.length == 0) return result;
		Arrays.sort(strs);
		Map<String, List<String>> map = new HashMap<>();
		for(int i = 0; i < strs.length; ++i) {
			char[] chars = strs[i].toCharArray();
			Arrays.sort(chars);
			String key = String.valueOf(chars);
			if(!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			List<String> list = map.get(key);
			list.add(strs[i]);
		}
		for(List<String> l : map.values()) {
			result.add(l);
		}
		return result;

	}
}
