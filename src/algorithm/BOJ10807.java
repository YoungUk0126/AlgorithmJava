package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10807 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int V;
	static int[] arr;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		V = Integer.parseInt(input.readLine());
		for(int t: arr) {
			if(t == V) {
				ans += 1;
			}
		}
		System.out.println(ans);
	}

}
