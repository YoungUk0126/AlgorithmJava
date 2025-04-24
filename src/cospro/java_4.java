package cospro;

import java.util.Arrays;

public class java_4 {

    static int N = 5924;
    static int M = 3904;

    public static void main(String[] args) {
        System.out.println(maxNum(N) - minNum(N));
        System.out.println(maxNum(M) - minNum(M));
    }

    private static int maxNum(int val) {
        int[] numbers = new int[4];

        for (int i = 0; i < 4; i++) {
            numbers[i] = val % 10;
            val /= 10;
        }
        Arrays.sort(numbers);
        int maxNum = 0;
        int temp = 1000;
        for (int i = 3; i >= 0; i--) {
            maxNum += numbers[i] * temp;
            temp /= 10;
        }
        return maxNum;
    }

    private static int minNum(int val) {
        int[] numbers = new int[4];

        for (int i = 0; i < 4; i++) {
            numbers[i] = val % 10;
            val /= 10;
        }
        Arrays.sort(numbers);
        int minNum = 0;
        int temp = 1000;
        for (int i = 0; i < 4; i++) {
            minNum += numbers[i] * temp;
            temp /= 10;
        }
        return minNum;


    }
}
