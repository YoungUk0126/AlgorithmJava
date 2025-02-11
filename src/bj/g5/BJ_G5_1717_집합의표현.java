package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_1717_집합의표현 {

    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static int[] parents;

    static int N,M;
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parents = new int[N+1];
        make();

        for(int i=0; i<M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int flag = Integer.parseInt(tokens.nextToken());

            if(flag == 0) { // union
                int a = Integer.parseInt(tokens.nextToken());
                int b = Integer.parseInt(tokens.nextToken());
                union(a, b);
            } else if(flag == 1) { // find
                int aRoot = find(Integer.parseInt(tokens.nextToken()));
                int bRoot = find(Integer.parseInt(tokens.nextToken()));

                if(aRoot == bRoot) builder.append("YES").append("\n");
                else builder.append("NO").append("\n");
            }
        }
        System.out.println(builder);
    }

    private static void make() {
        for (int i=1; i<=N; i++) parents[i] = i;
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot; // bRoot의 부모 노드는 aRoot이다
        return true;
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
