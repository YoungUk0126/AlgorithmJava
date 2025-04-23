package cospro;

import java.lang.reflect.Array;
import java.util.Arrays;

public class java_1 {

    static int[][] score = {{85, 92, 95, 90}, {91, 76, 85, 50}};
    static int[][] pure;

    public static void main(String[] args) {
        pure = new int[score.length][score[0].length-2];

        for(int i=0; i<score.length; i++) {
            Arrays.sort(score[i]);
            for(int j=1; j<score[i].length-1; j++) {
                pure[i][j-1] = score[i][j];
            }
        }
        int ans = 0;

        for(int i=0; i<pure.length; i++) {
            int sum = 0;
            for(int j=0; j<pure[i].length; j++) {
                sum += pure[i][j];
            }
            ans = Math.max(ans, sum/ pure[i].length);
        }
        System.out.println(ans);
    }
}
