package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @since 2023. 08. 14
 * @see https://www.acmicpc.net/problem/1074
 * @git
 * @performance
 * @category #
 * @note 시간 : 0.5초, N : 15 크기가 2^N * 2^N인 2차원 배열을 Z모양으로 탐색하려 한다. 배열을 크기가 2^N-1 X
 *       2^N-1로 4등분 한 후에 재귀적으로 순서대로 방문
 * 
 *       시간 제한이 너무 짜서 2차원 배열을 그리고 시작하면 바로 시간초과 날 것 같다. 계속 절반 크기로 배열을 모두 나눈 다음
 *       Z수행(0,0) -> (0,1) -> (1,0) -> (1,1)
 * 
 */
public class BJ_S1_1074_Z {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int N, r, c;
	static long ans;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());

		recur(0, 0, (int) Math.pow(2, N));
		builder.append(ans);
		System.out.println(builder);
	}

	static void recur(long x, long y, long size) {
		if (size == 1) {
			return;
		}

		long mid = size / 2;
		long plus = mid * mid;

		if (x + mid > r) {
			if (y + mid > c) {
				recur(x, y, mid);
			} else if (y + mid <= c) {
				ans += plus;
				recur(x, y + mid, mid);
			}
		}

		else if (x + mid <= r) {
			if (y + mid > c) {
				ans += plus * 2;
				recur(x + mid, y, mid);
			} else if (y + mid <= c) {
				ans += plus * 3;
				recur(x + mid, y + mid, mid);
			}
		}
	}

}
