public class RandomizedSet {
	List<Integer> nums;
	Map<Integer, Integer> locs;
	java.util.Random rand = new java.util.Random();	
	

    /** Initialize your data structure here. */
    public RandomizedSet() {
		nums = new ArrayList<>();
		locs = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
		if(locs.containsKey(val)) return false;
		nums.add(val);
		locs.put(val, nums.size()-1);
		return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
		if(!locs.containsKey(val)) return false;
		int loc = locs.get(val);
		if( loc != nums.size()-1){
			int last = nums.get(nums.size()-1);
			nums.set(loc, last);
			locs.put(last, loc);
		}
		nums.remove(nums.size()-1);
		locs.remove(val);
		return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
       return nums.get(rand.nextInt(nums.size())); 
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
