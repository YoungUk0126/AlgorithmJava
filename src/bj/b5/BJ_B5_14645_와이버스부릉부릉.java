package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B5_14645_와이버스부릉부릉 {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<N; i++) {
            tokens = new StringTokenizer(input.readLine());
        }

        System.out.println("비와이");

    }

}
