package bj.g5;
/**
@author 김영욱
@since 2023. 8. 7.
@see https://www.acmicpc.net/problem/2493
@git
@performance 600ms
@category #
@note

맨 밑에 보니까 스택을 쓰랜다. ㅎㅎ..
그래서 스택을 생각해서 문제를 보니까 알고리즘이 나왔음
일단 한줄 입력을 받고 맨 뒤부터 거꾸로 탐색
답을 담을 스택_ans, 상황을 해결할 스택_sol
1. 맨 뒤 4가 sol에 들어가기 전 sol에 들어있는 top요소와 비교,
더 크면 top pop하고 4의 인덱스 번호 sol에 in
더 작으면 그냥 in

2. 다음 맨 뒤 7이 sol에 들어가기 전 sol에 들어있는 top요소 4와 비교,
더 크니까 4 pop하고 7의 인덱스 ans에 offer
7은 다시 sol에 in 반복

끝까지 돈 후 sol에 남은 요소만큼 ans에 0 offer

https://steady-coding.tistory.com/15
내 뒤에서부터 하는 방식은 이유모를 이유가 있다...ㅠㅠ
이걸 참고해서 구현하니 맞았다
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class lader {
	int idx;
	int value;
	
	public lader(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
}

public class BJ_G5_2493_탑 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static Deque<lader> sol;
	
	static int N;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		sol = new ArrayDeque<>();
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=1; i<=N; i++) {
			int now = Integer.parseInt(tokens.nextToken());
			if(sol.isEmpty()) {
				sol.offerLast(new lader(i, now));
				builder.append(0).append(" ");
			} else {
				while(true) {
					if(sol.isEmpty()) {
						sol.offerLast(new lader(i, now));
						builder.append(0).append(" ");
						break;
					}
					
					lader top = sol.peekLast();
					
					if(now < top.value) {
						builder.append(top.idx + " ");
						sol.offerLast(new lader(i, now));
						break;
					} else {
						sol.pollLast();
					}
				}
			}
			
			
//			lazer[i] = Integer.parseInt(tokens.nextToken());
		}
		
//		int comp = 0;
//		for(int i=lazer.length-1; i>=0; i--) {
//			while(!sol.isEmpty()) {
//				comp = sol.peekLast();
//				if(comp<=lazer[i]) {
//					ans.offerLast(i+1);
//					sol.pollLast();
//				} else {
//					break;
//				}
//			}
//			sol.offerLast(lazer[i]);
//		}
//		//sol스택에 남아있는 만큼 0 ans에 넣어줌
//		while(!sol.isEmpty()) {
//			sol.pollLast();
//			ans.offerLast(0);
//		}
//		
//		while(!ans.isEmpty()) {
//			builder.append(ans.pollLast()).append(" ");
//		}
		System.out.println(builder);
	}

}
