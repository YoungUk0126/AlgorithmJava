package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 @author 김영욱
 @since 2023.08.22
 @see https://www.acmicpc.net/problem/13023
 @git 
 @performance
 @category #
 @note
 시간 2초

 N : 사람 숫자
 사람들은 0번부터 N-1번까지 번호가 매겨져 있어
 이런 관계가 있는지 찾아라(노드가 연결되어 있냐)
 AB => BC => CD => DE

 인접 리스트로 값들을 받아와
 그 후에 visited배열로 들어갔는지 안들어갔는지 체크 후에 들어가
 깊이가 4되면 멈추고 정답 출력 왜 깊이가 4냐면
 자기 자신을 제외한 친구가 4명이면 총 5명이기 때문이다.
 다 돌았는데 못찾으면 0 출력
 
 시간초과 날꺼같은데..흠..
 
 그래서 깊이가 5되면 멈추고 플레그를 이용하여 더 이상 재귀를 돌지 못하도록
 dfs안의 for문 안에 있는 if문 안에 플래그를 추가해 주었더니 시간초과가 나지 않았다.


 */

public class BJ_G5_13023_ABCDE {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int ans;
    static boolean ansFlag;
    static ArrayList<ArrayList<Integer>> friends = new ArrayList<>();
    static int N,M;
    static boolean v[];
    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<N; i++) {
            ArrayList temp = new ArrayList<>();
            friends.add(temp);
        }

        for(int i=0; i<M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());

            friends.get(from).add(to);
            friends.get(to).add(from);
        }
        ans = 0;
        for(int i=0; i<N; i++){
            v = new boolean[N]; // 각 정점마다 vistied 체크
            dfs(i,0);
        }

        System.out.println(ans);


    }
    static boolean flag = false;
    private static void dfs(int current, int depth){
        // 기저조건
        if(depth == 4){
            ans = 1;
            flag = true;
            return;
        }

        v[current] = true;

        for(int i: friends.get(current)){
            if(!v[i] && !flag ){
                dfs(i, depth + 1);
                v[i] = false;
            }
        }


    }



}
