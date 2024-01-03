package bj.g5;

/**
@author 김영욱
@since 2024. 01. 03
@see https://www.acmicpc.net/problem/2660
@git
@performance 
@category #
@note

축구 응원 모임에서 회장을 선출하려 한다.
회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다.
각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.

ex) 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점
다른 모든 회원이 친구이거나 친구의 친구이면, 이 회원의 점수는 2점
다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구이면, 이 회원의 점수는 3점
4점, 5점 등은 같은 방법으로 정해진다.
각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구사이이면서
동시에 친구의 친구사이이면, 이 두 사람은 친구사이라고 본다.

회장은 회원들 중에서 점수가 가장 작은 사람이 된다.
회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성하시오.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_G5_2660_회장뽑기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();
    static int N;
    static int maxScore = Integer.MAX_VALUE;
    static ArrayList<Integer> candidate = new ArrayList<>();
    static int[][] member;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(input.readLine());
        member = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            Arrays.fill(member[i], 99999999);
            member[i][i] = 0;
        }
        while(true){
            tokens = new StringTokenizer(input.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());

            if(A == -1 && B == -1) {
                break;
            }
            else {
                member[A][B] = 1;
                member[B][A] = 1;
            }
        }
        // 연결되지 않은 부분은 큰 값으로 대체
        

        // 플로이드 워셜로 최단 거리를 구한다.
        for(int k=1; k<=N; k++){ // 경유지
            for(int i=1; i<=N; i++) { // 출발지
                // 경유지와 출발지가 같으면 다음 탐색
                if(i == k) continue;
                for(int j=1; j<=N; j++) {
                    if(j == i || j == k) continue; // 도착지랑 출발지 혹은 경유지가 같다면 다음 탐색
                    // 출발지에서 경유지를 거쳐서 도착지에 가는 경우와 그냥 출발지에서 경유지로 가는 경우의
                    // 가중치를 비교해서 더 작은 값을 넣어준다.
                    member[i][j] = Math.min(member[i][k] + member[k][j], member[i][j]);
                }
            }
        }
        for(int i=1; i<=N; i++) {
            int tempMax = 0;
            for(int j=1; j<=N; j++) {
                if(i == j) continue;
                if(member[i][j] > tempMax) tempMax = member[i][j];
            }
            if(tempMax < maxScore) {
                candidate.clear();
                maxScore = tempMax;
                candidate.add(i);
            }
            else if(tempMax == maxScore) {
                candidate.add(i);
            }
        }
        Collections.sort(candidate);
        
        builder.append(maxScore).append(" ").append(candidate.size()).append("\n");
        for(int t: candidate) {
            builder.append(t).append(" ");
        }
        System.out.println(builder);
    }
}