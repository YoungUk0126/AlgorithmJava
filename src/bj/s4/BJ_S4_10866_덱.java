package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_S4_10866_덱 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static Deque<Integer> deque;
	static int N;
	static String order;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		
		deque = new ArrayDeque<Integer>();
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			order = tokens.nextToken();
			if(order.equals("push_front")) {
				int num = Integer.parseInt(tokens.nextToken());
				deque.offerFirst(num);
			}
			if(order.equals("push_back")) {
				int num = Integer.parseInt(tokens.nextToken());
				deque.offerLast(num);
			}
			if(order.equals("pop_front")) {
				if(deque.size() != 0)
					builder.append(deque.pollFirst()).append("\n");
				else {
					builder.append(-1).append("\n");
				}
			}
			if(order.equals("pop_back")) {
				if(deque.size() != 0)
					builder.append(deque.pollLast()).append("\n");
				else {
					builder.append(-1).append("\n");
				}
			}
			if(order.equals("size")) {
				builder.append(deque.size()).append("\n");
			}
			if(order.equals("empty")) {
				if(deque.size() != 0)
					builder.append(0).append("\n");
				else {
					builder.append(1).append("\n");
				}
			}
			if(order.equals("front")) {
				if(deque.size() != 0) {
					int num = deque.pollFirst();
					builder.append(num).append("\n");
					deque.offerFirst(num);
				}
				else {
					builder.append(-1).append("\n");
				}
			}
			if(order.equals("back")) {
				if(deque.size() != 0) {
					int num = deque.pollLast();
					builder.append(num).append("\n");
					deque.offerLast(num);
				}
				else {
					builder.append(-1).append("\n");
				}
			}
			
		}
		System.out.println(builder);
	}

}
