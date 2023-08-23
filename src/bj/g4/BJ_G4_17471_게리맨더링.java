package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @since 2023. 08. 23
 * @see https://www.acmicpc.net/problem/17471
 * @git
 * @performance 100ms
 * @category #
 * @note 시간 : 0.5초 N : 10, 구역의 인구수 : 100 백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지
 *       번호가 매겨져 있다. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다. 선거구는 구역을
 *       적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어야 한다. 구역 A에서 인접한 구역을 통해서 구역
 *       B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에
 *       포함된 구역이어야 한다.
 * 
 * 
 */
public class BJ_G4_17471_게리맨더링 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int min = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] v;

	static 구역[] 구역리스트;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(input.readLine());
		구역리스트 = new 구역[N + 1]; // 1 base
		tokens = new StringTokenizer(input.readLine());
		구역리스트[0] = new 구역(0, 0);
		for (int i = 1; i <= N; i++) { // 1base 구역리스트
			int 인구수 = Integer.parseInt(tokens.nextToken());
			구역리스트[i] = new 구역(i, 인구수);
		}
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i <= N; i++) { // 1base graph
			tokens = new StringTokenizer(input.readLine());
			int length = Integer.parseInt(tokens.nextToken());
			for (int j = 0; j < length; j++) {
				int to = Integer.parseInt(tokens.nextToken());
				graph.get(i).add(to);
			}
		}
		makeSubset(1, new boolean[N + 1]);
//        for (int i = 1; i <= N; i++) {
//            System.out.println(i + "에 연결 되어 있는 리스트들");
//            for (int j : graph.get(i)) {
//                System.out.print(j + " ");
//            }
//            System.out.println();
//        }
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	static void makeSubset(final int nthCheck, boolean[] status) {
		// 기저 조건
		if (nthCheck == status.length) {
			v = new boolean[N + 1];
			int blue = 0;
			int red = 0;
			for (int i = 1; i < status.length; i++) {
				boolean check = status[i];
				if (check) {
					blue++;
				} else {
					red++;
				}
			}
			// 다 같은 구역이라면 선거구역이 한개니까
			if (blue == N || red == N)
				return;
			// bfs로 모든 노드들이 이어져 있는지 테스트
			v = new boolean[N + 1];
			bfs(true, status);
			bfs(false, status);
			for (int i = 1; i < v.length; i++) {
				if (!v[i])
					return; // 하나라도 false라면 연결되어 있지 않음
			}
			// 다 각자 연결되어 있다.
			blue = 0;
			red = 0;
			for (int i = 1; i < 구역리스트.length; i++) {
				if (status[i]) {
					blue += 구역리스트[i].인구수;
				} else
					red += 구역리스트[i].인구수;
			}
			min = Math.min(min, Math.abs(blue - red));

			return;
		}
		// 재귀 처리
		status[nthCheck] = true;
		makeSubset(nthCheck + 1, status);
		status[nthCheck] = false;
		makeSubset(nthCheck + 1, status);
	}

	// true면 블루팀 false면 레드팀
	private static void bfs(boolean team, boolean[] status) {
		int startIdx = 0;
		for (int i = 1; i < status.length; i++) {
			if (status[i] == team) {
				startIdx = i;
				break;
			}
		}
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(startIdx);
		v[startIdx] = true;

		while (!q.isEmpty()) {
			int current = q.poll();
			for (int i : graph.get(current)) {
				if (!v[i] && status[i] == team) {
					q.offer(i);
					v[i] = true;
				}
			}
		}

	}

	static class 구역 {
		int 이름;
		int 인구수;

		public 구역(int 이름, int 인구수) {
			super();
			this.이름 = 이름;
			this.인구수 = 인구수;
		}

	}
}
