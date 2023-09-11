package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 09. 11
@see
@git 
@performance
@category #
@note
시간 : 1초
n <= 100
아무렇게나 있는 n개의 별들을 이어서 별자리를 하나 만들 거다.
조건 2개
 1. 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태다.
 2. 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다.

선을 하나 이을 때 두 별 사이의  거리만큼의 비용이 든다고 할 때, 별자리를 만드는 최소비용.
( 피타고라스 법칙을 이용해야 할 것 같다. sqrt(|(x2 - x1)|^2 + |(y2 - y1)|^2)으로 대각선 길이 구해

최소 비용 트리를 만들어야 하니까 크루스칼, 프림을 쓸 수 있다.
크루스칼을 복습하기로 마음 먹었다.

크루스칼은 간선 정보가 있어야 하는데..
정점들의 좌표 정보만 딸랑 줘서 간선리스트를 어떻게 만들어줘야하나 고민중...
일단 그냥 모든 점들을 이어보자.
두 점만 이어주면 되니 nC2 조합을 구하면 된다. (두개니 간단하게 이중포문 쓰자)
간선 안에는 a,b,w를 넣어주자

크루스칼은 간선리스트가 가중치로 오름차순 돼있어야 한다.
PQ를 쓰면 시간복잡도가 짧아지니 나중에 PQ로도 짜보자.

가장 작은 가중치 순서대로 union함수를 이용하여 부모를 같게 만들어 준다.
그렇게 모든 간선들을 돌면서 union에 성공한 간선의 가중치를 합쳐준다.

그러면 그게 그 최소 신장 트리의 합이다.

*/

public class BJ_G3_4386_별자리만들기 {
	
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;
    
    static Star[] stars;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parents;

	public static void main(String[] args) throws IOException{

		int n = Integer.parseInt(input.readLine());
		
		stars = new Star[n];
		make(n);
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(input.readLine());
			Star temp = new Star(Double.parseDouble(tokens.nextToken()), Double.parseDouble(tokens.nextToken()));
			stars[i] = temp;
		}
		
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				double tX = Math.pow(Math.abs(stars[j].x - stars[i].x), 2);
				double tY = Math.pow(Math.abs(stars[j].y - stars[i].y), 2);
				edges.add(new Edge(i, j, Math.sqrt( tX + tY )));
			}
		}
		// 간선 리스트라 Collections로 sort해야함
		Collections.sort(edges);
		
		double result = 0.0;
		
		for(Edge now: edges) {
			if(union( now.a, now.b )) {
				result += now.w;
			}
		}
		
		System.out.printf("%.2f", result);

	}
	
	static void make(int n) {
		parents = new int[n];
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
    // a가 속한 집합과 b가 속한집합을 합칠수 있다면 합치고 true반환, 아니면 false 반환
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false; // 서로의 대표자가 같은 즉, 같은 집합이니까 union하지 않음.
        // union 처리 ( bRoot를 aRoot 아래로 붙이기!! : 임의로..)
        parents[bRoot] = aRoot;
        return true;
    }
	
	
	static class Star{
		double x,y;

		public Star(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int a,b;
		double w;
		public Edge(int a, int b, double w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}

}
