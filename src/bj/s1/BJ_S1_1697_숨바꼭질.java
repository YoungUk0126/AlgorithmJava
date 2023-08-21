package bj.s1;
/**
@author 김영욱
@since 2023. 08. 11
@see https://www.acmicpc.net/problem/1697
@git
@performance
@category #
@note
수빈이의 위치가 X일 때 걷는다면 1초 후 X-1, X+1
순간이동을 하는 경우에는 2*X로 이동
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_S1_1697_숨바꼭질 {
	static final int MAX_DISTANCE = 100000;
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	static Queue<int []> q;
	static boolean[] v;
	
	static int N,K,min;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		min = Integer.MAX_VALUE;
		// 수빈이가 동생보다 먼 위치에 있다면 무조건 -1하는거니까
		if(N >= K) {
			System.out.println(N-K);
			return;
		}
		bfs(N);
		System.out.println(min);
	}
	//파멸적인 재귀 상태
	static void bfs(int start) {
		q = new ArrayDeque<>();
		v = new boolean[MAX_DISTANCE+1];
		q.offer(new int[] {start,0});
		v[start] = true;
		int[] arr = new int[2];
		
		while(!q.isEmpty()) {
			arr = q.poll();
			int current = arr[0];
			int cnt = arr[1];
			
			if(current > K*2) {
				continue;
			}
			if(current == K) {
				min = Math.min(min, cnt);
				break;
			}
			else {
				if((current+1)<=MAX_DISTANCE && !v[current+1]) {
					q.offer(new int[] {current+1,cnt+1});
					v[current+1] = true;
				}
				if((current - 1) >= 0&& (current-1)<=MAX_DISTANCE && !v[current-1]) {
					q.offer(new int[] {current-1,cnt+1});
					v[current-1] = true;
				}
				if((current*2)<=MAX_DISTANCE && !v[current*2]) {
					q.offer(new int[] {current*2,cnt+1});
					v[current*2] = true;
				}
			}
		}
	}
	
}
