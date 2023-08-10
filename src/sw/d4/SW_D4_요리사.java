package sw.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 10
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWIeUtVakTMDFAVH&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=7
@git
@performance 162ms
@category #
@note
시간 : 50개 TC 3초
N : 4이상 16이하
N*N의 식재료 테이블과 그 식재료 궁합 값을 줌
1*1, 2*2 이런 같은 재료들의 궁합 값은 0으로 표시

1,2를 탐색한다면 1,2값이랑 2,1값을 더해
이러면 한 음식의 시너지 값이 계산된거임
두번째 음식을 탐색해
3,4 탐색 => 3,4값 + 4,3값
두번째 음식 시너지 값 계산 완료
그럼 음식1 - 음식2의 절대값이 가장 작은 걸 출력하래

N을 2로 나눈만큼 재료를 나눠가져
ex) N:6 1,2,3/4,5,6           1,2,4/3,5,6 ....이렇게
6C3 A로 나온 값을 제외한 값은 B에 넣어(isSelected사용)

그리고 나온 요리들은 이중 for문으로 2값씩 꺼내서 더해줌(이 때 안쪽 배열은 i+1을 시작값으로 넣어 조합이니까 전에꺼 볼 필요 없어)
그리고 1,2 2,3 1,3의 요리의 시너지를 다 더해  4,5 5,6 4,6 이 요리의 시너지를 다 더해
그리고 요리 시너지의 차를 구해서 min보다 작으면 넣어
이거 반복


*/
public class SW_D4_요리사 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int min;
	static int N;
	static int[] ma;
	static int[] foodsA;
	static int[] foodsB;
	static int[][] map;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(input.readLine());
			min = Integer.MAX_VALUE;
			ma = new int[N];
			for(int i=0; i<N; i++) {
				ma[i] = i;
			}
			foodsA = new int[N/2];
			foodsB = new int[N/2];
			
			map = new int[N][N];
			isSelected = new boolean[N];
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			makeCombination(0, 0, foodsA);
			builder.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(builder);
	}
	
	static void makeCombination(final int nth, final int startIndex, int[] foods) {
        // 기저 조건
        if (nth == foods.length) {
        	int j=0;
        	// foodsA가 아닌거를 foodsB에 넣어줌
        	for(int i=0; i<N; i++) {
        		if(!isSelected[i]) {
        			foodsB[j++] = ma[i];
        		}
        	}
        	int sumA = solve(foodsA);
        	int sumB = solve(foodsB);
        	
        	min = Math.min(Math.abs(sumA - sumB), min);
            return;
        }

        for (int i = startIndex; i < ma.length; i++) {
        	if(!isSelected[i]) {
        		foods[nth] = ma[i];
                isSelected[i] = true;
                makeCombination(nth + 1, i + 1, foods);
                isSelected[i] = false;
        	}
        }
    }
	
	static int solve(int[] foods)
	{
		int sum = 0;
		for(int i=0; i<foods.length; i++) {
			for(int j=i+1; j<foods.length; j++) {
				sum += map[foods[i]][foods[j]];
				sum += map[foods[j]][foods[i]];
			}
		}
		return sum;
	}


}
