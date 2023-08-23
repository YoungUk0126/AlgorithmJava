package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @since 2023. 08. 22
 * @see https://www.acmicpc.net/problem/15683
 * @git
 * @performance
 * @category #
 * @note 시간 : 1초 N, M : 8이하 N*M 크기의 직사각형 K개의 CCTV가 설치되어 있는데, CCTV는 5가지 종류가 있다. 1
 *       : 한 쪽 방향만 감시할 수 있다. 2 : 2방향을 감시하는데, 감시하는 방향이 서로 반대 방향 3 : 2방향을 감시하는데,
 *       감시하는 방향이 직각 방향 4 : 세 방향 5 : 네 방향
 * 
 *       6 : 벽인데 CCTV가 벽을 통과할 수 없다.
 * 
 *       CCTV는 회전 시킬 수 있는데 90도 방향으로 해야한다. CCTV는 CCTV를 통과할 수 있다. 사각 지대의 최소 크기를
 *       구하는 프로그램을 작성하시오.
 * 
 *       각 CCTV마다 방향이 정해져 있고 다른 CCTV와의 모든 조합마다 최소값이 달라지기 때문에
 *       순열을 적용해야 한다...
 *       
 *       N, M을 받고 Map을 저장한다.
 *       Map을 입력 받는 도중 CCTV를 만나면 ArrayList에 CCTV 객체를 추가한다.
 *       CCTVList크기 만큼 중복순열을 저장할 배열을 선언한 다음
 *       4방향이니 0~4까지 돌면서 중복 순열 리스트에 값 저장
 *       값이 나오면 새로운 copyMap을 만든 후 나온 순열을 돌면서 cctv와 방향을 보낸다.
 *       cctv의 타입을 받은 후 cctv의 타입과 방향에 맞춰서 그림을 그리는 메소드를 호출한다.
 *       호출 받은 방향으로 맵 끝까지, 6을 만나기전까지 나아가며 그림을 그린다.
 *       
 *       다 그리면 copyMap에 0을 체크한 후 그 개수를 min과 비교하여 최솟값을 찾는다.
 *       모든 경우의 수를 찾을 때까지 돈다.
 * 
 */

public class BJ_G4_15683_감시 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int N, M;
	static int[][] map;
	static int[][] copyMap;
	static int[] perList;
	static int min;
	// 상우하좌
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<CCTV> cctvList;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new int[N][M];
		cctvList = new ArrayList<>();
		
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if (map[i][j] > 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		perList = new int[cctvList.size()];
		makePermutation(0, cctvList.size());
		System.out.println(min);
		

	}
	
	private static void makePermutation(int depth, int size) {
		if(depth == size) {
			// 맵을 배껴옴
			copyMap = new int[N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}
			// cctvlist 사이즈만큼 돌아서 방향이라는 함수로 보냄
			for(int i=0; i<size; i++) {
				direction(cctvList.get(i), perList[i]);
			}
			
			getMin();
			
			return;
		}
		// 4방향중에 구하는거니까 4까지
		for(int i=0; i<4; i++) {
			perList[depth] = i;
			makePermutation(depth+1, size);
		}
		
	}
	private static void getMin() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyMap[i][j] == 0) cnt++;
			}
		}
		min = Math.min(min, cnt);
	}

	// 방향에서 각 씨씨티비의 타입에 따라 그림을 그려줌
	private static void direction(CCTV cctv, int d) {
		int type = cctv.type;
		if(type == 1) {
			draw(cctv, d);// 들어온 방향대로 그려주면 끝
		}
		else if(type == 2) {
			if(d == 0 || d == 2) {
				draw(cctv, 0);
				draw(cctv, 2);
			}else {
				draw(cctv, 1);
				draw(cctv, 3);
			}
		}
		else if(type == 3) {
			if(d==0) {
				draw(cctv, 0);
				draw(cctv, 1);
			}
			else if(d==1) {
				draw(cctv,1);
				draw(cctv,2);
			}
			else if(d==2) {
				draw(cctv,2);
				draw(cctv,3);
			}
			else if(d==3) {
				draw(cctv,3);
				draw(cctv,0);
			}
		}
		else if(type == 4) {
			if(d==0) {
				draw(cctv, 3);
				draw(cctv, 0);
				draw(cctv, 1);
			}
			else if(d==1) {
				draw(cctv,0);
				draw(cctv,1);
				draw(cctv,2);
			}
			else if(d==2) {
				draw(cctv,1);
				draw(cctv,2);
				draw(cctv,3);
			}
			else if(d==3) {
				draw(cctv,2);
				draw(cctv,3);
				draw(cctv,0);
			}
		}
		else if(type == 5) {
			draw(cctv,0);
			draw(cctv,1);
			draw(cctv,2);
			draw(cctv,3);
		}
	}

	private static void draw(CCTV cctv, int d) {
		int startX = cctv.x;
		int startY = cctv.y;
		
		int nx = startX + deltas[d][0];
        int ny = startY + deltas[d][1];
        while (isIn(nx, ny) && copyMap[nx][ny] != 6) {
        	copyMap[nx][ny] = -1;
            nx = nx + deltas[d][0];
            ny = ny + deltas[d][1];
        }
	}

	static class CCTV{
		int x;
		int y;
		int type;
		public CCTV(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
