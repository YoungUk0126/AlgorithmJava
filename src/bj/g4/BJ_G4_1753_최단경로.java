package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1753_최단경로 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int d[];
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	
	static int V, E, start;
	public static void main(String[] args) throws IOException{
		
		tokens = new StringTokenizer(input.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		start = Integer.parseInt(input.readLine());
		d = new int[V+1];
		
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<E; i++) {
			tokens = new StringTokenizer(input.readLine());
			int A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			int distance = Integer.parseInt(tokens.nextToken());
			
			// A번 노드에서 B노드까지 거리를 저장한다.
			graph.get(A).add(new Node(B, distance));
		}
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(d, INF);
		
		dijkstra(start);
		
		for(int i=1; i<=V; i++) {
			if(d[i] == INF) System.out.println("INF");
			else System.out.println(d[i]);
		}
		
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(start, 0)); // 자기 자신부터 시작하니까 거리는 0이다.
		d[start] = 0;
		
		while(!pq.isEmpty()) {
			
			Node node = pq.poll();
			
			int dist = node.distance;
			int now = node.vertex;
			
			// 현재 노드가 이미 처리된 적이 있는 노드라면 무시
			if(d[now] < dist) continue;
			
			// 현재 노드와 연결된 다른 인접한 노드들을 확인
			for(int i=0; i<graph.get(now).size(); i++) {
				// 현재의 최단거리 + 현재의 연결된 노드의 비용
				int cost = d[now] + graph.get(now).get(i).distance;
				
				// 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧으면
				if (cost < d[graph.get(now).get(i).vertex]) {
					d[graph.get(now).get(i).vertex] = cost;
					pq.offer(new Node(graph.get(now).get(i).vertex, cost));
				}
			}
		}
		
	}

	static class Node implements Comparable<Node>{
		int vertex, distance;
		
		public Node(int vertex, int distance) {
			super();
			this.vertex = vertex;
			this.distance = distance;
		}



		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
		
	}

}
