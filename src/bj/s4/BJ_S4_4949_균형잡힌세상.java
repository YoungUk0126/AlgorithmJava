package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_S4_4949_균형잡힌세상 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static Stack<Character> stack;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str;
		while(true) {
			str = input.readLine();
			
			if(str.equals(".")) break;
			
			stack = new Stack<>();
			for(char word: str.toCharArray()) {
				if (word == '(' || word == '[') {
					stack.push(word);
				}
				else if(word == ')') {
					if (stack.size() > 0 && stack.peek() == '(') {
						stack.pop();
					}
					else {
						stack.push(word);
					}
				}
				else if(word == ']') {
					if (stack.size() > 0 && stack.peek() == '[') {
						stack.pop();
					}
					else {
						stack.push(word);
					}
				}
			}
			if(stack.size() > 0) {
				builder.append("no").append("\n");
			}
			else {
				builder.append("yes").append("\n");
			}
		}
		System.out.println(builder);
	}

}
