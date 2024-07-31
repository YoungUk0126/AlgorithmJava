package basecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 08. 22
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWBJKA6qr2oDFAWr&probBoxId=AYoaJoMabgADFAU6&type=PROBLEM&problemBoxTitle=0821%EC%A3%BC&problemBoxCnt=1
@git
@performance 
@category #
@note
초기에 {1}, {2}, ...{n}이 각각 n개의 집합을 이루고 있다.(자기 자신을 대표자로 내세우는 집합)
여기에 합칩합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행

===========입력============
T
n, m 	n: 집합의 개수, m: 입력으로 주어디는 연산의 개수
Union => 0 a b  	a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 Union
Find => 1 a b 		두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산
*/
public class 서로소집합 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N, M;
	static int[] parents;
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			builder.append("#").append(tc).append(" ");
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			make();
			
			for(int m=0; m<M; m++) {
				tokens = new StringTokenizer(input.readLine());
				int flag = Integer.parseInt(tokens.nextToken());
				if(flag == 0) { // Union 연산
					int a = Integer.parseInt(tokens.nextToken());
					int b = Integer.parseInt(tokens.nextToken());
					union(a, b);
				}
				else if(flag == 1) { // Find 연산
					int aRoot = find(Integer.parseInt(tokens.nextToken()));
					int bRoot = find(Integer.parseInt(tokens.nextToken()));
					if(aRoot == bRoot) builder.append(1);
					else builder.append(0);
				}
			}
			builder.append("\n");
		}
		System.out.println(builder);
	}
	private static void make() {
		parents = new int[N+1]; // 1 base
		for(int i=1; i<=N; i++) {
			parents[i] = i; 
		}
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a; // 내가 곧 대표자라면
		
		return parents[a] = find(parents[a]);
		
	}
	
	private static boolean union(int a, int b) { // 합칠거야
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false; // 같은 집합이니까
		parents[bRoot] = aRoot;
		return true;
	}

}
