package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* N * N배열은 구구단( 1열은 1단, 2열은 2단 이런식)
* 이걸 풀어서 1차원 배열로 만든게 B배열이고 B배열의 K번째 수를 구하는게 문제
* B배열은 작은 수부터 큰 수대로 정렬 되어 있다(ex) N이 4일때 1, 2, 2, 3, 3, 4, 4, 4 ...)
*
* N은 10의5승이다. 100000 * 100000 = 10,000,000,000(10억)
* 배열은 만들 수가 없어
*
* 그럼 어떻게 하냐?
* B배열은 N배열은 풀어 쓴거고, B[11] = 8이란 말은
* 8보다 작거나 같은 수는 11개가 있다는 말이다.
*
* 그래도 결국 행렬을 만들어야하는거 아닌가?
*
* 아까 N배열은 구구단이라 했다. 구구단을 예로 들면
* 만약 3보다 작은 숫자 개수를 찾는다면, 1부터N까지 3을 나눠버리면 된다
* 3/1 = 3, 3/2 = 1, 3/3 = 1   총 5개가 있다는 소리
*
* 자, 그럼 이쯤에서 정리를 한 번 해보자. B[K] = 𝑥 에서우리가 조정해가면서 탐색해야 하는 것은  𝑥 이다.
* 즉,  𝑥 를 통해  𝑥 보다 작은 원소의 개수(=K)를 찾고, 해당 값이 문제에서 주어지는 K값과 일치하는지를 이분탐색으로 구현을 하면 된다.
*
* 다만 주의할 점이 두가지 있다.
* 1. x의 범위이다. x의 초기 범위는 N^2이다.(N * N 행렬에서 제일 큰값이니까)
* 여기서 주의해야 할 점은 이분 탐색 과정에서 발생한다. 만약 이분탐색 과정에서 임의의 𝑥에 대해 𝑥=13 이 되었다고 가정해보자.
* 1단에 대해 13 / 1 = 13 이 될 것이다.
* 하지만, 우리가 A행렬에서 볼 수 있듯, 한 행에 대해 각 4개의 원소를 갖고 있다.
* 즉,  𝑥 보다 작은 원소의 개수는 최대 N개를 넘지 못한다.
*
*
* */

public class BJ_G1_1300_K번째수 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N, K;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        K = Integer.parseInt(input.readLine());

        long left = 1;
        long right = K;

        while (left < right) {
            long mid = (left + right) / 2; // 임의의 x를 mid로 상정
            long count = 0;
            System.out.println("left : " + left);
            System.out.println("right : " + right);
            System.out.println("mid : " + mid);

            /*
             *  임의의 x에 대해 i번 째 행을 나눔으로써 x보다 작거나 같은 원소의 개수
             *  누적 합을 구한다.
             *  이 때 각 행의 원소의 개수가 N(열 개수)를 초과하지 않는 선에서 합해주어야 한다.
             */
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }
            System.out.println("count : " + count);
            // count가 많다는 것은 임의의 x(mid)보다 작은 수가 B[K]보다 많다는 뜻
            if(K <= count) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);

    }
}
