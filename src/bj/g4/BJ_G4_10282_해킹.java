package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_10282_해킹 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int T, N, D, C;
    static ArrayList<ArrayList<Vertex>> map;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            map = new ArrayList<>();
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());// 컴터개수
            D = Integer.parseInt(tokens.nextToken());// 의존성 개수
            C = Integer.parseInt(tokens.nextToken());// 해킹당한 번호

            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int d = 0; d < D; d++) {
                tokens = new StringTokenizer(input.readLine());
                int to = Integer.parseInt(tokens.nextToken());
                int from = Integer.parseInt(tokens.nextToken());
                int weight = Integer.parseInt(tokens.nextToken());

                map.get(from).add(new Vertex(to, weight));
            }
            Dijkstra(C);

        }
        System.out.println(builder);

    }

    static void Dijkstra(int start) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        pq.offer(new Vertex(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();
            if(!visited[now.index]) {
                visited[now.index] = true;
                for(Vertex next: map.get(now.index)) {
                    if(!visited[next.index] && dist[next.index] > now.weight + next.weight) {
                        dist[next.index] = now.weight + next.weight;
                        pq.offer(new Vertex(next.index, dist[next.index]));
                    }
                }
            }
        }
        int cumCnt = 0;
        int totalSec = 0;
        for(int i=1; i<=N; i++) {
            if(dist[i] != Integer.MAX_VALUE) {
                cumCnt++;
                totalSec = Math.max(totalSec, dist[i]);
            }
        }
        builder.append(cumCnt).append(" ").append(totalSec).append("\n");
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
            return Integer.compare(this.weight, o.weight);
        }
    }
}
