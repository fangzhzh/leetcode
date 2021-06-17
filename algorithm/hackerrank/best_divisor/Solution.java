import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int max=0, best=n;
        for(int i=1;i<=Math.sqrt(n);i++) {
            if(n % i == 0){
                int tmp = i;
                if( isBetterDivisor(tmp, max, best)) {
                    max = digitSum(tmp);
                    best = tmp;
                }
                if(i!=n/i){
                    tmp = n/i;
                    if(isBetterDivisor(tmp, max, best)){
                        max = digitSum(tmp);
                        best = tmp;
                    }
                }
            }
        }
        System.out.println(best);
    }

    static boolean isBetterDivisor(int num, int max, int best) {
        return (digitSum(num) > max) || (digitSum(num) == max && num < best);
    }
    static int digitSum(int num) {
        int sum = 0;
        while(num>0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
