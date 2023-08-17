package bj.g4;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/6987
@git
@performance 80ms
@category #
@note
시간 제한 : 1초
월드컵 조별 최종 예선에서는 6개국으로 구성된 각 조별로 각 국가별로 5번의 경기를 치룬다.
조별리그가 끝난 후, 기자가 보내온 각 나라의 승, 무승부, 패의 수가 가능한 결과인지를 판별

네 가지의 결과가 주어질 때 각각의 결과에 대햐여 가능하면 1, 불가능하면 0을 출력하자

1. 승, 무, 패의 합이 각 나라마다 5여야함
2. 무는 1이 있으면 다른 나라도 1이 있어야함(혼자서 무승부를 이룰 순 없으니까)
3. 승의 합과 패의 합이 같아야하고, 그 경기의 조합도 맞아야함
 (5 0 0 5 0 0 | 0 0 5 0 0 5 안됨, 국가별로 한 경기씩 치루는데 5경기를 다 이긴 나라가 두 나라나 존재할 수 없다.)

각 경기당 3가지의 가짓수가 나오고 총 15경기이기 때문에
3의 15승의 시간 복잡도가 나온다.
14,348,907이여서 완탐이 가능하지만 가지치기를 할 수 있는 문제다.

====코드 설명====
입력을 받으면서 세번 받은 숫자의 합도 5가 나오는지 확인해준다.

6개의 국가로 만들 수 있는 15경기 => 6C2를 미리 2차원 배열에 만들어 준다.
백트래킹을 실행할 메소드를 만들고 매개변수로 나라들의 목록을 담은 배열과 현재
재귀의 깊이이면서, 15가지의 조합을 담은 2차원 배열 중 이번에 사용할 배열 인덱스 current를 받아온다.
A,B라고 하자.
A가 승리 수가 0보다 크고 B의 패배 수가 0보다 크다면 win, lose의 값을 서로 1씩 빼주고 current+1을 해주고 다시 재귀를 한다.
만약 재귀에서 나온다면 원래 값으로 되돌리도록 1씩 넣어준다(결과가 가능한지 불가능한지 빼기 연산으로 결정하기 때문이다.)

6C2 즉 15개의 가짓수를 2차원 배열에 미리 넣어 놓고 문제를 푸는 방식을 생각하지
못해서 시간이 오래 걸린 문제였다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class country {
	int win;
	int lose;
	int draw;
	
	public country(int vic, int lose, int draw) {
		this.win = vic;
		this.lose = lose;
		this.draw = draw;
	}
	public boolean matchCntCheck() {
		int sum = win + lose + draw;
		if(sum == 5) return true;
		return false;
	}
}

public class BJ_G4_6987_월드컵 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static boolean flag, ansFlag;
	static country[] cList;
	static int[][] Combinations;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for (int tc = 0; tc < 4; tc++) {
			flag = true;
			ansFlag = false;
			cList = new country[6];
			tokens = new StringTokenizer(input.readLine());
			for (int i = 0; i < 6; i++) {
				int vic = Integer.parseInt(tokens.nextToken());
				int draw = Integer.parseInt(tokens.nextToken());
				int lose = Integer.parseInt(tokens.nextToken());
				cList[i] = new country(vic, lose, draw);
				
				// 한 국가의 경기 수가 5인지 확인
				if(!cList[i].matchCntCheck()) {
					builder.append("0 ");
					flag = false;
					break;
				}
			}
			if (flag) {
				// 미리 6C2의 조합을 구해줌
				Combinations = new int[15][2];
				int index = 0;
				for(int i=0; i<6; i++) {
					for(int j=i+1; j<6; j++) {
						Combinations[index][0] = i;
						Combinations[index][1] = j;
						index++;
					}
				}
				recur(cList, 0);
				if(ansFlag) {
					builder.append("1 ");
				}
				else {
					builder.append("0 ");
				}
			}
		}
		System.out.println(builder);
		
	}
	
	private static void recur(country[] countries, int current) {
		// 기저 조건
		if(current == 15) {
			// 모든 국가들의 승, 패, 무승부가 0이 되지 않았다면 답이 아니다.
			for(int i=0; i<countries.length; i++) {
				if(countries[i].win != 0 && countries[i].lose != 0 && countries[i].draw != 0) {
					ansFlag = false;
					return;
				}
			}
			ansFlag = true;
			return;
		}
		int teamA = Combinations[current][0];
		int teamB = Combinations[current][1];
		// 재귀 조건
		if(countries[teamA].win > 0 && countries[teamB].lose > 0 ) {
			countries[teamA].win -= 1;
			countries[teamB].lose -= 1;
			recur(countries, current + 1);
			countries[teamA].win += 1;
			countries[teamB].lose += 1;
		}
		if(countries[teamA].lose > 0 && countries[teamB].win > 0 ) {
			countries[teamA].lose -= 1;
			countries[teamB].win -= 1;
			recur(countries, current + 1);
			countries[teamA].lose += 1;
			countries[teamB].win += 1;
		}
		if(countries[teamA].draw > 0 && countries[teamB].draw > 0 ) {
			countries[teamA].draw -= 1;
			countries[teamB].draw -= 1;
			recur(countries, current + 1);
			countries[teamA].draw += 1;
			countries[teamB].draw += 1;
		}
	}

}
