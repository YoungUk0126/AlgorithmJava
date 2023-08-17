package sw.d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

/**
@author 김영욱
@since 2023.08.17
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWXRDL1aeugDFAUo&probBoxId=AYoA3iR6NcgDFAU6&type=PROBLEM&problemBoxTitle=0814%EC%A3%BC&problemBoxCnt=2
@git
@performance
@category #
@note
스마트폰을 무선 충전 할 때 최적의 BC를 선택하는 알고리즘을 개발하고자 한다. 
가로 세로 10*10 영역의 지도가 주어졌을때, 설치된 BC정보는 다음과 같다.
위치
충전 범위
성능

BC의 충전 범위가 C일 때, BC와 거리가 C이하이면 BC에 접속할 수 있다.
좌표 거리 계산은 |xa - xb| + |ya - yb|
BC두 개의 충전 범위에 모두 속한다면 하나를 선택하여 접속 가능

사용자 A와 B의 이동 궤적도 준다. 
매초마다 BC의 충전 범위 안에 있게 되면 해당 BC에 접속이 가능하다.
한 BC에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수만큼 충전 양을 균등하게 분배한다.

모든 사용자가 충전한 양의 합의 최댓값을 구해라.

입력
T
totalTime(총 이동 시간), BC(BC의 개수)
사용자 A의 이동 정보(totalTime개의 숫자)
0: 이동X, 1: 상, 2: 우, 3: 하, 4: 좌
사용자 B의 이동 정보

BC개의 줄에 걸쳐 BC의 정보가 주어진다.
좌표(X,Y), 충전 범위(C), 처리량(P)
[제약사항]

1. 지도의 가로, 세로 크기는 10이다.
2. 사용자는 총 2명이며, 사용자A는 지도의 (1, 1) 지점에서, 
사용자B는 지도의 (10, 10) 지점에서 출발한다.
3. 총 이동 시간 M은 20이상 100이하의 정수이다. (20 ≤ M ≤ 100)
4. BC의 개수 A는 1이상 8이하의 정수이다. (1 ≤ A ≤ 8)
5. BC의 충전 범위 C는 1이상 4이하의 정수이다. (1 ≤ C ≤ 4)
6. BC의 성능 P는 10이상 500이하의 짝수이다. (10 ≤ P ≤ 500)
7. 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
8. 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 
그러나 사용자A, B가 동시에 같은 위치로 이동할 수는 있다. 
사용자가 지도 밖으로 이동하는 경우는 없다.

우선 지도를 만들어야 함.
dfs로 미리 채우려 했으나
*/
class Person {
	int x;
	int y;
	public Person(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

class Battery {
	int x;
	int y;
	int cRange;
	int p;
	
	public Battery(int x, int y, int cRange, int p) {
		super();
		this.x = x;
		this.y = y;
		this.cRange = cRange;
		this.p = p;
	}
	
}
public class SW_D5_무선충전 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int map[][];
	static int sumA, sumB;
	static int totalTime, BC;
	static int moving[][] = {{0,0}, {0,-1}, {-1,0}, {0,1}, {1,0}};
	
	static int Amove[];
	static int Bmove[];
	static Battery[] batteries;
	static Person A;
	static Person B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			map = new int[11][11];
			tokens = new StringTokenizer(input.readLine());
			totalTime = Integer.parseInt(tokens.nextToken());
			BC = Integer.parseInt(tokens.nextToken());
			
			Amove = new int[totalTime];
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<totalTime; i++) {
				Amove[i] = Integer.parseInt(tokens.nextToken());
			}
			
			Bmove = new int[totalTime];
			tokens = new StringTokenizer(input.readLine());
			for(int i=0; i<totalTime; i++) {
				Bmove[i] = Integer.parseInt(tokens.nextToken());
			}
			
			batteries = new Battery[BC+1];
			for(int b=1; b<=BC; b++) {
				tokens = new StringTokenizer(input.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				int cRange = Integer.parseInt(tokens.nextToken());
				int p = Integer.parseInt(tokens.nextToken());
				
				map[x][y] = 1;
				dfs(x, y, cRange, 0);
				
				batteries[b] = new Battery(x, y, cRange, p);
			}
			
			for(int i=1; i<11; i++) {
				for(int j=1; j<11; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			int cnt=0;
			A = new Person(1, 1);
			B = new Person(10, 10);
			
			getCharged();
			while(cnt<totalTime) {
				A.x += moving[Amove[cnt]][0];
				B.x += moving[Bmove[cnt]][0];
				A.y += moving[Amove[cnt]][1];
				B.y += moving[Bmove[cnt]][1];
				
				getCharged();
			}
			
		}
	}
	private static void getCharged() {
		ArrayList< int[] > ARangeList = new ArrayList<>();
		ArrayList< int[] > BRangeList = new ArrayList<>();
		
		if(map[A.x][A.y] == 1) {
			for(int b=1; b<=BC; b++) {
				if(BRangeCheck(batteries[b])) {
					ARangeList.add(new int[] {A.x, A.y});
				}
			}
		}
		if(map[B.x][B.y] == 1) {
			BRangeList.add(new int[] {B.x, B.y});
		}
		
		getCom(ARangeList, BRangeList);
		
	}
	private static void dfs(int x, int y, int cRange, int cnt) {
		// 기저 조건
		if(cnt == cRange) return;
		
		
		for(int i=1; i<5; i++) {
			int nx = x + moving[i][0];
			int ny = y + moving[i][1];
			if(isIn(nx,ny)) {
				map[nx][ny] = 1;
				dfs(nx,ny,cRange,cnt+1);
			}
		}
		
	}
	private static boolean BRangeCheck(Battery b) {
		
	}
	
	private static boolean isIn(int x, int y) {
		return 1<=x && x<11 && 1<=y && y<11;
	}

}
