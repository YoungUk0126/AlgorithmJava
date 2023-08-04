package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_S5_10814_나이순정렬 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	static HashMap<Integer, String> map;
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		map = new HashMap<>();
		N = Integer.parseInt(input.readLine());
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			String name = tokens.nextToken();
			
			map.put(num, name);
		}
		
//		Collections.sort(map);
	}

}
