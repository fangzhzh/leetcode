
## Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"


### the brutal force 
tow loop and check palindromic for every combination

### Manancher's 
```
public class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
                    
            // abc => #a#b#c#
            String str = generateString(s);
                    
            int[] palindrome = new int[str.length()];
            int mid = 0, longest = 1;
            palindrome[0] = 1;
            for (int i = 1; i < str.length(); i++) {
                int len = 1; 
                if (mid + longest > i) {
                    int mirrorOfI = mid - (i - mid);
                    len = Math.min(palindrome[mirrorOfI], mid + longest - i);
                }
                        
                while (i + len < str.length() && i - len >= 0) {
                    if (str.charAt(i - len) != str.charAt(i + len)) {
                        break;
                    }
                    len++;
                }
                        
                if (len > longest) {
                    longest = len;
                    mid = i;
                }
                        
                palindrome[i] = len;
            }
                    
            longest = longest - 1; // remove the extra #
            int start = (mid - 1) / 2 - (longest - 1) / 2;
            return s.substring(start, start + longest);
        }
                
        private String generateString(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append('#');
                sb.append(s.charAt(i));
            }
            sb.append('#');
                    
            return sb.toString();
        }
    }
    ```