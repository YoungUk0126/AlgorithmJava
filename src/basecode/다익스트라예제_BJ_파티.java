package basecode;
/**
@author 김영욱
@since 2023. 10. 15.
@see https://www.acmicpc.net/problem/1238
@git
@performance 
@category #
@note
N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
이 N명의 학생이 X번 마을에 모여서 파티를 하고 다시 자기 마을로 돌아와야 한다.
이 거리를 최단으로 왔다갔다 할 것이다.
N명의 학생 중 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

예제 입력 1
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
답 : 10

https://velog.io/@suk13574/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98Java%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BCDijkstra-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
참고해서 짜도록
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 다익스트라예제_BJ_파티 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N, M, X;
	static ArrayList<ArrayList<Node>> graph;
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		graph = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());
			
			graph.get(from).add(new Node(to, weight));
		}
		int ans = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			if(i != X) {
				int sum = 0;
				// 시작 정점을 보내~
				sum += Dijkstra(i, X);
				sum += Dijkstra(X, i);
				ans = Math.max(ans, sum);
			}
		}
		
		System.out.println(ans);
		

	}
	
	private static int Dijkstra(int start, int end) {
		int[] distance = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		// 우선 순위 큐 사용, 가중치를 기준으로 오름차순한다.
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		// 시작 노드에 대해서 초기화
		pq.add(new Node(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			// 현재 최단 거리가 가장 짧은 노드를 꺼내서 방문 처리 한다.
			Node now = pq.poll();
			if(!visited[now.index]) {
				visited[now.index] = true;
			}
			// 도착한 곳이 경유지면 스톱! 어차피 pq때매 최단거리로 왔을거야
			if(now.index == end) break;
			
			for(Node next : graph.get(now.index)) {
				// 방문하지 않았고, 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧을 경우
				if (!visited[next.index] && distance[next.index] > now.weight + next.weight) {
					distance[next.index] = now.weight + next.weight;
					// 여태 저장했던 값을 담아서 새로 간선 정보를 넘겨줘
					pq.add(new Node(next.index, distance[next.index]));
				}
			}
		}
		return distance[end];
		
		
	}

	static class Node implements Comparable<Node>{
		int index;
		int weight;
		
		public Node(int index, int weight) {
			super();
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}

}
