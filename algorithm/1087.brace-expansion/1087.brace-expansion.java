class Solution {
    public String[] expand(String s) {
        // BFS
        /*
// Queue BFS
  {a,b}c{d,e}f
      ^ queue("a","b")
       ^ queue("ac", "bc")
         ^ queue("acd", "bcd")
           ^ queue("acd", "bcd","ace", "bce")
             ^ queue("acdf", "bcdf", "acef", "bcef")

*/
        // Time O(n)
        // Space O(n) for queue
        Queue<StringBuilder> queue = new LinkedList<>();
        queue.offer(new StringBuilder());

        // scan one round the string
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是字符
            if(Character.isAlphabetic(c)) {
                appendToStringInQueue(queue, c);
            } else if(c == '{') {
                // 处理options
                List<Character> options = new ArrayList<>();
                while(i < s.length()) {
                    char newC = s.charAt(i);
                    if(Character.isAlphabetic(newC)) {
                        options.add(newC);
                    }
                    i++;
                    if(newC == '}') {
                        // } 本次options处理完毕 回退一个，然后break，
                        i--;
                        break;
                    }
                }
                // options的每个字符append到queue里每个字符
                appendToStringInQueue(queue, options);
            } else if( c == '}') {
                i++;
            }
        }
        String[] res = new String[queue.size()];
        int i = 0;
        while(!queue.isEmpty()) {
            res[i] = queue.poll().toString();
            i++;
        }
        Arrays.sort(res);
        return res;
    }
    private void appendToStringInQueue(Queue<StringBuilder> queue, char c) {
        int len = queue.size();
        while(len>0) {
            StringBuilder sb = queue.poll();
            System.out.println(sb + " plus " + c);
            queue.offer(sb.append(c));
            len--;
        }
    }

    private void appendToStringInQueue(Queue<StringBuilder> queue, List<Character> options) {
        int len = queue.size();
        while(len>0) {
            StringBuilder sb = queue.poll();
            for(char c : options) {
                // System.out.println(sb + " plus " + c);
                StringBuilder newSb = new StringBuilder(sb);
                queue.offer(newSb.append(c));

            }  
            sb = null;
            len--;
        }

    }    

}



// method2 
class Solution {/*
// Top down, DFS+回溯
        {a,b}  c {d,e} f
        /                \
        ac{d,e}f         bc{d,e}f
        /    \             /    \
        acdf  acef        bcdf  bcef

*/
    public String[] expand(String s) {
        // DFS 结果带上来
        List<String> result = new ArrayList<>();
        dfs(result, s, new StringBuilder(""), 0);
        String[] resultArray = new String[result.size()];
        result.toArray(resultArray);
        Arrays.sort(resultArray);
        return resultArray;

    }
    private void dfs(List<String> result, String s, StringBuilder prefix, int index) {
        // {a,b}c{d,e}f
        //     ^ 
        if(index == s.length()) {
            result.add(prefix.toString());
            return;
        }
        char c = s.charAt(index);
        // alphabet, dfs
        if(Character.isAlphabetic(c)) {
            prefix.append(c);
            dfs(result, s, prefix, index+1);
            prefix.deleteCharAt(prefix.length()-1);
        } else if(c == '{'){
            // {, scan to next }
            List<Character> options = new ArrayList();
            int i = index + 1;
            for(; i < s.length(); i++) {
                if(s.charAt(i) == '}') break;
                if(Character.isAlphabetic(s.charAt(i))) {
                    options.add(s.charAt(i));
                }
            }
            for(char option : options) {
                prefix.append(option);
                dfs(result, s, prefix, i+1);
                prefix.deleteCharAt(prefix.length()-1);
            }
        }
    }
}
