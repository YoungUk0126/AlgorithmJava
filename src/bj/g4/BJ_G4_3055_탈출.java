package bj.g4;

/**
@author 본인이름
@since 2023. 9. 27.
@see https://www.acmicpc.net/problem/3055
@git
@youtube
@performance
@category #
@note 
티떱숲의 지도는 R행 C열
비어있는곳 '.'
물이차있는곳 '*'
돌은 'X'
비버의 굴 'D'
고슴도치의 위치는 'S'

고슴도치는 상하좌우로 이동할 수 있다.
물도 매 분마다 비어있는 칸으로 확장한다.

고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.

고슴도치가 안전하게 비버의 굴로 이동하는 최소 시간을 구하여라.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_3055_탈출 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int R,C;
	static int ans;
	static char[][] map;
	
	static Queue<int[]> bieberQ;
	static Queue<int[]> waterQ;
	
	static int[] start;
	static ArrayList<int []> water;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		water = new ArrayList<>();
		start = new int[2];
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String temp = input.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				}
				else if(map[i][j] == '*') {
					water.add(new int[] {i, j});
				}
			}
		}
		bfs();
	}

	private static void bfs() {
		bieberQ = new ArrayDeque<>();
		waterQ = new ArrayDeque<>();
		
		boolean visited[][] = new boolean[R][C];
		
		bieberQ.offer(start);
		visited[start[0]][start[1]] = true;
		for(int[] item: water) {
			waterQ.offer(item);
			visited[item[0]][item[1]] = true;
		}
		
		while(!bieberQ.isEmpty()) {
			ans++;
			// 물이 먼저 흐르고 비버가 이동해야함( 그 다음 위치로 어차피 비버가 못감 )
			// 이렇게 해줘야 물을 먼저 다 퍼트리고 비버가 움질일 수 있음
			int wsize = waterQ.size();
			for(int i = 0 ; i < wsize ; i++) {
				int[] waterNow = waterQ.poll();
				
				for(int d = 0; d<4; d++) {
					int nx = waterNow[0] + deltas[d][0];
					int ny = waterNow[1] + deltas[d][1];
					
					if(isIn(nx,ny) && map[nx][ny] != 'X' &&
							map[nx][ny] != 'D' && !visited[nx][ny]) {
						visited[nx][ny] = true;
						map[nx][ny] = '*';
						waterQ.offer(new int[] {nx, ny});
					}
				}
				
			}
			
			int bsize = bieberQ.size();
			for(int i = 0 ; i < bsize ; i++) {
				int[] bieberNow = bieberQ.poll();
				
				for(int d = 0; d<4; d++) {
					int nx = bieberNow[0] + deltas[d][0];
					int ny = bieberNow[1] + deltas[d][1];
					
					if(isIn(nx,ny) && map[nx][ny] != '*' && map[nx][ny] != 'X' && 
							!visited[nx][ny]) {
						if(map[nx][ny] == 'D') {
							System.out.println(ans);
							return;
						}
						else {
							visited[nx][ny] = true;
							map[nx][ny] = 'S';
							bieberQ.offer(new int[] {nx, ny});
						}
					}
					
				}
			}
		}
		System.out.println("KAKTUS");
	}
	
	static boolean isIn(int x, int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}

}
