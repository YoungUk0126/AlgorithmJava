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
    
    static ArrayList<간선> graph;
    static boolean v[];
    static int L,startV;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		for(int tc=1; tc<=1; tc++) {
			
			graph = new ArrayList<>();
			
			tokens = new StringTokenizer(input.readLine());
			L = Integer.parseInt(tokens.nextToken());
			startV = Integer.parseInt(tokens.nextToken());
			

			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<L/2; i++) {
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());
				
				graph.add(new 간선(from, to));
			}
			
			bfs(startV);
		}
	}
	private static void bfs(int startV) {
		Queue<간선> q = new ArrayDeque<>();
		v = new boolean[101];
		
	}
	static class 간선{
		int from, to;

		public 간선(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
		
	}

}
