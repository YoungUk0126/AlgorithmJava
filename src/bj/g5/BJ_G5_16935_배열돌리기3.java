package bj.g5;
/**
@author 김영욱
@since 2023. 08. 09
@see https://www.acmicpc.net/problem/16935
@git
@performance
@category #
@note
크기가 N*M인 배열이 있을 때, 배열에 연산을 R번 적용하려고 한다.
알고보니까 R은 마지막에 연산 배열의 길이다...
같은걸 R번하는게 아니라...
문제를 끝까지 읽자 제발
N,M은 짝수이고, R은 1000번 이하다.

연산1: 상하 반전
연산2: 좌우 반전
연산3: 오른쪽으로 90도 회전
연산4: 왼쪽으로 90도 회전
연산5: 배열을 크기가 N/2 * M/2인 4개의 부분 배열로 나눈 후,
1번 => 2번, 2번 => 3번, 3번 => 4번, 4번 => 1번
연산6: 1번 => 4번, 4번 => 3번, 3번 => 2번, 2번 => 1번

연산 1,2번은 2로나눠서 짝수면 그대로 출력, 홀수일 때만 바꿔주면 되겠다.
연산 3,4번은 4로 나누고 나머지 만큼만 돌려주면 되겠다.
5,6번은 우선 깡으로 4개의 배열로 나누고, R을 4로 나누고 남은 숫자만큼
배열 위치를 돌려서 출력하면..되겠는데..어떻게 하지..??
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_16935_배열돌리기3 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M,R,toDo;
	static int[][] map;
	static int[][] ans;
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		toDo = 0;
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<R; i++) {
			toDo = Integer.parseInt(tokens.nextToken());
			switch (toDo) {
			case 1:
				process1();
				break;
			case 2:
				process2();
				break;
			case 3:
				process3();
				break;
			case 4:
				process4();
				break;
			case 5:
				process5();
				break;
			case 6:
				process6();
				break;
			}
		}
		printAns(map);
		System.out.println(builder);
	}
	
	static void process1() {
		ans = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				ans[i][j] = map[N-i-1][j];
			}
		}
		map = ans;
	}
	static void process2() {
		ans = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				ans[i][j] = map[i][M-j-1];
			}
		}
		map = ans;
	}
	static void process3() {
		ans = new int[M][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				ans[j][N-i-1] = map[i][j];
			}
		}
		int temp = N;
		N = M;
		M = temp;
		map = ans;
	}
	static void process4() {
		ans = new int[M][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				ans[M-j-1][i] = map[i][j];
			}
		}
		int temp = N;
		N = M;
		M = temp;
		map = ans;
	}
	static void process5() {
		ans = new int[N][M];
		// 1분면
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				ans[i][M/2+j] = map[i][j];
			}
		}
		// 2분면
		for(int i=0; i<N/2; i++) {
			for(int j=M/2; j<M; j++) {
				ans[N/2+i][j] = map[i][j];
			}
		}
		// 3분면
		for(int i=N/2; i<N; i++) {
			for(int j=M/2; j<M; j++) {
				ans[i][j - M/2] = map[i][j];
			}
		}
		// 4분면
		for(int i=N/2; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				ans[i - N/2][j] = map[i][j];
			}
		}
		map = ans;
				
		
	}
	static void process6() {
		ans = new int[N][M];
		// 1분면
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				ans[N/2 + i][j] = map[i][j];
			}
		}
		// 2분면
		for(int i=0; i<N/2; i++) {
			for(int j=M/2; j<M; j++) {
				ans[i][j - M/2] = map[i][j];
			}
		}
		// 3분면
		for(int i=N/2; i<N; i++) {
			for(int j=M/2; j<M; j++) {
				ans[i - N/2][j] = map[i][j];
			}
		}
		// 4분면
		for(int i=N/2; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				ans[i][j+M/2] = map[i][j];
			}
		}
		map = ans;
	}
	
	static void printAns(int[][] temp) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				builder.append(temp[i][j]).append(" ");
			}
			builder.append("\n");
		}
	}
	

}
