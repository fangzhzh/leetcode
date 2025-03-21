import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  // recursive
  

  public int romanToInt(String s) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X',10);
    map.put('L',50);
    map.put('C',100);
    map.put('D',500);
    map.put('M',1000);
    map.put('0',-1);
    int sum = 0;
    int idx = 0;
    while(idx < s.length()) {
      char cur = s.charAt(idx);
      char next = idx+1 < s.length() ? s.charAt(idx+1) : 
    '0';
      
      idx++;
      int value = map.get(cur);
      int nValue = map.get(next);
      if(value < nValue) {
        sum += (nValue-value);
        idx++;
      } else {
        sum += value;
      }
      
    }
    return sum;
    
  }


}

