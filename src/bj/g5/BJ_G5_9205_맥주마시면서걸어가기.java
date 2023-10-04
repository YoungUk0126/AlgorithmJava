package bj.g5;

/**
@author 본인이름
@since 2023. 9. 27.
@see https://www.acmicpc.net/problem/9205
@git
@youtube
@performance
@category #
@note 
시간: 1초
N(편의점 개수) : 0 <= N <= 100

맥주를 마시면서 락 페스티벌에 가려고 한다.
출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다.
맥주 한 박스에는 맥주가 20개가 들어있다. 50미터에 한 병씩 마시려고 한다.
즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.

페스티벌 가는 곳에는 편의점이 있는데, 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다.
하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 편의점을 나선 직후에도 50미터를 가기전에 맥주 한 병을 마셔야 한다.
편의점, 상근이네 집, 락 페스티벌의 좌표가 주어진다.
페스티벌에 행복하게 도착할 수 있다면 "happy" 안되면 "sad"를 출력하라

입력
t = 테스트 케이스 개수 ( 50개 )
N = 편의점 개수 ( 100개 )
N + 2개 줄에는 상근이네 집, 편의점, 락 페스티벌 좌표가 주어진다. x와 y로 이루어져있다.
( 32768 <= x,y <= 32767 )
두 좌표 사이의 거리는 x 좌표의 차이 + y좌표의 차이이다.(맨해튼 거리)

처음에는 무작정 다음 편의점으로 향하게 하여 갈 수 있다면( 맥주가 남는다면 ) 가게끔
편의점 개수만큼 반복 후 마지막 편의점에서 페스티벌장으로 갈 수 있다면 happy를 출력하게 했다.
테케는 통과하는데 3%에서 걸린다.

무조건 편의점을 들리는게 아니라 하나의 좌표처럼 생각해야 할 것 같다.
BFS식으로 좌표를 도는데, 대각선 방향까지 고려를 해줘야할 것 같다
그럼 이동할때마다 50씩 이동하여, 맥주 좌표에 닿는다면 맥주 값을 다시 20으로 세팅하고
만약 맥주 값이 0이하로 떨어진다면 그 이동경로는 OUT!!

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_9205_맥주마시면서걸어가기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int T, N;
	static Pos[] posList;
	static boolean[] visited;
	static Queue<Pos> q;
	static Pos home, end;
	// 시계방향
	static int[][] deltas = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
	

	public static void main(String[] args) throws IOException {

		T = Integer.parseInt(input.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(input.readLine());
			posList = new Pos[N];
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			home = new Pos(x,y);
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				x = Integer.parseInt(tokens.nextToken());
				y = Integer.parseInt(tokens.nextToken());
				
				posList[i] = new Pos(x,y);
			}
			tokens = new StringTokenizer(input.readLine());
			x = Integer.parseInt(tokens.nextToken());
			y = Integer.parseInt(tokens.nextToken());
			end = new Pos(x, y);
			
//			if(canWeHappy(home.x, home.y, end.x, end.y))
//				builder.append("happy").append("\n");
//			else
				LetsGo();
		}
		System.out.println(builder);

	}
	
	private static void LetsGo() {
		q = new ArrayDeque<>();
		visited = new boolean[N];
		
		q.offer(home);
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			if(canWeHappy(now.x, now.y, end.x, end.y)) {
				builder.append("happy").append("\n");
				return;
			}
			
			int x = now.x;
			int y = now.y;
			
			for(int i=0; i<posList.length; i++) {
				if(!visited[i] && canWeHappy(x, y, posList[i].x, posList[i].y)) {
					visited[i] = true;
					q.offer(posList[i]);
				}
			}
		}
		
		builder.append("sad").append("\n");
		
	}
	
	private static boolean canWeHappy(int x, int y, int nx, int ny) {
		int distance = Math.abs(Math.abs(nx - x) + Math.abs(ny - y));
		return distance <= 1000;
	}

	static class Pos {
		int x,y;
		Pos() {}
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
