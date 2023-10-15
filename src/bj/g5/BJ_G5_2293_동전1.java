package bj.g5;
/**
@author 김영욱
@since 2023. 10. 15.
@see https://www.acmicpc.net/problem/2293
@git
@performance 84ms
@category #
@note

*************내가 안풀었음****************
n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다.
이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.

사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다. (1, 2) (2, 1)
  1  2  3  4  5  6  7  8  9  10
1 1  1  1  1  1  1  1  1  1  1
2 X  2  2  3  3  4  4  5  5  6
5 X  X  X  X  4  5  6  7  8  10

dp[j] += dp[j - coins[i]]
i가지 동전을 사용했을 때 가치의 합이 j원이 되는 가짓수 += j원 - 현재 동전 가치

dp문제는 앞으로 표를 먼저 그리고 그 다음 점화식을 찾아내는게 더 편할 것 같다.

**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_G5_2293_동전1 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N, money;
	static int[] dp, coins;
	// dp문제는 앞으로 표를 먼저 그리고 그 다음 점화식을 찾아내는게
	// 더 편할 것 같다.
	public static void main(String[] args) throws IOException{
		
		tokens = new StringTokenizer(input.readLine());
		// N가지 종류의 동전
		N = Integer.parseInt(tokens.nextToken());
		// money만큼의 가치가 되려고 함.
		money = Integer.parseInt(tokens.nextToken());
		coins = new int[N+1];
		// 코인의 종류들을 구함
		dp = new int[money+1];
		dp[0] = 1;
		
		for(int i=1; i<=N; i++) {
			// 각 동전의 가치부터 인덱스를 잡고 돌리면 중복되는 것을 피할 수 있다.
			coins[i] = Integer.parseInt(input.readLine());
			for(int j=coins[i]; j<=money; j++) {
				dp[j] += dp[j - coins[i]];
			}
		}
		System.out.println(dp[money]);
		

	}

}
