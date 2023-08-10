package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 09
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV5LtJYKDzsDFAXc&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=4
@git
@performance
@category #
@note
27개 테스트케이스 합쳐서 2초
N: 10^3
BFS사용 N^2일 경우 10^6으로 가능

N*N형태로 늘어서 있다.
각 방마다 숫자가 있고, 이 숫자는 모두 다르다.
내가 어떤 방에 있다면 상하좌우에 있는 다른 방으로 이동할 수 있다.
이동하려는 방은 N*N범위 내에 있으며 다음 방에 적힌 숫자가 정확히 1 더 커야 한다.
처음 어떤 방에서 출발해야 가장 많은 개수의 방을 이동할 수 있는지 구하는 프로그램을 작성하라.

이동할 수 있는 방의 개수가 최대인 방이 여러시라면 그 중에서 가장 수가 작은 방을 출력.

DFS가 좋을듯 경로타고 끝까지 가야하니까
*/
public class SW_D4_정사각형방 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int T, N;
	static int[][] map;
	static int ansRoom;
	static int cnt;
	static int maxCnt;
	
	static Stack<int []> stack;
	static int deltas[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			ansRoom = Integer.MAX_VALUE;
			cnt = 0;
			maxCnt = 0;
			stack = new Stack<int[]>();
			N = Integer.parseInt(input.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			dfs();
			builder.append("#").append(tc).append(" ").append(ansRoom).append(" ");
			builder.append(maxCnt).append("\n");
		}
		System.out.println(builder);

	}
	
	static void dfs() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				cnt=0;
				stack.push(new int[] {i, j});
				while(!stack.isEmpty()) {
					cnt++;
					int cord[] = stack.pop();
					int x= cord[0];
					int y= cord[1];
					
					//4방 탐색
					for(int d=0; d<4; d++) {
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];
						
						if(isIn(nx,ny) && (map[nx][ny] - map[x][y] == 1)) {
							stack.push(new int[] {nx, ny});
						}
					}
				}
				if(cnt > maxCnt) {
					maxCnt = cnt;
					ansRoom = map[i][j];
				}
				else if(cnt == maxCnt) {
					ansRoom = ansRoom>map[i][j] ? map[i][j] : ansRoom;
				}
			}
		}

	}
	
	static boolean isIn(int i, int j) {
		return 0<=i && i<N && 0<=j && j<N;
	}

}
