package bj.s5;
/**
@author 김영욱
@since 2023.08.30
@see https://www.acmicpc.net/problem/1010
@git
@performance 
@category #
@note
시간 : 0.5초 , 0 <= N, M <= 30
도시 사이를 가로지르는 강에 다리를 짓기로 했음
다리를 짓기에 적합한 곳을 사이트라고 한다.
강의 서쪽에는 N개의 사이트, 동쪽에는 M개의 사이트가 있다.

서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다.(한 사이트에는 한개의 다리만 연결)
다리를 최대한 많이 지으려고 한다.(서쪽의 사이트 개수만큼)
다리끼리는 겹쳐질 수 없다.

M개에서 N개를 뽑는 조합인 것 같은데 그냥 조합을 돌리면 시간 초과가 날 것 같다.

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_1010_다리놓기 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			tokens = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			// MCN
			int dp[][] = new int[M+1][N+1];
			
			for(int i=0; i<=M; i++) {
				for(int j=0, end = Math.min(i, N); j<=end; j++) {
					if(j == 0 || i == j) dp[i][j] = 1;
					else dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
			
			builder.append(dp[M][N]).append("\n");
		}
		System.out.println(builder);
	}

}
