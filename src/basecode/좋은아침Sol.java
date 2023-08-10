package basecode;

import java.util.Arrays;

public class 좋은아침Sol {
    static char[] src = { 'a', 'b', 'c', 'd' };

    public static void main(String[] args) {
        // 4P3을 구하는 코드를 작성해보자.
        // makePermutation(0, new char[3], new boolean[src.length]);

        // 4C3을 구하는 코드를 작성해보자.
//        makeCombination(0, 0, new char[2]);
        // 부분집합을 구해보자.
        makeSubset1(0, new boolean[4]);
//        makeSubset2();
    }

    static void makePermutation(final int nthChoice, char[] choosed, boolean[] visited) {
        // 기저 조건
        if (nthChoice == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }
        // 재귀 처리
        for (int i = 0; i < src.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                choosed[nthChoice] = src[i];
                makePermutation(nthChoice + 1, choosed, visited);
                visited[i] = false;
            }
        }
    }

    static void makeCombination(final int nth, final int startIndex, char[] choosed) {
        // 기저 조건
        if (nth == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }

        for (int i = startIndex; i < src.length; i++) {
            choosed[nth] = src[i];
            makeCombination(nth + 1, i + 1, choosed);
        }
    }

    private static void makeSubset2() {
        for (int i = 0; i < 1 << src.length; i++) {
            System.out.print("[");
            //System.out.printf("%2d : %4s\n", i, Integer.toBinaryString(i));
            for (int j = 0; j < src.length; j++) {
                if ((i & (1 << j)) > 0) {
                    System.out.print(src[j]);
                }
            }
            System.out.println("]");
        }
    }

    static void makeSubset1(final int nthCheck, boolean[] status) {
        // 기저 조건
        if (nthCheck == status.length) {
            //System.out.println(Arrays.toString(status));
            printSubSet(status);
            return;
        }
        // 재귀 처리
        status[nthCheck] = true;
        makeSubset1(nthCheck + 1, status);
        status[nthCheck] = false;
        makeSubset1(nthCheck + 1, status);
    }

    private static void printSubSet(boolean[] status) {
        System.out.print("[");
        for (int i = 0; i < status.length; i++) {
            if (status[i]) {
                System.out.print(src[i]);
            }
        }
        System.out.println("]");
    }

}
