package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 29.
@see https://www.acmicpc.net/problem/11726
@git
@performance 
@category #
@note
	2*n 크기의 직사각형을 1*2, 2*1 로 채우는 방법의 수
	피보나치 수열이랑 비슷하다.
	memo[n] = memo[n-1] + memo[n-2] 
 
 
*/
public class BJ_S3_11726_2xn타일링 {
	

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static long memo[],ans;
	static int n;

	public static void main(String[] args) throws IOException{
		n = Integer.parseInt(input.readLine());
		memo = new long[n+1];
		
		Arrays.fill(memo, -1);
		memo[0] = 1;
		memo[1] = 2;
		ans = shape(n-1);
		System.out.println(ans);
		
	}
	
	static long shape(int n) {
		
		if(memo[n] == -1) {
			memo[n] = (shape(n-1) + shape(n-2)) % 10007;
		}
		
		return memo[n];
	}

}
