package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 자연수 N은 몇 개의 연속된 자연수의 합
 * "연속된"자연수의 합
 * 나타내는 가지수 : ans
 * 15 : 15, 1+2+3+4+5, 4+5+6, 7+8
 * 1부터 시작해서 15까지
 * i = 1: j=i+1 넣고
 * 계속 합쳐서 15면 ans++
 * 15를 넘어가면 break해서 i + 1
 * 
 * @author SSAFY
 *
 */
public class BOJ2018 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		long beforeTime = System.currentTimeMillis();
		int N = Integer.parseInt(input.readLine());
		int result = 1;
		int ans = 0;
		
		for(int i=1; i<=N; i++) {
			ans = i;
			for(int j=i+1; j<=N; j++) {
				ans += j;
				if(ans == N) {
					result++;
					break;
				} else if(ans>N) {
					break;
				}
			}
		}
		System.out.println(result);
		
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime) / 1000;
		System.out.println("시간차이 : " + secDiffTime);

	}

}
