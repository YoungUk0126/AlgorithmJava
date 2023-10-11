package sw.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_D2_나무높이 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, T, day;
	static int target;
	static int[] trees;
	static int shortest = Integer.MAX_VALUE;
	static int sIdx = 0;
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			day = 0;
			target = Integer.MIN_VALUE;
			N = Integer.parseInt(input.readLine());
			trees = new int[N];
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<N; i++) {
				trees[i] = Integer.parseInt(tokens.nextToken());
				if(trees[i] > target) {
					target = trees[i];
				}
			}
			// 짝수
			int even = 0;
			// 홀수
			int odd = 0;
			
			for(int i=0; i<N; i++) {
				int diff = target - trees[i];
				even += diff / 2;
				odd += diff % 2;
			}
			
			while(true) {
				day++;
				if(odd == 0 && even > 0) {
					even -=1;
					odd += 2;
				}
				
				if(day % 2 == 1) {
					odd -= 1;
				}
				else {
					even -= 1;
				}
			}
		}

	}

}
