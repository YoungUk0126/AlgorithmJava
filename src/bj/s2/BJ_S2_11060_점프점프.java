package bj.s2;

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
		Arrays.fill(dp, 1001);
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		dp[1] = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<i+1+arr[i]; j++) {
				if(j > N)break;
				dp[j] = Math.min(dp[i]+1, dp[j]);
			}
		}
		if(dp[N] == 1001) {
			System.out.println("-1");
		}
		else {
			System.out.println(dp[N]);
		}
	}

}
