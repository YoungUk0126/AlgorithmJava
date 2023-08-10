package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 10
@see https://www.acmicpc.net/problem/17406
@git
@performance
@category #
@note
시간 : 1초
N,M 크기 50
K는 6번

크기 N*M 배열이 있을 때 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값을 의미

N M K => N*M배열 K번 회전한다
N*M 배열 입력
r c s => (r-s, c-s)부터 (r+s, c+s)

저 구역을 시계 방향으로 돌려
이번에 연산 횟수는 6번밖에 안되니까 큐를 이용해서 풀자
왜냐하면 각 연산마다 한번씩 움직이는거니까.

*/
public class BJ_G4_17406_배열돌리기4 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int deltas[][] = {{0,1}, {1,0},{0,-1},{-1,0}};
	static int N,M,K,r,c,s;
	static int arr[][];
	static int ans[][];
	
	static Queue<int[]> queue;
	
	static int min;
	static int x1,y1,x2,y2;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			queue = new ArrayDeque<>();
			tokens = new StringTokenizer(input.readLine());
			r = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());
			s = Integer.parseInt(tokens.nextToken());
			
			x1 = r-s; y1 = c-s;
			x2 = r+s; y2 = c+s;
			
			rotate(x1-1, y1-1, x2, y2);
		}
	}

	private static void rotate(int x1, int y1, int x2, int y2) {
		for(int i=x1; i<x2; i++) {
			for(int j=x2; j<y2; j++) {
				
			}
		}
		
	}

}
