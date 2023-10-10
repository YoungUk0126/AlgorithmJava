package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_D4_특이한자석 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int K, ans, T;
	static int gear[][];

	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());

		for(int tc=1; tc<=T; tc++) {
			ans = 0;
			gear = new int[5][8];
			K = Integer.parseInt(input.readLine());
			for(int i=1; i<5; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j=0; j<8; j++) {
					gear[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			
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
			builder.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);

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
