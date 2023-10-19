package bj.g2;
/**
@author 김영욱
@since 2023. 10. 19.
@see https://www.acmicpc.net/problem/1007
@git
@performance 
@category #
@note
각 점들마다 짝을 지어줘서( 조합으로 뽑자 nCn/2)
점들의 가로 세로 길이를 구한 후 
피타고라스 정의를 이용하여 대각선 길이를 구한다.
( Math.sqrt(|가로1 - 가로2|^2 + |세로1 - 세로2|^2))
화이팅^^!

**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_1007_벡터매칭 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N, T;
	static cord[] cords;
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		
		int w = 200000;
		int h = 200000;
		System.out.println(Math.sqrt(Math.pow(w, 2)+ Math.pow(h, 2)));
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(input.readLine());
			cords = new cord[N];
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				cords[i] = new cord(x, y);
			}
			
		}

	}
	
	static class cord{
		int x,y;
		
		public cord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
