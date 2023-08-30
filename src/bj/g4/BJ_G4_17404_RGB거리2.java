package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_17404_RGB거리2 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int n, arr[][], dp[][];
	
	public static void main(String[] args) throws IOException{
		n = Integer.parseInt(input.readLine());
		
		arr = new int[n][3];
		dp = new int[n][3];
		
		for(int i=0; i<n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE - 1001);
		}
		
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		for(int i=0; i<3; i++) {
			dp[0][i] = arr[0][i];
		}
		// 빨간색 집, 파란색 집, 초록색 집 가는거 따로 계산해줘야함(고쳐라 여기)
		for(int i=1; i<n; i++) {
			for(int j=0; j<3; j++) {
				// 세가지들 선택해서 나머지 두자리에 더해줌
				for(int k=1; k<3; k++) {
					int r = (j+k) % 3;
					dp[i][j] = Math.min(dp[i-1][r] + arr[i][j], dp[i][j]);
				}
			}
		}
		int a = Math.min(dp[n-1][0], dp[n-1][1]);
		int b = Math.min(dp[n-1][1], dp[n-1][2]);
		System.out.println(Math.min(a, b));
		
	}

}
