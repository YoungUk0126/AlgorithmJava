package bj.s2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_1654_랜선자르기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static long max, ans;
    static int N,K;
    static long[] lines;
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        long left = 1;

        lines = new long[N];
        for(int i=0; i<N; i++) {
            lines[i] = Integer.parseInt(input.readLine());
            if(lines[i]>max){
                max = lines[i];
            }
        }
        long right = max+1;

        while(left<=right) {
            long mid = (left + right)/2;
            long lineCnt = getSum(mid);
            if(lineCnt < K)
            {
                right = mid-1;
            }
            else {
                left = mid + 1;
                ans = mid;
            }
        }
        System.out.println(ans);

    }
    static long getSum(long mid) {
        long lineCnt=0;
        for(long line: lines){
            lineCnt += line/mid;
        }
        return lineCnt;
    }

}
