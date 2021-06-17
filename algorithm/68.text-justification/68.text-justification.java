/*
 * @lc app=leetcode id=68 lang=java
 *
 * [68] Text Justification
 */

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 

Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
 */ 
// @lc code=start

/**
 * scan, find a match, then pack
 * 
 * tricky though, all of edge cases
 */
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int curLen = 0;
        List<String> curList = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            // scan 
            curLen += word.length();
            if(curLen > maxWidth) {
                ans.add(pack(curList, maxWidth, false));
                curLen = word.length();
                curLen++;
                curList.clear();
                curList.add(word);
                continue;
            }
            if(curLen == maxWidth) {
                boolean lastLine = (i == words.length-1);
                curList.add(word);
                ans.add(pack(curList, maxWidth, lastLine));
                curLen = 0;
                curList.clear();
                continue;
            }
            curList.add(word);
            // space
            curLen++;
            // add
        }
        if(!curList.isEmpty()) {
            ans.add(pack(curList, maxWidth, true));
        }
        return ans;
    }
    private String pack(List<String> list, int maxWidth, boolean lastLine) {
        List<StringBuilder> ans = new ArrayList<>();
        int len = 0;
        for(String word : list) {
            len += word.length();
            ans.add(new StringBuilder(word));
        }
        int i = 0;
        while(len < maxWidth) {
            if(!lastLine) {
                ans.get(i).append(" ");
                i = (i+1) % (list.size());
                if(i == list.size()-1) i = 0;
            } else {
                if(i != ans.size()-1) {
                    ans.get(i).append(" ");
                    i++;
                } else {
                    ans.get(i).append(" ");
                }

            }
            len++;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder sb : ans) {
            res.append(sb.toString());
        }
        return res.toString();
    }
}
// @lc code=end

