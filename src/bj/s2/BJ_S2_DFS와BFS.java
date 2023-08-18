package bj.s2;
/**
@author 김영욱
@since 
@see 
@git
@performance
@category #
@note
이 문제는 인접한 노드들을 입력으로 주어지기 때문에 인접 리스트로 구현해야겠다고 생각이 든다.
하지만 방문할 수 있는 정점이 여러 개면 작은거부터 방문해야해서 리스트 방식은 상당히 번거롭다.
그래서 노드를 만드는 방식 말고 ArrayList를 사용하면 정렬해주기 쉽다.

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_DFS와BFS {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M,V;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());
	}

}
