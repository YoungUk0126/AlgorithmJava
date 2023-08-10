package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 10
@see https://www.acmicpc.net/problem/17406
@git
@performance
@category #
@note
시간 : 1초
N,M 크기 50
K는 6번

크기 N*M 배열이 있을 때 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값을 의미

N M K => N*M배열 K번 회전한다
N*M 배열 입력
r c s => (r-s, c-s)부터 (r+s, c+s)

K번이면 K자리 수의 배열을 만들고
NP를 통해서 수열을 구해서 배열에 넣어
r c s들은 2차원 배열로 지정해서 미리 저장해둬.


저 구역을 시계 방향으로 돌려
이번에 연산 횟수는 6번밖에 안되니까 큐를 이용해서 풀자
왜냐하면 각 연산마다 한번씩 움직이는거니까.

근데 r c s 가 2번 이상 들어오면
r1 c1 s1
r2 c2 s2

1부터 하는 거랑
2부터 하는 거랑 결과가 달라
각각 구해서 최솟값 구해줘야돼.

*/
public class BJ_G4_17406_배열돌리기4 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static int deltas[][] = { {0,1}, {1,0}, {0,-1}, {-1, 0} };
	static int N,M,K,r,c,s;
	static int arr[][];
	static int ans[][];
	static int orders[][];
	static int ordersPermutation[];
	
	static Queue<Integer> queue;
	
	static int min;
	static int x1,y1,x2,y2;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		queue = new ArrayDeque<>();
		// kPk의 순열을 구해
		orders = new int[K][3];
		ordersPermutation = new int[K];
		for(int i=0; i<K; i++) {
			ordersPermutation[i] = i;
		}
		for(int i=0; i<K; i++) {
			tokens = new StringTokenizer(input.readLine());
			// r c s 값 미리 저장
			orders[i][0] = Integer.parseInt(tokens.nextToken());
			orders[i][1] = Integer.parseInt(tokens.nextToken());
			orders[i][2] = Integer.parseInt(tokens.nextToken());
		}
		do {
			for(int i=0; i<K; i++) {
				int now = ordersPermutation[i];
				r = orders[now][0];
				c = orders[now][1];
				s = orders[now][2];
				x1 = r-s-1; y1 = c-s-1;
				x2 = r+s; y2 = c+s;
				ans = new int[N][M];
				
//				System.out.println(r + " " + c + " " + s);
				//양변 큰값 작은값 구함
				int under = Math.min(x2 - x1, y2 - y1);
				int top = Math.min(x2-x1, y2 - y1);
				int start = 0;
				while(under>0) {
					int tempLength = ((top + under)*2)-4;
					rotate(x1+start, y1+start, x2-start, y2-start, tempLength);
					
					start++;
					top -= 2;
					under -= 2;
				}
				min = Integer.MAX_VALUE;
				for(int r=0; r<N; r++) {
					int sum=0;
					for(int c=0; c<M; c++) {
						System.out.print(ans[r][c] + " ");
						sum+= ans[r][c];
					}
					System.out.println("sum :"+sum);
					min = Math.min(sum, min);
				}
			}
		}while(nextPermutation(ordersPermutation));
		System.out.println("min : "+min);
	
	}

	static void rotate(int x1, int y1, int x2, int y2, int tempLength) {
		int move=0;
		
		int x = x1; int y = y1;
		int d = 0;
		while(tempLength>move) {
			System.out.println("x : "+x+"  y:"+y);
			queue.offer(arr[x][y]);
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];
			if(x1<=nx && nx<x2 && y1<=ny && ny<y2) {
				x = nx;
				y = ny;
				move++;
			} else {
				d = (d+1) % 4;
			}
		}
		
		System.out.println("done");
		
		move = 0;
		x = x1; y = y1 + 1;
		d = 0;
		
		while(tempLength>move) {
			System.out.println("x : "+x+"  y:"+y);
			if(!queue.isEmpty()) ans[x][y] = queue.poll();
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];
			if(x1<=nx && nx<x2 && y1<=ny && ny<y2) {
				x = nx;
				y = ny;
				move++;
			} else {
				d = (d+1) % 4;
			}
		}
		
	}
	
	private static boolean nextPermutation(int[] orders) {
    	// 1. 맨 뒤부터 최고 정점 찾기
    	int lastPeak = orders.length-1;
    	while( lastPeak > 0 && orders[lastPeak-1] >= orders[lastPeak] ) lastPeak--;
    	// 정점이 없는 상황
    	if(lastPeak == 0) return false;
    	
    	// 2. 새 지도자 찾아오기
    	int nextBoss = orders.length - 1;
    	
    	while( orders[lastPeak-1] >= orders[nextBoss] ) nextBoss--;
    	
    	// 3. 지도자의 세대 교체!
    	swap(orders, nextBoss, lastPeak-1);
    	// 4. 새로운 조직의 시작!! 뒤쪽의 정렬
    	for(int left = lastPeak, right = orders.length-1; left < right; left++, right--) {
    		swap(orders, left, right);
    	}
    	return true;
    }
	
	private static void swap(int[] src, int i, int j) {
		// TODO Auto-generated method stub
		int temp = src[i];
		src[i] = src[j];
		src[j] = temp;
	}

}
