package bj.g1;
/**
@author 김영욱
@since 2023. 09. 19
@see https://www.acmicpc.net/problem/1194
@git
@performance
@category #
@note
방문 배열을 쓰긴 해야되는데 개조를 해야함
a,b,c,d,e를 먹었는지 안먹었는지 확인해줘야 하기 때문
8차원 배열을 만들 순 없기 때문에 3차원에 첫번째 값을 비트마스킹으로 처리해야함


*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class pos{
	int x;
	int y;
	int key;
	public pos(int x, int y, int key) {
		super();
		this.x = x;
		this.y = y;
		this.key = key;
	}
}

public class BJ_G1_1194_달이차오른다 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int deltas[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static char[][] map;
	static int N, M, ans = Integer.MAX_VALUE;
	static pos start;
	
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String temp = input.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == '0')
					start = new pos(i,j,0);
			}
		}
		

	}
	
	public static boolean isIn(int row, int col) {
        return (row >= 0 && row < N) && (col >= 0 && col < M);
    }
}
