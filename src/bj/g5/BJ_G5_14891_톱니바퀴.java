package bj.g5;
/**
@author 김영욱
@since 2023. 10. 10
@see https://www.acmicpc.net/problem/14891
@git
@performance
@category #
@note
8개의 톱니를 가진 톱니바퀴 4개가 주어진다.
톱니바퀴를 K번 회전시키려고한다.
톱니바퀴가 회전할 때, A를 회전시키려고 하고 B가 맞닿아 있다고 치자.
서로 맞닿아 있는 톱니의 극이 다르다면, B는 A가 회전한 방향과 반대방향으로 회전한다.

맞닿을 톱니 오른쪽: 2번인덱스
맞닿을 톱니 왼쪽: 6번 인덱스

입력 : 톱니 정보 1 => S, 0 => N

회전 정보 1 => 시계방향
		 -1 => 반시계방향
		 
톱니바퀴들은 하나가 움직이면 다같이 움직임

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_14891_톱니바퀴 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int K, ans;
	static int gear[][] = new int[5][8];

	public static void main(String[] args) throws IOException{
		
		for(int i=1; i<5; i++) {
			String temp = input.readLine();
			char[] temp2 = temp.toCharArray();
			for(int j=0; j<8; j++) {
				gear[i][j] = temp2[j] - '0';
			}
		}
		
		K = Integer.parseInt(input.readLine());
		
		for(int k=0; k<K; k++) {
			tokens = new StringTokenizer(input.readLine());
			int whichGear = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			startGear(whichGear, d);
			
		}
		int score = 1;
		for(int i=1; i<5; i++) {
			if(gear[i][0] == 1) {
				ans += score;
			}
			score *= 2;
		}
		System.out.println(ans);

	}
	// 맞닿은 기어가 회전하지 않거나, 극이 같으면 회전하지 않음
	private static void startGear(int whichGear, int d) {
		int directions[] = new int[5];
		
		if(whichGear == 1) {
			directions[1] = d;
			for(int i=2; i<5; i++) {
				if(gear[i-1][2] == gear[i][6]) {
					break;
				}
				else {
					directions[i] = directions[i-1]*-1;
				}
			}
		}
		else if(whichGear == 2) {
			directions[2] = d;
			if(gear[1][2] != gear[2][6]) {
				directions[1] = d*-1;
			}
			if(gear[2][2] != gear[3][6]) {
				directions[3] = d*-1;
				if(gear[3][2] != gear[4][6]) {
					directions[4] = directions[3] * -1;
				}
			}
		}
		else if(whichGear == 3) {
			directions[3] = d;
			if(gear[3][2] != gear[4][6]) {
				directions[4] = d*-1;
			}
			if(gear[2][2] != gear[3][6]) {
				directions[2] = d*-1;
				if(gear[1][2] != gear[2][6]) {
					directions[1] = directions[2] * -1;
				}
			}
		}
		else if(whichGear == 4) {
			directions[4] = d;
			for(int i=3; i>0; i--) {
				if(gear[i+1][6] == gear[i][2]) {
					break;
				}
				else {
					directions[i] = directions[i+1]*-1;
				}
			}
		}
		
		moveGear(directions);
		
	}
	
	// 톱니바퀴 돌려주는 메서드
	private static void moveGear(int[] directions) {
		int N = 8;
		for(int i=1; i<5; i++) {
			if(directions[i] == 0) {
				continue;
			}
			// 시계
			if(directions[i] == 1) {
				int temp = gear[i][0];
				gear[i][0] = gear[i][N-1];
				for(int j = N-2; j>=0; j--) {
					gear[i][j+1] = gear[i][j];
				}
				gear[i][1] = temp;
			}
			// 반시계
			else if(directions[i] == -1){
				int temp = gear[i][N-1];
				gear[i][N-1] = gear[i][0];
				for(int j=1; j<N-1; j++) {
					gear[i][j-1] = gear[i][j];
				}
				gear[i][N-2] = temp;
			}
		}
		
	}

}
