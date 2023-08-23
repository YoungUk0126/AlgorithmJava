package bj.g4;

/**
@author 김영욱
@since 2023.08.23
@see https://www.acmicpc.net/problem/6497
@git 
@performance 748ms
@category #
@note
시간 1초

전력을 아끼려고 한다.
모든 길마다 원래 켜져 있던 가로등 중 일부를 소등하기로 했다.(간선을 자르기로 했다)
가로등을 켜 두면 하루에 길의 미터 수만큼 돈이 들어가는데, 일부를 소등하여 그만큼의 돈을 절약할 수 있다.
모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로를 왕래할 수 있어야 한다.
절약할 수 있는 최대 액수(원래 액수에서 MTS로 줄인 액수 빼주면 됨)


V, E : 집의 수V, 길의 수E ( 결국 정점과 간선의 개수다 )
x, y, z : x번 집과 y번 집 사이에 양방향(무향 그래프)도로가 있으며 그 거리가 z미터이다.

도시는 항상 연결 그래프의 형태이다.
모든 길의 거리 합은 2^31미터보다 작다.(int형 써도 됨)

입력을 받을 때 z의 모든 합을 더해 놓고
크루스칼 알고리즘으로 최소 거리 트리를 구해서 그 가중치의 합을 원래 합에서 빼주면 그게 답이다.

0 0이 나오면 첫번째 테스트 케이스 끝인거고 더 나올 수도 있다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_6497_전력난 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int V, E, x, y, z;
	static int 원래합, 줄인합;
	static int[] parents;
	static Light[] lightList;

	public static void main(String[] args) throws IOException {
		while (true) {
			원래합 = 0;
			줄인합 = 0;
			tokens = new StringTokenizer(input.readLine());
			
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			
			if(V == 0 && E == 0) break;
			lightList = new Light[E];
			for (int i = 0; i < E; i++) {
				tokens = new StringTokenizer(input.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				int z = Integer.parseInt(tokens.nextToken());
				lightList[i] = new Light(x, y, z);
				원래합 += z;
			}
			make();

			Arrays.sort(lightList);
			int cnt = 0;
			for (Light light : lightList) {
				if (union(light.x, light.y)) {
					줄인합 += light.meter;
					if (++cnt == V - 1)
						break;
				}
			}
			System.out.println(원래합 - 줄인합);
		}
	}

	private static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}

	}

	private static int find(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	static class Light implements Comparable<Light> {
		int x, y, meter;

		public Light(int x, int y, int meter) {
			super();
			this.x = x;
			this.y = y;
			this.meter = meter;
		}

		@Override
		public int compareTo(Light light) {
			return Integer.compare(this.meter, light.meter);
		}

	}

}
