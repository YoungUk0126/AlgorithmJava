package bj.g5;

/**
@author 김영욱
@since 2024. 01. 12
@see https://www.acmicpc.net/problem/2251
@git
@performance
@category #
@note
각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 
처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 
이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 
이 과정에서 손실되는 물은 없다고 가정한다.

이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 
첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.

입력
8 9 10

 움직일 수 있는 경우의 수
 A->B
 A->C
 B->A
 B->C
 C->A
 C->B

 이렇게 움직이면서 A가 비었을 경우 체크
 이미 체크한거는 어떻게 판별할까, 방문 배열인건 아는데
 그냥 단순히 3차원 배열로 확인하고, 물통의 부피 최대 크기는 200이니까 201*201*201 = 8,120,601 크기의 3차원 boolean 배열로 해결
 아니면 셋 중에 가장 큰 크기의 물통을 기준으로 방문 배열을 만들어 주면 어느정도 최적화가 될 듯.

 물감통은 길이 세개짜리 int형 배열로 해도 되나 편의상 클래스를 만들기로 했음



*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G5_2251_물통 {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();

    static TreeSet<Integer> answer = new TreeSet<>();
    static int A,B,C;
    static int[] barrelState;
    static int[] barrelMax;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());
        barrelState = new int[3];
        barrelMax = new int[3];
        int maxBarrel = 0;

        for(int i=0; i<3; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            barrelMax[i] = num;
            barrelState[i] = 0;
            maxBarrel = Math.max(maxBarrel, num);
        }
        visited = new boolean[maxBarrel+1][maxBarrel+1][maxBarrel+1];

        // A,B 물통은 비어있다.
        // C 물통은 꽉차있다.
        barrelState[2] = barrelMax[2];
        bfs(barrelState);
        for(int num: answer) {
            builder.append(num).append(" ");
        }
        System.out.println(builder);

    }
    private static void bfs(int[] barrelState) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(barrelState);

        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(visited[now[0]][now[1]][now[2]]) continue;
            visited[now[0]][now[1]][now[2]] = true;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(i == j) continue;
                    int[] next = {now[0], now[1], now[2]};
                    if(next[i] + next[j] > barrelMax[j]) {
                        int temp = next[j];
                        next[j] = barrelMax[j];
                        next[i] = (next[i] + temp)- barrelMax[j];
                    }
                    else {
                        next[j] = next[j] + next[i];
                        next[i] = 0;
                    }
                    if(next[0] == 0) {
                        answer.add(next[2]);
                    }
                    q.offer(new int[] {next[0], next[1], next[2]});
                }
            }
        }
    }
}
