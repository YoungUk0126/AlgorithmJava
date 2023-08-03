package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 2.
@see https://www.acmicpc.net/problem/11660
@git
@performance 760ms
@category #
@note
*/
public class BJ_S1_11660_구간합구하기5 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	static int[][] arr;
	static int N;
	static int M;
	static int x1;
	static int x2;
	static int y1;
	static int y2;
	static int sum;
	static int temp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		arr = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=1; j<=N; j++) {
				temp = Integer.parseInt(tokens.nextToken());
				arr[i][j] = temp + (arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1]);
			}
		}
//		for (int i = 1; i < arr.length; i++) {
//			for(int j: arr[i]) {
//				System.out.print(j + " ");
//			}
//			System.out.println();
//		}
		
		// 검색
		for(int m=0; m<M; m++) {
			sum = 0;
			tokens = new StringTokenizer(input.readLine());
			x1 = Integer.parseInt(tokens.nextToken());
			y1 = Integer.parseInt(tokens.nextToken());
			x2 = Integer.parseInt(tokens.nextToken());
			y2 = Integer.parseInt(tokens.nextToken());
			
			
			solution();
			builder.append(sum).append("\n");
		}
		System.out.println(builder);
		

	}
	
	static void solution() {
		sum = arr[x2][y2] - (arr[x2][y1-1] + arr[x1-1][y2]) + arr[x1-1][y1-1];
	}

}
