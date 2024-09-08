package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note P[0], P[1], ...., P[N-1]은 0부터 N-1까지(포함)의 수를 한 번씩 포함하고 있는 수열이다.
 * 수열 P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B가 된다.
 * 적용하는 방법은 B[P[i]] = A[i]이다.
 *
 * 배열 A가 주어졌을 때, 수열 P를 적용한 결과가 비내림차순이 되는 수열을 찾는 프로그램을 작성하시오.
 * 비내림차순이란, 각각의 원소가 바로 앞에 있는 원소보다 크거나 같을 경우를 말한다.
 * 만약 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력한다.
 *
 * 입력
 * 첫째 줄에 배열 A의 크기 N이 주어진다. 둘째 줄에는 배열 A의 원소가 0번부터 차례대로 주어진다.
 * N은 50보다 작거나 같은 자연수이고, 배열의 원소는 1,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 비내림차순으로 만드는 수열 P를 출력한다.
 * 예제 입력 1
 * 3
 * 2 3 1
 * 예제 출력 1
 * 1 2 0
 * 예제 입력 2
 * 4
 * 2 1 3 1
 * 예제 출력 2
 * 2 0 3 1
 * 예제 입력 3
 * 8
 * 4 1 6 1 3 6 1 4
 * 예제 출력 3
 * 4 0 6 1 3 7 2 5
 *
 * 1 1 1 3 4 4 6 6
 * @see https://www.acmicpc.net/problem/1015
 * @since 2024. 09. 08
 */

public class BJ_S4_1015_수열정렬 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static ArrayList<Queue> arr = new ArrayList<>();
    static int[] sortedSequence;
    static int[] sequence;
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        sequence = new int[N];
        sortedSequence = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i=0; i<=1000; i++) {
            arr.add(new ArrayDeque());
        }
        for(int i=0; i<N; i++) {
            sequence[i] = Integer.parseInt(tokens.nextToken());
            sortedSequence[i] = sequence[i];
        }
        Arrays.sort(sortedSequence);

        for(int i=0; i<N; i++) {
            arr.get(sortedSequence[i]).offer(i);
        }

        for(int num: sequence) {
            builder.append(arr.get(num).poll()).append(" ");
        }
        System.out.println(builder);
    }


}
