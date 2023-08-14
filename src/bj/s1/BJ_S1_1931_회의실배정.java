package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class meeting{
    int start;
    int end;

    public meeting(int start, int end){
        this.start = start;
        this.end = end;

    }


}

/**
 * N개의 회의에 대하여 각 회의가 겹치지 않게 하면서
 * 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
 *
 * 회의는 한 번 시작하면 중간에 중단될 수 없어
 * 회의가 끝나는 동시에 다음 회의가 시작할 수 있어
 * 회의의 시작시간과 끝나는 시간이 같을 수 있어(시작하자마자 끝)
 *
 * 종료시간이 같다면 시작 시간이 빠른 순으로 정렬해야하는 부분을 생각하지 못했다.
 */

public class BJ_S1_1931_회의실배정 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N, ans, usingStart, usingEnd;
    static meeting[] schedules;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        schedules = new meeting[N];
        for (int i=0; i<N; i++){
            tokens = new StringTokenizer(input.readLine());
            int s = Integer.parseInt(tokens.nextToken());
            int e = Integer.parseInt(tokens.nextToken());
            schedules[i] = new meeting(s,e);
        }
        Arrays.sort(schedules, new Comparator<meeting>() {
                    @Override
                    public int compare(meeting o1, meeting o2) {
                        if(o1.end == o2.end){
                            return o1.start - o2.start;
                        }
                        return o1.end - o2.end;
                    }
        });

        usingStart = schedules[0].start;
        usingEnd = schedules[0].end;

        ans = 1;
        for(int i=1; i< schedules.length; i++) {
            if(schedules[i].start >= usingEnd){
                ans++;
                usingStart = schedules[i].start;
                usingEnd = schedules[i].end;
            }
        }
        System.out.println(ans);
    }
}
