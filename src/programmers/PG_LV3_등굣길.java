package programmers;

import java.util.*;
import java.io.*;

public class PG_LV3_등굣길 {

    static int[][] deltas = {{0, 1}, {1, 0}};
    static int mod = 1000000007;

    public static void main(String[] args) {
        int[][] temp = {{2, 2}};
        int n = 3;
        int m = 4;

        int answer = solution(n, m, temp);

        System.out.println(answer);

    }

    private static int solution(int N, int M, int[][] puddles) {
        int[][] map = new int[N + 1][M + 1];

        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];

            map[x][y] = -1;
        }

        return dp(map, N, M);
    }
    private static int dp(int[][] map, int N, int M) {
        map[1][1] = 1;

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(map[i][j] == 0) { // 미방문지라면
                    if(i != 1 && map[i-1][j] != -1) {
                        map[i][j] += map[i-1][j];
                    }
                    if(j != 1 && map[i][j-1] != -1) {
                        map[i][j] += map[i][j-1];
                    }
                    map[i][j] %= mod;
                }
            }
        }
        return map[N][M];
    }
}
