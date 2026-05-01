/*
 * @lc id=71 lang=java
 *
 * [71] Simplify Path
 */

 /**
  * 
  Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
  */

  /**
   * ## analysis
   * - use library, spit make the parsing super easy
   * - take care some edge cases
   */
// @lc code=start
class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < paths.length; i++) {
            switch (paths[i]) {
                case "":
                case ".":
                    break;
                case "..": {
                        if(!stack.isEmpty()) {
                            stack.pop();
                        }
                    }
                    break;
                default: {
                    if(paths[i].length() != 0) {
                        stack.push("/"+paths[i]);
                    }
                }
                    break;
            }
        }
        StringBuilder builder = new StringBuilder();
        if(stack.isEmpty()) {
            stack.push("/");
        }
        while(!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }
        return builder.toString();
    }
}
// @lc code=end


