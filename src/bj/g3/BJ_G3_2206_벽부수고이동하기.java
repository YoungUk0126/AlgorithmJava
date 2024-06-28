package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 @author 김영욱
 @since 2024.06.21
 @see https://www.acmicpc.net/problem/2206
 @git
 @performance
 @category #
 @note
 0,0에서 시작해서 N,M까지 가야함
 도중에 벽으로 막혀있으면 딱 한 번 뿌실 수 있음

 이런 경우 부수고 간 경우랑 안부수고 간 경우를 나눠서 visited배열을 짜야함
 나는 3차원 배열로 0,1로 표시했음 부술 수 있는 기회?로 나눠놓음
 그래서 벽을 만났을때 부술 수 있는 기회가 있고 그 곳을 한 번도 부수고 가본적이 가는거임
 그리고 이 visited는 내가 걸어온 길의 길이를 표시해 놔야함
 그래서 Queue에 넣을 때 {부술 수 있는 횟수, 다음 X, 다음 Y, 걸어온 길의 길이 }
 이렇게 넘겨줬음

 입력
 N M : 가로 세로
 맵  : 0은 평지, 1은 장애물

 map[N][M]
 visited[1][N][M]


 */

public class BJ_G3_2206_벽부수고이동하기 {
  static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder builder = new StringBuilder();
  static StringTokenizer tokens;

  static int N, M;
  static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
  static int[][] map;
  static int[][][] visited;

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(input.readLine());
    N = Integer.parseInt(tokens.nextToken());
    M = Integer.parseInt(tokens.nextToken());

    map = new int[N][M];
    for (int i = 0; i < map.length; i++) {
      String str = input.readLine();
      for (int j = 0; j < map[i].length; j++) {
        int num = str.charAt(j) - '0';
        if (num == 1) {
          map[i][j] = num;
        }
      }
    }

    bfs(0, 0);
    if(visited[0][N-1][M-1] == 0 && visited[1][N-1][M-1] == 0) System.out.println(-1);
  }

  private static void bfs(int startX, int startY) {
    Queue<int[]> q = new ArrayDeque<>();
    visited = new int[2][N][M];// 부순거 부수지 않은거

    int move = 1;// 시작한 곳도 1이니까
    visited[1][0][0] = move;// 벽을 부술 수 있는 기회가 있을 때 부터 시작
    visited[0][0][0] = move;// 벽 부순 후에 돌아갈 수도 있잖아
    q.offer(new int[]{1, startX, startY, move});
    while(!q.isEmpty()) {
      int[] now = q.poll();
      int remain = now[0];
      int x = now[1];
      int y = now[2];
      move = now[3];

      if(x == N-1 && y == M-1) {
        System.out.println(move);
        break;
      }

      for(int i=0; i<4; i++) {
        int dx = x + deltas[i][0];
        int dy = y + deltas[i][1];

        if(!isIn(dx, dy)) continue;

        if(map[dx][dy] == 0 && visited[remain][dx][dy] == 0) {// 벽이 없으면 걍 가
          visited[remain][dx][dy] = move+1;
          q.offer(new int[]{remain, dx, dy, move+1});
        } else if (map[dx][dy] == 1) {
          if(remain > 0 && visited[remain-1][dx][dy] == 0) {// 벽 부술 기회가 있고 지금 부수고 가는게 안가본 길이면
            visited[remain-1][dx][dy] = move+1;
            q.offer(new int[]{remain-1, dx, dy, move+1});
          }
//          부술 기회가 없거나 더 짧은 길이 아니면 안감 ㅋㅋ 예외날 수도 있겠다
        }
      }
    }
  }
  private static boolean isIn(int x, int y) {
    return 0<=x && x<N && 0<=y && y<M;
  }
}
