package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G1_17472_다리만들기2 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N , M, landCnt, expensive;
	static int map[][];
	static int deltas[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int parents[];
	static ArrayList<bridge> graph;
	
	private static void make() {
		parents = new int[landCnt+1];
		for (int i = 1; i <= landCnt; i++) {
			parents[i] = i; // 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자신이 곧 대표자인 루트로 표현)
		}
	}
	private static int find(int a) { // 자기가 속한 집합의 대표자를 리턴하는 함수
		if(a == parents[a]) return a; // 내가 곧 대표자라면
		
		return parents[a] = find(parents[a]); // path compression
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

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -1) {
					makeLand(i, j);
				}
			}
		}
		make();
		graph = new ArrayList<>();
		// 놓을 수 있는 다리를 모두 찾아서 ArrayList에 넣었음
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] > 0) {
					makeBridge(i, j, map[i][j]);
				}
			}
		}
		// 정렬^^
		Collections.sort(graph);
		
		// 최소 가중치인 간선부터 돌면서 Union을 해준다.
		for(bridge line: graph) {
            int aRoot = find(line.from);
            int bRoot = find(line.to);
            if(aRoot == bRoot) continue;
            union(aRoot, bRoot);
            expensive += line.weight;
        }
		// 모두 Union이 됐는지 확인
		// find로 연결이 돼있는지 확인
		for(int i=2; i<=landCnt; i++) {
			if(find(i) != find(i-1)) {
				expensive = 0;
				break;
			}
		}
		
		if(expensive == 0) System.out.println(-1);
		else System.out.println(expensive);
	}
	
	private static void makeBridge(int startX, int startY, int from) {
		
		int x = startX;
		int y = startY;
		int weight;
		
		for(int d=0; d<4; d++) {
			int nx = startX + deltas[d][0];
			int ny = startY + deltas[d][1];
			
			// 이 방향으로 끝까지 가
			if(isIn(nx,ny) && map[nx][ny] == 0 ) {
				weight = 1;
				while(true) {
					x = nx;
					y = ny;
					nx = x + deltas[d][0];
					ny = y + deltas[d][1];
					if(isIn(nx,ny) && map[nx][ny] == 0) weight++;
					else break;
				}
				if(isIn(nx, ny) && map[nx][ny] > 0 && weight >= 2 && map[nx][ny] != from) {
					graph.add(new bridge(from, map[nx][ny], weight));
				}
			}
		}
	}

	
	private static void makeLand(int startI, int startJ) {
		Queue<int []> q = new ArrayDeque<>();
		landCnt++;
		q.offer(new int[] {startI, startJ});
		map[startI][startJ] = landCnt;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			for(int d=0; d<4; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if(isIn(nx, ny) && map[nx][ny] == -1) {
					q.offer(new int[] {nx, ny});
					map[nx][ny] = landCnt;
				}
			}
		}
	}
	
	private static boolean isIn(int nx, int ny) {
		
		return 0<=nx && nx < N && 0<=ny && ny<M;
	}
	
	static class bridge implements Comparable<bridge>{
		int from, to, weight;

		public bridge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(bridge 다리) {
            return Integer.compare(weight, 다리.weight);
		}
		
	}

}
