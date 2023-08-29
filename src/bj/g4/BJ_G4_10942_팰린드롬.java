package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_10942_팰린드롬 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, S, E, M, nums[];
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		nums = new int[N+1]; // 1 base
		for(int i=1; i<=N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}

		M = Integer.parseInt(input.readLine());
		for(int i=1; i<=M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(tokens.nextToken());
			int e = Integer.parseInt(tokens.nextToken());
			
			
		}
		
	}

}
