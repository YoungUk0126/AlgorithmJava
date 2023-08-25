package sw.d4;

/**
 * @author 김영욱
 * @since 2023.08.25
 * @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV4suNtaXFEDFAUf&probBoxId=AYoaJoMabgADFAU6&type=PROBLEM&problemBoxTitle=0821%EC%A3%BC&problemBoxCnt=4
 * @git
 * @performance 
 * @category #
 * @note 
 * 시간 : 60개의 테스트케이스 4초
 * N : 12
 * Core : 12
 * 
 * N * N 개의 cell로 구성
 * 각 칸에는 1개의 Core 혹은 전선이 올 수 있다
 * 맥시노스의 가장 자리(테두리)에는 전원이 흐르고 있다.(맵의 끝까지 전선을 연결하면 core가 연결된거)
 * 만약 코어가 맵의 끝에 존재한다면 이미 전원이 흐르는거라 전선 필요 없음
 * 
 * 전선은 서로 교차할 수 없다.
 * 최대한 많은 Core에 전원을 연결해도, 전원이 연결되지 않는 Core가 존재할 수 있다.
 * 우선순위
 * 1. 최대한 많은 Core에 전원을 연결하였을 경우, => 2. 전선 길이의 합이 최소가 되는 값을 구하라.
 * 
 * 또 낚일 뻔했다, CCTV랑 거의 같은 유형이네, 또 각 Core마다 가장 짧은 길이 가게끔 할 뻔했다.
 * 
 * 각 Core마다 전선을 연결할 방향을 정해줘야함, Core길이만큼의 방향 순열이 완성되면
 * 새로운 맵을 복사해서 그 맵에 전선을 연결해줌
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_D4_프로세서연결하기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	// 상우하좌
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int[][] copyMap;
	static int[] perList;
	static int N, T, min;
	static int map[][];
	static ArrayList<Core> coreList;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			builder.append("#").append(tc).append(" ");

			N = Integer.parseInt(input.readLine().trim());
			map = new int[N][N];
			coreList = new ArrayList<>();
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
					// 경계선에 있는 코어가 아니라면
					if (i != 0 || j != 0 || i != N-1 || j != N-1) {
						if (map[i][j] == 1) {
							coreList.add(new Core(i, j));
						}
					}
				}
			}
			// core에 각각 방향을 정해서 넘겨주기 위해 순열 리스트를 선언
			perList = new int[coreList.size()];
			makePermutation(0, coreList.size());
			
			builder.append(min).append("\n");

		}
		System.out.println(builder);

	}

	private static void makePermutation(int cnt, int size) {
		// 기저 조건
		if (cnt == size) {
			// 맵을 배껴옴
			copyMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}
			boolean flag = true;
			for(int i = 0; i < size; i++) {
				flag = draw(coreList.get(i), perList[i]);
				if(!flag) break;
			}
			
			if(flag)getMin();
			
			
			
			return;
		}
		// 상하좌우 4방향 중복으로 넘겨줌
		for (int d = 0; d < 4; d++) {
			perList[cnt] = d;
			makePermutation(cnt + 1, size);
		}
		

	}
	
	private static void getMin() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(copyMap[i][j] == -1) cnt++;
				
			}
		}
		min = Math.min(min, cnt);
	}

	static class Core {
		int x, y;

		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static boolean draw(Core core, int d) {
		int startX = core.x;
		int startY = core.y;

		int nx = startX + deltas[d][0];
		int ny = startY + deltas[d][1];
		while (isIn(nx, ny)) {
			if(copyMap[nx][ny] == 1 || copyMap[nx][ny] == -1) {
				return false;
			}
			copyMap[nx][ny] = -1;
			nx = nx + deltas[d][0];
			ny = ny + deltas[d][1];
		}
		return true;
	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
