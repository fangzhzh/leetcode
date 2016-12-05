/* The knows API is defined in the parent class Relation.
 *  boolean knows(int a, int b); */
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int find = 0;
        for(int i = 1; i < n; i++) {
            if(i!=find && !knows(i, find)) {
                find = i;
            }
        }
        for(int i = 0; i < n; i++) {
            if(i!=find && !knows(i, find)) return -1;
            if(i!=find && knows(find, i)) return -1;
        }
        return find;
    }
}

