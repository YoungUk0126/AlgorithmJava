package bj.g5;
/**
@author 김영욱
@since 2023.08.11
@see https://www.acmicpc.net/problem/15686
@git
@performance 260ms
@category #
@note
시간 1초
N*N 크기인 도시
M : 최대 치킨집의 개수(M이하여도 됨)

(r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|
(2,1)에 있는 집과 (1,2)에 있는 치킨집과의 거리는 |2-1| + |1-2| = 2

모든 1에서 가장 가까운 위치의 2로 갔을때 걸린 거리의 총 합의 최소는?
이 때 2의 개수가 M보다 크면 안된다.(작거나 같은건 상관없음)

처음 입력받을때 숫자가 2라면 그때 치킨 배열에 i,j를 배열안에 넣어놔
그리고 치킨집의 총 개수를 구해
처음 입력받을때 숫자가 1라면 그때 집 배열에 i,j를 배열안에 넣어놔

0부터 치킨집의 총 개수-1만큼의 배열을 만들어 (0,1,2,3)
얘를 가지고 M길이 만큼 조합을 만들어(이건 1씩 줄여나가)

나온 숫자의 조합에 해당하는 치킨집만 들려서 최소거리를 구해서 클래스에 넣어
클래스에 있는 최소거리의 합이 전체 최소거리보다 작으면 넣어줘
다시 클래스에 있는 최소거리 매개변수 초기화 해줘.

반복하면 끝

제일 큰걸 없애



*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class BBQ{
	int i;
	int j;
	
	public BBQ(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
class Home{
	int i;
	int j;
	int minDistance;
	
	public Home(int i, int j) {
		this.i = i;
		this.j = j;
		this.minDistance = Integer.MAX_VALUE;
	}
	public void setD(int d) {
		this.minDistance = d;
	}
}

public class BJ_G5_15686_치킨배달 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int[][] map;
	static int N, M, min, chickenCnt;
	static ArrayList<BBQ> c = new ArrayList<>();
	static ArrayList<Home> h = new ArrayList<>();
	static int[] forCom;
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 2)
				{
					c.add(new BBQ(i,j));
					chickenCnt++;
				}
				else if(map[i][j] == 1)
				{
					h.add(new Home(i,j));
				}
			}
		}
		
		forCom = new int[chickenCnt];
		for(int j=0; j<chickenCnt; j++) {
			forCom[j] = j;
		}
		//조합을 구해서
		for(int i=M; i>0; i--) {
			makeCombination(0, 0, new int[i]);
		}
		System.out.println(min);
	}
	static void makeCombination(final int nth, final int startIndex, int[] choosed) {
        // M길이의 조합을 구했으면
        if (nth == choosed.length) {
        	// 선택된 인덱스에 있는 치킨집 인덱스를 가져옴
        	for(int i=0; i<choosed.length; i++) {
        		int now = choosed[i];
        		// 집에서 치킨집까지의 최소거리를 각각 저장해주고
        		for(int j=0; j<h.size(); j++) {
        			BBQ b = c.get(now);
        			Home home = h.get(j);
        			int temp = cal(home.i, home.j, b.i, b.j);
    				home.setD(Math.min(temp, home.minDistance));
        		}
        		int sum = 0;
        		for(int j=0; j<h.size(); j++) {
        			Home home = h.get(j);
        			sum += home.minDistance;
        		}
        		min = Math.min(sum, min);
        		
        	}
        	// 다시 최소거리 초기화
        	for(int i=0; i<h.size(); i++) {
        		Home home = h.get(i);
        		home.setD(Integer.MAX_VALUE);
        	}
            return;
        }

        for (int i = startIndex; i < forCom.length; i++) {
            choosed[nth] = forCom[i];
            makeCombination(nth + 1, i + 1, choosed);
        }
    }
	
	static int cal(int homeRow, int homeCol, int cRow, int cCol) {
		return Math.abs(homeRow - cRow) + Math.abs(homeCol - cCol);
	}

}
