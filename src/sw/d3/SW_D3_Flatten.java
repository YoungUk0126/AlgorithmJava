package sw.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 1.
@see https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
@git
@performance 0.129s
@category #완전 탐색
@note
상자의 최고점에서 최저점으로 상자를 옮기는 것을 덤프라고함
첫 줄에 덤프 횟수 주어짐 => N
상자 배열의 길이는 항상 100
상자 높이가 담긴 배열 주어짐 => boxes
평탄화 모두 수행시 최고점과 최저점의 높이 차이가 1이거나 0임

값을 빼거나 더하기 위해 배열 인덱스 필요 => 최고점 maxIdx, 최저점 minIdx
							=> 최고점값 max, 최저점값 min

완전 탐색
배열 길이 끝까지 돌면서 최고점 최저점, 찾아서 덤프, 덤프값 -1
계속 반복

*/


public class SW_D3_Flatten {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	static int[] boxes;
	static int N;
	static int maxIdx;
	static int minIdx;
	final static int length = 100;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		for(int tc=1; tc<11; tc++) {
			N = Integer.parseInt(input.readLine());
			boxes = new int[length];
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<length; i++) {
				boxes[i] = Integer.parseInt(tokens.nextToken());
			}
			for(int n=0; n<N; n++) {
				for(int i=0; i<length; i++) {
					if(boxes[i] > boxes[maxIdx]) {
						maxIdx = i;
					}
					if(boxes[i] < boxes[minIdx]) {
						minIdx = i;
					}
				}
				boxes[minIdx] += 1;
				boxes[maxIdx] -= 1;
				if(boxes[maxIdx] - boxes[minIdx] <= 1) break;
			}
			for(int i=0; i<length; i++) {
				if(boxes[i] > boxes[maxIdx]) {
					maxIdx = i;
				}
				if(boxes[i] < boxes[minIdx]) {
					minIdx = i;
				}
			}
			int diff = boxes[maxIdx] - boxes[minIdx];
			System.out.printf("#%d %d\n",tc,diff);
		}
		
	}

}
