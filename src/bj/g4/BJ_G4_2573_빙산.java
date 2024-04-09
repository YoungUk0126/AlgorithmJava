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
 *
 * 빙산이 두 덩어리 이상으로 나누어지는데 걸리는 해의 숫자를 구해라
 * 전부다 녹았는데도 두 덩이리 이상으로 분리되지 않으면 0을 출력
 *
 * 0,1로 된 빙산의 위
 * 0보다 큰 각 칸마다 BFS로 0만큼 숫자를 깎아
 *
 * 처음에 입력을 받아 N, M
 * 그리고 N*M 크기의 Map
 *
 * 반례 있음
 * (테두리 0 생략)
 * 0 0 0 0 0 0 0
 * 0 10 5 5 5 10 0
 * 0 5 10 1 10 5 0
 * 0 10 5 5 5 10 0
 * 0 0 0 0 0 0 0
 * => 가로 길이(0포함): 5 + 2, 빙산 있는 칸 수: 3 * 5, 답: 1 + 5
 * 10 5 5 5 5 5 10
 * 5 10 5 5 5 10 5
 * 5 5 10 1 10 5 5
 * 5 10 5 5 5 10 5
 * 10 5 5 5 5 5 10
 * => 가로 길이: 5 + 2 * 2, 빙산 있는 칸 수: 5 * 7, 답: 1 + 5 * 2
 * ...
 * => 가로 길이: 5 + 2 * 48, 빙산 있는 칸 수: 97 * 99, 답: 1 + 5 * 48
 *
 * 아니 왜 미리 빙산이 있는 위치를 N * M만큼 구해서 미리 큐에 담아 놓는게
 * 시간이 더 절약되는지 잘 모르겠다.
 */

public class BJ_G4_2573_빙산 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N, M, year;
    static int map[][];
    static int ocean[][];

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        ocean = new int[N][M];
        int x = 0;
        int y = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if(map[i][j] > 0) {
                    x = i;
                    y = j;
                }
            }
        }
        int year = 0;
        while(true) {
            int bigIceCnt = checkPiece();
            if(bigIceCnt >= 2) {
                break;
            } else if(bigIceCnt == 0) {
                year = 0;
                break;
            }

            findOcean(x, y);
            year++;
        }

        System.out.println(year);

    }
    static class IceBlock{
        int x;
        int y;

        public IceBlock(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

//    모든 블록이 2개로 나눠지지 않고 끝까지 전부 다 녹는다면 0을 출력하는 로직 필요
    static public void findOcean(int startX, int startY) {
        Queue<IceBlock> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int oceanCnt;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != 0) {
                    q.offer(new IceBlock(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            IceBlock now = q.poll();
            int x = now.x;
            int y = now.y;

            oceanCnt = 0;

            for(int d=0; d<4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(!isOk(nx, ny)) continue;
                else if(!visited[nx][ny] && map[nx][ny] == 0){
                    oceanCnt++;
                }
            }
            if(oceanCnt >= map[x][y]) map[x][y] = 0;
            else map[x][y] -= oceanCnt;
        }
    }

    static public int checkPiece() {
        boolean[][] visited = new boolean[N][M];
        int bigIceCnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j] && map[i][j] > 0) {
                    dfs(i,j,visited);
                    bigIceCnt++;
                }
            }
        }
        return bigIceCnt;
    }


    static public void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for(int d=0; d<4; d++) {
            int nx = x + deltas[d][0];
            int ny = y + deltas[d][1];
//            기저 조건
            if(isOk(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
                dfs(nx, ny, visited);
            }
        }
    }

    static public boolean isOk(int x, int y){
        return 0 <= x && x < N && 0<= y && y < M;
    }


}
