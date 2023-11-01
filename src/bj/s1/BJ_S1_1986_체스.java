package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_1986_체스 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N,M;					// 시계방향
	static int[][] deltasQueen = {{-1,0},{-1, 1}, {0, 1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
	static int[][] deltasHorse = {{1, 2}, {2, 1}, {-1,-2}, {-2,-1} ,{-2,1}, {-1,2}, {1,-2}, {2,-1}};
	static char[][] map;
	static boolean[][] visited;
	static int queenCnt, horseCnt, pawnCnt, ans;
	static int[][] queens, horses;

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				map[i][j] = '0';
			}
		}
		
		// 퀸받기
		tokens = new StringTokenizer(input.readLine());
		queenCnt = Integer.parseInt(tokens.nextToken());
		queens = new int[queenCnt][2];
		for(int i=0; i<queenCnt; i++) {
			queens[i][0] = Integer.parseInt(tokens.nextToken());
			queens[i][1] = Integer.parseInt(tokens.nextToken());
			map[queens[i][0]][queens[i][1]] = 'q';
			visited[queens[i][0]][queens[i][1]] = true;
		}
		tokens = new StringTokenizer(input.readLine());
		horseCnt = Integer.parseInt(tokens.nextToken());
		horses = new int[horseCnt][2];
		for(int i=0; i<horseCnt; i++) {
			horses[i][0] = Integer.parseInt(tokens.nextToken());
			horses[i][1] = Integer.parseInt(tokens.nextToken());
			map[horses[i][0]][horses[i][1]] = 'h';
			visited[horses[i][0]][horses[i][1]] = true;
		}
		tokens = new StringTokenizer(input.readLine());
		pawnCnt = Integer.parseInt(tokens.nextToken());
		for(int i=0; i<pawnCnt; i++) {
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			map[x][y] = 'p';
			visited[x][y] = true;
		}
		for(int i=0; i<queenCnt; i++) {
			draw(queens[i][0], queens[i][1], deltasQueen);
		}
		for(int i=0; i<horseCnt; i++) {
			for(int j=0; j<deltasHorse.length; j++) {
				int nx = horses[i][0] + deltasHorse[j][0];
				int ny = horses[i][1] + deltasHorse[j][1];
				if(isIn(nx, ny) && map[nx][ny] == '0') {
					visited[nx][ny] = true;
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!visited[i][j]) ans++;
			}
		}
		System.out.println(ans);
	}
	
	static void draw(int startX, int startY, int[][] deltas) {
		int x = startX;
		int y = startY;
		visited[x][y] = true;
		
		for(int i=0; i<deltas.length; i++) {
			int nx = x + deltas[i][0];
			int ny = y + deltas[i][1];
			while(isIn(nx,ny) && map[nx][ny] == '0') {
				visited[nx][ny] = true;
				nx = nx + deltas[i][0];
				ny = ny + deltas[i][1];
			}
		}
		
	}
	
	static boolean isIn(int nx, int ny) {
		return 0<nx && nx<=N && 0<ny && ny<=M;
	}

}
