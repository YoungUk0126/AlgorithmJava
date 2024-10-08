package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 @author 김영욱
 @since 2023. 09 03
 @see https://www.acmicpc.net/problem/1005
 @git
 @performance
 @category #
 @note
 ACM Craft게임이 있다. 일종의 그래프 탐색인데 건물을 짓는데 걸리는
 최소시간을 알아내는 문제이다.
 유향그래프이고, 어떤 건물을 짓기 위해서는 전에 연결되어 있는 건물들을 완성해야
 그 다음 건물을 지을 수 있다.
 */
public class BJ_G3_1005_ACMCraft {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int T,N,K,end,ans;
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<Integer> q;
    static int[] inDegree, costs, dp;


    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(input.readLine());
        for(int tc=1; tc<=T; tc++) {
            q = new ArrayDeque<>();
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            K = Integer.parseInt(tokens.nextToken());
            ans = 0;
            graph = new ArrayList<>();

            inDegree = new int[N+1];
            costs = new int[N+1];
            dp = new int[N+1];

            tokens = new StringTokenizer(input.readLine());
            for(int i=1; i<=N; i++) {
                costs[i] = Integer.parseInt(tokens.nextToken());
            }

            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=1; i<=K; i++) {
                tokens = new StringTokenizer(input.readLine());
                int from = Integer.parseInt(tokens.nextToken());
                int to = Integer.parseInt(tokens.nextToken());

                graph.get(from).add(to);
                inDegree[to]++;
            }

            end = Integer.parseInt(input.readLine());

            for(int i=1; i<=N; i++) {
                if(inDegree[i] == 0) {
                    q.offer(i);
                }
            }

            bfs();
            builder.append(dp[end]).append("\n");
        }
        System.out.println(builder);

    }
    static void bfs() {
        int max=0;
        while(!q.isEmpty()) {
            int nodeNo = q.poll();
            dp[nodeNo] = Math.max(dp[nodeNo], costs[nodeNo]);
            if(nodeNo == end) {
                return;
            }
            ArrayList<Integer> now = graph.get(nodeNo);
            for(int i: now) {
                inDegree[i]--;
                dp[i] = Math.max(dp[i], dp[nodeNo] + costs[i]);
                if(inDegree[i] == 0) {
                    q.offer(i);
                }
            }
        }
    }
}

