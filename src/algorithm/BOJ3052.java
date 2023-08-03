package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BOJ3052 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static Set<Integer> set = new HashSet<>();
	static int mod=0;
	static int count=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		for(int i=0; i<10; i++) {
			int t = Integer.parseInt(input.readLine());
			mod = t % 42;
			set.add(mod);
		}
		System.out.println(set.size());
	}

}
