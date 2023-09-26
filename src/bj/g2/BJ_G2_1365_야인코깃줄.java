package bj.g2;

/**
@author 본인이름
@since 2023. 9. 26.
@see https://www.acmicpc.net/problem/1365
@git
@youtube
@performance
@category #
@note 
1초
N <= 100,000
길 왼편과 오른편에 전봇대는 하나의 전선으로 연결되어 있다.
어떤 전봇대도 두 개 이상의 다른 전봇대와 연결되어 있지 않다.

문제는 이 전봇대 사이에 있는 전깃줄이 매우 꼬여 있다는 점이다.
꼬여 있는 전깃줄 중 몇 개를 최소로 하여 잘라내어 이 문제를 해결하기로 했다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G2_1365_야인코깃줄 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[] lines;
	static int ans;
	
	static ArrayList<Integer> dp = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		lines = new int[N];
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=0; i<N; i++) {
			lines[i] = Integer.parseInt(tokens.nextToken());
		}
		// DP 처음 값은 그냥 넣기
		dp.add(lines[0]);
		for(int i=1; i<lines.length; i++) {
			binarySearch(lines[i]);
		}
		System.out.println(lines.length - dp.size());
	}

	private static void binarySearch(int line) {

		// dp 처음 값보다 현재 들어갈 값이 더 작다면 바꿔준다.
		if(dp.get(0) >= line) {
			dp.set(0, line);
		}
		// dp 끝 값보다 현재 들어갈 값이 더 크다면 끝에 추가한다.
		else if(dp.get(dp.size()-1) < line) {
			dp.add(line);
		}
		// 모두 아니라면 이분탐색 시작
		else {
			int left = 0;
			int right = dp.size()-1;
			int mid = (left + right) / 2;
			while(left < right) {
				if(line > dp.get(mid)) {
					left = mid+1;
				}
				else {
					right = mid;
				}
				mid = (left + right) / 2;
			}
			dp.set(mid, line);
		}
		
	}

}
