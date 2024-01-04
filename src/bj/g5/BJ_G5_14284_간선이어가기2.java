package bj.g5;

/**
@author 김영욱
@since 2024. 01. 03
@see https://www.acmicpc.net/problem/14284
@git
@performance 
@category 크루스칼
@note

정점 n개, 0개의 간선으로 이루어진 무방향 그래프가 주어진다.
그리고 m개의 가중치 간선의 정보가 있는 간선리스트가 주어진다.
간선리스트에 있는 간선 하나씩 그래프에 추가해 나갈 것이다.
이때, 특정 정점 s와 t가 연결이 되는 시점에서 간선 추가를 멈출 것이다.
연결이란 두 정점이 간선을 통해 방문 가능한 것을 말한다.

s와 t가 연결이 되는 시점의 간선의 가중치의 합이 최소가 되게 추가하는 간선의 순서를 조정할 때, 그 최솟값을 구하시오.

다익스트라로 풀어야 하는 문제



*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G5_14284_간선이어가기2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();
    static PriorityQueue<Node> PQ = new PriorityQueue<>();

    static int N,M,expensive;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Node>());
		}

        dist = new int[N+1];

        for(int i=0; i<M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            
            // 2차원 ArrayList
            graph.get(a).add(new Node(b, c));

        }

        tokens = new StringTokenizer(input.readLine());
        int s = Integer.parseInt(tokens.nextToken());
        int t = Integer.parseInt(tokens.nextToken());


    }

    private static class Node implements Comparable<Node>{
        int index, weight;

        Node(int a, int w) {
            this.index = a;
            this.weight = w;
        }
        @Override
        public int compareTo(Node l) {
            return weight - l.weight;
        }
    }
}