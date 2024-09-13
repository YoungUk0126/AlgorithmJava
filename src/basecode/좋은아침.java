package basecode;

import java.util.Arrays;

public class 좋은아침 {
	static char[] src = { 'a', 'b', 'c', 'd' };
	
	public static void main(String[] args) {
		// 4P3을 구하는 코드를 작성해보자.
//		makePermutation(0, new char[3], new boolean[src.length]);
//		makeCombination(0, 0, new char[3]);
		makeSubSet1(0, new boolean[4]);
//		makeSubSet2();
	}
	
	// 순열
	static void makePermutation(final int nthChoice, char [] choosed, boolean [] visited) {
		// 기저 조건 (2)
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		// 재귀 처리 (1)
		// 모든 시도를 해본다.
		for(int i=0; i<src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				//nthChoice같은 결정자는 전위,후위 연산자를 써주면 재귀에서 돌아왔을 때도 nthChoice가 증가된 채로 머물게됨.
				makePermutation(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	// 조합
	static void makeCombination(final int nthChoice, final int startIdx, char [] choosed ) {
		// 기저 조건
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		// 재귀 처리
		for(int i=startIdx; i<src.length; i++) {
			choosed[nthChoice] = src[i];
			makeCombination(nthChoice+1, i+1, choosed);
		}
	}
	// 중복 조합
	static void makeDupCombination(final int nthChoice, final int startIdx, char [] choosed ) {
		// 기저 조건
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		// 재귀 처리
		for(int i=startIdx; i<src.length; i++) {
			choosed[nthChoice] = src[i];
			makeDupCombination(nthChoice+1, i, choosed);
		}
	}
	static boolean[] bools = {true, false};
	
	private static void makeSubSet2() {
		for(int i=0; i<1<<src.length; i++) {
			System.out.print("[");
//			System.out.printf("%2d : %4s\n", i, Integer.toBinaryString(i));
			for(int j=0; j<src.length; j++) {
				if((i & (1 << j)) > 0) {
					System.out.print(src[j]);
				}
			}
			System.out.println("]");
		}
		
	}
	
	// 부분집합(중복순열) 2^n개의 결과
	static void makeSubSet1(final int nthCheck, boolean [] status) {
		// 기저 조건 (2)
		if(nthCheck == status.length) {
//			System.out.println(Arrays.toString(status));
			printSubSet(status);
			return;
		}
		status[nthCheck] = true;
		makeSubSet1(nthCheck+1, status);
		status[nthCheck] = false;
		makeSubSet1(nthCheck+1, status);
			
	}
	
	private static void printSubSet(boolean [] status) {
		System.out.print("[");
		for(int i=0; i<status.length; i++) {
			if(status[i]) {
				System.out.print(src[i]);
			}
		}
		System.out.println("]");
	}
	
	
}
