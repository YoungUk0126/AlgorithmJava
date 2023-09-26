package bj.g4;
/**
@author 김영욱
@since 2023. 09. 25
@see https://www.acmicpc.net/problem/14502
@git
@performance
@category #
@note
시간 : 2초
3<=N , M<=8
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다.
바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.
연구소는 크기가 N*M이다.
0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다.
바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다.
벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

연구소의 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

벽의 위치를 조합으로 뽑아서 BFS를 돌리는 방법이 유일한 해법이겠다
64C3로 값을 받아서 칠공주 했던 것 처럼 인덱싱을 해주면 된다
arr[i/M][i%M] 요런 식^^

그리고 벽 세워놓고 BFS돌리면 되겠지

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_14502_연구소 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M, ans;
	static int land;
	static int[][] map;
	static int[] forCombi;
	static ArrayList<int []> virusCord = new ArrayList<>();
	static int deltas[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException
	{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		ans = Integer.MIN_VALUE;
		map = new int[N][M];
		forCombi = new int[N*M];
		for(int i=0; i<forCombi.length; i++) {
			forCombi[i] = i;
		}
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 2) {
					// 바이러스 좌표
					virusCord.add(new int[] {i, j});
				}
				else if(map[i][j] == 0) {
					// 빈 칸 개수
					land++;
				}
			}
		}
		
		makeCombination(0, 0, new int[3]);
		System.out.println(ans);
	}
	
	static void makeCombination(int start, int cnt, int choosed[]) {
		if(cnt == choosed.length) {
			boolean[][] visited = new boolean[N][M];
			for(int i=0; i<choosed.length; i++) {
				// 벽 3개 세워
				visited[choosed[i]/M][choosed[i]%M] = true;
			}
			bfs(visited);
			return;
		}
		
		for(int i=start; i<forCombi.length; i++) {
			choosed[cnt] = i;
			makeCombination(i+1, cnt+1, choosed);
		}
	}
	// 바이러스 퍼트릴 함수
	static void bfs(boolean[][] visited) {
		Queue<int []> q = new ArrayDeque<>();
		
		// 바이러스 좌표들 큐에 담아줌
		for(int i=0; i<virusCord.size(); i++) {
			int[] cord = virusCord.get(i);
			q.offer(cord);
			// 바이러스 위치는 visited 처리 해줌
			visited[cord[0]][cord[1]] = true;
		}
		int cnt = land - 3; // 벽을 세 개 세웠으니까 원래 빈칸에서 3개를 까고 시작해야함
		
		while(!q.isEmpty()) {
			
			if(cnt < ans) return;
			
			int[] cord = q.poll();
			int x = cord[0];
			int y = cord[1];
			
			for(int d=0; d<4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if( isIn(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					cnt--;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		ans = Math.max(cnt, ans);
	}
	
	static boolean isIn(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}

}



