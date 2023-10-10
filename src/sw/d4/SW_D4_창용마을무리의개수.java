package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SW_D4_창용마을무리의개수 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int T, N, M;
	static int parents[];
	static HashSet<Integer> set;

	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			set = new HashSet<>();
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			
			make();
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				
				union(a,b);
			}
			
			for(int i=1; i<=N; i++) {
				set.add(find(parents[i]));
			}
			builder.append("#").append(tc).append(" ").append(set.size()).append("\n");
			
		}
		System.out.println(builder);

	}

	private static void make() {
		parents = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
	}
	
	private static int find(int x) {
		if(x == parents[x]) return x;
		
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
}
