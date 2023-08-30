package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 30.
@see https://www.acmicpc.net/problem/10971
@git
@performance 
@category #
@note
시간 : 2초
N : 10 , 0<Wij<=1,000,000

도시들 사이에는 길이 있다.(없을 수도 있다)
한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획
한 번 갔던 도시로는 다시 갈 수 없다.(돌아오는거는 제외)
가장 적은 비용을 들이는 여행 계획을 세우고자 한다.

순열을 구한 후 마지막에 처음 방문했던 도시를 추가한다.
맵에 다음 행선지로 가는 값이 0이 아니라면 방문하고 cost추가
가장 작은 cost를 구하면 끝

근데 순열을 구할때 미리 map인덱스에 0인지 아닌지 체크해주면 더 빠르게 동작하는 것 같다.
(그 재귀는 아예 안도니까)


도시의 수 N이 주어진다.
다음 N개의 줄에는 비용 행렬이 주어진다.



*/
public class BJ_S2_10971_외판원순회2 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, map[][];
	static int min;
	// 순열을 위해 만들어 놓은 배열
	static int choosed[];
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		map = new int[N][N];
		min = Integer.MAX_VALUE;

		choosed = new int[N];
		for(int i=0; i<N; i++) {
			choosed[i] = i;
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		makePermutation(0, new int[N+1], new boolean[N]);
		System.out.println(min);
		

	}
	
	static void makePermutation(int cnt, int per[], boolean isSelected[]) {
		// 기저조건
		if(cnt == per.length-1) {
			// 끝에 돌아올 지점 넣어놓음
			per[per.length-1] = per[0];
//			System.out.println(Arrays.toString(per));
			int cost = 0;
			for(int i=0; i<per.length-1; i++) {
				if(map[per[i]][per[i+1]] != 0)
					cost += map[per[i]][per[i+1]];
				else
					return;
			}
			min = Math.min(min, cost);
			return;
		}
		
		for(int i=0; i<choosed.length; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				per[cnt] = choosed[i];
				makePermutation(cnt + 1, per, isSelected);
				isSelected[i] = false;
			}
		}
		
	}

}
