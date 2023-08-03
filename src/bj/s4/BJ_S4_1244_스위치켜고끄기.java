package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 1.
@see https://www.acmicpc.net/problem/1244
@git
@performance
@category #구현
@note
1부터 연속적으로 번호가 붙어있는 스위치들이 있음
1이 켜진거 0이 꺼진거
남자가 스위치를 받으면 그 수의 배수의 상태를 바꿈
여자가 스위치를 받으면 그 수의 양 옆에 상태가 같으면 다 바꾸고 그 양 옆으로 옮겨서 또 검사
검사해서 나온 범위를 다 바꿈
만약 받은 숫자의 양 옆이 상태가 다르면 받은 숫자만 상태를 바꿈

입력
스위치 개수 => N
스위치 상태 => int [] switchs
학생 숫자(반복 숫자) => M
학생 성별, 스위치 번호 => int yas, int s
*/

public class BJ_S4_1244_스위치켜고끄기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int[] switchs;
	static int M;
	static int yas;
	static int s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		switchs = new int[N];
		for(int i=0; i<switchs.length; i++) {
			switchs[i] = Integer.parseInt(tokens.nextToken());
		}
		M = Integer.parseInt(input.readLine());
		for(int m=0; m<M; m++) {
			tokens = new StringTokenizer(input.readLine());
			yas = Integer.parseInt(tokens.nextToken());
			s = Integer.parseInt(tokens.nextToken());
			//남자 Okay
			if(yas == 1) {
				for(int i=s-1; i<switchs.length; i++) {
					if((i+1) % s == 0) {
						if(switchs[i] == 1)
							switchs[i] = 0;
						else { 
							switchs[i] = 1;
						}
					}
				}
			//여자
			} else {
				//좌우 띄워서 탐색하기 위한 숫자
				int i=1;
				if(switchs[s-i-1] != switchs[s+i-1] && isIn(s-i-1, s+i-1))
				{
					// 걍 받은 숫자 인덱스 스위치 상태 바꿈
					if(switchs[s-1] == 1)
						switchs[s-1] = 0;
					else { 
						switchs[s-1] = 1;
					}
				} else {
					while(true) {
						int low = s-i-1;
						int high = s+i-1;
						if(isIn(low,high) && switchs[low] == switchs[high]) {
							i++;
						}
						else {
							for(int j=low+1; j<=high-1; j++) {
								if(switchs[j] == 1)
									switchs[j] = 0;
								else { 
									switchs[j] = 1;
								}
							}
							break;
						}
					}
				}
			}
		}
		for(int i=0, j=0; i<switchs.length; i++, j++) {
			if(j>=20) {
				System.out.println();
				System.out.print(switchs[i] + " ");
				j = 0;
			} else {
				System.out.print(switchs[i] + " ");
			}
		}
		
		
	}
	static boolean isIn(int i, int j) {
		if(0<=i && i<switchs.length && 0<=j && j<switchs.length) {
			return true;
		}
		return false;
	}

}
