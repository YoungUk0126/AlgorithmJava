package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_2467_용액 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();

	static int N;
	static int sum;
	static int[] water;
	static int min;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		water = new int[N];
		min = Integer.MAX_VALUE;
		
		int left = 0;
		int right = N-1;
		
		for(int i=0; i<N; i++) {
			water[i] = Integer.parseInt(tokens.nextToken());
		}
		
		while(left<right) {
			sum = water[left] + water[right];
			if(sum == 0) {
				builder.append(water[left]).append(" ").append(water[right]);
				break;
			}
			else if(sum > 0) {
				right--;
			} else {
				left++;
			}
			if(min > Math.abs(sum)) {
				min = sum;
			}
		}
		System.out.println(builder);
		
	}

}
