package bj.b2;

/*
* 문제
총 N개의 시험장이 있고, 각각의 시험장마다 응시자들이 있다. i번 시험장에 있는 응시자의 수는 Ai명이다.

감독관은 총감독관과 부감독관으로 두 종류가 있다. 총감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 B명이고, 부감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 C명이다.

각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.

각 시험장마다 응시생들을 모두 감시해야 한다. 이때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성하시오.
*
* 응시자 수 / 부감독관이랑 응시자 수 - 총감독관 / 부감독관 중 더 작은거로 빼면 될듯?

입력
첫째 줄에 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.

셋째 줄에는 B와 C가 주어진다. (1 ≤ B, C ≤ 1,000,000)
*
*
5
1000000 1000000 1000000 1000000 1000000
5 7
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B2_13458_시험감독관 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer token;

    static int N,B,C;
    static long answer;

    static int persons[];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        persons = new int[N];

        token = new StringTokenizer(input.readLine());
        for(int i=0; i<N; i++) {
            persons[i] = Integer.parseInt(token.nextToken());
        }

        token = new StringTokenizer(input.readLine());
        B = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
//  응시자 수 / 부감독관이랑 응시자 수 - 총감독관 / 부감독관 중 더 작은거로 빼면 될듯?
        for(int i=0; i<N; i++){
            if(persons[i] - B <= 0){
                answer++;
            }
            else {
                persons[i] = persons[i] - B;
                answer++;
                if(persons[i] % C > 0){
                    answer += persons[i] / C + 1;
                }
                else {
                    answer += persons[i] / C;
                }
            }
        }

        System.out.println(answer);
    }

}
