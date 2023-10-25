package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_2805_나무자르기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,M,trees[];

	public static void main(String[] args) throws IOException{
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		trees = new int[N];
		
		tokens = new StringTokenizer(input.readLine());
		int right=0;
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(tokens.nextToken());
			if(trees[i] > right) right = trees[i];
		}
		int left = 0; 
		
		while(left <= right) {
			int mid = (left + right) / 2;
			// long도 까먹지 말자
			long sum = 0;
			
			// 나무를 다 짤라봐
			for(int tree: trees) {
				if(tree - mid > 0) {
					sum += (tree-mid);
				}
			}
			
			if(sum >= M) {
				// 진짜 +1 까먹지 말자 무한루프 도니까
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		System.out.println(right);
		
	}

}
