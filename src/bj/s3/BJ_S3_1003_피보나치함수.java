package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_S3_1003_피보나치함수 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static int N;
    static Integer[][] memo = new Integer[41][2];

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(input.readLine());
        memo[0][0] = 1;// n이 0일때 0의 개수
        memo[0][1] = 0;// n이 1일때 1의 개수
        memo[1][0] = 0;
        memo[1][1] = 1;
        for(int t=0; t<T; t++){
            N = Integer.parseInt(input.readLine());
            fibo(N);
            builder.append(memo[N][0]).append(" ").append(memo[N][1]).append("\n");
        }
        System.out.println(builder);
    }

    private static Integer[] fibo(int n) {
        if(memo[n][0] == null || memo[n][1] == null) {
            memo[n][0] = fibo(n-1)[0] + fibo(n-2)[0];
            memo[n][1] = fibo(n-1)[1] + fibo(n-2)[1];
        }
        return memo[n];
    }
}
