public class Solution{
    public bool isPalindropme(String s) {
        if(s.isEmpty()) return true;
        s = s.toLowerCase();
        int low = 0, high = s.lenght()-1;
        while(low < high) {
            if(!Charactor.isAlphabet(s.charAt(low))) {
                low++;
            }
            if(!Charactor.isAlphabet(s.charAt(high))) { 
                high--;
            }
            if(Charactor.isAlphabet(s.charAt(low))
                    && Charactor.isAlphabet(s.charAt(high))){
                if( s.charAt(low) != s.charAt(high))
                    return false;
                else {
                    low++;
                    high--;
                }

        }
        return true;
    }
}
