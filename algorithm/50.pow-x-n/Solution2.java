// if loop by x^n, it will get TLE, so the point of iterative case is to reduce loop size
public class Solution {
    public double myPow(double x, int n) {
        long absN = Math.abs(n);
        double re = 1.0;
        while(absN > 0) {
            if((absN & 1) == 1) {
                re *= x;
            }
            x *= x;
            absN >>= 1;
        }
        return (n < 0) ? 1.0 / re : re;
    }
}
