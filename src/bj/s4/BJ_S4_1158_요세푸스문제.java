package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.08.07
@see https://www.acmicpc.net/problem/1158
@git
@performance
@category #
@note
1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K가 주어진다.
순서대로 K번째 사람을 제거하고, 남은 사람들로 계속 반복한다.
이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.

우선 큐에 1부터 N까지 값을 넣어
큐에 값이 있을때까지 반복을 돌아
flag로 세번째 인지 아닌지 체크
아니면 첫번째꺼 뽑아서 맨 뒤로 넣어
*/
public class BJ_S4_1158_요세푸스문제 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,K;
	static Deque<Integer> queue;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		queue = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++) {
			queue.offerLast(i);
		}
		builder.append("<");
		int k=1;
		while(!queue.isEmpty()) {
			if(k==K) {
				builder.append(queue.pollFirst()).append(", ");
				k = 1;
			} else {
				k++;
				int temp = queue.pollFirst();
				queue.offerLast(temp);
			}
		}
		builder.deleteCharAt(builder.length()-1);
		builder.deleteCharAt(builder.length()-1);
		builder.append(">");
		System.out.println(builder);
	}
}
