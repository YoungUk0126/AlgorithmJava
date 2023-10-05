package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_S5_10814_나이순정렬 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N;
	static person[] pList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(input.readLine());
		pList = new person[N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			String name = tokens.nextToken();
			
			pList[i] = new person(num, name);
		}
		Arrays.sort(pList);
		
		for(person now: pList) {
			System.out.println(now.toString());
		}
	}
	
	static class person implements Comparable<person>{
		int age;
		String name;
		
		public person () {}
		

		@Override
		public String toString() {
			return age + " " + name;
		}


		public person(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(person o) {
			return Integer.compare(this.age, o.age);
		}
		
	}

}
