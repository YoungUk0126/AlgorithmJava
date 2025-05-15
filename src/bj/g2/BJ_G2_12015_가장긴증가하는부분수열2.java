package bj.g2;

import java.io.*;
import java.util.*;

/*
반례
25
10 12 14 11 15 22 1 21 8 24 8 20 3 2 5 6 17 7 16 25 19 23 18 13 3

이분 탐색 결과 : 1 2 3 7 13 18 25

본래 답 : 1 2 5 6 7 16 19 23
*
* */


public class BJ_G2_12015_가장긴증가하는부분수열2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N;
    static int[] lines; // 원본 수열
    static ArrayList<Integer> dp;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(input.readLine());
        lines = new int[N];
        dp = new ArrayList<>();

        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            lines[i] = Integer.parseInt(tokens.nextToken());
        }
        for (int line : lines) {
            BinarySearch(line);
        }
        System.out.println(dp.size());


    }

    private static void BinarySearch(int line) {
        if (dp.isEmpty()) dp.add(line);
        if (dp.get(0) > line) dp.set(0, line);
        if (dp.get(dp.size() - 1) < line) dp.add(line);
        else {
            int left = 0;
            int right = dp.size() - 1;
            int mid = 0;
            while (left < right) {
                mid = (left + right) / 2;
                if (dp.get(mid) < line) {// 중간 값보다 크다면 왼쪽 영역을 위로 댕겨와야해
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            dp.set(left, line);
        }
    }
}
