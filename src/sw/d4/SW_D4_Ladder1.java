package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 1.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV14ABYKADACFAYh&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=++3+
@git
@performance 0.15719s
@category #
@note
사다리 타기를 하는데 원하는 곳으로 가려면 어떤 입구 인덱스로 들어가야 하는지가 궁금하대
0행에 1들이 입구
나머지 1들은 경로, 2가 도착 부분
도착 부분부터 거꾸로 움직인다면 반복 횟수를 줄일 수 있다(지웅이 아이디어)

처음 도착 부분 좌표 따는 탐색 N, 도착 부분 경로 따라가는 부분 N, 총 2N
O(N)이면 해결 가능하다

int [][] ladder => 길이는 100*100 고정
*/
public class SW_D4_Ladder1 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] ladder;
	static int[][] visited;
	static int destinationI = 99;
	static int destinationJ;
	static int X;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(input.readLine());
			
			ladder = new int[100][100];
			visited = new int[100][100];
			
			for(int i=0; i<ladder.length; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j=0; j<ladder[i].length; j++) {
					ladder[i][j] = Integer.parseInt(tokens.nextToken());
					if(ladder[i][j] == 2) {
						destinationJ = j;
					}
				}
			}
			for(int i=destinationI; i>=0; ) {
				visited[i][destinationJ] = 1;
				// 좌측 체크
				if((destinationJ-1)>=0 && ladder[i][destinationJ - 1] == 1 && visited[i][destinationJ-1] == 0) {
					destinationJ--;
					continue;
				} else if((destinationJ+1)<100 && ladder[i][destinationJ + 1] == 1 && visited[i][destinationJ+1] == 0) {
					destinationJ++;
					continue;
				} else {
					i--;
				}
				
				
			}
			X = destinationJ;
			System.out.println("#" + tc + " " + X);
		}
	}

}
