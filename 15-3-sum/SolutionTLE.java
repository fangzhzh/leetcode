public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		Map<String, Integer> remap = new HashMap<>();
		for(int i = 0; i < nums.length; ++i) {
			map.put(nums[i], i); 
		} 
		for(int i = 0; i < nums.length; ++i) { 
			for(int j = i+1; j < nums.length; ++j) { 
				if(map.containsKey(0-nums[i]-nums[j])) {
					int k = map.get(0-nums[i]-nums[j]);
					if( k == i || k == j) continue;
					List<Integer> l = new ArrayList();
					l.add(nums[i]);
					l.add(nums[j]);
					l.add(0-nums[i]-nums[j]);
					Collections.sort(l);
					String key = String.format("%d%d%d", l.get(0), l.get(1), l.get(2));
					if(!remap.containsKey(key)) {
						remap.put(key, 0);
						result.add(l);
					}
				}
			}
		}
		return result;
	}
}

