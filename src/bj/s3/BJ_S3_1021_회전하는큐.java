package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 4.
@see https://www.acmicpc.net/problem/1021
@git
@performance 80ms
@category #
@note
 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다.
 3가지 연산을 수행할 수 있다.
 1. 첫 번쨰 원소를 뽑아낸다.
 2. 왼쪽으로 한 칸 이동
 3. 오른쪽으로 한 칸 이동
 
 N : 1~N까지의 수를 가진 양방향 큐
 M : 뽑아낼 수의 개수(순서대로 뽑아야해)
 
 첫 번째 원소가 M의 첫번쨰 원소랑 같으면 pollfirst
 아니라면 중앙 값을 기준으로 더 빠른 연산을 실행(왼쪽이냐 오른쪽이냐)
 
 deque를 쓰려고 했는데 중앙값을 구하는 방법이 도저히 떠오르지 않았다.
 인터넷을 살짝 보니 LinkedList로 풀더라...;;;
 그래서 LinkedList로 풀었더니 바로 실행이 되었다.
 
 
 
*/
public class BJ_S3_1021_회전하는큐 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static LinkedList<Integer> deque;
	
	static int N;
	static int M;
	static int count;
	
	static int[] targetArr;
	
	// 아직 미해결
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		count = 0;
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		deque = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			deque.offer(i);
		}
		M = Integer.parseInt(tokens.nextToken());
		targetArr = new int[M];
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<M; i++) {
			targetArr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			int targetIdx = deque.indexOf(targetArr[i]);
			int halfIdx;
			
			if(deque.peek() == targetArr[i]) {
				deque.pollFirst();
				continue;
			}
			
			if(deque.size()%2 == 0) {
				halfIdx = deque.size()/2 - 1;
			} else {
				halfIdx = deque.size()/2;
			}
			// 타겟 인덱스가 중앙값보다 작다면
			if(targetIdx <= halfIdx) {
				for(int j=0; j<targetIdx; j++) {
					count++;
					int temp = deque.pollFirst();
					deque.offerLast(temp);
				}
			} else {
				for(int j=0; j<deque.size() - targetIdx; j++) {
					count++;
					int temp = deque.pollLast();
					deque.offerFirst(temp);
				}
			}
			deque.pollFirst();
		}
		System.out.println(count);
		
		
		
	}

}
