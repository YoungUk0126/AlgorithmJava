package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_2178_미로탐색 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M, cnt;
	static int[][] map;
	static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}}; //상하좌우

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M+1];// 1 base index
		
		for(int i=1; i<=N; i++) {
			String[] strArr = input.readLine().split("");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(strArr[j-1]);
			}
		}
		bfs();
		System.out.println(map[N][M]);
	}
	
	private static void bfs() {
		// 준비물 챙겨
		Queue<int[]> q = new ArrayDeque<>();
		boolean v[][] = new boolean[N+1][M+1];
		
		q.offer(new int[] {1,1});
		v[1][1] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			int x = temp[0];
			int y = temp[1];
			
			for(int d=0; d<4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if(isIn(nx, ny) && map[nx][ny] > 0 && !v[nx][ny]){
					v[nx][ny] = true;
					q.offer(new int[] {nx, ny});
					map[nx][ny] = map[x][y] + 1;
				}
			}
		}
		
	}
	private static boolean isIn(int x, int y) {
		return 0<x && x<=N && 0<y && y<=M;
	}

}
