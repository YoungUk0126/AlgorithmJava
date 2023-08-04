package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class SW_D4_괄호짝짓기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static Deque<Character> deque;
	static HashMap<Character, Character> map;
	static StringBuilder builder = new StringBuilder();
	
	static int N;
	static char word;
	static char compareWord;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		for(int tc=1; tc<=10; tc++) {
			
			//꼭 초기화는 테스트케이스 반복문 안에서^^
			deque = new ArrayDeque<>();
			map = new HashMap<>();
			ans = 1;
			map.put(')', '(');
			map.put(']', '[');
			map.put('}', '{');
			map.put('>', '<');
			N = Integer.parseInt(input.readLine());
			
			String str = input.readLine();
			
			
			for(int i=0; i<N; i++) {
				word = str.charAt(i);
				if(!map.containsKey(word)) {
					deque.offer(word);
				} else {
					compareWord = deque.pollLast();
					if(map.get(word) != compareWord) {
						break;
					}
				}
			}
			if(!deque.isEmpty()) {
				ans = 0;
			}
			builder.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}
}
