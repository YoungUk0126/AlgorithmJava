package sw.d4;
/**
@author 김영욱
@since 2023. 8. 4.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV14eWb6AAkCFAYD&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=++6+
@git
@performance 0.1s
@category #
@note

N만큼 반복을 돌면서 열린 괄호들을 스택에 저장하다가 닫힌 괄호들을 만나면
top에 있는 요소를 뽑아서 괄호의 짝이 맞는지 확인(괄호의 짝을 확인하기 편하게 map으로 개발해야겠다)
모든 검사를 통과하면 큐에 요소가 남아있는지 확인(남아 있으면 닫힌 괄호가 알맞게 등장하지 않은 것.)
*/
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
