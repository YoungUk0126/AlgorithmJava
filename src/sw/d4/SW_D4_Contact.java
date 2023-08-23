package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_D4_Contact {
	
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;
    
    static ArrayList<Integer>[] graph;
    static boolean v[];
    static int L,startV;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		for(int tc=1; tc<=10; tc++) {
			builder.append("#").append(tc).append(" ");
			
			graph = new ArrayList[101];
			
			for(int i=0; i<101; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			tokens = new StringTokenizer(input.readLine());
			L = Integer.parseInt(tokens.nextToken());
			startV = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<L / 2; i++) {
				int from = Integer.parseInt(tokens.nextToken());
				int to = Integer.parseInt(tokens.nextToken());

				graph[from].add(to);
			}

			bfs(startV);
		}
		System.out.println(builder);
	}
	private static void bfs(int startV) {
		Queue< int[] > q = new ArrayDeque<>();
		v = new boolean[101];
		
		int depth = 0;
		
		q.offer( new int[] { startV, 0 } );
		v[startV] = true;
		int[] Mx = new int[ 2 ];
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if( Mx[ 1 ] < now[ 1 ] ) {
				Mx = now.clone();
			}else if( Mx[ 1 ] == now[ 1 ] ) {
				if( Mx[ 0 ] < now[ 0 ] ) {
					Mx = now.clone();
				}
			}
			
			
			for (int num : graph[ now[ 0 ] ]) {
				if (!v[num]) {
					v[num] = true;
					q.offer( new int[] { num, now[ 1 ] + 1 } );
				}
			}
			
		}
		builder.append(Mx[ 0 ]).append("\n");
	}

}
