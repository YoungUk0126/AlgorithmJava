package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @git
 * @see https://www.acmicpc.net/problem/2573
 * @since 2024-02-22
 * @performance
 * @category #
 * @note 1초 3 <= N,M <= 300 , 90000
 * 지구 온난화로 인해 빙산이 녹고 있대
 * 2차원 배열이 주어지는데 0은 바다고 0 이상의 숫자는 빙산의 높이야
 * 빙산은 1년마다 동서남북 바다에 인접한 곳만큼 해수면이 줄어들어
 * 빙산의 높이는 0보다 낮아질 수 없어
 * <p>
 * 빙산이 두 덩어리 이상으로 나누어지는데 걸리는 해의 숫자를 구해라
 * 전부다 녹았는데도 두 덩이리 이상으로 분리되지 않으면 0을 출력
 * <p>
 * 0,1로 된 빙산의 위
 * 0보다 큰 각 칸마다 BFS로 0만큼 숫자를 깎아
 */

public class BJ_G4_2573_빙산 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {1, 0}};
    static int N, M;
    static int map[][];

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }



    }

    static public void findOceanBfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new int[] {0,0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {

        }


    }

    static public void checkPiece() {}

}
