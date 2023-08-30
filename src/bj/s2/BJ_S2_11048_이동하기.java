package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S2_11048_이동하기 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N,M, map[][], dp[][];
	static int deltas[][] = {{-1, 0}, {0,-1}, {-1, -1}};

	public static void main(String[] args) throws IOException{
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				int max=0;
				for(int d = 0; d < 3; d++) {
					int r = i + deltas[d][0];
					int c = j + deltas[d][1];
					max = Math.max(max, dp[r][c] + map[i][j]);
				}
				dp[i][j] = max;
			}
		}
		System.out.println(dp[N][M]);
	}
}
