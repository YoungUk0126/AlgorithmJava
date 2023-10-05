package sw.d5;

/**
 * @author 김영욱
 * @since 2023. 10. 05
 * @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWXRUN9KfZ8DFAUo&probBoxId=AYr3k03KgA4DFAV6&type=PROBLEM&problemBoxTitle=1004%EC%A3%BC&problemBoxCnt=4
 * @performance 
 * @category 
 * @note
 * 
 * N은 4의 배수로 들어온다.
 * 자물쇠의 비밀번호 자릿수는 N을 4로 나눈 숫자다 ( num ).
 * N을 num만큼 옆으로 밀면 모든 자릿수의 경우의 수를 구할 수 있다.
 * 이 모든 경우의 수들 중에서 K번째로 큰 수를 출력하는 문제이다.
 * 
 *  미는 것은 Stack로 구현하면 될거 같다. ( 마지막 수를 빼서 맨 앞에 붙여야 하기 때문 )
 *  숫자를 꺼내는 건 Queue형식으로 앞에서부터 꺼내야 한다.( num만큼 끊자 )
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SW_D5_보물상자비밀번호 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int T, N, K, num;
	static char[] numbers;
	static ArrayDeque<Character> q;
	static Set<String> set;

	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			set = new HashSet<>();
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			num = N/4;
			numbers = new char[N];
			
			String temp = input.readLine();
			numbers = temp.toCharArray();
			
			q = new ArrayDeque<>();
			
			for(char c: numbers) {
				q.offerLast(c);
			}
			int cnt = 0;
			while(true) {
				cnt++;
				// 다 회전 했다면 끝내
				if(cnt > num) {
					break;
				}
				else {
					// 회전하기 전에 값들 다 뽑아서 경우의 수 저장하는 set에 저장
					for(int i=0; i<4; i++) {
						temp = "";
						for(int j=0; j<num; j++) {
							char t = q.pollFirst();
							temp += t;
							q.offerLast(t);
						}
						set.add(temp);
					}
					// 한 바퀴 회전
					char t = q.pollLast();
					q.offerFirst(t);
				}
			}
			List<String> li = new ArrayList<String>(set);
			Collections.sort(li, Collections.reverseOrder());
			
			ArrayList<Integer> ans = new ArrayList<>();
			
			int decimal = Integer.parseInt(li.get(K-1), 16);
			
			builder.append("#").append(tc).append(" ").append(decimal).append("\n");
			
		}
		System.out.println(builder);

	}

}
