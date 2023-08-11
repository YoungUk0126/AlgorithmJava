package bj.s1;
/**
@author 김영욱
@since 2023. 08. 11
@see https://www.acmicpc.net/problem/1697
@git
@performance
@category #
@note
수빈이의 위치가 X일 때 걷는다면 1초 후 X-1, X+1
순간이동을 하는 경우에는 2*X로 이동
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_1697_숨바꼭질 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N,K,min;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		min = Integer.MAX_VALUE;
		dfs(0, 0);
		System.out.println(min);
	}
	//파멸적인 재귀 상태
	static void dfs(int cnt, int sum) {
		if(sum==K) {
			min = Math.min(cnt, min);
			return;
		}
		
		if(sum * 2  < K) {
			dfs(cnt + 1, sum*2);
		}
		else {
			dfs(cnt + 1, sum-1);
		}
	}
	
}
