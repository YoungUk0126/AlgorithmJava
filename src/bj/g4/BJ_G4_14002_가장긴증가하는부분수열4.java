package bj.g4;

import java.io.*;
import java.util.*;

/*
 * 반례 :
 * 4
 * 1 20 30 7
 *
 * 정답 : 3
 *        1 20 30
 * 오답 : 3
 *          1 7 30
 * */
public class BJ_G4_14002_가장긴증가하는부분수열4 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N;
    static int[] lines; // 원본 수열
    static int[] dp;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(input.readLine());
        tokens = new StringTokenizer(input.readLine());
        lines = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            lines[i] = Integer.parseInt(tokens.nextToken());
        }
        int longestLen = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (lines[i] > lines[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    longestLen = Math.max(dp[i], longestLen);
                }
            }
        }
        builder.append(longestLen).append("\n");

        Stack<Integer> seq = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            if (dp[i] == longestLen) {
                seq.push(lines[i]);
                longestLen--;
            }
        }

        while (!seq.isEmpty()) {
            builder.append(seq.pop()).append(" ");
        }
        System.out.println(builder);
    }
}
