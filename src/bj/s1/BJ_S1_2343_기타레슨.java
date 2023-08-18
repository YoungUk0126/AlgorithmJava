package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_2343_기타레슨 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M;
	static int[] guitar;
	static int blueLen;
	static int blueCnt;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		guitar = new int[N];
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<N; i++) {
			guitar[i] = Integer.parseInt(tokens.nextToken());
			// max값 찾기
			blueLen = Math.max(blueLen, guitar[i]);
		}
		
		while(true) {
			
		}
		
	}
}
