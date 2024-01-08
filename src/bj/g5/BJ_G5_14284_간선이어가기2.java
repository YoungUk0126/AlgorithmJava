package bj.g5;

/**
@author 김영욱
@since 2024. 01. 08
@see https://www.acmicpc.net/problem/14284
@git
@performance 
@category 다익스트라
@note

정점 n개, 0개의 간선으로 이루어진 무방향 그래프가 주어진다.
그리고 m개의 가중치 간선의 정보가 있는 간선리스트가 주어진다.
간선리스트에 있는 간선 하나씩 그래프에 추가해 나갈 것이다.
이때, 특정 정점 s와 t가 연결이 되는 시점에서 간선 추가를 멈출 것이다.
연결이란 두 정점이 간선을 통해 방문 가능한 것을 말한다.

s와 t가 연결이 되는 시점의 간선의 가중치의 합이 최소가 되게 추가하는 간선의 순서를 조정할 때, 그 최솟값을 구하시오.

다익스트라로 풀어야 하는 문제
priority Queue를 사용하여 푼다.
가중치를 기준으로 가장 짧은 간선부터 나오기 때문에 최단 거리 보장

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G5_14284_간선이어가기2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N,M,expensive;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Node>());
		}

        for(int i=0; i<M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            
            // 2차원 ArrayList
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        tokens = new StringTokenizer(input.readLine());
        int s = Integer.parseInt(tokens.nextToken());
        int t = Integer.parseInt(tokens.nextToken());


        int answer = Dijkstra(s, t);
        System.out.println(answer);

    }

    private static int Dijkstra(int start, int end) {
        int distance[] = new int[N+1];
        boolean visited[] = new boolean[N+1];
        
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		// 우선 순위 큐 사용, 가중치를 기준으로 오름차순한다.
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		// 시작 노드에 대해서 초기화
		pq.add(new Node(start, 0));
		distance[start] = 0;

        while(!pq.isEmpty()) {
            // 현재 최단 거리가 가장 짧은 노드를 꺼내서 방문 한다.
            Node now = pq.poll();
            if(!visited[now.index]) {
                visited[now.index] = true;
            }
            // 도착 했으면 멈춰!
            if(now.index == end) break;

            // now와 연결돼있는 노드들을 탐색
            for(Node next: graph.get(now.index)) {
				// 방문하지 않았고, 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧을 경우
                if(!visited[next.index] && distance[next.index] > now.weight + next.weight){
                    distance[next.index] = now.weight + next.weight;
                    pq.add(new Node(next.index, distance[next.index]));
                }
            }
        }
        return distance[end];
    }

    private static class Node implements Comparable<Node>{
        int index, weight;

        Node(int a, int w) {
            this.index = a;
            this.weight = w;
        }
        @Override
        public int compareTo(Node l) {
            return weight - l.weight;
        }
    }
}