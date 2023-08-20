package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_1325_효율적인해킹 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M,max;
	static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
	static ArrayList<Integer> answer = new ArrayList<>();
	static Queue<Integer> queue = new ArrayDeque<>();
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[M+1];
		
		for(int i=0; i<=N; i++) {
			node.add(new ArrayList<>());
		}
		
		for(int i=1; i<=M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			node.get(to).add(from);
		}
		for(int i=1; i<=N; i++) {
			visited = new boolean[N+1];
			queue.offer(i);
			visited[i] = true;
			int cnt = 0;
			
			while(!queue.isEmpty()) {
				for(int c: node.get(queue.poll())) {
					if(!visited[c]) {
						visited[c] = true;
						cnt++;
						queue.offer(c);
					}
				}
			}
			if(max < cnt) {
				answer = new ArrayList<>();
				answer.add(i);
				max = cnt;
			}
			else if(max == cnt) {
				answer.add(i);
			}
		}
		Collections.sort(answer);
		for(int i: answer) {
			System.out.print(i + " ");
		}
	}

//	private static void bfs(int start) {
//		visited = new boolean[N+1];
//		queue.offer(start);
//		visited[start] = true;
//		int cnt = 0;
//		
//		while(!queue.isEmpty()) {
//			ArrayList<Integer> computer = node.get(queue.poll());
//			for(int c: computer) {
//				if(!visited[c]) {
//					visited[c] = true;
//					cnt++;
//					queue.offer(c);
//				}
//			}
//		}
//		if(max < cnt) {
//			answer = new ArrayList<>();
//			answer.add(start);
//			max = cnt;
//		}
//		else if(max == cnt) {
//			answer.add(start);
//		}
//		
//	}

}
