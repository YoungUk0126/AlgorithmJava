package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_12865_평범한배낭 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N, K;
	static int[][] dp;
	static int[] W, V;

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		// 물건의 무게, 물건의 가치
		W = new int[N+1];
		V = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			tokens = new StringTokenizer(input.readLine());
			W[i] = Integer.parseInt(tokens.nextToken());
			V[i] = Integer.parseInt(tokens.nextToken());
		}
		
		dp = new int[N+1][K+1];
		
		for (int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				// i번째 무게를 더 담을 수 없는 경우
				if(W[i] > j) {
					dp[i][j] = dp[i-1][j];
				}
				// i번째 무게를 더 담을 수 있는 경우
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
