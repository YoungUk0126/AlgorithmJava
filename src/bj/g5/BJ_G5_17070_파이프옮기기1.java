package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_17070_파이프옮기기1 {


    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N;
    static int[][] map;
    // 가로 대각선 세로
    static int[][] deltas = { {0,1}, {1,1}, {1,0} };
    static long[][][] dp;
    static boolean[][][] visited;
    
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(input.readLine());
        visited = new boolean[3][N][N];
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        dp = new long[3][N][N];
        // x, y, 현재 파이프 생김새
        dfs(0, 1, 0);
        for(int d=0; d<3; d++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(dp[d][i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
        System.out.println(dp[0][0][1]);
    }
    private static void dfs(int i, int j, int shape) {
        // 기저조건, N,N에 도착했을 때
        if(i == N-1 && j == N-1) {
            dp[shape][i][j] = 1;
            return;
        }
        
        // 결과값은 long ^^
        long result = 0;
        // 현재 방향에 대한 visited를 처리해주기
        visited[shape][i][j] = true;

        for(int d=0; d<3; d++) {
            int nx = i + deltas[d][0];
            int ny = j + deltas[d][1];
            // 가로
            if(shape == 0 && d != 2 && isIn(nx, ny) && checkWall(nx, ny, d)) {
                // 아직 다음 칸을 방문하지 않았다면 방문하기
                if(!visited[d][nx][ny]) {
                    dfs(nx, ny, d);
                }
                // 더해주는 값은 현재 방향이 아닌 다음 방향의 값 (끝까지 갔다가 돌아오기 때문)
                result += dp[d][nx][ny];                    
            }
            // 대각선
            else if(shape == 1 && isIn(nx, ny) && checkWall(nx, ny, d)) {
                if(!visited[d][nx][ny]) {
                    dfs(nx, ny, d);
                }
                result += dp[d][nx][ny];    
            } 
            // 세로
            else if(shape == 2 && d != 0 && isIn(nx, ny) && checkWall(nx, ny, d)) {
                if(!visited[d][nx][ny]) {
                    dfs(nx, ny, d);
                }
                result += dp[d][nx][ny];    
            }
        }
        dp[shape][i][j] += result;

        return;
    }
    

    private static boolean isIn(int nx, int ny) {
        return 0<=nx && nx<N && 0<=ny && ny<N ;
    }

    private static boolean checkWall(int nx, int ny, int d) {
        if(map[nx][ny] != 0) {
            return false;
        }
        
        if(d == 1) {
            if(map[nx-1][ny] != 0 || map[nx][ny-1] != 0) {
                return false;
            }
        }
        return true;
    }
}
