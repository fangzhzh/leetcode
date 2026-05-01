public class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        char[] tmp = new char[4];
        int cnt = 0;
        while(cnt < n) {
            int k = read4(tmp);
            for(int i = 0; i < k && cnt < n; ++i) {
                buf[cnt++] = tmp[i];
            }
            if(k<4) {
                break;
            }
        }
        return cnt;
        
    }
}
