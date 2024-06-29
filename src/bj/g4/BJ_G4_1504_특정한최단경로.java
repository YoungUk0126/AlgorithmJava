package bj.g4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 시간 : 1초
 * 방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다.
 * 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.
 * <p>
 * 세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다.* 여기서 뭔가 반례가 생길 것 같네
 * 근데 내가 생각한대로 3번 쪼개서 다익스트라를 돌리고 돌릴때마다 방문배열을 초기화한다면 별 문제 안생길 것 같다.
 * 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라.
 * 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000)
 * 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000)
 * 양방향 인거에 주목 *
 * 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다.
 * (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.
 * <p>
 * 최단경로, 시작 정점 주어짐 => 다익스트라
 * 근데 임의로 주어진 두 정점을 반드시 통과해야 한다는 것이다.
 * <p>
 * 첫 번째 생각, 1번부터 v1, v1부터 v2, v2부터 도착 경로를 구하는 것이다.
 * v1,v2 중에 1번이랑 가장 가까운 곳을 구해서 먼저 그 곳으로 가는거다
 * ex) 1 -> v1 -> v2 -> N , 1 -> v2 -> v1 -> N
 * 그니까 (1->v1 + v1->v2 + v2->N) (1->v2 + v2->v1 + v1->N) 둘 중에 더 작은 값을 출력하는 거야
 * @see
 * @since 2024. 06. 29
 */
public class BJ_G4_1504_특정한최단경로 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();
    static int N, E, V1, V2;
    static ArrayList<ArrayList<Vertex>> map = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokens = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            int weight = Integer.parseInt(tokens.nextToken());

            map.get(from).add(new Vertex(to, weight));
            map.get(to).add(new Vertex(from, weight));
        }

        tokens = new StringTokenizer(input.readLine());
        V1 = Integer.parseInt(tokens.nextToken());
        V2 = Integer.parseInt(tokens.nextToken());

        int case1 = solve(1, V1, V2, N);
        int case2 = solve(1, V2, V1, N);

        if (case1 != -1 && case2 != -1) {
            int ans = Math.min(case1, case2);
            builder.append(ans);
        } else if (case1 == -1 && case2 == -1) builder.append(-1);
        else {
            int ans = case1 == -1 ? case2 : case1;
            builder.append(ans);
        }
        System.out.println(builder);

    }

    static int solve(int start, int m1, int m2, int end) {
        int root1 = Dijkstra(start, m1);
        int root2 = Dijkstra(m1, m2);
        int root3 = Dijkstra(m2, end);

        int sum = 0;

        if (root1 != -1 && root2 != -1 && root3 != -1) {
            sum = root1 + root2 + root3;
        } else {
            sum = -1;
        }
        return sum;
    }

    static int Dijkstra(int start, int end) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();
            if (!visited[now.index]) {
                visited[now.index] = true;
            }
            if (now.index == end) break;

            for (Vertex next : map.get(now.index)) {
                if (!visited[next.index] && dist[next.index] > now.weight + next.weight) {
                    dist[next.index] = now.weight + next.weight;
                    pq.offer(new Vertex(next.index, dist[next.index]));
                }
            }
        }
        if (dist[end] == Integer.MAX_VALUE) dist[end] = -1;
        return dist[end];

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
