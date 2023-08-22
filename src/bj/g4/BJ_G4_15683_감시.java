package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 22
@see https://www.acmicpc.net/problem/15683
@git
@performance 
@category #
@note
시간 : 1초
N, M : 8이하
N*M 크기의 직사각형
K개의 CCTV가 설치되어 있는데, CCTV는 5가지 종류가 있다.
1 : 한 쪽 방향만 감시할 수 있다.
2 : 2방향을 감시하는데, 감시하는 방향이 서로 반대 방향
3 : 2방향을 감시하는데, 감시하는 방향이 직각 방향
4 : 세 방향
5 : 네 방향

6 : 벽인데 CCTV가 벽을 통과할 수 없다.

CCTV는 회전 시킬 수 있는데 90도 방향으로 해야한다.
CCTV는 CCTV를 통과할 수 있다.
사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.

맵을 돌다가 1,2,3,4,5를 만나면 각 CCTV별로 CCTV를 회전시켜 본 후
각각의 CCTV가 지운 개수를 cnt해준 다음 맵에 적용시키는 방법을 사용해야 겠다.
걍 1,2,3,4,5 다 4방향 개수 구한다음에 각각 맞는 값만 서로 더해서 구하면 더 빠를 거다
다른 CCTV를 신경 쓰지 않고 자기가 자기 자리에서 최선으로 지울 수 있는 방법을 선택하면 최소 개수가 될 거다.
그럼 지운 개수가 같은 방향은 어떻게 해줘야 할까,,??


*/

public class BJ_G4_15683_감시 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M;
	static int[][] map;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0 && map[i][j] != 6) {
					CCTV(i, j, map[i][j]);
				}
			}
		}
		
		
	}
	// CCTV 돌려버리는 메소드
	private static void CCTV(int startX, int startY, int type) {
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		
		int max = 0;
		int directionFlag = 0;
		
		
		for(int d=0; d<4; d++) {
			int x = startX;
			int y = startY;
			int nx = x;
			int ny = y;
			int cnt = 0;
			while(isIn(nx, ny) && map[nx][ny] != 6) {
				nx = x + deltas[d][0];
				ny = y + deltas[d][1];
				cnt++;
				x = nx;
				y = ny;
			}
			if(d == 0) {
				up = cnt;
				if(up > max) {
					directionFlag = 0;
					max = up;
				}
			}
			else if(d == 1) {
				down = cnt;
			}
			else if(d == 2) {
				left = cnt;
			}
			else {
				right = cnt;
			}
		}
		if(type == 1) {
			
		}
	}
	private static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
