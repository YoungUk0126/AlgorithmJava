package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 @author 김영욱
 @since 2025.01.14
 @see https://www.acmicpc.net/problem/4991
 @git
 @performance
 @category #
 @note
 시간 1초


 */

public class BJ_G1_4991_로봇청소기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int deltas[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        N = 1;
        M = 1;
        while(true) {
            tokens = new StringTokenizer(input.readLine());
            M = Integer.parseInt(tokens.nextToken());
            N = Integer.parseInt(tokens.nextToken());
            map = new char[N][M];
            if(N == 0 && M == 0) break;
            for(int i=0; i<N; i++) {
                String temp = input.readLine();
                map[i] = temp.toCharArray();
            }
            for(int i=0; i<N; i++) {
                for(char word: map[i]) System.out.print(word + " ");
                System.out.println();
            }
        }
    }
}
