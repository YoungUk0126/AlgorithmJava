package bj.g2;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/3109
@git
@performance
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

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String str = input.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'x') visited[i][j] = true;
			}
		}
		pipe(0, 0);
		System.out.println(ans);
	}
	
	static void pipe(int row, int col) {
		// 가지 조건
		
		// 기저 조건
		if(col == C-1) {
			ans++;
			return;
		}
		// 재귀 조건
		for(int i=row; i<R; i++) {
			// 오른쪽 위
			if(0<=i-1 && !visited[i-1][col+1]) {
				visited[i-1][col+1] = true;
				pipe(i-1, col+1);
			}
			// 오른쪽
			if(!visited[i][col+1]) {
				visited[i][col+1] = true;
				pipe(i, col+1);
			}
			// 오른쪽 아래
			if(i<R && !visited[i+1][col+1]) {
				visited[i+1][col+1] = true;
				pipe(i+1, col+1);
			}
			
		}
		
	}

}
