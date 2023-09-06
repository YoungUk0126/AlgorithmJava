package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 @author 김영욱
 @since 2023. 09 05
 @see https://www.acmicpc.net/problem/20040
 @git
 @performance
 @category #
 @note
 시간: 1초
 3 <= N <= 500,000
 3 <= M <= 1,000,000
 두 명의 플레이어가 차례대로 돌아가며 진행하는 게임, 선 플레이어가 홀수 번째 차례, 후 플레이어가 짝수 번째 차례
 0부터 n-1까지 고유한 번호가 부여된 평면 상의 점 n개가 주어짐
 이 중 어느 세 점도 일직선 위에 놓이지 않는다.
 매 차례마다 두 점을 선택해서 이를 연결하는 선분을 긋는데, 이전에 그린 선문은 다시 그을 수 없지만
 다른 선분과 교차하는 것은 가능하다.
 게임을 진행하다가 처음으로 사이클을 완성하는 순간 게임이 종료된다.

 사이클 : 임의의 선분이 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 출발점으로 돌아오는 것
 선분을 여러 개 그리다 보면 사이클이 완성 되었는지 여부를 판단하기 어려워 사이클이 생겼어도 계속 게임을 진행할 수 있다.
 그래서 몇 번째 차례에서 사이클이 완성되었는지, 혹은 아직 게임이 진행 중인지를 판단하는 프로그램을 만들어라.

 N M : N(점의 개수) M(진행된 게임의 차례 수)
 0 1 : 0하고 1을 잇는다
 1 2 : 1하고 2를 잇는다

 점들을 정점으로 생각하고 인접리스트로 모두 이어준다.
 **/


public class BJ_G4_20040_사이클게임 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int N,M,ans;
    static ArrayList<ArrayList<Integer>> graph;
    static int parents[];

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        ans = 0;
        // 0 base

        make();

        for(int i=1; i<=M; i++)
        {
            tokens = new StringTokenizer(input.readLine());
            int to = Integer.parseInt(tokens.nextToken());
            int from = Integer.parseInt(tokens.nextToken());

            if(!union(from, to)){
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    static void make(){
        parents = new int[N];
        for (int i=0; i<N; i++){
            parents[i] = i;
        }
    }

    static int find(int a){
        if(a == parents[a]) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        // 사이클을 만들기 위해 부모에 규칙성을 부여함
        // 숫자가 더 큰노드가 더 작은 노드를 바라보게 함
        if(aRoot > bRoot){
            parents[aRoot] = bRoot;
        }
        else {
            parents[bRoot] = aRoot;
        }
        return true;
    }
}
