package sw.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 10
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWT-lPB6dHUDFAVT&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=7
@git
@performance
@category #
@note
공간 복잡도는 신경 쓸 필요 없음
시간은 20개 합쳐서 8초

민기는 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거를 주문하려 한다.
재료를 선택하면 준비해놓은 재료를 그대로 사용하여 조합.
햄버거 재료에 대한 점수, 재료에 대한 칼로리

정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거
점수의 합이 가장 높다 => 가장 선호한다.
같은 재료를 여러 번 사용할 수 없다(중복 X!!)

============입력=============
T
N : 재료의 수 , L : 제한 칼로리
N개 만큼 재료에 대한 점수와 칼로리
score fat

==============출력===========
가장 맛에 대한 점수가 높은 햄버거

단순하게 생각해서 부분집합으로 해도 통과는 되지만
시간이 너무 느리다.
재귀를 돌 때 좀 더 조건을 붙이는 방식으로 돌아야 겠다.

재료들을 담을 Map이 필요하다.
Map에 담고 부분집합들을 쭈욱 구한다.
제한 칼로리를 넘지 않으면서 가장 높은 점수를 max에 넣는다.

재귀를 할 때 칼로리와 점수를 더해서 넘겨줘야한다.
그리고 칼로리가 1000을 넘어가면 멈추고 현재 더한 점수의 합이 max보다 높으면 max값을 바꿔준다.

*/
public class SW_D3_햄버거다이어트2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static HashMap<Integer, int[]> parts;
	static int max, L, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			max = Integer.MIN_VALUE;
			parts = new HashMap<>();
			tokens = new StringTokenizer(input.readLine());
			
			N = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				int score = Integer.parseInt(tokens.nextToken());
				int fat = Integer.parseInt(tokens.nextToken());
				parts.put(i, new int[] {score, fat});
			}
			solve(0, 0, 0);
			builder.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(builder);
	}
	
	private static void solve(int cnt, int fatSum, int scoreSum) {
		if(cnt == parts.size()) {
			if(scoreSum > max) max = scoreSum;
			return;
		}
		
		if(fatSum + parts.get(cnt)[1] <= L) {
			solve(cnt + 1, fatSum + parts.get(cnt)[1], scoreSum + parts.get(cnt)[0]);
		}
		solve(cnt + 1, fatSum, scoreSum);
		
		
	}

}
