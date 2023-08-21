package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 14
@see https://www.acmicpc.net/problem/10026
@git
@performance
@category #
@note
시간 : 1초(1억번) N:100 N^2이여도 시간 통과 가능
RGB가 들어있는 2차원 배열이 있다.
근데 색약있는 사람은 RG를 같은 색으로 본다.
두 사람이 인식하는 구역의 개수를 구해라.

BFS를 먼저 생각했다.
visited 배열도 만든다(N*N)
N*N을 탐색하면서
*/
public class BJ_G5_10026_적록색약 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static char[][] map;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] vNormal, vRedGreen;
	static int N;
	static int normal, redGreen;
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		
		map = new char[N][N];
		vNormal = new boolean[N][N];
		vRedGreen = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String str = input.readLine();
			map[i] = str.toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!vNormal[i][j]) {
					normalBfs(i, j, vNormal);
					normal++;
				}
				if(!vRedGreen[i][j]) {
					redGreenBfs(i, j, vRedGreen);
					redGreen++;
				}
			}
		}
		
		System.out.println(normal + " " + redGreen);

	}
	private static void redGreenBfs(int startX, int startY, boolean[][] v) {
		Queue<int []> q = new ArrayDeque<>();
		
		q.offer(new int[] {startX,startY});
		v[startX][startY] = true;
		
		char color = map[startX][startY];
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int x = arr[0];
			int y = arr[1];
			for(int d=0; d<4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if(color == 'R' || color == 'G') {
					if(isIn(nx, ny) && !v[nx][ny] ) {
						if(map[nx][ny] == 'R' || map[nx][ny] == 'G') {
							v[nx][ny] = true;
							q.offer(new int[] {nx, ny});
						}
					}
				}
				else if(color == 'B'){
					if(isIn(nx, ny) && !v[nx][ny] && map[nx][ny] == color) {
						v[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}
				}
				
			}
		}
	}
	
	private static void normalBfs(int startX, int startY, boolean[][] v) {
		Queue<int []> q = new ArrayDeque<>();
		
		q.offer(new int[] {startX,startY});
		v[startX][startY] = true;
		
		char color = map[startX][startY];
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int x = arr[0];
			int y = arr[1];
			for(int d=0; d<4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if(isIn(nx, ny) && !v[nx][ny] && map[nx][ny] == color) {
					v[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}

}
