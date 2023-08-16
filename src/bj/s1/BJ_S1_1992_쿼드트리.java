package bj.s1;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/1992
@git
@performance 80ms
@category #
@note
시간 : 2초
주어진 영상 범위를 4분할로 나눈다.
그 영상 범위 전체가 다 0이거나 1이면 더 들어갈 필요 없음
아니라면 또 그안에서 4분할

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_1992_쿼드트리 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int N;
	static int[][] video;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		video = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = input.readLine();
			for(int j=0; j<N; j++) {
				video[i][j] = Integer.valueOf(str.charAt(j)) - '0';
			}
		}

		recur(0,0,N);
		System.out.println(builder);
	}
	
	static void recur(int x, int y, int size) {
		// 기저 조건
		if(can(x, y, size)) {
			if(video[x][y] == 1) builder.append(1);
			else builder.append(0);
			return;
		}
		int mid = size/2;
		builder.append("(");
		recur(x, y, mid);
		recur(x, y+mid, mid);
		recur(x+mid, y, mid);
		recur(x+mid, y+mid, mid);
		builder.append(")");
	}
	
	static boolean can(int x, int y, int size) {
		int color = video[x][y];
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(color != video[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
