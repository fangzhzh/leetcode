import java.util.*;
public class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            if(map.containsKey(num)) {
                continue;
            }
            Set<Integer> set=null;
            if(map.containsKey(num-1)) {
                set = map.get(num-1);
            }
            int len1 = (set == null) ? 0 : set.size();
            if(map.containsKey(num+1)) {
                if(set == null) {
                    set = map.get(num+1);
                } else {
                    set.addAll(map.get(num+1));
//                    map.put(num+1, set);
//                    System.out.println("map.put num+1: " + (num+1) + " " +  Arrays.toString(set.toArray()));
                }
            } 
            int len2 = (set == null) ? 0 : set.size() - len1;
            if(set == null) {
                set = new HashSet<>();
            }
            set.add(num);
            map.put(num, set);
            map.put(num-len1, set);
            map.put(num+len2, set);
//            System.out.println("map.put num: " + num + " " + Arrays.toString(set.toArray()));
        }
        Set<Integer> s = null;
        for(Set<Integer> set : map.values()) {
            if(s == null || set.size() > s.size()) {
                s = set;
            }
        }
        return s.size();
    }
}
