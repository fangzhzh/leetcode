public class Solution {
    int[][] skip = new int[10][10];
    boolean[] vis = new boolean[10];
    public int numberOfPatterns(int m, int n) {
        fillSkip(1, 3, 2);
        fillSkip(7, 9, 8);
        fillSkip(1, 7, 4);
        fillSkip(3, 9, 6);
        fillSkip(4, 6, 5);
        fillSkip(2, 8, 5);
        fillSkip(1, 9, 5);
        fillSkip(3, 7, 5);
        int sum = 0;
        sum += backtrack(1, m, n, 1, 0)*4;
        sum += backtrack(2, m, n, 1, 0)*4;
        sum += backtrack(5, m, n, 1, 0);
        return sum;
    }
    void fillSkip(int start, int end, int mid) {
        skip[start][end] = skip[end][start] = mid;
    }
    
    int backtrack(int start, int m, int n, int len, int count) {
        if(len > n) return count;
        if(len >= m) count++;
        vis[start] = true;
        for(int i = 1; i < 10; ++i) {
            int jmp = skip[start][i];
            if(!vis[i] && ( jmp == 0 || vis[jmp] )) {
                count = backtrack(i, m, n, len+1, count);
            }
        }
        vis[start] = false;
        return count;
    }
}
