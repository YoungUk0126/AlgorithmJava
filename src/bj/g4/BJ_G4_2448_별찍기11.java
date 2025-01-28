package bj.g4;

/**
 @author 김영욱
 @since 2025. 01. 29.
 @see https://www.acmicpc.net/problem/2448
 @git
 @performance
 @category #
 @note
 첫째 줄에 N이 주어진다. N은 항상 3×2k 수이다. (3, 6, 12, 24, 48, ...) (0 ≤ k ≤ 10, k는 정수)
 N = 23
             *
            * *
           *****
          *     *
         * *   * *
        ***** *****
       *           *
      * *         * *
     *****       *****
    *     *     *     *
   * *   * *   * *   * *
  ***** ***** ***** *****

 12번째 줄 5 1 패턴 4번( 12/3 )
 그 윗줄 1 1 1 3 4번
 그 윗줄 1 5 1 5 1 5 1 5 4번


 첫번째 공백은 N-1부터 1씩줄음


  *     *
**/

public class BJ_G4_2448_별찍기11 {

    public static void main(String[] args) {
        for (int i=0; i<23; i++) {
            System.out.print(" ");
        }
        System.out.println("*");
        for (int i=0; i<22; i++) {
            System.out.print(" ");
        }
        System.out.print("*");
        System.out.print(" ");
        System.out.println("*");
        for (int i=0; i<21; i++) {
            System.out.print(" ");
        }
        for(int i=0; i<5; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
