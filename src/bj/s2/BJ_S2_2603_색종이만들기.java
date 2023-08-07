package bj.s2;
/**
@author 김영욱
@since 2023. 08. 07
@see https://www.acmicpc.net/problem/2630
@git
@performance
@category #
@note
전체 종이의 크기는 N*N
전체 종이가 모두 같은 색으로 칠해져 있지 않다면
가로, 세로로 중간 부분을 잘라서 똑같은 크기의 네 개의 색종이로 나눈다.
나누어진 종이 각각에 대해서도 같은 색으로 칠해져 있지 않으면 같은 방법으로 네 개의 색종이로 나눈다.
잘라진 종이가 모두 0이거나 1이거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없다면 스탑.

재귀를 사용해야 하는 문제인 것 같다.
가로 세로 2중 for문을 돌다가 처음 값의 숫자와 달라진다면 break후 재귀
시작 좌표 x1, y1
끝 좌표 x2, y2
(x1, y1, x2/2-1, y2/2-1)
(x1,y1/2-1, x2/2-1, y2)
(x2/2-1, y1, x2, y2/2-1)
(x1/2-1, y1/2-1, x2/2-1, y2/2-1)

처음 값의 숫자와 달라진 적이 없다면 해당하는 색ans에 +1
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_2603_색종이만들기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, cntBlue, cntWhite;
	static int board[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		cntBlue = 0; // 1일때 파란색
		cntWhite = 0; // 0일때 하얀색
		
		board = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

	}
	
	static void solve(int x1, int y1, int x2, int y2) {
		if((x2 - x1) == 1 || ) {
			if(board[x1][y1] == 1) {
				cntBlue++;
			} else {
				cntWhite++;
			}
			return;
		}

		for(int x=x1; x<x2; x++) {
			for(int y=y1; y<y2; y++) {
				
			}
		}
	}

}
