package bj.g4;
/**
@author 김영욱
@since 2023.08.16
@see https://www.acmicpc.net/problem/6987
@git
@performance
@category #
@note
월드컵 조별 최종 예썬에서는 6개국으로 구성된 각 조별로 각 국가별로 5번의 경기를 치룬다.
조별리그가 끝난 후, 기자가 보내온 각 나라의 승, 무승부, 패의 수가 가능한 결과인지를 판별

네 가지의 결과가 주어질 때 각각의 결과에 대햐여 가능하면 1, 불가능하면 0을 출력하자

1. 승, 무, 패의 합이 각 나라마다 5여야함
2. 무는 1이 있으면 다른 나라도 1이 있어야함(혼자서 무승부를 이룰 순 없으니까)
3. 승의 합과 패의 합이 같아야함

입력 받으면서 승을 더하고 패를 승에 뺀다
입력 세번 받은 숫자의 합도 5가 나오는지 확인해준다.
무승부를 만나면 1에 *-1을 해준다

입력이 끝나면 무승부가 -1이고 승이 0보다 크다면 실패
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
				
				int sum=vic+lose+draw;
				if(sum != 5) {
					builder.append("0 ");
					flag = false;
					break;
				}
			}
			if (flag) {
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
			for(int i=0; i<countries.length; i++) {
//				System.out.printf("country num : %d, win : %d, lose : %d, draw : %d\n", i, countries[i].win,countries[i].lose,countries[i].draw);
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
