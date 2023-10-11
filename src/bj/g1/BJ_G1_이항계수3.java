package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G1_이항계수3 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static long P = 1000000007;
	static long N, K;
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Long.parseLong(tokens.nextToken());
		K = Long.parseLong(tokens.nextToken());
		
		long bunja = factorial(N);

		// 분모 (K! * (N-K)!) mod P
		long bunmo = factorial(N - K) * factorial(K) % P;
		
		// factorial(N) * ((factorial(K) * factorial(N))
		System.out.println(bunja * pow(bunmo, P - 2) % P);
	}
	
	static long factorial(long N) {
		long fac = 1L;
		
		while(N > 1) {
			fac = (fac * N) % P;
			N--;
		}
		return fac;
	}
	
	static long pow(long base, long expo) {
		if(expo == 1) {
			return base % P;
		}
		
		long temp = pow(base, expo / 2);
		
		if(expo % 2 == 1) {
			return (temp * temp % P) * base % P;
		}
		return temp * temp % P;
	}
}
