package basecode;

import java.util.Arrays;

public class 좋은아침5 {

	static char[] src = { 'a', 'b', 'c', 'd' };

    public static void main(String[] args) {
        // 4P3을 구하는 코드를 작성해보자.
//         makePermutation(0, new char[3], new boolean[src.length]);

        // 4C3을 구하는 코드를 작성해보자.
//        makeCombination(0, 0, new char[2]);
        // 부분집합을 구해보자.
        makeSubset1(0, new boolean[4]);
//        makeSubset2();
    }
    
    static void makePermutation(int cnt, char[] choosed, boolean[] visited) {
    	//기저 조건
    	if(cnt == choosed.length) {
    		System.out.println(Arrays.toString(choosed));
    		return;
    	}
    	// 재귀호출
    	
    	for(int i=0; i<src.length; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			choosed[cnt] = src[i];
    			makePermutation(cnt + 1, choosed, visited);
    			visited[i] = false;
    		}
    	}
    }
    
    static void makeCombination(int cnt, int start, char[] choosed) {
    	// 기저 조건
    	if(cnt == choosed.length) {
    		System.out.println(Arrays.toString(choosed));
    		return;
    	}
    	// 재귀 호출
    	for(int i=start; i<src.length; i++) {
    		choosed[cnt] = src[i];
    		makeCombination(cnt +1, i+1, choosed);
    		
    	}
    }
    
    static void makeSubset1(int cnt, boolean[] status) {
    	if(cnt == status.length) {
    		printSubset(status);
    		return;
    	}
    	status[cnt] = true;
    	makeSubset1(cnt + 1, status);
    	status[cnt] = false;
    	makeSubset1(cnt + 1, status);
    }
    
    static void printSubset(boolean[] status) {
    	System.out.print("[");
    	for(int i=0; i<src.length; i++) {
    		if(status[i]) {
    			System.out.print(src[i]);
    		} else {
    			System.out.print("x");
    		}
    	}
    	System.out.println("]");
    }

}
