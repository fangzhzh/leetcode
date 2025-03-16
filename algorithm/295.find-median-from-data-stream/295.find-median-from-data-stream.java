/*
 * @lc app=leetcode id=295 lang=java
 *
 * [295] Find Median from Data Stream
 */

/**
Category	Difficulty	Likes	Dislikes
algorithms	Hard (45.58%)	3868	72
Tags
Companies
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.
 

Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
// @lc code=start
class MedianFinder {
    private PriorityQueue<Integer> large;
    private PriorityQueue<Integer> small;
    /** initialize your data structure here. */
    public MedianFinder() {
        large = new PriorityQueue<>();
        small = new PriorityQueue<>((a,b) -> {
            return b - a;
        });
    }
    
    public void addNum(int num) {
        if(large.size() > small.size()) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
    }
    
    public double findMedian() {
        if(large.size() > small.size()) {
            return large.peek();
        } else if(large.size() < small.size()) {
            return small.peek();
        } else {
            return (large.peek() + small.peek()) / 2.0;
        }
        
    }
}


// version 2: 2025-03-15
class MedianFinder {
    // small number stack is using a max heap to keep the small number by popping out the largest number
    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    // large number stack is using a min heap to keep the large numbers by popping out the smallest number
    PriorityQueue<Integer> large = new PriorityQueue<>();
    // k, k-> add to large
    // k, k+1 -> add to small
    boolean even = true;
    public MedianFinder() {
        
    }
    public double findMedian() {
        if(even) {
            return (small.peek() + large.peek()) / 2.0;
        } else {
            return large.peek();
        }
    }


    public void addNum(int num) {
        if(even) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
        even = !even;
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
// @lc code=end

