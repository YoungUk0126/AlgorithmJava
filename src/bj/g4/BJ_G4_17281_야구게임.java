package bj.g4;
/**
 * @author 김영욱
 * @since 2023. 08. 24
 * @see https://www.acmicpc.net/problem/17281
 * @git
 * @performance 
 * @category #
 * @note 
 * 시간: 1초
 * 9명으로 이루어진 두 팀이 공격과 수비를 번갈아 하는 게임
 * 한 이닝에 3아웃이면 이닝 종료
 * 
 * 타순을 정해야 한다.
 * 2이닝에 6번 타자가 마지막 타자였다면, 3이닝은 7번타자부터 시작이다.
 * 
 * 이닝이 시작 될 때는 주자가 없다.
 * 팀의 선수는 총 9명이고, 1번 선수를 4번 타자로 미리 결정했다.
 * 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있을 때, 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구해보자.
 * 
 * 1번 선수는 4번 인덱스에 고정시켜 놓고 나머지를 Next순열을 돌려서 타자의 선수를 뽑아낸다
 * 그 순서에 맞춰서 이닝을 도는데 필요한건 out을 카운트 해주고 3이되는 순간 현재 선수+1을 한 인덱스 값을 기억해주고
 * 다음 이닝으로 넘어간다. 현재 선수+1 % 9
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_17281_야구게임 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, max;
	static int[][] 이닝;
	static int[] forPermutation = { 1, 2, 3, 4, 5, 6, 7, 8};
	static int[] testP = {2, 4, 1, 5, 8, 7, 6, 3};
	
	static int[] players;
	static int[] toDo;
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		이닝 = new int[N][9];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<9; j++) {
				이닝[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		do {
			players = new int[9];
			int i = 0;
			int j = 0;
			// players목록 받았음
			while(i<players.length) {
				if(i == 3) {
					players[i++] = 0;
				}
				else {
					players[i++] = forPermutation[j++];
				}
			}
			// toDo에 이닝별로 할꺼 뽑아서 순서대로 넣어줌
			int 이닝cnt = 0;
			int score = 0;
			int 타격 = 0;
			int flag = 0;
			while(이닝cnt < N) {
				toDo = new int[9];
				int[] 주자 = new int[7];
				int outCnt = 0;
				i = 0;
				for(int n: players) {
					toDo[i++] = 이닝[이닝cnt][n];
				}
				while(true) {
					타격 = toDo[flag];
					
					if(타격 == 0) {
						outCnt++;
					}
					// 안타쳤을때
					else if(타격 == 1) {
						for(int runner = 2; runner >= 0; runner-- ) {
							주자[runner+1] = 주자[runner];
							주자[runner] = 0;
						}
						// 타자주자 1루 출진
						주자[0] = 1;
					}
					else if(타격 == 2) {
						for(int runner = 2; runner >= 0; runner-- ) {
							주자[runner+2] = 주자[runner];
							주자[runner] = 0;
						}
						// 타자주자 2루 출진
						주자[1] = 1;
					}
					else if(타격 == 3) {
						for(int runner = 2; runner >= 0; runner-- ) {
							주자[runner+3] = 주자[runner];
							주자[runner] = 0;
						}
						// 타자주자 3루 출진
						주자[2] = 1;
					}
					else if(타격 == 4){
						for(int runner = 2; runner >= 0; runner-- ) {
							주자[runner+4] = 주자[runner];
							주자[runner] = 0;
						}
						주자[3] = 1;
					}
					
					for(int r=3; r<주자.length; r++) {
						if(주자[r] > 0) {
							score++;
							주자[r] = 0;
						}
					}
					if(outCnt == 3) {
						이닝cnt++;
						outCnt = 0;
						if(flag == 8) {
							flag = (flag+1)%9;
						}
						else {
							flag += 1;
						}
						break;
					} else {
						flag += 1;
					}
//					타자 순서가 한바퀴 돌았을때
					if(flag == 9) {
						flag = flag % 9;
					}
				}
				
			}
			max = Math.max(score, max);
    	} while(NP());
		System.out.println(max);

	}
	
	private static boolean NP() {
		int lastPeak = forPermutation.length - 1;
		
		while(lastPeak > 0 && forPermutation[lastPeak-1] >= forPermutation[lastPeak]) lastPeak--;
		if(lastPeak == 0) return false;
		
		int newBoss = forPermutation.length - 1;
		
		while(forPermutation[lastPeak - 1] >= forPermutation[newBoss]) newBoss--;
		
    	swap(forPermutation, newBoss, lastPeak-1);
    	for(int left = lastPeak, right = forPermutation.length-1; left < right; left++, right--) {
    		swap(forPermutation, left, right);
    	}
    	return true;
	}
	
	private static void swap(int[] forPermutaion, int i, int j) {
		// TODO Auto-generated method stub
		int temp = forPermutaion[i];
		forPermutaion[i] = forPermutaion[j];
		forPermutaion[j] = temp;
	}
}
