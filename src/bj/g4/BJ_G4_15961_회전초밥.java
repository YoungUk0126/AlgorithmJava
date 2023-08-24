package bj.g4;
/**
 * @author 김영욱
 * @since 2023. 08. 24
 * @see https://www.acmicpc.net/problem/15961
 * @git
 * @performance 
 * @category #
 * @note 
 * 시간 : 1초
 * 벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다.
 * 행사 두 개
 * 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격
 * 2. 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다.
 *           만약 쿠폰으로 제공할 초밥이 벨트 위에 없을 경우, 요리사가 새로 만들어 제공.
 *           
 * k=4이고, 30번 초밥을 쿠폰으로 받았다고 가정하자. 쿠폰을 고려하지 않으면 4가지 다른 초밥을 먹을 수 있는 경우는 
 * (9, 7, 30, 2), (30, 2, 7, 9), (2, 7, 9, 25) 세 가지 경우가 있는데, 
 * 30번 초밥을 추가로 쿠폰으로 먹을 수 있으므로 (2, 7, 9, 25)를 고르면 5가지 종류의 초밥을 먹을 수 있다.
 * 
 *  손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램을 작성해라.
 * 
 * 접시의 수 N
 * 초밥의 가짓수 d
 * 연속해서 먹는 접시의 수 k(슬라이딩 윈도우 크기)
 * 쿠폰 번호 c
 * 두번째 줄부터는 초밥의 종류가 나온다.
 * 
 * 윈도우 안에 쿠폰 번호가 없으면 가짓수 +1
 * 있으면 그냥 그 안에 있는 가짓수 카운트
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_15961_회전초밥 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,d,k,c,max;
//	static int[] window;
	static int start, end;
	static int[] belt;
	static int[] type;
	static int totalCnt, count;
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		d = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		
		end = k-1;
		start = 0;
		belt = new int[N];
		type = new int[3001];
		
		for(int i=0; i<N; i++) {
			belt[i] = Integer.parseInt(input.readLine());
		}
		// 처음 윈도우는 다 구해줘야함
		for(int i=0; i<k; i++) {
			if(type[belt[i]] == 0) {
				count++;
			}
			type[belt[i]]++;
		}
		for(int i=1; i<N; i++) {
			type[belt[i-1]]--;
			if(type[belt[i-1]] == 0) count--;
			if(type[belt[(i+k-1)%N]] == 0) count++; // 원형 큐니까
			type[belt[(i+k-1)%N]]++;

			if(type[c] == 0) {
				count++;
				max = Math.max(max, count);
				count--;
			}
			else {
				max = Math.max(max, count);
			}
			
		}
		System.out.println(max);
		
	}

}
