package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_17144_미세먼지안녕 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int R, C, T;
	static int[][] map;
	// 우상좌하
	static int[][] rClockDeltas = { { 0, 1 }, { -1, 0 }, { 0, -1 },{ 1, 0 }  };
	// 우하좌상
	static int[][] clockDeltas = { { 0, 1 }, { 1, 0 }, { 0, -1 },{ -1, 0 }  };
	static ArrayList<int[]> aircon = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		map = new int[R][C];
		
		for(int r=0; r<R; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] == -1) {
					// 에어컨 좌표 미리받아
					aircon.add(new int[] { r, c });
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			// 먼지 4방향으로 퍼트리기~
			int memo[][] = new int[R][C];
			spreadDust(memo);
			// 에어컨 돌려버리기~
			refresh(memo);
			// 맵에 적용
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					map[r][c] = memo[r][c];
				}
			}
		}
		int ans = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] > 0) {
					ans += map[r][c];
				}
			}
		}
		System.out.println(ans);
	}
	private static void spreadDust(int[][] memo) {
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] > 0) {
					int cnt=0;
					int value = map[r][c] / 5;
					for(int d=0; d<4; d++) {
						int nx = r + clockDeltas[d][0];
						int ny = c + clockDeltas[d][1];
						
						if(isIn(nx, ny) && map[nx][ny] != -1) {
							cnt+= value;
							memo[nx][ny] += value;
						}
					}
					memo[r][c] += map[r][c] - cnt;
				}
			}
		}
		
	}
	private static void refresh(int[][] memo) {
		Queue<Integer> q = new ArrayDeque<>();
		int d = 0;
		int[] topAircon = aircon.get(0);
		int[] bottomAircon = aircon.get(1);
		memo[topAircon[0]][topAircon[1]] = -1;
		memo[bottomAircon[0]][bottomAircon[1]] = -1;
		// topAirCon 돌리기
		q.offer(memo[topAircon[0]][topAircon[1] + 1]);
		memo[topAircon[0]][topAircon[1] + 1] = 0;
		int x = topAircon[0];
		int y = topAircon[1] + 1;
		while(true) {
			int nx = x + rClockDeltas[d][0];
			int ny = y + rClockDeltas[d][1];
			
			if(nx == topAircon[0] && ny == topAircon[1]) break;
			
			if(!isIn(nx, ny)) {
				d++;
			}
			else {
				q.offer(memo[nx][ny]);
				memo[nx][ny] = q.poll();
				x = nx;
				y = ny;
			}
		}
		// q 남은 값 하나 버려
		q.poll();
		
		d = 0;
		q.offer(memo[bottomAircon[0]][bottomAircon[1] + 1]);
		memo[bottomAircon[0]][bottomAircon[1] + 1] = 0;
		x = bottomAircon[0];
		y = bottomAircon[1] + 1;
		while(true) {
			int nx = x + clockDeltas[d][0];
			int ny = y + clockDeltas[d][1];
			
			if(nx == bottomAircon[0] && ny == bottomAircon[1]) break;
			
			if(!isIn(nx, ny)) {
				d++;
			}
			else {
				q.offer(memo[nx][ny]);
				memo[nx][ny] = q.poll();
				x = nx;
				y = ny;
			}
		}
	}

	private static boolean isIn(int nx, int ny) {
		return 0<=nx && nx<R && 0<=ny && ny<C;
	}

}
