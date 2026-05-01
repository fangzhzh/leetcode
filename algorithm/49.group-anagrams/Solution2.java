import java.util.*;
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; ++i) {
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String key = String.valueOf(charArray);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        for(List<String> l : map.values()) {
            result.add(l);
        }
        return result;
    }
}
