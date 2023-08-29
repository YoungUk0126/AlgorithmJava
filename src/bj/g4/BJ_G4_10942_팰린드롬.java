package bj.g4;
/**
@author 김영욱
@since 2023. 08. 29
@see https://www.acmicpc.net/problem/10942
@git
@performance
@category #
@note
팰린드롬은 역삼역 같이 거꾸로 해도 같은걸 말함
2차원 배열을 만들어서 미리 싹다 1 5라면 1~n까지 팰린드롬인지 아닌지 계산해서 넣어놓고
질문이 들어오면 바로바로 인덱스에서 조회해서 출력할 수 있도록 만들까 구상중이다.

미래의 나 화이팅!
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_10942_팰린드롬 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, S, E, M, nums[];
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		nums = new int[N+1]; // 1 base
		for(int i=1; i<=N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}

		M = Integer.parseInt(input.readLine());
		for(int i=1; i<=M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(tokens.nextToken());
			int e = Integer.parseInt(tokens.nextToken());
			
			
		}
		
	}

}
