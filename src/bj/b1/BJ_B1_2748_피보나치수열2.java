package bj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class BJ_B1_2748_피보나치수열2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(input.readLine());
        if(N == 0) System.out.println(0);
        else {
            long[] arr = new long[N+1];
            arr[0] = 0;
            arr[1] = 1;

            for(int i=2; i<=N; i++) {
                arr[i] = arr[i-2] + arr[i-1];
            }

            System.out.println(arr[N]);
        }


    }
}
