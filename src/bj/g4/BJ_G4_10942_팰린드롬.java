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
계산해서 넣을 때 메모이제이션 방식을 사용해서 시간을 줄여야 한다.(그 방법이 젤 어렵긴 하지..)
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
	static boolean pelin[][];
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		nums = new int[N+1]; // 1 base
		for(int i=1; i<=N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		
		
		pelin = new boolean[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			pelin[i][i] = true;
			
		}
		for(int i=1; i<N; i++) {
			if(nums[i] == nums[i+1]) pelin[i][i+1] = true;
		}
		for(int i=2; i<N; i++) {
			for(int j=1; i+j<=N; j++) {
				// j+1이랑 j바로 앞에꺼 확인, 양끝 숫자 같은지 확인
				// i가 커질수록 s랑 e가 점점멀어지지만 이미 그 안에껀 계산해놓음
				if(pelin[j+1][j+i-1] && nums[j] == nums[j+i])
					pelin[j][j+i] = true;
			}
		}
		M = Integer.parseInt(input.readLine());
		for(int i=1; i<=M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(tokens.nextToken());
			int e = Integer.parseInt(tokens.nextToken());
			
			if(pelin[s][e]) builder.append("1").append("\n");
			else builder.append("0").append("\n");
		}
		
		System.out.println(builder);
		
	}

}
