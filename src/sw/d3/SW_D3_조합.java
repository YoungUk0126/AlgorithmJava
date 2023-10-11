package sw.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_D3_조합 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static long div = 1234567891;
	static int N, R, T;

	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(input.readLine());
			
			N = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			
			// combi 공식
			// N! / (N-R)! * R!
			
			long bunja = factorial(N);
			// 분모 (K! * (N-K)!) mod P
			long bunmo = factorial(N - R) * factorial(R);
			
			long ans = bunja * pow(bunmo, div-2) % div;
			
			builder.append("#").append(tc).append(" ").append(ans).append("\n");
			
		}
		System.out.println(builder);

	}
	// 팩토리얼 연산도 값이 너무 커지기 때문에 모듈러 해줌
	// 그래서 위에 분모구할 때 factorial들 뒤에 모듈러 안해줘도 됨
	static long factorial(long N) {
		long fac = 1L;
		
		while(N > 1) {
			fac = (fac * N) % div;
			N--;
		}
		return fac;
		
	}
	
	static long pow(long base, long expo) {
		if(expo == 1) {
			return base % div;
		}
		
		long temp = pow(base, expo / 2);
		
		if(expo % 2 == 1) {
			return ((temp * temp) % div) * base % div;
		}
		return temp * temp % div;
	}

}
