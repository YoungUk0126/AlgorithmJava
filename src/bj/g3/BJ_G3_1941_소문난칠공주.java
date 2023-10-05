package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.09.19
@see https://www.acmicpc.net/problem/1941
@git
@performance
@category #
@note
시간: 2초
총 25명의 여학생들로 이루어진 여학생반은 5*5의 정사각형 격자 형태로 자리가 배치,
'이다솜'과 '임도연' 두 파로 갈라지게 되었으며 얼마 지나지 않아 '임도연'파가 세력을 확장시키며
'이다솜'파를 위협하기 시작했다.

'이다솜'파는 소문난 칠공주를 결성하려고 한다.
규칙
1. 7명의 여학생들로 구성되어야 한다.
2. 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
3. 화합과 번영을 위해, 반드시 '이다솜파'일 필요는 없지만, '이다솜파'가 4명 이상은 포함되어야 한다.

소문난 칠공주를 결성할 수 있는 모든 경우의 수를 구해라

S가 4개 이상, 모두 인접한, 7개의 배열

25C7의 조합을 구해
Y > 3이면 가지치기
탐색 알고리즘으로 모두가 인접해 있는지 확인, 인접해 있지 않다면 가지치기

만약 7부터 시작이라면 일단 7%5 == 1이 아니라면
*/
public class BJ_G3_1941_소문난칠공주 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static char[][] students = new char[5][5];
	static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int[] nums;
	static int ans;
	
	public static void main(String[] args) throws IOException{
		for(int i=0; i<5; i++) {
			String temp = input.readLine();
			for(int j=0; j<5; j++) {
				students[i][j] = temp.charAt(j);
			}
		}
		nums = new int[25];
		for(int i=0; i<25; i++) {
			nums[i] = i;
		}
		Combination(0, 0, new int[7], 0);
		System.out.println(ans);
	}
	
	static void Combination(int cnt, int start, int[] choosed, int yCnt) {
		if(cnt == choosed.length) {
			// 모두 이어져 있는지 확인
			if(bfs(choosed[0]/5 , choosed[0]%5, choosed)) {
				ans++;
			}
			return;
		}
		
		for(int i=start; i<nums.length; i++) {
			choosed[cnt] = nums[i];
			int index = choosed[cnt];
			if(students[index/5][index%5] == 'Y') {
				if(yCnt+1 > 3) return;
				Combination(cnt + 1, i + 1, choosed, yCnt + 1);
			}
			else
				Combination(cnt + 1, i + 1, choosed, yCnt);
		}
		
	}

	private static boolean bfs(int i, int j, int[] choosed) {
		boolean [][] visited = new boolean[5][5];
		Queue<int []> q = new ArrayDeque<>();
		q.offer(new int[] {i,j});
		visited[i][j] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			//4방향
			for(int d=0; d<4; d++) {
				int nx = now[0] + deltas[d][0];
				int ny = now[1] + deltas[d][1];
				
				if(isIn(nx, ny) && !visited[nx][ny] && isInChoosed(nx, ny, choosed)) {
					cnt++;
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		if(cnt == 7) return true;
		else return false;
		
	}
	
	private static boolean isInChoosed(int x, int y, int[] choosed) {
		for(int num: choosed) {
			if(num / 5 == x && num % 5 == y) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isIn(int x, int y) {
		return 0<=x && x<5 && 0<=y && y<5;
	}

}
