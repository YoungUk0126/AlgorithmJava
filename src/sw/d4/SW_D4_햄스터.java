package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 2.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWxQ310aOlQDFAWL
@git
@performance 0.09968s
@category #
@note
정우는 햄스터 우리를 N개 가지고 있고
각 우리는 1~N번까지의 번호를 붙여 일렬로 놔뒀다.
각 우리에 0마리 이상 X마리 이하의 햄스터
M개의 기록 => l번 우리에서 r번 우리까지 햄스터 수를 세었더니 s마리다.
기록을 모두 만족하는 햄스터 수 배치를 구하는 프로그램을 작성해라.



테스트 케이스의 수 : T
우리 : N
우리 번호 : 1~N
각 우리의 최고 햄스터 마리 수 : X
기록 수 : M
제일 왼쪽 우리 : l
제일 오른쪽 우리 : r
몇 마리였냐 : s

자료 구조
정답 저장 배열 : ans[N+1]
시험한걸 저장한 배열 : cage[N+1]
기록들 저장 배열 : record[M][3];
재귀로 해야 할듯(배열 길이인 N이 변함)

입력 형식
T
N X M
l r s => M번만큼 받아옴

출력 규칙
1번 우리부터 N번 우리의 순서대로 우리에 있는 햄스터 수를 공백 하나로 구분하여 출력
방법이 여러가지면, 햄스터 수가 가장 많은 것을 출력
그래도 가능한 방법이 여러 가지면, 가장 앞선 것을 출력 ex) 9 10 10 > 10 9 10

3개의 우리, 각 우리의 햄스터 마리 수 5, 기록 수 1
1번 우리부터 2번우리까지 세었더니 5마리다.

0 5 5
1 4 5
2 3 5
3 2 5
4 1 5
5 0 5
0 5 4

중복을 허용하고 순서를 가지니까 중복 순열인거 같다.
1부터 N까지 뒤져 각 숫자마다
그리고 깊이가 N+1이랑 같아지면
저장한 배열을 보고 조건식을 따져
조건식 통과하면 각 요소의 합이 제일 큰지 비교해
제일 크면 제일 앞의 요소부터 비교해서 작으면 다음 요소 비교 크면 그냥 끝나



*/
public class SW_D4_햄스터 {
	static int[] ans;
	static int[] cage;
	static int[][] record;
	
	static int T;
	static int N;
	static int X;
	static int M;
	static int maxSum;
	static boolean flag;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			maxSum = -1;
			flag = false;
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken()); //우리수
			X = Integer.parseInt(tokens.nextToken()); //최대마리 수
			M = Integer.parseInt(tokens.nextToken()); //기록 수
			
			ans = new int[N];
			for(int i=0; i<ans.length; i++) {
				ans[i] = X+1;
			}
			ans[N-1] = 0;
			cage = new int[N];
			record = new int[M][3];
			
			for(int i=0; i<M; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j=0; j<3; j++) {
					record[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			solution(N-1);
			if(flag) {
				builder.append("#").append(tc).append(" ");
				for(int i=0; i<ans.length; i++) {
					builder.append(ans[i]).append(" ");
				}
			}
			else {
				builder.append("#").append(tc).append(" ").append("-1");
			}
			builder.append("\n");
		}
		System.out.println(builder);
	
	}
	
	static void solution(int cnt) {
		if(cnt == -1) {
			// 조건식 불러와
			for(int i=0; i<M; i++) {
				int left = record[i][0] - 1;
				int right = record[i][1] - 1;
				int s = record[i][2];
				int sum =0 ;
				for( ; left<=right ; left++) {
					sum += cage[left];
				}
				// 조건 마리수가 맞는지 확인
				if(sum == s) {
				// 조건 통과
					continue;
				// 조건 마리수가 안맞으면 돌아가
				} 
				
				else {
					return;
				}
			}
			int sum=0;
			for(int i=0; i<cage.length; i++) {
				sum += i;
			}
			if(sum > maxSum) {
				maxSum = sum;
//				if(cage[0] < ans[0]) {
//					flag = true;
//					ans = Arrays.copyOf(cage, cage.length);
//				}
				flag = true;
				ans = Arrays.copyOf(cage, cage.length);
			}
			return;
		}
		
		
		for(int i=X; i>=0; i--) {
			cage[cnt] = i;
			solution(cnt-1);
		}
	}

}
