package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_2420_사파리월드 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        long N = Integer.parseInt(tokens.nextToken());
        long M = Integer.parseInt(tokens.nextToken());

        System.out.println(Math.abs(N-M));



    }
}
