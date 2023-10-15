package bj.g3;
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

https://velog.io/@suk13574/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98Java%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BCDijkstra-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
참고해서 짜도록
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G3_1238_파티 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N, M, X;
	static int STD[]; // students total disdance
	static ArrayList<ArrayList<Node>> graph;
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		
		STD = new int[N+1];
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
		for(int i=1; i<=N; i++) {
			// 시작 정점을 보내~
			Dijkstra(i);
		}
		

	}
	
	private static void Dijkstra(int start) {
		int distance[] = new int[N+1];
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
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
