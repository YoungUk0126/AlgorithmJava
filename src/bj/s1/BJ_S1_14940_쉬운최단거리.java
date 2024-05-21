package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_14940_쉬운최단거리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N, M;
    static int[][] map;
    static int[][] answer;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        int startX = 0, startY = 0;

        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        answer = new int[N][M];
        visited = new boolean[N][M];
        for (int[] temp : answer) {
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
                else if(map[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }

        solve(startX, startY);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (answer[i][j] == Integer.MAX_VALUE) {
                    if (map[i][j] == 1) {
                        answer[i][j] = -1;
                    } else if (map[i][j] == 0) {
                        answer[i][j] = 0;
                    }
                }
                builder.append(answer[i][j]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    static private void solve(int startX, int startY) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        answer[startX][startY] = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + deltas[i][0];
                int ny = y + deltas[i][1];
                /*
                 * 다음 위치에 있는 값이 현재 위치 + 1보다 적다면 다음 위치에 현재 위치에 있는 값이랑 같이 더해
                 * */
                if (isIn(nx, ny) && map[nx][ny] == 1 && ((answer[x][y] + map[nx][ny]) < answer[nx][ny]) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    answer[nx][ny] = answer[x][y] + map[nx][ny];
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static private boolean isIn(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < M;
    }
}
