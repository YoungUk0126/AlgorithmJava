package sw.d3;

/**
@author 김영욱
@since 2023. 8. 4.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV14uWl6AF0CFAYD&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=++6+
@git
@performance
@category #
@note

테스트 케이스가 몇개인지 모른다...ㅠㅠ
이럴 땐 어떻게 해야하나..

8개의 숫자를 입력받는다(숫자는 8자리 고정)
첫 번째 숫자를 1감소한 뒤, 맨 뒤로 보냄
 '' 2감소한 뒤, 맨 뒤로 보냄, 5번 도는게 한 사이클
 다시 사이클을 반복한다. 
 요소는 0보다 작으면 0을 유지하며 프로그램은 종료된다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SW_D3_암호생성기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static Deque<Integer> deque;
	static StringBuilder builder = new StringBuilder();

	static int N;
	static String Hi;
	static int tc;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		while ((Hi = input.readLine()) != null && !Hi.isEmpty()) {
			tc++;
			deque = new ArrayDeque<Integer>();
			N = Integer.parseInt(Hi);
			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < 8; i++) {
				int num = Integer.parseInt(tokens.nextToken());
				deque.offer(num);
			}
			
			outer: while (true) {
				for (int i = 1; i <= 5; i++) {
					int num = deque.pollFirst();
					num -= i;
					if (num <= 0) {
						deque.offerLast(0);
						break outer;
					}
					deque.offerLast(num);
				}
			}
			builder.append("#").append(tc).append(" ");
			for (int i = 0; i < 8; i++) {
				builder.append(deque.pollFirst()).append(" ");
			}
			builder.append("\n");

		}
		System.out.println(builder);
	}
}
