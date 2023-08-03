package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @see 2차원 배열, 2008.데일리실습 3-3. 빌딩건설 Lv3
 * @perf 메모리, 시간
 * @cat  2차원 배열 탐색
 * ****************문제 읽기******************
 * N : 부지의 길이(3-20)
 * 구획당 하나의 빌딩 - 빌딩 : B, 공원 : G
 * 빌딩의 높이 - 인접한 구역(8방 탐색)에 G? - 2층
 * 			없으면 - 가로 B + 세로 B(4방 탐색)
 * 						현재 위치 포함(중복 주의)
 * 			영역 밖은 고려X
 * 가장 높이 세울 수 있는 빌딩?
 * ****************접근 방법******************
 * 맵 행우선 탐색
 *  - B를 만나면 8방탐색 -> G가 있나?
 *  - G가 있다면 높이는 2, 없으면 가로로, 세로로 B 더하기
 *  - 이 높이와 최대 높이를 비교 -> 최대값 구하기
 * ****************시간 복잡도******************
 * O(N^2) => 최대 20 => 400
 * 
 * @author SSAFY
 *
 */
public class BuildingTeacher {
	//입력은 bufferedReader로 받는다.
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	//출력은 StringBuilder로 한번에 출력한다.
	static StringBuilder output = new StringBuilder();
	//입력 받은 String을 쪼개줄 Tokenizer 사용
	static StringTokenizer tokens;
	
	static int T;
	static int N;
	static String [][] map;
	static int A; // 입력값이 아니기 때문에 초기화를 해줘야한다.
	
	static int [][] deltas = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0, -1},{-1, -1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new StringReader(str));
		T = Integer.parseInt(input.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(input.readLine());
			map = new String [N][N];
			
			for(int r=0; r<N; r++) {
				tokens = new StringTokenizer(input.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = tokens.nextToken();
				}
			}
//			for(String [] row: map) {
//				System.out.println(Arrays.toString(row));
//			}
			// 입력 완료
			A = 0;
			// solve
			travel();
			output.append("#").append(t).append(" ").append(A).append("\n");
		}
		System.out.println(output);
		
	}
	
	private static void travel() {
		// 맵을 행우선 탐색
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				//B를 만나면 팔방탐색 => deltas만들자 => deltas를 규칙성있게 만들어서 8방 4방 다 할 수 있게 만들자.
				int newHeight = 0;
				if(map[r][c].equals("B")) {
					//G가 있다면 높이 2, 없으면 가로로, 세로로 B 더하기
					boolean hasGarden = false;
					for(int d=0; d<deltas.length; d++) {
						int nr = r + deltas[d][0];
						int nc = c + deltas[d][1];
						
						if(isIn(nr, nc) && map[nr][nc].equals("G")) {
							hasGarden = true;
							break;
						}
					}//가든 탐색 완료
					
					if(hasGarden) {
						newHeight = 2;
					} else {
						newHeight = getHeight(r, c);
					}
				}
				A = Math.max(A, newHeight);
			}
		}
	}
	
	
	private static int getHeight(int r, int c) {
		int sum = 1;//자기 자신
		for(int d=0; d<deltas.length; d+=2) {
			for(int l=1; ; l++) {
				int nr = r + deltas[d][0] * l;
				int nc = c + deltas[d][1] * l;
				if(isIn(nr,nc)) {
					if(map[nr][nc].equals("B")) {
						sum += 1;
					}
				} else {
					break;
				}
			}
		}
		return sum;
	}

	private static boolean isIn(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	
	
	
	
	
	
	
	
	//미리 테스트 케이스를 받아놓음
	static String str="3\r\n" + 
			"6\r\n" + 
			"G B G G B B\r\n" + 
			"G B G G B G \r\n" + 
			"B B B B G B\r\n" + 
			"B G B B B B\r\n" + 
			"G B B B B G\r\n" + 
			"G B B B B G\r\n" + 
			"5\r\n" + 
			"G B G G B \r\n" + 
			"G B G G B  \r\n" + 
			"B B B B G \r\n" + 
			"B G B B B \r\n" + 
			"G B B B B \r\n" + 
			"3\r\n" + 
			"G G B\r\n" + 
			"G B B\r\n" + 
			"B B B";

}
