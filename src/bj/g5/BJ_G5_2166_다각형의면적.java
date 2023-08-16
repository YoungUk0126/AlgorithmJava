package bj.g5;
/**
@author 김영욱
@since 2023. 08. 15
@see https://www.acmicpc.net/problem/2166
@git
@performance
@category #
@note
다각형 꼭지점의 좌표를 주면 다각형의 넓이를 구하는 문제이다.
삼각형으로 나눠서 푸는 방식을 생각했으나, 고1때 배우는 신발끈 공식으로 쉽게 풀 수 있는 문제이다.
https://darkpgmr.tistory.com/86
이 사이트에 다각형의 면적을 구하는 여러가지 공식이 잘 정리돼 있다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_2166_다각형의면적 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();

	static int N;
	static long[] x;
	static long[] y;
	
	static long red, blue;
	static double ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		
		x = new long[N+1];
		y = new long[N+1];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			x[i] = Integer.parseInt(tokens.nextToken());
			y[i] = Integer.parseInt(tokens.nextToken());
		}
		x[N] = x[0];
		y[N] = y[0];
		
		for (int i = 0; i < N; i++) {
			red += x[i] * y[i + 1];
			blue += y[i] * x[i + 1];
		}
		ans = Math.abs(red - blue)/2.0;

		System.out.printf("%.1f\n",ans);
		
		
	}

}
