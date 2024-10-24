package bj.s1;

import java.io.*;
import java.util.*;

/**
 @author 김영욱
 @since 2024. 10. 24
 @see https://www.acmicpc.net/problem/1629
 @git
 @performance
 @category #수학
 @note
 시간 0.5초

 자연수 A를 B번 곱한 수를 알고 싶다.
 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.

 입력
 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다

 딱봐도 정석대로 하면 시간초과 문제이다.

 알아야 할 수학 공식 2가지
 1. a^5+5 == a^5 * a^5 // 이렇게 분할해서 풀 수 있다(재귀문제)
 2. (a*b)%c == (a%c * b%c) % c // 지수가 무지막지하게 크다면 Long타입 마저 넘어가니 중간중간 모듈러 연산 해야함

 알아야 할 예외
 1. 지수가 홀수일 경우 현재 값을 한번 더 곱해줘야 한다.

 */

public class BJ_S1_1629_곱셈 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static Long A, B, C;


    public static void main(String[] args) throws IOException{

        tokens = new StringTokenizer(input.readLine());
        A = Long.parseLong(tokens.nextToken());
        B = Long.parseLong(tokens.nextToken());
        C = Long.parseLong(tokens.nextToken());

        System.out.println(pow(A, B));


    }

    private static Long pow(Long num, Long exp) {
        if(exp == 1) {
            return num % C;
        }

        Long half = pow(num, exp/2);

        if(exp % 2 == 1) {
            return (half * half % C) * num % C;
        }

        return half * half % C;
    }

//    private static Long pow2(Long num, Long exp) {
//        if(exp == 1) {
//            System.out.println("num : " + num);
//            System.out.println("exp : " + exp);
//            System.out.println();
//            return num;
//        }
//
//        Long half = pow2(num, exp/2);
//
//        System.out.println("half : " + half);
//        System.out.println();
//
//        if(exp % 2 == 1) {
//            System.out.println("half * half * num : " + half * half * num);
//            System.out.println("num : " + num);
//            System.out.println("exp : " + exp);
//            System.out.println();
//            return half * half * num;
//        }
//        System.out.println("half * half : " + half * half);
//        System.out.println("num : " + num);
//        System.out.println("exp : " + exp);
//        System.out.println();
//
//        return half * half;
//    }
}
