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

크루스칼 == MST(union-find)

1. 간선의 가중치를 오름차순으로 정렬한다.
2. 정렬된 간선 중에서 순서대로(가중치가 낮은 순으로) 간선을 조회한다.
    2-1. 간선을 선택하게 될 때, 사이클이 형성된다면 다음 간선으로 넘어간다.
    2-2. 사이클이 형성되지 않는다면 해당 간선을 선택한다.
3. 정점의 개수가 N일때, N-1만큼 간선을 뽑았다면 반복문을 종료한다.



*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_G5_14284_간선이어가기2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N,M;
    static line[] lineList;
    static int[] parent;
    static int minTotal;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parent = new int[N+1];
        lineList = new line[N+1];

        for(int i=1; i<=M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());

            lineList[i] = new line(a, b, c);

        }
        // make 부분
        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
        }

    }

    private static class line implements Comparable<line>{
        int a,b,w;

        line(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
        @Override
        public int compareTo(line l) {
            return w - l.w;
        }
    }
}