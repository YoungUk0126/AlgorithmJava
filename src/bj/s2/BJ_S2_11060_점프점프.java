package bj.s2;
/**
@author 김영욱
@since 2023. 8. 30.
@see https://www.acmicpc.net/problem/11060
@git
@performance 88ms
@category #
@note
시간 : 1초
N : 1000 , Ai : 100
문자열 N개를 입력받는다.
각 칸에 적힌 숫자는 점프를 할 수 있는 거리이다.
최소 몇 번 점프를 해야 갈 수 있는지 구하는 프로그램을 작성해라.

최소 점프를 구해야하기 때문에 메모이제이션을 사용한 Dp를 사용하여 문제를 풀었다.
각 인덱스 별로 최소 점프를 넣는 DP테이블을 이용하지 않고 완전탐색을 시행한다면
각 인덱스 별로 N까지 돌아야 하기 때문에 시간초과가 발생하게 된다.
하지만 DP테이블을 이용하여 똑똑하게 완전탐색을 시행해 최소값을 미리미리 저장한다면 탐색을 줄일 수 있다.



*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_11060_점프점프 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, move;
	static int[] arr, dp;

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		arr = new int[N+1];
		dp = new int[N+1];
		Arrays.fill(dp, 1001); // dp테이블을 큰 값으로 초기화
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		dp[1] = 0; // 초기 값은 0을 넣어줌
		
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<i+1+arr[i]; j++) { // i의 다음 인덱스부터 i + arr[i]까지
				if(j > N)break; // j값이 N을 넘어간다면 인덱스 아웃이 나기 때문에 종료
				dp[j] = Math.min(dp[i]+1, dp[j]); // dp테이블에 최소값을 저장
				// dp[i]에 저장되어있는 최소값에 1을 더한값이 미리 저장되어있던 dp[j]보다 작다면 저장
			}
		}
		if(dp[N] == 1001) {
			System.out.println("-1"); // 마지막 까지 닿지 않았다면
		}
		else {
			System.out.println(dp[N]); // 아니라면 출력
		}
	}

}
