package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 본인이름
 * @git
 * @youtube
 * @performance
 * @category #
 * @note 각 지역마다 물품이 있고, 각 지역을 잇는 간선마다 가중치가 있음
 * 이 가중치를 최소로 가장 많은 물품을 뒤졌을 때 물품의 개수를 구하는 문제.
 * <p>
 * 다익스트라는 시작 정점이 정해져 있어야 유리하나, 이 문제는 시작 정점이 정해져 있지 않다.
 * 하지만 모든 정점을 시작으로 두고 for문을 돌려서 풀 수도 있다.
 * <p>
 * 플로이드-워셜이 더 적절한 것 같다. 시작 정점이 정해져 있지 않으니까. 하지만 플로이드-워셜은
 * n^3이므로 사용에 주의하여야 하나 이 문제에서 n은 100밖에 되지 않으니
 * 결국, 두 알고리즘 모두 사용하여 풀 수 있다.
 * @see https://www.acmicpc.net/problem/14938
 * @since 2024. 06. 24
 */
public class BJ_G4_14938_서강그라운드 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int N, M, R, answer;
    static ArrayList<ArrayList<Vertex>> map = new ArrayList<>();
    static int[] areaVal;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        areaVal = new int[N + 1];
        tokens = new StringTokenizer(input.readLine());

        for (int i = 1; i < areaVal.length; i++) {
            areaVal[i] = Integer.parseInt(tokens.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

//            양방향임을 꼭!!!!!!!! 기억하자 제발
            map.get(from).add(new Vertex(to, weight));
            map.get(to).add(new Vertex(from, weight));
        }
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, Dijkstra(i));
        }

        System.out.println(answer);

    }

    static int Dijkstra(int start) {// A에서 C로 갈 때 여러 경로가 있을 수 있고
//        그 중 가장 짧은 경로로 갔을 때 내 수색 범위 안에 들어오면 아이템을 먹을 수 있기 떄문에
//        다익스트라 알고리즘을 사용해야함( 사실 플로이드 워셜이 더 적합함 )
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        pq.add(new Vertex(start, 0));
        dist[start] = 0;// 출발지는 0

        while(!pq.isEmpty()) {
            Vertex now = pq.poll();
            if(!visited[now.index]) {
                visited[now.index] = true;
                // 현재 경유지랑 연결되어 있는 놈들 다 델꼬와
                for(Vertex next: map.get(now.index)) {
                    // 방문하지 않았고, 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧을 경우
                    if(!visited[next.index] && dist[next.index] > now.weight + next.weight) {
                        dist[next.index] = now.weight + next.weight;
                        pq.add(new Vertex(next.index, dist[next.index]));
                    }
                }
            }
        }
//        for(int i=1; i<=N; i++) {
//            System.out.println(start+"에서 "+i + "까지의 거리 : " + dist[i]);
//        }
        int result = 0;
        for(int i=1; i<=N; i++) {
            if(dist[i] <= M) {
                result += areaVal[i];
            }
        }
//        System.out.println(start + "에서 시작할 때 얻을 수 있는 아이템 수 : " + result);
        return result;
    }


    static class Vertex implements Comparable<Vertex> {
        int index;
        int weight;

        public Vertex(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }
}
