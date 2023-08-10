package sw.d3;
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

부분집합으로 계산해야할 것 같다.
왜냐면 구매하는 개수가 정해져 있지 않기 때문이다.

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SW_D3_햄버거다이어트 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static HashMap<Integer, int[]> parts;
	static int max, L, N;
	static boolean [] isSelected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			max = Integer.MIN_VALUE;
			parts = new HashMap<>();
			tokens = new StringTokenizer(input.readLine());
			
			N = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			
			isSelected = new boolean[N];
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				int score = Integer.parseInt(tokens.nextToken());
				int fat = Integer.parseInt(tokens.nextToken());
				parts.put(i, new int[] {score, fat});
			}
			makeSubSum(0);
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void makeSubSum(int cnt) {
		// 기저 조건
		if(cnt == parts.size()) {
			int scoreSum = 0;
			int fatSum = 0;
			for(int i=0; i<parts.size(); i++) {
				if(isSelected[i]) {
					scoreSum += parts.get(i)[0];
					fatSum += parts.get(i)[1];
				}
			}
			if(fatSum < L && scoreSum > max) {
				max = scoreSum;
			}
			return;
		}
		
		isSelected[cnt] = true;
		makeSubSum(cnt + 1);
		isSelected[cnt] = false;
		makeSubSum(cnt + 1);
		
	}

}
