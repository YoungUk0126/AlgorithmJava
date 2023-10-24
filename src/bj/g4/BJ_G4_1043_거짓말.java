package bj.g4;
/**
@author 김영욱
@since 2023. 10. 24.
@see https://www.acmicpc.net/problem/1043
@git
@performance 80ms
@category #
@note

사람의 수 N과 파티의 수 M이 주어짐
진실을 아는 사람의 번호가 주어짐
이 번호랑 겹치지 않는 파티의 개수를 구해라

단, 진실을 아는 사람이 있는 파티는 무조건 터지고
그 안에 있던 사람들도 전부 진실을 아는 사람이 돼버림

테케는 다 통과하는데

8 4
1 1
3 1 2 3
3 4 5 6
3 6 7 8
2 3 8

이렇게 하니까 반례가 나옴
8에 연관된 678을 감염시키고
그럼 감염된 6을따라서
456도 감염돼서
정답이 0이여야하는데 서로 이어진 정보가 없다보니 불가능함
그렇다고 하나 받으면 나머지들 전부 ArrayList로 이어주려면
한줄입력받을때마다 최대 50^2씩 돌아서 너무 비효율적임

진실을 한번이라도 들은 놈을 타고 모든걸 이어준다에서 힌트를 얻어서
Union-find로 대표자를 찾는 방법이 갑자기 떠오름
맞는지 확인하니까 맞는 것 같음

근데 BFS로 돌 수 있을거 같음
전부 다 안이어주고 앞뒤로만 이어주면 지들끼리 알아서 이어짐( 이걸 왜 생각 못했지 )
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G4_1043_거짓말 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N,M,ans;
	static int persons[][];
	static boolean[] truth, visited;
	static ArrayList<ArrayList<Integer>> parties;

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(input.readLine());
		
		persons = new int[N+1][N+1];
		truth = new boolean[N+1];
		visited = new boolean[N+1];
		parties = new ArrayList<>();
		int truth_cnt = Integer.parseInt(tokens.nextToken());
		if(truth_cnt == 0) {
			System.out.println(M);
		} else {
			for(int i=0; i<truth_cnt; i++) {
				int t = Integer.parseInt(tokens.nextToken());
				truth[t] = true;
			}
			for(int i=0; i<M; i++) {
				// 나중에 답 찾아야 돼서 필요함
				parties.add(new ArrayList<>());
				tokens = new StringTokenizer(input.readLine());
				int pLen = Integer.parseInt(tokens.nextToken());
				
				if(pLen == 1) {
					int temp = Integer.parseInt(tokens.nextToken());
					parties.get(i).add(temp);
				}
				else {
					int num1 = Integer.parseInt(tokens.nextToken());
					parties.get(i).add(num1);
					for(int j=1; j<pLen; j++) {
						int num2 = Integer.parseInt(tokens.nextToken());
						parties.get(i).add(num2);
						// BFS돌꺼임
						persons[num1][num2] = 1;
						persons[num2][num1] = 1;
						num1 = num2;
					}
				}
			}
			for(int i=1; i<truth.length; i++) {
				if(truth[i] && !visited[i]) {
					bfs(i);
				}
			}
			for(int i=0; i<M; i++) {
				boolean flag = false;
				for(int now: parties.get(i)) {
					if(truth[now]) {
						flag = true;
						break;
					}
				}
				if(!flag) ans++;
			}
			System.out.println(ans);
		}

	}

	private static void bfs(int i) {
		Queue<Integer> q = new ArrayDeque<>();
		
		visited[i] = true;
		q.offer(i);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int j=1; j<=N; j++) {
				if(persons[now][j] == 1 && !visited[j]) {
					truth[j] = true;
					visited[j] = true;
					q.offer(j);
				}
			}
		}
		
	}
}

