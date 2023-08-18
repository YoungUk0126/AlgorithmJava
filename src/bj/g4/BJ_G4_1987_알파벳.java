package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_1987_알파벳 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int R, C, max;
	static char[][] map;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[] alpa;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = input.readLine();
			map[i] = str.toCharArray();
		}
		alpa = new boolean['Z' - 'A' + 1];
		visited = new boolean[R][C];

		visited[0][0] = true;
		alpa[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(max);
	}

	private static void dfs(int x, int y, int cnt) {
		if(cnt>max) max = cnt;

		for (int d = 0; d < 4; d++) {
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];

			if (isIn(nx, ny) && !visited[nx][ny] && !alpa[map[nx][ny] - 'A']) {
				alpa[map[nx][ny] - 'A'] = true;
				visited[nx][ny] = true;
				dfs(nx, ny, cnt + 1);
				visited[nx][ny] = false;
				alpa[map[nx][ny] - 'A'] = false;
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < R && 0 <= y && y < C;
	}
}
