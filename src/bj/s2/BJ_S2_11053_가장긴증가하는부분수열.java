package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_S2_11053_가장긴증가하는부분수열 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static int[] lines;
	
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
		System.out.println(dp.size());
	}

	private static void binarySearch(int line) {
		
		// 그냥 추가
		if(dp.size() == 0) {
			dp.add(line);
		}

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
