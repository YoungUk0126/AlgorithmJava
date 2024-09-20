package programmers;
import java.util.*;
import java.io.*;

public class PG_LV3_입국심사 {

    static int[] times = {7, 10};
    static int n = 6;

    public static void main(String[] args) {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = times[times.length-1] * (long)n; //모든 사람이 가장 느리게 심사받음

        // 1. 모든 사람 심사하는데 걸리는 시간의 범위를 정해놓고
        while(left<= right) {
            long mid = (left + right) / 2;
            long complete = 0;

            for(int num: times) {
                complete += mid / num;
            }
            if(complete < n) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                answer = mid;
            }
        }
        System.out.println(answer);
    }
}
