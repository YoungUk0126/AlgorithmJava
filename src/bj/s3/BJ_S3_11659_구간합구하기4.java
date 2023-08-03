package bj.s3;
/**
@author 김영욱
@since 2023. 8. 2.
@see
@git
@performance 568ms
@category #
@note
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_11659_구간합구하기4 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	static int[] arr;
	static int N;
	static int M;
	static int sum;
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N+1];

		tokens = new StringTokenizer(input.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(tokens.nextToken());
		}
		
		for(int m=0; m<M; m++) {
			sum = 0;
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			
			
			solution(x, y);
			builder.append(sum).append("\n");
		}
		System.out.println(builder);
	}
	
	static void solution(int x, int y) {
		sum = arr[y] - arr[x-1];
	}

}
