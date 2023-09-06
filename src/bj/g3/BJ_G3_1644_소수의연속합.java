package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_1644_소수의연속합 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int end, ans, a[];
	
	// 투포인터 + 에라토스테네스의 체
	public static void main(String[] args) throws IOException{
		end = Integer.parseInt(input.readLine());
		ans = 0;
		a = new int[end+1];
		// 에라토스테네스의 체, 소수가 아닌 것들에 1이 들어감
		for(int i=2; i<=end; i++) {
			if(a[i] > 0) {
				continue;
			}
			
			for(int j=i*2; j<=end; j+=i) {
				if(a[j] > 0) continue;
				a[j] += 1;
			}
		}
		int left = 2;
		int right = 2;
		int sum = left;
		while(left <= right) {
			// 합이 목적 숫자보다 작다면
			if(sum < end) {
				// 일단 끝점 한칸 밀어
				right++;
				// 이게 목적 숫자보다 작거나 같고, 소수가 아니라면
				while(right <= end && a[right]>0) {
					// 소수를 찾을때 까지 계속 밀어
					right++;
				}
				// 그 값을 더해
				sum += right;
			}
			// 연속된 소수의 합이면
			else if(sum == end) {
				// 답넣어
				ans++;
				// 왼쪽에 있던거 미리 빼
				sum -= left;
				// 일단 시작점 한칸 밀어
				left++;
				// 이게 목적 숫자보다 작거나 같고, 소수가 아니라면
				while(left <= end && a[left]>0) {
					// 소수를 찾을때 까지 밀어
					left++;
				}
			}
			else {
				// 연속된 소수의 합이 목적 숫자보다 커지면
				sum -= left;
				left++;
				while(left <= end && a[left]>0) {
					left++;
				}
			}
		}
		System.out.println(ans);
		
		
	}

}
