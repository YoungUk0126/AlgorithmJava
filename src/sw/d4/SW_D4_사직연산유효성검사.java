package sw.d4;
/**
@author 김영욱
@since 2023. 08. 08
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV141176AIwCFAYD&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=2
@git
@performance 0.12s
@category #
@note
각 테스트 케이스의 첫 줄에는 각 케이스의 트리가 갖는 정점의 총 수N이 주어진다.
그 다음 N줄에 걸쳐 각각의 정점 정보가 주어진다.
해당 정점의 알파벳, 해당 정점의 왼쪽 자식, 오른쪽 자식의 정점 번호가 차례대로 주어진다.
정점 번호는 1부터 N까지의 정수로 구분된다.

입력에서 이웃한 숫자 또는 연산자, 자식 정점의 번호는 모두 공백으로 구분한다.
///입력///
정점번호 숫자
정점번호 연산자 왼쪽자식 오른쪽자식

트리에 넣고 이 트리가 연산이 가능하면 1, 불가능하면 0을 출력하라.

어차피 이진트리라 따로 클래스를 줄 필요가 없음.

정점 개수 N
트리들을 넣을 배열[N+1]

트리를 거꾸로 순회하면서 숫자를 만나면 부모 노드를 찾아 i/2
그 값에서 왼쪽 자식과 오른쪽 자식이 숫자인지 부모는 숫자가 아닌지 확인해
아니라면 부모가 숫자라면 실패.
아니면 ans = 0넣고 종료
맞으면 계속 반복문 지속
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_D4_사직연산유효성검사 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N;
	static char[] tree;
	static int ans;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		for(int tc=1; tc<=10; tc++) {
			N = Integer.parseInt(input.readLine());
			ans = 1;
			tree = new char[N+1];
			
			for(int i=1; i<=N; i++) {
				tokens = new StringTokenizer(input.readLine());
				char dummy = tokens.nextToken().charAt(0);
				tree[i] = tokens.nextToken().charAt(0);
			}
			
			for(int i=N; i>=0; i--) {
				if(Character.isDigit(tree[i])) {
					char parent = tree[i/2];
					int parentIdx = i/2;
					// 오른쪽 자식 인덱스가 N을 넘어간다면 이번 반복 패스
					if((parentIdx*2)+1>=N) continue;
					
					char leftChild = tree[parentIdx * 2];
					char rightChild = tree[(parentIdx * 2)+1];
					
					if(Character.isDigit(leftChild) && Character.isDigit(rightChild) && !Character.isDigit(parent)) {
						i = leftChild;
						continue;
					} else {
						if(Character.isDigit(parent)) {
							ans = 0;
							break;
						}
					}
				}
			}
			builder.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

}
