package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 09.14
@see https://www.acmicpc.net/problem/2623
@git
@performance
@category #
@note
음악프로그램 가수 출연 순서를 정해야한다.
근데 보조PD의 수 M명이 출연 순서를 제출한다.
 1 4 3
 6 2 5 4
 2 3
 이 순서를 지켜서 가수 N명(여기선 6명)의 출연 순서를 정해라.
 
 13퍼에서 틀려서 질문게시판 가보니까 모든 가수가 배정받지는 않는대
 2 2
 1 1
 1 1
 
 답: 1  2 or 2 1
*/
public class BJ_G3_2623_음악프로그램 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int N,M, ansCnt, inDegrees[], ans[];
	static int[][] edges;
	static Queue<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		inDegrees = new int[N+1];
		edges = new int[N+1][N+1];
		ans = new int[N];
		
		int temp[];
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int singer = Integer.parseInt(tokens.nextToken());
			temp = new int[singer];
			for(int j=0; j<singer; j++) {
				temp[j] = Integer.parseInt(tokens.nextToken());
			}
			for(int j=0; j<temp.length-1; j++) {
				int from = temp[j];
				int to = temp[j+1];
				edges[from][to] = 1;
				inDegrees[to]++;
			}
		}
		
		for(int i=1; i<inDegrees.length; i++) {
			if(inDegrees[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			ans[ansCnt++] = now;
			
			for(int i=1; i<N+1; i++) {
				if(edges[now][i] == 1) {
					inDegrees[i]--;
					if(inDegrees[i] == 0)
						q.offer(i);
				}
			}
		}
		if(ansCnt == ans.length) {
			for(int i=0; i<ans.length; i++) {
				builder.append(ans[i]).append("\n");
			}
			System.out.println(builder);
		}
		else {
			System.out.println("0");
		}

	}

}
