package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.lang.Math;

/**
@author 김영욱
@since 2023. 09. 12
@see https://www.acmicpc.net/problem/2473
@git
@performance 183ms
@category #
@note
음수부터 양수까지 용액의  특성값이 주어진다.
이 특성값들이 세가지 합이 0과 가장 가까운 값을 출력해라.

두 용액처럼 정렬 후 투포인터를 쓰는 것은 확실한데
용액을 한 가지 더 섞어야 하는 문제다.
for문을 N-2까지 돌리고 그 용액은 고정이다.
선택된 용액 +1 ,끝부터 두개의 포인터를 출발시키자
두 개의 포인터를 찍은 후 값을 비교하여 left, right를 이동시킨다.
그 수가 min보다 0에 가깝다면 min을 업데이트 한다.
*/
public class BJ_G3_2473_세용액 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, left, right,mid;
	static long min, ans[], arr[];
	

	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(input.readLine());
		arr = new long[N];
		min = Long.MAX_VALUE;
		ans = new long[3];
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(arr);
		long sum = 0;
		for(int i=0; i<N-2; i++) {
			left = i+1;
			right = N-1;
			while( left < right ) {
				sum = (long)arr[left] + arr[right] + arr[i];
				if(min > Math.abs(sum)) {
					min = Math.abs(sum);
					ans[0] = arr[left];
					ans[1] = arr[right];
					ans[2] = arr[i];
				}
				else if(sum > 0) {
					right--;
				}
				else {
					left++;
				}
			}
		}
		
		Arrays.sort(ans);
		for(int i=0; i<3; i++) {
			System.out.print(ans[i] + " ");
		}
	}

}
