package bj.g4;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/9663
@git
@performance
@category #
@note
이 방법 외에도 대각선을 체크하는 배열을 두 개 만들어서
하나는 행,열합이 같은 곳은 못가게 하는 가지치기
다른 하나는 행열 차이 + N이 같은 곳은 못가게 하는 가지치기를 한다.
그럼 더 빠르다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 같은 행에는 퀸을 놓지 않는 버전
// 놓아진 퀸의 열번호를 기록하는 버전
public class BJ_G4_9663_NQueen {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N;
	static long ans;
	static int col[]; // 퀸이 놓인 열번호 기록지
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		
		col = new int[N+1]; // 1행부터 사용할거라 크기+1
		ans = 0; // 가능한 경우의 수
		
		setQueen(1);
		System.out.println(ans);
	}
	// 해당 퀸을 가능한 모든 곳에 놓아보기
	private static void setQueen(int row) {
		// 가지치기
		if(!isAvailable(row-1)) return;
		// 기저조건
		if(row > N) {
			ans++;
			return;
		}
		//유도파트
		for (int c = 1; c <= N; c++) { // 1열부터 N열까지 시도
			col[row] = c;
			setQueen(row+1);
		}
	}
	
	private static boolean isAvailable(int row) { // row: 마지막으로 놓아진 퀸의 행
		
		for (int i = 1; i < row; i++) {
			// 행차이와 열차이의 절댓값이 같으면 대각선에 있다고 판단.
			if(col[i] == col[row] || row-i == Math.abs(col[row] - col[i])) return false;
		}
		return true;
		
	}
}
