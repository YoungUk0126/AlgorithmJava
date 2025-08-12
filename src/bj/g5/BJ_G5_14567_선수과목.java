package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 @author 김영욱
 @since 2025.08.12
 @see https://www.acmicpc.net/problem/14567
 @git
 @performance
 @category #
 @note
 시간 5초

 올해 Z대학 컴퓨터공학부에 새로 입학한 민욱이는 학부에 개설된 모든 전공과목을 듣고 졸업하려는 원대한 목표를 세웠다.
 어떤 과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다.
 공학인증을 포기할 수 없는 불쌍한 민욱이는 선수과목 조건을 반드시 지켜야만 한다.
 민욱이는 선수과목 조건을 지킬 경우 각각의 전공과목을 언제 이수할 수 있는지 궁금해졌다.
 계산을 편리하게 하기 위해 아래와 같이 조건을 간소화하여 계산하기로 하였다.

 1. 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
 2. 모든 과목은 매 학기 항상 개설된다.

 모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 걸리는지 계산하는 프로그램을 작성하여라.

 첫 번째 줄에 과목의 수 N(1 ≤ N ≤ 1000)과 선수 조건의 수 M(0 ≤ M ≤ 500000)이 주어진다.
 선수과목 조건은 M개의 줄에 걸쳐 한 줄에 정수 A B 형태로 주어진다.
 A번 과목이 B번 과목의 선수과목이다. A < B인 입력만 주어진다. (1 ≤ A < B ≤ N)

 1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지를 한 줄에 공백으로 구분하여 출력한다.
 */



public class BJ_G5_14567_선수과목 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int[] inDegree;
    static int[] ans;
    static int N,M;
    static Queue<Integer> q = new ArrayDeque<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        inDegree = new int[N+1];
        ans = new int[N+1];

        for(int i=0; i<=N; i++) { // 과목이 N개 있다니까
            graph.add(new ArrayList<>());
        }
        ans[0] = -1;
        for(int i=1; i<=N; i++) ans[i] = 1;

        for(int i=0; i<M; i++) { // 조건의 수
            tokens = new StringTokenizer(input.readLine());
            int prerequisite = Integer.parseInt(tokens.nextToken());// 선수과목은 먼저 들어야함
            int subject = Integer.parseInt(tokens.nextToken());// 선수 과목을 들어야 들을 수 있는 얘의 진입 차수를 높여

            graph.get(prerequisite).add(subject);// 이 과목을 수강하려면 선수 과목이 필요하다는 연결
            inDegree[subject]++;
        }

        for(int i=1; i<=N; i++) {
            if(inDegree[i] == 0) { // 진입 차수가 없으면 선수 과목이 없는 과목이니까 얘부터 수강 들으면 됨
                q.offer(i);// 수강 들으면 큐에 넣어
            }
        }
        subjectPlan();
        for(int i=1; i<=N; i++) {
            builder.append(ans[i]).append(" ");
        }
        System.out.println(builder);

    }

    private static void subjectPlan() {
        while(!q.isEmpty()) {
            int nowSub = q.poll();

            ArrayList<Integer> connectedList = graph.get(nowSub);

            for(int connected: connectedList) {
                inDegree[connected]--;
                ans[connected] = ans[nowSub] + 1;
                if(inDegree[connected] == 0) {
                    q.offer(connected);
                }
            }
        }
    }
}
