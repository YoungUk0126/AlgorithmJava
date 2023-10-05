package bj.g4;
/**
 * @author 김영욱
 * @since 2023. 10. 05
 * @see https://www.acmicpc.net/problem/4485
 * @performance 
 * @category 
 * @note
 * 
 * 루피는 돈인데 검은 루피는 오히려 돈이 깎임
 * N * N크기의 동굴이고 시작은 0,0
 * N-1, N-1 까지 가는데 동굴의 칸마다 검은 루피가 있음
 * 링크가 목적지까지 가는데 잃는 최소한의 금액을 구해라
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_4485_녹색옷입은애가젤다지 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] map;
	static int N = -1;
	static int[][] dp;
	static int tc = 1;
	public static void main(String[] args) throws IOException {
		
		while(true) {
			N = Integer.parseInt(input.readLine());
			if(N == 0) break;
			
			builder.append("Problem ").append(tc).append(": ");
			tc++;
			
			map = new int[N][N];
			dp = new int[N][N];
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			
			bfs();
			builder.append(dp[N-1][N-1]).append("\n");
		}
		System.out.println(builder);

	}
	private static void bfs() {
		Queue<int []> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		dp[0][0] = map[0][0];
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int d=0; d<4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if(isIn(nx, ny) && dp[nx][ny] > (dp[x][y] + map[nx][ny])) {
					q.offer(new int[] {nx, ny});
					dp[nx][ny] = dp[x][y] + map[nx][ny];
				}
			}
		}
		
	}
	private static boolean isIn(int nx, int ny) {
		return 0<=nx && nx < N && 0<=ny && ny<N;
	}

}
