package bj.g4;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/2580
@git
@performance
@category #
@note
시간 : 1초
스도쿠를 푸는 문제다.
2차원 배열을 주고 빈칸은 0으로 표시한다.
DFS를 이용하되 스도쿠 규칙을 이용하여 가지치기를 하는게 핵심이다.
입력을 받을 때 미리 한 개 밖에 들어갈 수 없는 곳은 체크를 해줘서 입력부터 숫자를 넣어주자.
가로를 체크하는 배열 1개
세로를 체크하는 배열 1개
3*3을 체크하는 배열 1개씩 일단 만들어보자

흠... 0을 찾을때 0을 찾으려고 계속 2차원 배열을 도는게 너무 비효율 적인거 같아.
입력받을때 0이면 스택에 좌표값을 미리미리 넣어둘까
그 입력받을때 0혼자면 채워주니까 그땐 스택에서 pop해주는거지

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_G4_2580_스도쿠 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int board[][];
	static boolean width[];
	static boolean height[];
	static boolean square[];
	static Stack<int[]> zeroLocation = new Stack<>();

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		board = new int [9][9];
		width = new boolean[9+1];
		height = new boolean[9+1];
		square = new boolean[9+1];
		for(int i=0; i<9; i++) {
			width = new boolean[9+1];
			int singleZeroCnt = 0;
			tokens = new StringTokenizer(input.readLine());
			int mI = 0;
			int mJ = 0;
			for(int j=0; j<9; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				if(board[i][j] == 0) {
					// 0입력부분 기억하기
					zeroLocation.push(new int[] {i,j});
					singleZeroCnt++;
				}
				// 가로 숫자 들어온거 인덱스 true로 바꿔주기
				width[board[i][j]] = true;
			}
			// 가로에 자리 하나만 남아있다면
			if(singleZeroCnt == 1) {
				for(int j=1; j<=9; j++) {
					if(!width[j]) {
						int[] zero = zeroLocation.pop();
						board[zero[0]][zero[1]] = j;
					}
				}
			}
		}
		width = new boolean[9+1];
		check(0,0);
		
	}
	private static void check(int row, int col) {
		// 가지 치기
		
		// 기저 조건
		
		// 재귀 조건
		if(board[row][col] == 0) {
			// 숫자 넣을 수 있는지 체크
			for(int i=1; i<=9; i++) {
				if(tripleCheck(row, col, i)) {
					board[row][col] = i;
					check(row, col+1);
				}
			}
		}

	}
	
	private static boolean tripleCheck(int row, int col, int value) {
		
	}

}
