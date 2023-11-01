package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_9711_피보나치 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static long T,Q,ans;
	static int P;
	static long memo[];
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		memo = new long[10001];
		memo[1] = 1;
		memo[2] = 1;
		T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			builder.append("Case #").append(tc).append(": ");
			tokens = new StringTokenizer(input.readLine());
			P = Integer.parseInt(tokens.nextToken());
			Q = Long.parseLong(tokens.nextToken());
			long result = fibo(P);
			ans = result % Q;
			builder.append(ans).append("\n");
		}
		System.out.println(builder);
		
	}
	
	static long fibo(int n) {
		if (n <= 1)
			return n;
		else if(memo[n] != 0)
			return memo[n];
		else
			return memo[n] = (fibo(n-2) + fibo(n-1))%Q;
	}

}
