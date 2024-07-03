package bj.g3;

import java.io.*;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다.
 * 한 칸에는 물고기가 최대 1마리 존재한다. 아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다.
 * 가장 처음에 아기 상어의 크기는 2이고, *아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다*.
 * <p>
 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고*, 나머지 칸은 모두 지나갈 수 있다.
 * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다*. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
 * <p>
 * 아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.
 * <p>
 * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다.
 * 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.
 * <p>
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
 * 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
 * <p>
 * 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.
 * <p>
 * 둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.
 * <p>
 * 0: 빈 칸
 * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
 * 9: 아기 상어의 위치
 * 아기 상어는 공간에 한 마리 있다.
 * <p>
 * 처음 아기 상어 크기 : 2
 * 물고기가 작거나 같은 크기면 지나갈 수 있음, 단 같은 크기면 먹지는 못함.
 * 더 작다면 먹으면서 지나갈 수 있음
 * <p>
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 감
 * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 * <p>
 * 아기 상어는 자기 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
 * ex) 크기 : 2 => 2마리 => 크기 : 3, 크기 : 3 => 3마리 => 크기 : 4
 * 먹는 물고기의 크기랑은 상관 없음.
 * 우선순위 : 가장 가까운 물고기 > 가장 위에 있는 물고기 > 가장 왼쪽에 있는 물고기
 * 먹고 난 다음에 먹을게 있는지 어떻게 알려줘야 할까? 미리 알아야 더 헤메기 전에 엄마 부를꺼 아니야
 * @see https://www.acmicpc.net/problem/16236
 * @since 2024. 07.02
 */

public class BJ_G3_16236_아기상어 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int N, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[] fishes = new int[7];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        ans = 0;
        map = new int[N][N];
        boolean nothingToEat = false;
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 9) {
                    startX = i;
                    startY = j;
                    map[i][j] = 0;
                } else if (map[i][j] != 0) {
                    nothingToEat = true;
                    fishes[map[i][j]]++;
                }
            }
        }

        if (!nothingToEat) {
            System.out.println(0);
        } else {
            letsEat(startX, startY);
            System.out.println(ans);
        }
    }

    private static void letsEat(int startX, int startY) {
        visited = new boolean[N][N];
        Queue<BabyShark> q = new ArrayDeque<>();
        q.offer(new BabyShark(2, 0, startX, startY, 0, fishes));

        while (!q.isEmpty()) {
            BabyShark now = q.poll();
            System.out.println("x : " + now.x + ", y : " + now.y + ", weight : " + now.weight + ", fishCnt : " + now.fishCnt);
            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int dx = now.x + deltas[i][0];
                int dy = now.y + deltas[i][1];
                if (isIn(dx, dy) && !visited[dx][dy]) {
                    int eatFlag = now.move(dx, dy);
                    if(eatFlag >= 0){
                        q.offer(new BabyShark(now.weight, now.fishCnt, now.x, now.y, now.sec, now.myGoalFishes));
                        if(eatFlag > 0){
                            visited = new boolean[N][N];
                            boolean flag = false;
                            for (int j = 1; j < 7; j++) {
                                if (j < now.weight && now.myGoalFishes[j] > 0) {
                                    System.out.println("아직 먹을꺼 있어");
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                System.out.println("정답 찾음!");
                                ans = now.sec;
                            }
                        }
                    }
                }
            }
        }
    }

    static private boolean isIn(int dx, int dy) {
        return 0 <= dx && dx < N && 0 <= dy && dy < N;
    }

    static class BabyShark {
        int weight;
        int fishCnt;
        int x;
        int y;
        int sec;
        int[] myGoalFishes;

        public BabyShark(int weight, int fishCnt, int x, int y, int sec, int[] myGoalFishes) {
            this.weight = weight;
            this.fishCnt = fishCnt;
            this.x = x;
            this.y = y;
            this.sec = sec;
            this.myGoalFishes = myGoalFishes.clone();
        }

        public void eat() {
            fishCnt++;
            myGoalFishes[map[x][y]]--;
            System.out.println("제가 이거 먹음");
            System.out.println(map[x][y] + " : " + myGoalFishes[map[x][y]]);
            map[x][y] = 0;
            if (fishCnt == weight) {
                this.weight++;
                this.fishCnt = 0;
            }
        }

        public int move(int dx, int dy) {
            if (map[dx][dy] <= this.weight) { // 지나갈 수 있냐?
                this.x = dx;
                this.y = dy;
                if (map[dx][dy] != 0 && map[dx][dy] < this.weight) {// 먹을 수 있냐?
                    eat();
                    this.sec++;
                    return 1;
                }
                this.sec++;
                return 0;
            }
            return -1;
        }
    }
}
