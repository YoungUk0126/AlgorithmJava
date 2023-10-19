package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G3_11437_LCA {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N;
	static ArrayList<ArrayList<Integer>> trees;
	static int M;
	static int[] depth, parent;
	

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		trees = new ArrayList<>();
		
		for(int i=1; i<=N+1; i++) {
			trees.add(new ArrayList<>());
		}
		
		for(int i=1; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int node1 = Integer.parseInt(tokens.nextToken());
			int node2 = Integer.parseInt(tokens.nextToken());
			
			trees.get(node1).add(node2);
			trees.get(node2).add(node1);
		}
		
		depth = new int[N + 1];
		parent = new int[N + 1];
		
		dfs(1, 1);
		
		M = Integer.parseInt(input.readLine());
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int node1 = Integer.parseInt(tokens.nextToken());
			int node2 = Integer.parseInt(tokens.nextToken());
			
			int p = findParent(node1, node2);
			System.out.println(p);
		}
		
		

	}

	private static int findParent(int node1, int node2) {
		int p = 0;
		// 두번째꺼가 더 깊이 있을 경우
		if(depth[node1] < depth[node2]) {
//			System.out.println("처음 끌어 올려질 노드 : " + node2);
			while(depth[node1] != depth[node2]) {
				node2 = parent[node2];
//				System.out.println("올라간당" + node2);
			}
		}
		else {
//			System.out.println("처음 끌어 올려질 노드 : " + node1);
			while(depth[node1] != depth[node2]) {
				node1 = parent[node1];
//				System.out.println("올라간당" + node1);
			}
		}
		// 끌어 올려졌는데 두 노드가 같아졌으면
		if(node1 == node2) return node1;
		// 두 노드의 부모가 같아질 때 까지 둘 다 끌어올려
		while(parent[node1] != parent[node2]) {
			node1 = parent[node1];
			node2 = parent[node2];
		}
		return parent[node1];
		
	}


	private static void dfs(int now, int level) {
		depth[now] = level++;
		for(int next: trees.get(now)) {
			// 깊이를 기록한 적이 없다면
			if(depth[next] == 0) {
				dfs(next, level);
				// 다음꺼에 내가 부모인걸 미리 알려줌
				parent[next] = now;
			}
		}
		
	}

}
