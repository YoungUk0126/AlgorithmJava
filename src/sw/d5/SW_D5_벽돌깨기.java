package sw.d5;

/**
 * @author 김영욱
 * @since 2023. 10. 05
 * @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWXRQm6qfL0DFAUo&probBoxId=AYr3k03KgA4DFAV6&type=PROBLEM&problemBoxTitle=1004%EC%A3%BC&problemBoxCnt=4
 * @performance 
 * @category 
 * @note
 * 
 * 50개 TC 합쳐서 3초
 * 
 * N<=4
 * W<=12 , H <= 15
 * 
 * 구슬을 쏘아 벽돌을 깨뜨리는 게임
 * N번 쏘고, W * H 배열로 주어진다.
 * 
 * 규칙 1. 구슬은 좌,우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * 규칙 2. 벽돌은 숫자 1 ~ 9로 표현되며, 구슬이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 -1 ) 칸 만큼 같이 제거된다.
 * 
 * N 개의 구슬을 쏘아 최대한 많은 벽돌을 제거하려 한다.
 * 최대한 많은 벽돌을 제거한 후 남은 벽돌의 개수를 구하라!
 * 
 * 터트린 후 벽돌을 옮기는 것은
 * 0이아닌 숫자들을 밑에서 부터 읽은 후( 행-- )
 * 밑에서부터 poll해주면 끝~!
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.Delayed;

public class SW_D5_벽돌깨기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int T, N, W, H, ans;
	static int map[][];
	static int copyMap[][];
	static ArrayList<int[]> per;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			H = Integer.parseInt(tokens.nextToken());
			ans = Integer.MAX_VALUE;

			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				tokens = new StringTokenizer(input.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			per = new ArrayList<>();
			makeDupPermu(0, new int[N]);

			startGame();
			builder.append("#").append(tc).append(" ").append(ans).append("\n");

		}
		System.out.println(builder);

	}

	private static void startGame() {
		for (int[] now : per) {
			copyMap = new int[H][W];
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					copyMap[h][w] = map[h][w];
				}
			}
			for (int i = 0; i < N; i++) {
				dropBall(now[i]);
				// 공떨구고 벽돌 다 지우고 남은거 세줘야함
				breakWall();

			}
			int cnt = 0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(copyMap[i][j] > 0) cnt++;
				}
			}
			ans = Math.min(ans, cnt);
		}

	}

	private static void breakWall() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int j = 0; j < W; j++) {
			for (int i = H - 1; i >= 0; i--) {
				if (copyMap[i][j] > 0) {
					q.offer(copyMap[i][j]);
				}
			}
			for (int i = H - 1; i >= 0; i--) {
				if (!q.isEmpty()) {
					copyMap[i][j] = q.poll();
				} else {
					copyMap[i][j] = 0;
				}
			}
		}
	}

	private static void dropBall(int hitCol) {

		for (int i = 0; i < H; i++) {
			if (copyMap[i][hitCol] > 0) {
				checkWall(i, hitCol);
				break;
			}
		}
	}

	private static void checkWall(int startX, int startY) {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { startX, startY, copyMap[startX][startY] - 1 });
		copyMap[startX][startY] = 0;
		while (!q.isEmpty()) {

			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			int len = temp[2];

			for (int d = 0; d < 4; d++) {

				for (int j = 1; j <= len; j++) {
					int nx = x + deltas[d][0] * j;
					int ny = y + deltas[d][1] * j;
					if (isIn(nx, ny)) {
						if (copyMap[nx][ny] > 1) {
							q.offer(new int[] { nx, ny, copyMap[nx][ny] - 1 });
							copyMap[nx][ny] = 0;
						} else if (copyMap[nx][ny] == 1) {
							copyMap[nx][ny] = 0;
						}
					}
				}
			}
		}

	}

	private static void makeDupPermu(int cnt, int[] choosed) {
		if (cnt == N) {
			per.add(choosed.clone());
			return;
		}

		for (int i = 0; i < W; i++) {
			choosed[cnt] = i;
			makeDupPermu(cnt + 1, choosed);
		}

	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < H && 0 <= y && y < W;
	}

}
