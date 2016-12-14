public class Solution {
    boolean isAlphabet(char cur) {
        return ((cur >='a' && cur <='z') || (cur >= 'A' && cur <= 'Z')); 
    }
    public String simplifyPath(String path) {
        int len = path.length();
        if(len == 0) return "";
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < len; ++i) {
            switch(path.charAt(i)) {
                case '/': {
                              i++;
                              if(i >= len) {
                                  break;
                              }
                              char cur = path.charAt(i);
                              if(isAlphabet(cur)) {
                                  StringBuilder b = new StringBuilder();
                                  while(i < len && isAlphabet(path.charAt(i))) {
                                      b.append(path.charAt(i));
                                      i++;
                                  }
                                  i--;
                                  stack.push(b.toString());
                              } else if(cur == '/' ){
                                  i--;
                                  continue;
                              } else if(cur == '.') {
                                  if(i<len-1 && path.charAt(i+1) == '.') {
                                      i++;
                                      if(!stack.isEmpty()) {
                                          stack.pop();
                                      }
                                  }
                              } else {
                              }
                }
                break;
                case '.': {
                }
                break;
            }
        }
        StringBuilder re = new StringBuilder();
        while(!stack.isEmpty()) {
            re.insert(0, stack.pop());
            re.insert(0,'/');
        }
        if(re.length() <= 0) {
            re.append('/');
        }
        return re.toString();
    }
}
