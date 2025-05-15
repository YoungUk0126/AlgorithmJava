package bj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections; // 순서 뒤집기 위해 추가
import java.util.StringTokenizer;



public class BJ_G2_12015_가장긴증가하는부분수열2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N;
    static int[] lines; // 원본 수열
    static ArrayList<Integer> dp;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(input.readLine());
        tokens = new StringTokenizer(input.readLine());
        lines = new int[N];
        for(int i=0; i<N; i++) {
            lines[i] = Integer.parseInt(tokens.nextToken());
        }


    }
}
