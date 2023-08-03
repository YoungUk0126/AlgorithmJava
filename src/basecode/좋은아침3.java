package basecode;

import java.util.Arrays;

public class 좋은아침3 {
	static char[] src = { 'a', 'b', 'c', 'd' };

	public static void main(String[] args) {
		// 4P3을 구하는 코드를 작성해보자.
		int N = 4;
		int R = 3;
//		makePermutation(0, new char[R], new boolean[src.length]);
		makeCombination(0, 0, new char[R]);
	}

	private static void makeCombination(int cnt, int start, char[] comb) {
		if(cnt == comb.length) {
			System.out.println(Arrays.toString(comb));
			return;
		}
		
		
		for(int i=start; i<src.length; i++) {
			comb[cnt] = src[i];
			makeCombination(cnt+1, i+1, comb);
		}
		
	}

	private static void makePermutation(int cnt, char[] perm, boolean[] visited) {
		//종료 조건
		if(cnt == perm.length) {
			System.out.println(Arrays.toString(perm));
			return;
		}
		
		
		
		//재귀 조건
		
		for(int i=0; i<src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				perm[cnt] = src[i];
				makePermutation(cnt+1, perm, visited);
				visited[i] = false;
			}
		}
		
	}
	

}
