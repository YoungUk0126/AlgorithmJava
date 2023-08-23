package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_D4_Contact {
	
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;
    
    static ArrayList<Integer>[] graph = new ArrayList[101];
    static boolean v[];
    static int L,startV;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		for(int tc=1; tc<=1; tc++) {
			
			
			for(int i=0; i<101; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			tokens = new StringTokenizer(input.readLine());
			L = Integer.parseInt(tokens.nextToken());
			startV = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<tokens.countTokens(); i+=2) {
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());

				graph[from].add(to);
			}
			
			bfs(startV);
		}
	}
	private static void bfs(int startV) {
		Queue<ArrayList> q = new ArrayDeque<>();
		v = new boolean[101];
		
		int depth = 0;
		
		q.offer(graph[startV]);
		v[startV] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			depth++;
			while (size-- > 0) {
				ArrayList<Integer> now = q.poll();
				for(int num: now) {
					if(!v[num]) {
						v[num] = true;
						System.out.print(now + " ");
						q.offer(graph[num]);
					}
				}
				System.out.println();
			}
		}
	}

}
