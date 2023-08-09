package bj.g5;
/**
@author 김영욱
@since 2023. 08. 09
@see https://www.acmicpc.net/problem/16927
@git
@performance
@category #
@note
N*M크기의 배열이 있을때 배열을 반시계 방향으로 돌리려 한대
바깥부터 한 줄 한 줄씩 따로따로 회전을함.
N,M과 회전해야하는 수 R이 주어지는데 R이 어마무시하게 큼
R을 줄일 필요가 있어.
한바퀴 도는데 필요한 횟수는 큰값*2 + (작은값-2) *2야
그리고 한줄씩 안으로 들어갈 수록 양 값에 -2씩 해주면 됨




*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_16927_배열돌리기2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int deltas[][] = {{1,0}, {0,1},{-1,0},{0,-1}};
	static int N,M,R,repeat,max,min;
	static int arr[][];
	static int ans[][];
	static int m;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		int start = 0;
		
		max = Math.max(N, M);
		min = Math.min(N, M);
		
		arr = new int[N][M];
		ans = new int[N][M];

		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		while(min>0) {
			int maxRotate = (max*2) + ((min-2)*2);
			repeat = R%maxRotate;
			rotate(start, start, maxRotate);
			
			start++;
			max -= 2;
			min -= 2;
		}
		
		for(int[] row: ans) {
			for(int col: row) {
				builder.append(col).append(" ");
			}
			builder.append("\n");
		}
		System.out.println(builder);
		
	}
	
	static void rotate(int startX, int startY, int tempLength) {
		// repeat만큼 deltas더해서 돌려준다.
		int x = startX;
		int y = startY;
		int temp[] = new int[tempLength];
		temp[0] = arr[x][y];
		int cnt=1;
		int direction = 0;
		
		// 1차원 배열에 테두리 담기
		while(cnt<tempLength) {
			int nx= x + deltas[direction][0];
			int ny= y + deltas[direction][1];
			
			if(0+startX<=nx && nx < N-startX && 0+startY<=ny && ny < M-startY) {
				temp[cnt++] = arr[nx][ny];
				x = nx;
				y = ny;
			} else {
				direction = (direction+1)%4;
			}
		}
		cnt = 0;
		x = startX;
		y = startY;
		direction = 0;
		// 시작 위치 찾기
		while(cnt<repeat) {
			int nx= x + deltas[direction][0];
			int ny= y + deltas[direction][1];
			if(0+startX<=nx && nx < N-startX && 0+startY<=ny && ny < M-startY) {
				x = nx;
				y = ny;
				cnt++;
			} else {
				direction = (direction+1)%4;
			}
		}
		
		ans[x][y] = temp[0];
		cnt = 1;
		// 값 넣기
		while(cnt<tempLength) {
			int nx= x + deltas[direction][0];
			int ny= y + deltas[direction][1];
			if(0+startX<=nx && nx < N-startX && 0+startY<=ny && ny < M-startY) {
				x = nx;
				y = ny;
				ans[x][y] = temp[cnt++];
			} else {
				direction = (direction+1)%4;
			}
		}
	}

}
