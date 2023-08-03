package sw.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 2.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq
@git
@performance
@category #
@note


*/
public class SW_D2_달팽이 {
	static int[][] deltas = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static int[][] board;
	static int T;
	static int N;
	static int cnt;
	static int total;
	static int direction;
	
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(input.readLine());
			board = new int[N][N];
			total = N*N;
			direction = 0;
			
			
			int r =0; int c = 0; cnt = 1;
			board[r][c] = cnt++;
			
			while(total>=cnt) {
				int nr = r + deltas[direction][0];
				int nc = c + deltas[direction][1];
				
				if(0<=nr && nr < N && 0<=nc && nc < N && board[nr][nc] == 0) {
					board[nr][nc] = cnt++;
					r = nr;
					c = nc;
				} else {
					direction = (direction+1)%4;
				}
			}
			builder.append("#").append(tc).append("\n");
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					builder.append(board[i][j]).append(" ");
				}
				builder.append("\n");
			}
		}
		System.out.println(builder);
	}

}
