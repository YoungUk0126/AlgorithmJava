package bj.g1;
/**
@author 김영욱
@since 2023. 09. 19
@see https://www.acmicpc.net/problem/1194
@git
@performance
@category #
@note
방문 배열을 쓰긴 해야되는데 개조를 해야함
a,b,c,d,e를 먹었는지 안먹었는지 확인해줘야 하기 때문
8차원 배열을 만들 순 없기 때문에 3차원에 첫번째 값을 비트마스킹으로 처리해야함
기본 베이스는 Queue이다

반례
4 5
#f#..
#.#..
#a#..
0.FA1

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class pos{
	int x;
	int y;
	int key;
	int cnt;
	
	public pos(int x, int y, int key, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.key = key;
		this.cnt = cnt;
	}
}

public class BJ_G1_1194_달이차오른다 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int deltas[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static char[][] map;
	static int N, M;
	static pos start;
	static Queue<pos> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String temp = input.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == '0')
					start = new pos(i,j,0,0);
			}
		}
		System.out.println(bfs());
		

	}
	
	private static int bfs() {
		boolean [][][] visit = new boolean[64][N][M];
		q.offer(start);
		visit[0][start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			pos now = q.poll();
			
			if(map[now.x][now.y] == '1') {
				return now.cnt;
			}
			for(int d=0; d<4; d++) {
				int nx = now.x + deltas[d][0];
				int ny = now.y + deltas[d][1];
				
				// 갈 수 있다면, 간 적 없다면
				if(isIn(nx, ny) && map[nx][ny] != '#' && !visit[now.key][nx][ny]) {
					// 그냥 갈 수 있다면
					if(map[nx][ny] == '.' || map[nx][ny] == '0' || map[nx][ny] == '1') {
						visit[now.key][nx][ny] = true;
						q.offer(new pos(nx, ny, now.key, now.cnt+1));
					}
					// 소문자를 만난다면
					else if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						int newKey = 1<<(map[nx][ny] - 'a');
						newKey = newKey | now.key;
						if(!visit[newKey][nx][ny]) {
							visit[now.key][nx][ny] = true;
							visit[newKey][nx][ny] = true;
							q.offer(new pos(nx, ny, newKey, now.cnt+1));
						}
					}
					// 대문자를 만난다면
					else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						int door = 1<<(map[nx][ny] - 'A');
						// 현재 그 문에 해당하는 키를 소지하고 있다면
						if((door & now.key) > 0) {
							visit[now.key][nx][ny] = true;
							// 열쇠는 여러 번 사용할 수 있기 때문에 없애주지 않음
							q.offer(new pos(nx, ny, now.key, now.cnt+1));
						}
					}
				}
				
			}
		}
		return -1;
	}
	
	private static boolean isIn(int row, int col) {
        return (row >= 0 && row < N) && (col >= 0 && col < M);
    }
}
