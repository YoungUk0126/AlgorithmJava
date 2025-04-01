package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1261_알고스팟 {

    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String temp = input.readLine();
            char[] tempArr = temp.toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tempArr[j] - '0';
            }
        }


        System.out.println(bfs());

    }

    private static int bfs() {
        PriorityQueue<AlgoTeam> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];

        pq.offer(new AlgoTeam(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            AlgoTeam now = pq.poll();
            int x = now.x;
            int y = now.y;
            if (x == N - 1 && y == M - 1) return now.cnt;


            for (int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];
                if(isIn(nx, ny) && !visited[nx][ny]) {
                    if (map[nx][ny] == 1) pq.offer(new AlgoTeam(nx, ny, now.cnt + 1));
                    else pq.offer(new AlgoTeam(nx, ny, now.cnt));
                    visited[nx][ny] = true;
                }
            }
        }
        return 0;
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static class AlgoTeam implements Comparable<AlgoTeam> {
        int x;
        int y;
        int cnt;

        public AlgoTeam(int x, int y, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(AlgoTeam o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }
}
