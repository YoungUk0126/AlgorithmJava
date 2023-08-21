package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 70퍼 컽 남
public class BJ_G4_1806_부분합 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int N, S, min;
	static int nums[];
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		min = Integer.MAX_VALUE;
		
		tokens = new StringTokenizer(input.readLine());
		
		nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		

		sum += nums[0];
		while(start < N) {
			if (sum >= S || end == N-1) {
				if( sum >= S) {
					min = Math.min(min, (end-start)+1);
				}
				sum -= nums[start];
				start++;
			}
			else if(sum < S) {
				end++;
				sum += nums[end];
			}
		}
		if(min == Integer.MAX_VALUE) System.out.println("0");
		else System.out.println(min);
		
	}

}
