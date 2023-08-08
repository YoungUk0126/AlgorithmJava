package basecode;

import java.util.Arrays;

public class 좋은아침4 {
	static char[] src = { 'a', 'b', 'c', 'd' };
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//4n3을 뽑아보자.
		
//		makePermutation(0, new char[3], new boolean[4]);
		makeCombination(0, 0, new char[3]);

	}
	
	static void makePermutation(int cnt, char[] choosed, boolean[] isVisited) {
		// 기저 조건
		if(cnt == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		// 재귀 조건
		
		for(int i=0; i<src.length; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				choosed[cnt] = src[i];
				makePermutation(cnt + 1, choosed, isVisited);
				isVisited[i] = false;
			}
		}
	}
	
	static void makeCombination(int cnt, int startIdx, char[] choosed) {
		
		if(cnt == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i=startIdx; i<src.length; i++) {
			choosed[cnt] = src[i];
			makeCombination(cnt + 1, i + 1, choosed);
		}
	}

}
