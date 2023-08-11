package bj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.08.11
@see https://www.acmicpc.net/problem/16435
@git
@performance 83ms
@category #
@note
시간 : 1초, N:1000, h, 10000
스네이크버드가 과일을 하나 먹으면 길이가 1만큼 늘어난다.
스네이크버드는 자신의 길이 >= 과일 높이들만 먹을 수 있음
처음 길이 L일때 과일들을 먹어 늘릴 수 있는 최대 길이를 구해라.

=========입력==========
N : 과일의 개수  L : 처음 모가지 길이

*/
public class BJ_S5_16435_스네이크버드 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int[] fruits;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int L = Integer.parseInt(tokens.nextToken());
		
		fruits = new int[N];
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<N; i++) {
			fruits[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(fruits);
		
		for(int i=0; i<N; i++) {
			if(fruits[i] > L) continue;
			L++;
		}
		System.out.println(L);
		
	}

}
