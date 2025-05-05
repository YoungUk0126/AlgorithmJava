package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note N에서 K까지 최단 시간에 가야함
 * 방법 1. 걷기
 * X+1, X-1로 이동하고 1초 걸림
 * 방법 2. 순간이동
 * 2*X 시간이 걸리고 0초 걸림
 * <p>
 * 현재 위치에서 앞뒤로 걷기, 순간이동하기 둘다 큐에 넣어주고, 가중치를... 굳이 잡아야 할까
 * 5 -> 17이면
 * 5 -> 10 -> 9 -> 18 -> 17 = 2초
 * 이런 방식인데
 * X에서 K까지의 남은 거리로 가중치를 잡으면 계속 곱하기만 할듯
 * 아닌가 절댓값으로 하면 괜찮을꺼 같은데
 * 5 -> 10 -> 20(3)
 * 9 -> 18
 * 거리 절댓값으로 PQ정렬을 잡아놓고
 * dist로 걸린 시간을 넣어놓자 굿
 * 근데 이러면 종료 시점을 어떻게 판별하지
 * 남은 거리로 PQ넣어버리면 방문 체크도 애매함..
 * 그냥 도착하면 딱 멈추면서 바로 시간 출력하게 해보자 일단
 * 시간으로 가중치를 잡아도 순간이동이 0초라 계속 순간이동만 할듯
 * 그냥 완탐처럼 다 해보자
 * 반례가 있나보다... 44퍼 탈락
 * <p>
 * 다익스트라로 하면 시간을 기준으로 한다는데... 그럼 일단 현재 위치에서 *2는 다 가본단 소린가
 * 10만이라 괜찮은건가보다
 * @see https://www.acmicpc.net/problem/13549
 * @since 2025/ 05. 05
 **/

public class BJ_G5_13549_숨바꼭질3 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N, K;
    static final int MAX_POSITION = 100_000;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());


        System.out.println(stra());
    }

    private static int stra() {
        int[] time = new int[MAX_POSITION + 1];
        PriorityQueue<Position> q = new PriorityQueue<>();
        q.offer(new Position(N, 0));
        Arrays.fill(time, MAX_POSITION);
        time[N] = 0;


        while (!q.isEmpty()) {
            Position now = q.poll();

            if (time[now.x] < now.sec) continue;

            if (isIn(now.x * 2) && time[now.x * 2] > now.sec) {
                time[now.x * 2] = now.sec;
                q.offer(new Position(now.x * 2, time[now.x * 2]));
            }
            if (isIn(now.x + 1) && time[now.x + 1] > now.sec + 1) {
                time[now.x + 1] = now.sec + 1;
                q.offer(new Position(now.x + 1, time[now.x + 1]));
            }
            if (isIn(now.x - 1) && time[now.x - 1] > now.sec + 1) {
                time[now.x - 1] = now.sec + 1;
                q.offer(new Position(now.x - 1, time[now.x - 1]));
            }

        }
        return time[K];
    }

    private static boolean isIn(int x) {
        return 0 <= x && x <= MAX_POSITION;
    }

    private static class Position implements Comparable<Position> {
        int x;
        int sec;

        public Position(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }

        @Override
        public int compareTo(Position o) {
            return this.sec - o.sec;
        }
    }
}
