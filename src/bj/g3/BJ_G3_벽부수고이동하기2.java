package bj.g3;

import java.io.*;
import java.util.*;

public class BJ_G3_벽부수고이동하기2 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, K;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    static int[][][] dp;


    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        dp = new int[K + 1][N][M];

        for (int i = 0; i < map.length; i++) {
            String str = input.readLine();
            for (int j = 0; j < map[i].length; j++) {
                int num = str.charAt(j) - '0';
                if (num == 1) map[i][j] = -1; // 벽은 -1
            }
        }
        bfs();
        int answer = Integer.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
            if (dp[k][N - 1][M - 1] == 0) continue;
            else answer = Math.min(answer, dp[k][N - 1][M - 1]);
        }
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);

    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{K, 0, 0});
        for (int k = 0; k <= K; k++) {
            dp[k][0][0] = 1;// 모든 벽 부술 기회 시작부분 1
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int k = now[0];
            int x = now[1];
            int y = now[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if (!isIn(nx, ny)) continue;

                if (map[nx][ny] == 0 && dp[k][nx][ny] == 0) {
                    q.offer(new int[]{k, nx, ny});
                    dp[k][nx][ny] = dp[k][x][y] + 1;
                } else if (k > 0 && map[nx][ny] < 0 && dp[k - 1][nx][ny] == 0) {
                    q.offer(new int[]{k - 1, nx, ny});
                    dp[k - 1][nx][ny] = dp[k][x][y] + 1;
                }
            }
        }
    }

    private static boolean isIn(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < M;
    }

}
