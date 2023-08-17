package sw.d5;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.08.17
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV15OZ4qAPICFAYD&probBoxId=AYoA3iR6NcgDFAU6&type=PROBLEM&problemBoxTitle=0814%EC%A3%BC&problemBoxCnt=2
@git
@performance
@category #
@note
김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집에 돌아간다.
회사와 집의 위치, 그리고 각 고객의 위치는 2차원 정수 좌표(x,y)로 주어지고
두 위치 (x1, y1)와 (x2, y2)사이의 거리는 |x1-x2| + |y1-y2|로 계산
회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.

회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 가장 짧은 경로를 찾으려한다.

====입력======
고객의 수 N
회사의 좌표 2개, 집의 좌표 2개, N명의 고객의 좌표가 차례대로 나온다.

NextPermutation을 이용하여 고객N명의 위치를 순열로 쭉 뽑아준다.
나온 순열을 바탕으로 탐색을 하고, 식들 계산해서 최소값 구함
*/
class node{
	int x;
	int y;
	
	public node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}



public class SW_D5_최적경로 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static boolean isVisited[];
	static int forNp[];
	static int ans, N, min;
	static node company;
	static node home;
	static node[] customers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(input.readLine());
			
			min = Integer.MAX_VALUE;
			isVisited = new boolean[N];
			forNp = new int[N];
			
			tokens = new StringTokenizer(input.readLine());
			
			int i = Integer.parseInt(tokens.nextToken());
			int j = Integer.parseInt(tokens.nextToken());
			company = new node(i,j);
			i = Integer.parseInt(tokens.nextToken());
			j = Integer.parseInt(tokens.nextToken());
			home = new node(i,j);
			
			customers = new node[N];
			
			for(int k=0; k<N; k++) {
				forNp[k] = k;
				i = Integer.parseInt(tokens.nextToken());
				j = Integer.parseInt(tokens.nextToken());
				customers[k] = new node(i, j);
			}
			
			do {
				// 집이랑 첫번째 손님이랑 거리 계산
				ans = Math.abs(customers[forNp[0]].x - company.x) + Math.abs(customers[forNp[0]].y - company.y);
				for(i=1; i<N; i++) {
					// 각 손님에서 손님 거리 계산해서 넣어주기
					ans += Math.abs(customers[forNp[i]].x - customers[forNp[i-1]].x) + Math.abs(customers[forNp[i]].y - customers[forNp[i-1]].y);
				}
				ans += Math.abs(customers[forNp[N-1]].x - home.x) + Math.abs(customers[forNp[N-1]].y - home.y);
				min = Math.min(min, ans);
			} while(Np());
			builder.append("#").append(tc).append(" ").append(min);
			builder.append("\n");
		}
		System.out.println(builder);
	}
	

	private static boolean Np() {
		int lastPeek = forNp.length - 1;
		while(lastPeek > 0 && forNp[lastPeek-1] >= forNp[lastPeek] ) lastPeek--;
		
		if(lastPeek == 0) return false;
		
		int newBoss = forNp.length - 1;
		
		while(forNp[lastPeek - 1] >= forNp[newBoss]) newBoss--;
		
		swap(forNp, lastPeek-1, newBoss);
		
		for(int left=lastPeek, right=forNp.length-1; left<right; left++,right--) {
			swap(forNp, left, right);
		}
		return true;
	}

	private static void swap(int[] forNp, int lastPeek, int newBoss) {
		// TODO Auto-generated method stub
		int temp = forNp[lastPeek];
		forNp[lastPeek] = forNp[newBoss];
		forNp[newBoss] = temp;
	}

}
