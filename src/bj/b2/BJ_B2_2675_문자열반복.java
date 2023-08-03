package bj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_2675_문자열반복 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for(int tc=0; tc<T; tc++) {
			tokens = new StringTokenizer(input.readLine());
			int R = Integer.parseInt(tokens.nextToken());
			String word = tokens.nextToken();

			for(int i=0; i<word.length(); i++) {
				for(int r=0; r<R; r++) {
					System.out.print(word.charAt(i));
				}
			}
			System.out.println();
		}
	}

}
