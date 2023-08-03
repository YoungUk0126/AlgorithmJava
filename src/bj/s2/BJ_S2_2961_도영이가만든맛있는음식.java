package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 3.
@see https://www.acmicpc.net/problem/2961
@git
@performance
@category #
@note

1. 왜 그생각을 하게됐는가?
2. 이렇게 했을 때 수도코드(어떻게 짜겠다는 대략 설명)
3. 시간복잡도 : 2^10(코드 보면서 해도 됨)
4. 가능한가?
5. 코드 설명

도영이의 앞에는 재료가 N개
각 재료의 신맛 S와 쓴맛 B
요리한 음식의 신맛은 신맛 S의 곱, 쓴맛 B의 합
요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다.
재료는 적어도 하나 이상 사용해야함.

입력 형식
N : 재료의 개수
S B : 신맛, 쓴맛
ex)
4
1 7
2 6				2*3*4 랑 6+8+9의 차이는 1, 이게 최소
3 8
4 9

신맛, 쓴맛을 써야하는 개수가 정해져 있지 않으므로 부분집합이다.
우선 신맛과 쓴맛을 받는 배열, sin[N], ssen[N]이 있어야해
그리고 미리 다 받아 놓고, 부분집합을 구해, 부분집합에 필요한 boolean isSelected[N]이 필요해
함수의 매개변수는 신맛의 합, 쓴맛의 합을 넘겨줄 매개변수가 필요하고
넘겨줄 때는 신맛은 곱하고, 쓴맛은 더해서 곱해

그리고 가장 작은 차이를 구하기 위해 min이라는 변수도 필요할 것 같아.

*/
public class BJ_S2_2961_도영이가만든맛있는음식 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, sin[], ssen[];
	static int min = Integer.MAX_VALUE;
	static boolean isSelected[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		sin = new int[N];
		ssen = new int[N];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			sin[i] = Integer.parseInt(tokens.nextToken());
			ssen[i] = Integer.parseInt(tokens.nextToken());
		}
		
		generateSubset(0, 1, 0, 0);
		System.out.println(min);
		
		
	}
	
	static void generateSubset(int cnt, int sinSum, int ssenSum, int selectedCnt) {
		
		if(cnt == N) {
			if(Math.abs(sinSum - ssenSum) < min && selectedCnt > 0) {
				min = Math.abs(sinSum - ssenSum);
			}
			return;
		}
		
		isSelected[cnt] = true;
		generateSubset(cnt+1, sinSum*sin[cnt], ssenSum+ssen[cnt], selectedCnt + 1);
		isSelected[cnt] = false;
		generateSubset(cnt+1, sinSum, ssenSum, selectedCnt);
		
	}

}
