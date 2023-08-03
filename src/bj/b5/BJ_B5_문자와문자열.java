package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_B5_문자와문자열 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String word = input.readLine();
		int N = Integer.parseInt(input.readLine());
		System.out.println(word.charAt(N-1));
	}

}
