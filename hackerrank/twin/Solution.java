
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int lastP=0, count=0;
        for(int i = m; i <=n; ++i ){
            if(isPrime(i)) {
                if(lastP != 0 && i-lastP==2) {
                    count++;
                }
                lastP = i;
            }
        }
        System.out.println(count);
    }
    static boolean isPrime(int n ) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}
