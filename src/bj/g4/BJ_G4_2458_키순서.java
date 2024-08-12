package bj.g4;

import java.io.*;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance 736ms
 * @category # 그래프 탐색
 * @note 시간 : 초, N : 500
 * <p>
 * 6 6 ( N, M )
 * 1 5
 * 3 4
 * 5 4
 * 4 2
 * 4 6
 * 5 2
 * <p>
 * 1 < 5 , 3 < 4 인거다
 * 1번은 5번보다 키가 작고, 5번은 4번보다 작기 때문에, 1번은 4번보다 작게 된다.
 * 그러면 1번, 3번, 5번은 모두 4번보다 작게 된다.
 * 또한 4번은 2번과 6번보다 작기 때문에, 4번 학생은 자기보다 작은 학생이 3명이 있고,
 * 자기보다 큰 학생이 2명이 있게 되어 자신의 키가 몇 번째인지 정확히 알 수 있다.
 * 그러나 4번을 제외한 학생들은 자신의 키가 몇 번째인지 알 수 없다.
 * 4는 정확히 자기 키 순서를 알 수 있음
 *
 * 그래프를 두 가지 방식으로 받아준다( 정방향, 역방향 ) 양방향은 안됨
 * 탐색을 할 때 정확히 방향이 있어야 하기 때문
 * 그 후에 그래프 탐색을 하면서 탐색하는 노드 숫자를 세준 후, 그 숫자가 N-1이라면
 * 자기 자신의 위치를 정확히 아는 것
 * @see https://www.acmicpc.net/problem/2458
 * @since 2024. 08. 12
 */

public class BJ_G4_2458_키순서 {

    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, ans;
    static ArrayList<ArrayList<Integer>> bigGraph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> smallGraph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for (int i = 0; i <= N; i++) {
            bigGraph.add(new ArrayList<>());
            smallGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());

            bigGraph.get(start).add(end);
            smallGraph.get(end).add(start);
        }

        int count;
        for(int i=1; i<=N; i++) {
            count = 0;
            count += bfs(bigGraph, i);
            count += bfs(smallGraph, i);

            if(count == N-1) ans++;
        }
        System.out.println(ans);

    }
    private static int bfs(ArrayList<ArrayList<Integer>> graph, int start){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next: graph.get(now)) {
                if(!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    q.offer(next);
                }
            }
        }
        return cnt;
    }
}
