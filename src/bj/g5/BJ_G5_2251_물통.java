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

나올 수 있는 barrelState
0 9 1
8 0 2
0 2 8
1 0 9
0 0 10
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G5_2251_물통 {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();

    static ArrayList<Integer> answer = new ArrayList<>();
    static int A,B,C;
    static int[] barrelState;
    static int[] barrelMax;

    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());
        barrelState = new int[3];
        barrelMax = new int[3];

        for(int i=0; i<3; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            barrelMax[i] = num;
            barrelState[i] = 0;
        }

        // A,B 물통은 비어있다.
        // C 물통은 꽉차있다.
        barrelState[2] = C;

    }

    private static void bfs() {
        boolean[] visited = new boolean[C+1];
        Queue<Integer> q = new ArrayDeque<>();
    }

    
}
