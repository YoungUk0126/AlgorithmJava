package basecode;

import java.util.Arrays;

public class 좋은아침Debug {
    static char[] src = { 'a', 'b', 'c', 'd' };
	
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		calc(a, b);
		src = "hello java world".toCharArray();
		makePermutation(0, new char[3], new boolean[src.length]);
	}
	
	static void calc(int a, int b) {
		System.out.println(a+b);
		System.out.println(a*b);
		
	}
	static int count=0;
	// 재귀를 이용해서 3개를 고르는 순열을 작성해보자.
	static void makePermutation(int cnt, char[] choosed, boolean[] isSelected) {
		System.out.println("hit count + " + ++count);
		if(cnt == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i=0; i<src.length; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				choosed[cnt] = src[i];
				makePermutation(cnt+1, choosed, isSelected);
				isSelected[i] = false;
			}
		}
	}

}
