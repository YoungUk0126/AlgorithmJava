package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_S4_2164_카드2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		Queue<Integer> q = new LinkedList<>();		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int temp = 0;
		
		for(int i=1; i<=N; i++) {
			q.add(i);
		}
		// 1일때 멈추고 마지막 요소 pop하면서 매무리
		while(q.size() > 1) {
			q.remove();
			temp = q.poll();
			q.add(temp);
		}
		System.out.println(q.poll());
	}

}
