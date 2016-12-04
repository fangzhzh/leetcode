/**
 *
 * ["wrt","wrf","er","ett","rftt"]
 * The correct order is: "wertf".
 *
 *
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class Solution {
    public String alienOrder(String[] words) {
        List<Set<Integer>> list = new ArrayList<>(26);
        for(int i = 0; i < 26; ++i) {
            list.add(new HashSet<>());
        }
        int[] degree = new int[26];
        Arrays.fill(degree, -1);

        // build graph
        for(int i = 0; i < words.length; ++i) {
            for(char c : words[i].toCharArray()) {
                if(degree[c-'a'] == -1) {
                    degree[c-'a'] = 0;
                }
            }
            if(i == 0) {
                continue;
            }
            String p = words[i-1];
            String cur = words[i];
            int len = Math.min(p.length(), cur.length());
            for(int l = 0; l < len; ++l) {
                int pi = p.charAt(l)-'a';
                int ci = cur.charAt(l)-'a';
                if(pi != ci) {
                    if(!list.get(pi).contains(ci)) {
                        list.get(pi).add(ci);
                        degree[ci]++;
                    }
                    break;
                }
                if(l == cur.length()-1 && p.length() > cur.length()) {
                    return "";
                }
            }
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < list.size(); ++i) {
            if(degree[i] == 0) {
                q.offer(i);
            }
        }
        StringBuilder builder = new StringBuilder();
        while(!q.isEmpty()) {
            int ele = q.poll();
            builder.append((char)(ele+'a'));
            for(int c : list.get(ele)) {
                degree[c]--;
                if(degree[c] == 0) {
                    q.offer(c);
                }
            }
        }
        for(int d : degree) {
            if(d > 0) {
                return "";
            }
        }
        return builder.toString();
        
    }
}
