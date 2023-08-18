package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.08.18
@see https://www.acmicpc.net/problem/17135
@git
@performance
@category #
@note
성을 향해 몰려오는 적을 잡는 턴 방식의 게임을 진행함
N * M 격자판
각 칸에 포함된 적의 수는 최대 하나

###격자판의 N번행의 바로 아래의 모든 칸에는 성이 있다.###
###궁수 3명###을 배치하려고 해
성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있대
각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.
###궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적###
그런 적이 여러명일 경우 ###가장 왼쪽에 있는 적###
###같은 적이 여러 궁수에게 공격당할 수 있고###, 공격 받은 적은 게임에서 제외된다.
공격이 끝나면 적이 아래로 한 칸 이동함. 만약 성이 있는 칸으로 이동한 경우에는
게임에서 제외된다.

5C3의 조합으로 궁수의 위치를 구한 후, 각 궁수의 위치에서 범위 안에 있는 적의 위치를 찾아서
제거 (좌표 기억하고 있다가 0으로 바꾸고 그 숫자만큼 cnt해주면 될 듯?)
한 턴 끝나면 turn이라는 변수를 1씩 증가시켜서 궁수들의 행 인덱스에 빼주면서 좌표 계산 해주면 됨

이걸 조합의 개수만큼 해주면 됨(M의 개수를 몰라서 2차원 배열로 할 수가 없음)
각 조합에 해당하는 게임 진행 후 max값에 제일 큰 수 저장
*/
public class BJ_G3_17135_캐슬디펜스 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M, D, enemyCnt, max;
	static ArrayList<int[]> archers;
	static int forCom[];
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,-1}, {0,1}}; //상좌우
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());
		max = Integer.MIN_VALUE;
		forCom = new int[M];
		for(int m=0; m<M; m++) {
			forCom[m] = m;
		}
		
		map = new int[N+1][M];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		archers = new ArrayList<>();
		makeCombination(0, 0, new int[3]);
		playGame();
	}
	
	private static void makeCombination(int cnt, int start, int[] choosed) { 
		// 기저 조건
		if(cnt == choosed.length) {
			archers.add(choosed.clone());
			return;
		}
		// 반복 조건
		for(int i=start; i<forCom.length; i++) {
			choosed[cnt] = forCom[i];
			makeCombination(cnt+1, i+1, choosed);
		}
	}

	private static void playGame() {
		// TODO Auto-generated method stub
		int turn = 0;
		// 조합 별로
		for (int i = 0; i < archers.size(); i++) {
			// 맵의 행만큼 게임을 진행해야함
			for (turn = 0; turn < N; turn++) {
				// 조합 안에 궁수 세명 위치마다 가장 가까운 적 찾아
				for (int col : archers.get(i)) {
					bfs(N, col);
				}
			}
		}
	}

	private static void bfs(int startX, int startY) {
		// 준비물 챙겨라잇
		Queue<int[]> q = new ArrayDeque<>();
		boolean v[][] = new boolean[N][M];
		int min = Integer.MAX_VALUE;
		int[] target = new int[2];
		
		q.offer(new int[] {startX, startY});
		v[startX][startY] = true;
		
		/*
		 * 1. 거리가 D이하인 적 중에서 가장 가까운 적
		 * 2. 여럿일 경우 가장 왼쪽에 있는 적
		 * 3. 한 놈이 계속 맞을 수도 있음
		 */
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			int x = temp[0];
			int y = temp[1];
			
			for(int d=0; d<3; d++) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				
				if(isIn(nx, ny) && map[nx][ny] == 1 && !v[nx][ny] 
						&& isRange(startX, startY, nx, ny)){
					v[nx][ny] = true;
					q.offer(new int[] {nx, ny});
					// 다음에 갈놈의 거리가 원래 적과의 거리보다 짧다면 타겟팅을 바꿈
					int distance = Math.abs(startX - nx) + Math.abs(startY - ny);
					if(min > distance) {
						target[0] = nx;
						target[1] = ny;
						min = distance;
					}
				}
			}
		}
	}
	private static boolean isIn(int x, int y) {
		return 0<x && x<=N && 0<y && y<=M;
	}
	private static boolean isRange(int ax, int ay, int x, int y) {
		if(D >= Math.abs(ax - x) + Math.abs(ay - y)) {
			return true;
		}
		return false;
	}

}
