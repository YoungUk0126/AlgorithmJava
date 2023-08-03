package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 1.
@see https://www.acmicpc.net/problem/15649
@git
@performance 176ms
@category #수열
@note
1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
수열은 한 줄에 하나씩, 중복되는 수열 안되고, 공백으로 구분하여 출력

boolean Visited[N+1]
int numbers[M]
int N
int M

재귀함수로 구현
반복문으로 1부터 N까지
Visited[i]가 중복이 아니라면 true넣고 다시 재귀, 재귀할 땐 현재 M값을 +1해준다
만약 재귀 실행할 때 매개변수 M이 전역변수 M과 길이가 같다면 출력하고 그 메서드 리턴

*/
public class BJ_S3_15649_N과M {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	static boolean[] visited;
	static int numbers[];
	static int N;
	static int R;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[N+1];
		numbers = new int[R];
		
		makePermutation(0);
		System.out.println(builder);
	}
	
	static void makePermutation(int M) {
		// 종료 조건
		if(M == R) {
			for(int i=0; i<numbers.length; i++) {
				builder.append(numbers[i]).append(" ");
			}
			builder.append("\n");
			return;
		}
		// 재귀 처리
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[M] = i;
				makePermutation(M+1);
				visited[i] = false;
			}
		}
	}

}
