package bj.g1;
/**
@author 김영욱
@since 2023. 08. 08
@see https://www.acmicpc.net/problem/2042
@git
@performance
@category #
@note
N개의 수가 정해진다. 근데 M만큼 구간의 수가 변경한다.
K는 구간의 합을 구하는 횟수다.
둘째 줄부터 N+1까지 N개의 수가 정해진다.
그리고 그 다음 줄부터 세 개의 정수 a,b,c가 주어진다.
a가 1 => b번째 수를 c로 바꾼다.
a가 2 => b번째 수부터 c번째 수까지의 합을 구하여 출력

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G1_2042_구간합구하기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M,K;
	static int[] binaryIndex;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		binaryIndex = new int[N+1];
		
		for(int i=1; i<binaryIndex.length; i++) {
			int num = Integer.parseInt(input.readLine());
			update(i, num);
		}
		
		int a,b,c;
		
		for(int i=0; i<M+K; i++) {
			tokens = new StringTokenizer(input.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());
			
			if(a == 1) {
				update(b,c);
			} else {
				long ans = getSum(c) - getSum(b);
				builder.append(ans).append("\n");
			}
		}
		System.out.println(builder);
		
		
	}
	
	static void update(int idx, int val) {
		while(idx<binaryIndex.length) {
			binaryIndex[idx] += val;
			idx += idx & -idx;
		}
	}
	
	static long getSum(int idx) { 
		long result = 0;
		while(idx>0) {
			result += binaryIndex[idx];
			idx -= idx & -idx;
		}
		return result;
	}

}
