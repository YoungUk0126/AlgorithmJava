package bj.g2;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/3109
@git
@performance 364ms
@category #
@note
파이프를 놓을건데 가장 많은 파이프를 놓고 싶어
왼쪽에서 오른쪽으로 잇는거고 x표시된 벽은 설치를 못함
오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있어
경로는 겹쳐선 안됨(visited로 막아줘야해)
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_3109_빵집 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static boolean[][] visited;
	static char[][] map;
	static int R,C;
	
	static int ans;
	static boolean backFlag;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		backFlag = true;
		
		for(int i=0; i<R; i++) {
			String str = input.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'x') visited[i][j] = true;
			}
		}
		for(int i=0; i<R; i++) {
			pipe(i, 0);
		}
		System.out.println(ans);
	}
	
	static void pipe(int row, int col) {
		// 기저 조건
		if(col == C-1) {
			backFlag = false;
			ans++;
			return;
		}
		// 재귀 조건
		// 오른쪽 위
		backFlag = true;
		if (isIn(row - 1, col + 1) && !visited[row - 1][col + 1] && backFlag) {
			visited[row - 1][col + 1] = true;
			pipe(row - 1, col + 1);
		}
		// 오른쪽
		if (isIn(row, col + 1) && !visited[row][col + 1] && backFlag) {
			visited[row][col + 1] = true;
			pipe(row, col + 1);
		}
		// 오른쪽 아래
		if (isIn(row + 1, col + 1) && !visited[row + 1][col + 1] && backFlag) {
			visited[row + 1][col + 1] = true;
			pipe(row + 1, col + 1);
		}
	}
	static boolean isIn(int x, int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}

}
