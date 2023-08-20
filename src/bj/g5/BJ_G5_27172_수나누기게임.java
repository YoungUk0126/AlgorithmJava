package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.08.19
@see https://www.acmicpc.net/problem/27172
@git
@performance
@category #
@note
시간: 1초
플레이어 수: 2<= N <= 10만
카드 숫자 : 1 ~ 100만


《수 나누기 게임》의 규칙은 다음과 같습니다.

- 게임을 시작하기 전 각 플레이어는 1부터 1,000,000 사이의 수가 적힌 서로 다른 카드를 잘 섞은 뒤 한 장씩 나눠 가집니다.
- 매 턴마다 플레이어는 다른 플레이어와 한 번씩 결투를 합니다.
- 결투는 서로의 카드를 보여주는 방식으로 진행되며, 플레이어의 카드에 적힌 수로 다른 플레이어의 카드에 적힌 수를 나눴을 때, 나머지가 
0이면 승리합니다. 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나누어 떨어지면 패배합니다. 둘 다 아니라면 무승부입니다.
- 승리한 플레이어는 1점을 획득하고, 패배한 플레이어는 1점을 잃습니다. 
무승부인 경우 점수의 변화가 없습니다.
- 본인을 제외한 다른 모든 플레이어와 정확히 한 번씩 결투를 하고 나면 게임이 종료됩니다.

이 게임을 조합으로 생각했을 경우
100000C2 => 2^100000이고, 이는 시간 복잡도를 아득히 넘어선다.



1. 데이터들을 객체화하여 N크기의 배열로 저장한다.
2. 배열로 카드의 최대 수를 미리 구해서 그 크기만큼 생성한다.
3. 이때 배열의 인덱스가 카드의 숫자고 그안의 값이 몇번째 카드인지를 나타낸다.
 


*/


public class BJ_G5_27172_수나누기게임 {
	
	public static class player{
		int value;
		int score;
		public player(int value) {
			super();
			this.value = value;
			this.score = 0;
		}
	}
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static player[] players;
	static int cardArr[];
	static int max;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		players = new player[N];
		max = Integer.MIN_VALUE;
		
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=0; i<N; i++) {
			int card_value = Integer.parseInt(tokens.nextToken());
			players[i] = new player(card_value);
			max = Math.max(card_value, max);
		}
		cardArr = new int[max+1];
		// 왜 1부터냐면 0이상만 써칭할꺼라서 그럼
		int cnt = 1;
		for(player p: players) {
			cardArr[p.value] = cnt++;
		}

//	 	배열로 카드 숫자의 최대 수를 미리 구해서 그 크기만큼 생성한다.
//	 	이때 배열의 인덱스가 카드의 숫자고 그안의 값이 몇번째 카드인지를 나타낸다.
		for(int start=0; start<cardArr.length; start++) {
			// 생성된 카드가 맞으면 카드에게 인덱스가 주어져 있을거임
			if(cardArr[start] > 0) {
				// 현재 카드의 수로 나눠주는거임, 3이면 3씩 증가한 인덱스들의 수를 늘려줌
				// start *2는 자기 자신은 제외해야 하니까
				for(int 비교대상 = start*2; 비교대상<cardArr.length; 비교대상+=start) {
					if(cardArr[비교대상] > 0) {
						// cardArr[인덱스] 안에 값에는 players에 접근할 수 있는 인덱스 값이 들어있음
						// 근데 cardArr 안의 값을 아까 1부터 시작해줬으니까 -1 해줌
						players[cardArr[start]-1].score+=1;
						players[cardArr[비교대상]-1].score-=1;
					}
				}
			}
		}
		for(player p: players) {
			builder.append(p.score).append(" ");
		}
		System.out.println(builder);
	}

}

