package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_16927_배열돌리기2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	
	static int N,M,R,repeat,max,min;
	static int arr[][];
	static int m;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		max = Math.max(N, M);
		min = Math.min(N, M);
		
		arr = new int[N][M];

		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		while(min>0) {
			repeat = (max*2) + ((min-2)*2);
			
			
			
			
			max -= 2;
			min -= 2;
		}
	}

}
