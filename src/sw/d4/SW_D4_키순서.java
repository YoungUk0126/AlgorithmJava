package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_D4_키순서 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static ArrayList<ArrayList<Integer>> goGraph;
	static ArrayList<ArrayList<Integer>> comeGraph;
	static int T, N, M, total;
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		for(int t=1; t<=T; t++) {
			// 노드 수
			N = Integer.parseInt(input.readLine());
			// 간선 수
			M = Integer.parseInt(input.readLine());
			
			total = 0;
			
			goGraph = new ArrayList<>();
			comeGraph = new ArrayList<>();
			
			for(int i=0; i<=N; i++) {
				goGraph.add(new ArrayList<>());
				comeGraph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(input.readLine());
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());
				goGraph.get(from).add(to);
				comeGraph.get(to).add(from);
			}
			
			for(int i=1; i<N+1; i++) {
				int ans = 0;
				ans += bfs(goGraph, i);
				ans += bfs(comeGraph, i);
				
				if(ans == N-1) {
					total++;
				}
			}
			builder.append("#").append(t).append(" ").append(total).append("\n");
		}
		System.out.println(builder);

	}
	private static int bfs(ArrayList<ArrayList<Integer>> graph, int start) {
		int ans=0;
		Queue<Integer> q = new ArrayDeque<>();
		boolean visited[] = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next: graph.get(now)) {
				if(!visited[next]) {
					visited[next] = true;
					q.offer(next);
					ans++;
				}
			}
		}
		
		return ans;
	}

}
