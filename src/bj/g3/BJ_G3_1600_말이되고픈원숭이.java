package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.08.30
@see https://www.acmicpc.net/problem/1600
@git
@performance
@category #
@note
원숭이가 말이 되기를 원한대
말은 격자판에서 체스의 나이트와 같은 이동방식을 가졌대.
말처럼 움직일 때는 장애물도 다 뛰어 넘음
근데 원숭이는 능력이 부족해서 총 K번만 저렇게 움직이고, 그 외에는 그냥 인접한 칸으로만 움직인대(상하좌우)
격자판 맨 왼쪽 위에서 맨 오른쪽 아래까지 이동한대.
말처럼 움직이나 그냥 움직이나 한 번의 동작으로 친대.
최소한의 동작으로 도착할 수 있는 방법을 찾으래.

벽 뿌수고 이동하기
BFS를 해보니까 안됐다.=> 경로탐색을하다가 벽을뿌수던가 말던가 => 방

입력
정수 K
W H : 가로 세로
H줄에 걸쳐 맵  : 0은 평지, 1은 장애물

*/
public class BJ_G3_1600_말이되고픈원숭이 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int K,W,H,map[][];
	static int min;
	static int[][] deltas = {{1,0}, {0,1}, {-1,0},{0,-1} }; // 하 우 상 좌
	static int[][] horse = { {1, 2}, {2, 1}, {-1,-2}, {-2,-1} ,{-2,1}, {-1,2}, {1,-2}, {2,-1}}; 
	static int[][][] visited; // k, i, j

	public static void main(String[] args) throws IOException{
		min = Integer.MAX_VALUE;
		K = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		map = new int[H][W];
		
		for(int i=0; i<H; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		bfs(0, 0);
		
		
		if(min == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min-1);
		
	}
	static void bfs(int startX, int startY) {
		Queue<int []> q = new ArrayDeque<>();
		visited = new int[K+1][H][W];
		int move = 1;
		
		q.offer(new int[] {K, startX, startY, move});
		visited[K][startX][startY] = move;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int remainK = now[0];
			int x = now[1];
			int y = now[2];
			move = now[3];
			
			if(x == H-1 && y == W-1) {
				for(int k=0; k<=K; k++) {
					if(visited[k][H-1][W-1] != 0) {
						min = Math.min(min, visited[k][H-1][W-1]);
					}
				}
				break;
			}
			// k가 아직 남아있을때
			if(remainK > 0) {
				for(int d=0; d<8; d++) {
					int nx = x + horse[d][0];
					int ny = y + horse[d][1];
					
					if(isIn(nx,ny) && visited[remainK-1][nx][ny] == 0) {
						visited[remainK - 1][nx][ny] = move+1;
						q.offer(new int[] {remainK-1, nx, ny, move+1});
					}
				}
			}
			for (int d = 0; d < 4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (isIn(nx, ny) && visited[remainK][nx][ny] == 0) {
					visited[remainK][nx][ny] = move + 1;
					q.offer(new int[] { remainK, nx, ny, move+1 });
				}
			}
		}
		
		
	}
	private static boolean isIn(int x, int y) {
		return 0<=x && x<H && 0<=y && y<W && map[x][y] == 0;
	}

}
