package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//import java.lang.Math;

/**
@author 김영욱
@since 2023. 09 12
@see https://www.acmicpc.net/problem/2473
@git
@performance
@category #
@note
음수부터 양수까지 용액의  특성값이 주어진다.
이 특성값들이 세가지 합이 0과 가장 가까운 값을 출력해라.

두 용액처럼 정렬 후 투포인터를 쓰는 것은 확실한데
용액을 한 가지 더 섞어야 하는 문제다.
양 옆에 
*/
public class BJ_G3_2473_세용액 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, left, right, arr[], min;
	

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		arr = new int[N];
		left = 0;
		right = 1;
		min = Integer.MAX_VALUE;
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(arr);
		int sum = 0;
		while( left <= right ) {
			sum = arr[left] + arr[right];
			if(min > Math.abs(sum)) {
				
			}
			if(sum > 0) {
				right--;
			}
			
		}
	}

}
