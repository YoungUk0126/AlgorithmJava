package bj.s5;
/**
@author 김영욱
@since 2023. 08. 09
@see https://www.acmicpc.net/problem/2563
@git
@performance
@category #
@note
가로, 세로의 크기가 각각 100인 정사각형 흰색 도화지
이 도화지 위에 10*10 크기의 검은색 색종이를 붙이려해
검은 영역의 넓이를 구하는 프로그램을 작성해.

N : 검은색 색종이 개수
두 개의 정수를 받음
왼쪽 변과 도화지의 왼쪽 변 사이의 거리
아래쪽 변과 도화지 아래쪽 변 사이의 거리

점화식이 있는 줄 알았는데 그냥 100*100 boolean map을 주고
true로 바꾸는 숫자만큼 세서 내면 된다;;


*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_2563_색종이 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N, cnt;
	static boolean v[][] = new boolean[101][101];
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		for(int t=0; t<N; t++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					if(!v[x+i][y+j]) {
						v[x+i][y+j] = true;
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}

}
