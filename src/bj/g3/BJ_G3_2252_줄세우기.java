package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_2252_줄세우기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int[] inDegree;
	static int N,M;
	static Queue<Integer> q = new ArrayDeque<>();
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		inDegree = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			ArrayList temp = new ArrayList<>();
			graph.add(temp);
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			
			graph.get(from).add(to);
			inDegree[to]++;
		}
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		bfs();
	}
	private static void bfs() {
		
		while(!q.isEmpty()) {
			int node = q.poll();
			ArrayList<Integer> temp = graph.get(node);
			System.out.print(node + " ");
			for(int i: temp) {
				inDegree[i] -= 1;
				if(inDegree[i] == 0) {
					q.offer(i);
				}
			}
		}
		
	}

}
