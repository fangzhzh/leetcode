import java.util.*;
public class Solution{
    public boolean isPalindrome(String s) {
        if(s.isEmpty()) return true;
        s = s.toLowerCase();
        int low = 0, high = s.length()-1;
        while(low < high) {
            if(!Character.isLetterOrDigit(s.charAt(low))) {
                low++;
            }
            if(!Character.isLetterOrDigit(s.charAt(high))) { 
                high--;
            }

            if(Character.isLetterOrDigit(s.charAt(low)) && Character.isLetterOrDigit(s.charAt(high))){
                if( s.charAt(low) != s.charAt(high)) {
                    return false;
                }
                else {
                    low++;
                    high--;
                }
            }
        }
        return true;
    }
}
