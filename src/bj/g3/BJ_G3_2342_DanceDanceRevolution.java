package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 09. 13
@see https://www.acmicpc.net/problem/2342
@git
@performance 472ms
@category #
@note
시간 제한: 2초
수열의 길이: 10만
DDR을 하는 문제다.
발판은 하나의  중점을 기준으로 위, 아래, 왼쪽, 오른쪽으로 연결되어 있다.
중점을 0, 위를 1, 왼쪽을 2, 아래를 3, 오른쪽을 4라고 한다.

처음에 게이머는두 발을 중앙에 모으고 있다.(0위치)
지시에 따라  왼쪽 또는 오른쪽 발을 움직이고 동시에 움직이지는 않는다.

두 발이 같은 지점에 있는것이 허락되지 않는다. 같은 지점을 연속해서 눌러야 하면
한  발로 계속 누르는거다.

같은 지점을 한번 더 누를 때, 1의 힘을 사용
중앙에 있던 발이 다른 지점으로 움직일 때, 2의 힘을 사용
다른 지점에서 인접한 지점으로  움직일 때, 3의 힘을 사용.(왼쪽에서 위나 아래로)
반대편으로 움직일때, 4의 힘을 사용.(위쪽에서 아래족으로, 오른쪽에서 왼쪽)

만약 1 → 2 → 2 → 4를 눌러야 한다고 가정해 보자.
당신의 두 발은 처음에 (point 0, point 0)에 위치하여 있을 것이다.
그리고 (0, 0) → (0, 1) → (2, 1) → (2, 1) → (2, 4)로 이동하면, 당신은 8의 힘을 사용하게 된다.

수열이 입력되고 마지막엔 0이 들어온다.

사용되는 최소의 힘을 출력

수열을 ArrayList에 담어, DDR판 3*3배열을 만들어
Switch문으로 case를 나눠

첫 두개는 숫자가 같지 않는 이상 무조건 중점에서 발이 나가게 된다.(cost가 2니까)

1번 안
	현재 발의 위치를 받어 -> 제일 힘이 적게 드는 걸 골라서 힘을 써
	발의 위치를 옮겨줘 -> 0을 받을때 까지 반복

	반대편으로 움직일때, 4의 힘을 사용은 없어도 된다.
	반대편으로 발을 움직일 일이 없을 것 같다.
	아닌가? 만약에 왼쪽이 2, 오른쪽 발이  3에 있을 때, 1을 간 후 2가 계속 나온다면
			1 2 2 2
	오른쪽(3)이 1로 옮기고 4+1+1+1 = 7
	왼쪽(2)이 1로 옮기고 오른쪽(3)이 2로 옮기는 경우 3+3+1+1 = 8
	벌써 반례가 있네..

2번 안
	이러면 DFS + 백트래킹 쪽으로 가야한다.
	근데 수열 길이가 10만이고 백트래킹 최악의 경우의 수는 2^n이니까 안될 것 같네

3번 안
	DP테이블을 이용하여 각 지점마다 최소의 힘을 더해주면 될 것 같다.
	어떻게 구현하지... 30분 생각하다가 dp테이블 구성이 어려워서 살짝 봤는데
	3차원 dp테이블을 만드는 거였다..ㄷㄷ
	각각 어떤 발판을 밟느냐에 따라 들어갈 값이 달라지기 때문이다.
	그리고 백트래킹이 맞았다. 다만 DP(메모이제이션)이 섞인...
	참고 블로그
	https://subbak2.com/79

*/
public class BJ_G3_2342_DanceDanceRevolution {
	
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	static int total, move[], dp[][][];
	public static void main(String[] args) throws IOException {
		String[] line = input.readLine().split(" ");
		move = new int[line.length-1];
		for(int i=0; i<line.length-1; i++) {
			move[i] = Integer.parseInt(line[i]);
		}
		
		// 스텝마다 왼쪽 오른쪽 갈 수 있는 2차원 배열이 있음
		dp = new int[line.length][5][5];
		
		total = dfs(0, 0, 0);
		System.out.println(total);
		
		

	}
	private static int dfs(int step, int left, int right) {
		// 기저조건
		if(step == move.length) {
			return 0;
		}
		if (dp[step][left][right] != 0) { // 값이 이미 있으면 그 값을 리턴
			return dp[step][left][right];
		}
		// 왼발로 갈 경우를 계산,
		// 왼발의 현재 위치에서 가려는 곳으로 갈때 나오는 힘 + 다음 재귀 함수(left를 옮겨줌)
		int leftScore = force(left, move[step]) + dfs(step+1, move[step], right);
		

		// 오른발로 갈 경우를 계산,
		// 오른발의 현재 위치에서 가려는 곳으로 갈때 나오는 힘 + 다음 재귀 함수(right를 옮겨줌)
		int rightScore = force(right, move[step]) + dfs(step+1, left, move[step]);
		// 둘 중에 더 작은 값을 현재 step의 dp테이블에 저장
		dp[step][left][right] = Math.min(leftScore, rightScore); 
		
		
		return dp[step][left][right];
	}
	
	private static int force(int from, int to) { // 어디에서 어디로 가느냐에 따라서 힘이 달라져
		if(from == 0) return 2;
		else if(from == to) return 1;
		else if(Math.abs(from - to) == 2) return 4;
		return 3;
		
	}

}
