package sw.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 10
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWgv9va6HnkDFAW0&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=7
@git
@performance
@category #
@note
1에서 18까지의 수가 적힌 18장의 카드
9장씩 카드를 나누고, 9라운드에 걸쳐 게임을 진행
한 라운드에 한 장씩 카드를 내서 적힌 수를 비교해서 점수를 계산
높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻는다.
낮은 수는 점수X
아홉 라운드를 끝내고 총점이 더 높은 사람이 게임에서 이김
두 사람의 총점이 같으면 무승부

=======입력=======
규영이가 받은 9장의 카드가 배열로 주어짐
인영이가 내는 9!가지 순서에 따라 규영이의 승패가 정해짐

규영이가 이기는 경우와 지는 경우가 총 몇 가지인지 구해라.
규영이는 정수가 주어지는 순서대로 카드를 낸다.(순서, 순열)

어차피 인영이는 9가지의 숫자를 내야하기 때문에 nPn가지를 구해야 한다.
그러므로 nPn을 구하는 NextPermutation이 적합하다.
nPn을 구해서 각 숫자들을 비교해서 점수를 합산하여 이기는 경우, 지는 경우를 나눠주면 끝!
*/
public class SW_D3_규영이와인영이의카드게임 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N = 9;
	static int gyuCards[];
	static int inCards[];
	static int inSum;
	static int gyuSum;
	static int gyuWinCnt;
	static int gyuLoseCnt;
	
	static boolean check[];
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
//			inSum = 0;
//			gyuSum = 0;
			gyuWinCnt = 0;
			gyuLoseCnt = 0;
			
			gyuCards = new int[N];
			inCards = new int[N];
			
			check = new boolean[19];
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<N; i++) {
				gyuCards[i] = Integer.parseInt(tokens.nextToken());
				check[gyuCards[i]] = true;
			}
			int j=0;
			for(int i=1; i<=18; i++) {
				if(check[i]) continue;
				inCards[j++] = i;
			}
			// 입력 확정
			
			do {
				gyuSum = 0; inSum = 0;
				for(int i=0; i<9; i++) {
					if(gyuCards[i] > inCards[i]) {
						gyuSum += gyuCards[i] + inCards[i];
					} 
					else if(inCards[i] > gyuCards[i]) {
						inSum += inCards[i] + gyuCards[i];
					}
				}
				if(gyuSum > inSum) gyuWinCnt++;
				else if(inSum > gyuSum) gyuLoseCnt++;
				
			} while(np(inCards)) ;
			
			builder.append("#").append(tc).append(" ").append(gyuWinCnt)
			.append(" ").append(gyuLoseCnt).append("\n");
		}
		System.out.println(builder);
		
	}
	private static boolean np(int[] inCards) {
		// 1. 꼭대기 구하기
		int lastPeak = inCards.length - 1;
		while(lastPeak > 0 && inCards[lastPeak-1] >= inCards[lastPeak]) lastPeak--;
		
		if(lastPeak == 0) return false;
		
		// 2. 꼭지점 바로 앞의 값보다 살짝 큰 새로운 값 뒤에서부터 찾기
		int newBoss = inCards.length - 1;
		
		while(inCards[lastPeak-1] >= inCards[newBoss]) newBoss--;
		
		// 3. 값 교체
		swap(inCards, newBoss, lastPeak-1);
		
		// 4. 뒤에 값들 다시 정렬
		
		for(int left = lastPeak, right = inCards.length-1; left<right; left++, right--) {
			swap(inCards, left, right);
		}
		
		return true;
		
	}
	private static void swap(int[] inCards, int i, int j) {
		// TODO Auto-generated method stub
		int temp = inCards[i];
		inCards[i] = inCards[j];
		inCards[j] = temp;
		
	}

}
