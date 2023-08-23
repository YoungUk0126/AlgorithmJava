package bj.g4;

/**
@author 김영욱
@since 2023. 08. 23
@see https://www.acmicpc.net/problem/1647
@git 
@performance
@category #
@note
시간 : 2초
N,M 크기 100,000 과 1,000,000
마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다.
마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다.
각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻
마을에는 집이 하나 이상 있어야 됨.

길 유지비의 합의 최솟값을 출력
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ_G4_1647_도시분할계획 {
	
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int N,M,expensive;
    static int[] parents;
    static 간선[] 간선리스트;

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		make();
		
		간선리스트 = new 간선[M];
		
		for(int i=0; i<M; i++)
		{
			tokens = new StringTokenizer(input.readLine());
			int A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			int C = Integer.parseInt(tokens.nextToken());
			
			간선리스트[i] = new 간선(A,B,C);
		}
		간선 마지막간선 = new 간선();
		Arrays.sort(간선리스트);

		int count = 0; // 연결된 간선 개수
		for(간선 line: 간선리스트) {
			if(union(line.A, line.B)) {
				expensive += line.W;
				마지막간선 = line;
				if(++count == N-1) break;
			}
		}
		expensive -= 마지막간선.W;
		System.out.println(expensive);
		

	}

	private static void make() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	// 간선 정보를 담을 간선 클래스
	private static class 간선 implements Comparable<간선>{
		int A;
		int B;
		int W;
		public 간선() {}
		public 간선(int a, int b, int w) {
			super();
			A = a;
			B = b;
			W = w;
		}
		@Override
		public int compareTo(간선 line) {
			return W - line.W;
		}
		
		
		
	}

}
