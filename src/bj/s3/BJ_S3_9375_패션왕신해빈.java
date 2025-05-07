package bj.s3;

import java.io.*;
import java.util.*;

public class BJ_S3_9375_패션왕신해빈 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(input.readLine());
            map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                tokens = new StringTokenizer(input.readLine());
                String name = tokens.nextToken();
                String kind = tokens.nextToken();
                map.put(kind, map.getOrDefault(kind,0) + 1);
            }
        builder.append(calCombinations())
                .append("\n");
        }
        System.out.println(builder);
    }
    private static int calCombinations() {
        // 전체 조합의 초기값 (곱셈을 위해 1로 시작)
        int totalCombinations = 1;
        // 각 카테고리에 대해 (아이템 개수 + 1)을 곱함 (아이템 미선택의 경우 포함)
        for (int count : map.values()) {
            totalCombinations *= (count + 1);
        }
        // 모든 카테고리에서 아무것도 선택하지 않은 경우 1을 빼줌
        return totalCombinations - 1;
    }
}
