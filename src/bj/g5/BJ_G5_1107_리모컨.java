package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 시간 2초
 * <p>
 * 수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.
 * 리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다.
 * 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.
 * <p>
 * 수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.
 * <p>
 * !!!!!수빈이가 지금 보고 있는 채널은 100번이다.!!!!!
 * <p>
 * 입력
 * 첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다. 둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다.
 * 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.
 * <p>
 * 5457
 * 3
 * 6 7 8
 * answer 6
 * <p>
 * 5455 => 4번, + => 1번, + => 1번     total: 6번
 * @see https://www.acmicpc.net/problem/1107
 * @since 2024.05.22
 */

public class BJ_G5_1107_리모컨 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int numN, M, answer, answerNum, min;
    static String[] NArray;
    static boolean[] brokenButtons;

    static final int PLUS = 1;
    static final int MINUS = -1;

    public static void main(String[] args) throws IOException {
        String stringN = input.readLine();
        NArray = stringN.split("");
        numN = Integer.parseInt(stringN);
        M = Integer.parseInt(input.readLine());
        brokenButtons = new boolean[10];
        min = Math.abs(numN - 100);
        if (M != 0){
            tokens = new StringTokenizer(input.readLine());
            for (int i = 0; i < M; i++) {
                int btn = Integer.parseInt(tokens.nextToken());
                brokenButtons[btn] = true;
            }
        }
        answer = 10000000;
        answerNum = 10000000;
        if (stringN.equals("100")) {
            System.out.println("0");
            return;
        }
        if (canRemote(numN)) System.out.println(Math.min(NArray.length, min));
        else {
            bfs();
            int totalClickCnt = answer + Integer.toString(answerNum).length();
            System.out.println(Math.min(totalClickCnt, min));
        }
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[1000001];
        int[] deltas = {PLUS, MINUS};

        q.offer(numN);
        visited[numN] = true;

        while (!q.isEmpty()) {
            int num = q.poll();

            for (int i = 0; i < 2; i++) {
                int nextNum = num + deltas[i];
                if (isIn(nextNum) && !visited[nextNum]) {
                    if (canRemote(nextNum)) {
                        if(Math.abs(numN - nextNum) <= answer && answerNum > nextNum) {
                            answer = Math.abs(numN - nextNum);
                            answerNum = nextNum;
                        }
                    }
                    else {
                        q.offer(nextNum);
                        visited[nextNum] = true;
                    }
                }
            }
        }
    }

    static private boolean isIn(int nextNum) {
        return 0 <= nextNum && nextNum < 1000000;
    }

    private static boolean canRemote(int nextNum) {
        while (nextNum >= 10) {
            int temp = nextNum % 10;
            if (brokenButtons[temp]) return false;
            nextNum /= 10;
        }
        if (brokenButtons[nextNum]) return false;
        return true;
    }
}
