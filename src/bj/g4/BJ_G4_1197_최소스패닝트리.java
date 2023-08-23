package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 @author 김영욱
 @since 2023. 08. 22
 @see
 @git 
 @performance
 @category #
 @note
 시간 : 1초
 V,E 크기 10,000 과 100,000
 다음 E개의 줄에는 간선에 대한 정보 A,B,C
 A번 정점과 B번 정점이 가중치C인 간선으로 연결되어 있다.
 C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.

 최소 신장 트리를 구하기 위해 크루스칼이나 프림 알고리즘을 사용하는 알고리즘이다.
 크루스칼 알고리즘이란?
 - 간선 중심으로 최소 신장 트리를 구하는 알고리즘(간선이 적을 때 프림보다 유리)
 1. 간선들을 가중치의 오름차순 정렬하여 가장 작은 간선 선택
 2. 그 간선이 지금까지 만들어진 MST와 사이클을 형성한다면 제외, 아니면 MST에 추가
 3. 모든 간선에 대해 반복

 프림 알고리즘이란?
 - 정점 중심으로 최소 신장 트리를 구하는 알고리즘(정점이 적을 때 크루스칼보다 유리)
 1. 임의의 정점 선택
 2. 그 정점과 인접한 정점을 잇는 간선 중 가중치가 가장 낮은 간선 선택
 3. 그 간선이 연결하는 정점 선택
 4. 모든 정점에 대해 2번 과정 반복

    Union-Find로도 풀 수 있다니까
    복습 겸 해보겠습니다.
 */
public class BJ_G4_1197_최소스패닝트리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int V,E,expensive;
    static int[] parents;
    static 간선[] 간선리스트;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        tokens = new StringTokenizer(input.readLine());
        V = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());
        expensive = 0;
        make();

        간선리스트 = new 간선[E];
        for(int i=0; i<E; i++){
            tokens = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());
            int W = Integer.parseInt(tokens.nextToken());
            간선리스트[i] = new 간선(A,B,W);
        }
        // 가중치 기준으로 오름차순
        Arrays.sort(간선리스트);

        for(간선 line: 간선리스트) {
            int aRoot = find(line.A);
            int bRoot = find(line.B);
            if(aRoot == bRoot) continue;
            union(aRoot, bRoot);
            expensive += line.W;
        }

        System.out.println(expensive);

    }

    private static void make() {
        parents = new int[V+1]; // 1 base
        for(int i=1; i<=V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) { // 자기가 속한 집합의 대표자를 리턴하는 함수
        if(a == parents[a]) return a; // 내가 곧 대표자라면

        return parents[a] = find(parents[a]); // path compression
    }

    // a가 속한 집합과 b가 속한집합을 합칠수 있다면 합치고 true반환, 아니면 false 반환
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false; // 서로의 대표자가 같은 즉, 같은 집합이니까 union하지 않음.
        // union 처리 ( bRoot를 aRoot 아래로 붙이기!! : 임의로..)
        parents[bRoot] = aRoot;
        return true;
    }


    static class 간선 implements Comparable<간선> {
        int A;
        int B;
        int W;

        간선(int A, int B, int W){
            this.A = A;
            this.B = B;
            this.W = W;
        }


        @Override
        public int compareTo(간선 line) {
            return W - line.W;
        }
    }


}
