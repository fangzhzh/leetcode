public class Solution {
	public boolean isValid(String s) {
		Stack<Character> queue = new Stack<>();
		for(int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			switch(c){
					case '(':
					case '{':
					case '[':
						queue.push(c);
					break;
					case ')':
						if(!queue.isEmpty() && queue.peek().charValue() == '(') queue.pop();
						else return false;
					break;
					case ']':
						if(!queue.isEmpty() && queue.peek().charValue() == '[') queue.pop();
						else return false;
					break;
					case '}':
						if(!queue.isEmpty() && queue.peek().charValue() == '{') queue.pop();
						else return false;
					break;
			}
		}
		return queue.isEmpty();
	}
}
