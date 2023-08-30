package bj.g5;

import java.util.Arrays;
import java.util.Scanner;

/**
@author 김영욱
@since 2023. 8. 30.
@see https://www.acmicpc.net/problem/2294
@git
@performance 
@category #
@note

n가지 종류의 동전이 있고, 그 합이 k원이 되도록 하고 싶다.
그러면서 동전의 개수가 최소가 되도록 하고 싶다.

DP테이블과 동전의 종류들을 배열로 만든다.

D[0]은 0원이기에 0을 넣어주고 1부터 구하고 싶은 금액의 합까지 반복을한다
그 안에서 동전의 종류만큼 또 반복(이중반복)을 하는데,
i가 현재 동전의 종류의 금액보다 크고 i - 현재 동전 종류의 금액 인덱스 DP테이블에 +1한 값이 D[i]보다 작다면
그 인덱스는 더 큰 종류의 금액으로 떼울수 있다는 의미로 값을 초기화 해준다. 

*/

public class BJ_G5_2294_동전2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int money = sc.nextInt();

		int[] D = new int[money + 1]; // 금액n에 대한 최소동전수
		int[] coins = new int[N]; // 동전 종류
		
		Arrays.fill(D, Integer.MAX_VALUE - 100000);
		D[0] = 0; // 점화식으로 채워질 수 없는 동적테이블의 값 초기화
		
		for(int i=0; i<N; i++) {
			coins[i] = sc.nextInt();
		}
		
		for(int i=1; i<=money; i++) {
			
			for(int j=0; j<N; j++) {
				if(i >= coins[j] && D[i] > D[i - coins[j]] + 1) {
					D[i] = D[i - coins[j]] + 1;
				}
			}
		}
		if(D[money] != Integer.MAX_VALUE - 100000) System.out.println(D[money]);
		else System.out.println("-1");

	}

}
