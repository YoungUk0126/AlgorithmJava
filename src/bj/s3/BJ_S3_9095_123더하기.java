package bj.s3;

import java.io.*;
import java.util.*;

public class BJ_S3_9095_123더하기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N, T;
    static int[] dp = new int[11];

    public static void main(String[] args) throws IOException{
        T = Integer.parseInt(input.readLine());
        for(int i=0; i<3; i++) {
            dp[i] = i;
        }
        dp[3] = 4;

        for(int i=4; i<11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }



        for(int t=0; t<T; t++) {
            int num = Integer.parseInt(input.readLine());
        }

    }
}
