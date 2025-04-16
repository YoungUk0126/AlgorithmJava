package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
i = 0
j = 0

i = 1
j = 0
30이 10보다 안작아

i = 2
j = 0
10이 10보다 안작아

i = 2
j = 1
10이 30보다 작아
dp[2]보다 dp[1] + 1가 더 커
dp[2] = 2
dp
1, 1, 2, 1, 1, 1

i = 3
j = 0
20이 10보다 안작아

i = 3
j = 1
20이 30보다 작아
dp[3]보다 dp[1] +1이 더 커
dp[3] = 2
dp
1, 1, 2, 2, 1, 1

i = 3
j = 2
20이 10보다 안작아

i = 4
j = 0
20이 10보다 안작아

i = 4
j = 1
20이 30보다 작아
dp[4]보다 dp[1]+1이 더 커
dp[4] = 2
dp
1, 1, 2, 2, 2, 1

i = 4
j = 2, 3
다 20이 더 커

i = 5
j = 1
10이 30보다 작아
dp[5]보다 dp[1] + 1이 더 커
dp[5] = 2
dp
1, 1, 2, 2, 2, 2

i = 5
j = 2
10으로 둘다 같아

i = 5
j = 3
10이 20보다 작아
dp[5]보다 dp[3] + 1이 더 커
dp[5] = 3
dp
1, 1, 2, 2, 2, 3

i = 5
j = 4
10이 20보다 작아
dp[5]랑 dp[4] + 1은 같아
dp[5] = 3 그대로

답 3


*/

public class BJ_S2_11722_가장긴감소하는부분수열 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N;
    static int[] seq, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        seq = new int[N];
        dp = new int[N];

        tokens = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(tokens.nextToken());
            dp[i] = 1;
        }
        int ans = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (seq[i] < seq[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        System.out.println(ans);
    }
}
