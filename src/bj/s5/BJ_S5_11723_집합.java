package bj.s5;
/*비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.

add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
all: S를 {1, 2, ..., 20} 으로 바꾼다.
empty: S를 공집합으로 바꾼다.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S5_11723_집합 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int M;
    static boolean[] d;

    public static void main(String[] args) throws IOException {
        M = Integer.parseInt(input.readLine());
        d = new boolean[21];
        for (int m = 0; m < M; m++) {
            tokens = new StringTokenizer(input.readLine());
            String order = tokens.nextToken();
            if (order.equals("all")) all();
            else if (order.equals("empty")) empty();
            else {
                int n = Integer.parseInt(tokens.nextToken());
                if (order.equals("add")) add(n);
                else if (order.equals("remove")) remove(n);
                else if (order.equals("check")) check(n);
                else if (order.equals("toggle")) toggle(n);
            }
        }
        System.out.println(builder);
    }

    private static void add(int x) {
        d[x] = true;
    }

    private static void remove(int x) {
        d[x] = false;
    }

    private static void check(int x) {
        if(d[x]) builder.append("1\n");
        else builder.append("0\n");
    }

    private static void toggle(int x) {
        d[x] = !d[x];
    }

    private static void all() {
        Arrays.fill(d, true);
    }

    private static void empty() {
        Arrays.fill(d, false);
    }
}
