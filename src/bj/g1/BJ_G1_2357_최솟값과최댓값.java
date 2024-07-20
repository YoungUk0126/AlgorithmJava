package bj.g1;

import java.io.*;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #세그먼트트리
 * @note 2초
 * N(1 ≤ N ≤ 100, 000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 또는 제일 큰 정수를 찾아라.
 * 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개가 주어진다.
 * <p>
 * 일반적인 탐색 방식
 * (N * M) = 10억;;
 * <p>
 * 예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최소, 최댓값을 찾아야 한다.
 * 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.( Long 쓸 것 )
 * <p>
 * 입력
 * 첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다. 다음 M개의 줄에는 a, b의 쌍이 주어진다.
 * <p>
 * 출력
 * M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 최솟값, 최댓값 순서로 출력한다.
 * <p>
 * 구간을 최소한의 시간으로 찾는 문제이므로 세그먼트트리라고 단박에 알았다.
 * 그리고 최솟값과 최댓값을 따로 구해야 하므로, 트리를 두가지 만들어야 겠다고 생각했다.
 *
 * 중복되는 코드가 많아서 불편했지만 코드를 다시 보면서 이해하기에는 저렇게 해두는게 편할 것 같아서 냅뒀다.
 * @see https://www.acmicpc.net/problem/2357
 * @since 2024. 07. 21
 */

public class BJ_G1_2357_최솟값과최댓값 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N, M;
    static long[] nums;

    static long[] minTree;
    static long[] maxTree;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        nums = new long[N + 1];
        minTree = new long[N * 4];
        maxTree = new long[N * 4];

        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(input.readLine());
        }

        minInit(1, N, 1);
        maxInit(1, N, 1);

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            builder.append(findMin(1, N, 1, a, b)).append(" ").append(findMax(1, N, 1, a, b)).append("\n");

        }
        System.out.println(builder);
    }

    private static long findMax(int start, int end, int index, int left, int right) {
        if (start > right || end < left) {
            return -1;
        }
        if (left <= start && right >= end) {
            return maxTree[index];
        }

        int mid = (start + end) / 2;

        return Math.max(findMax(start, mid, index * 2, left, right), findMax(mid + 1, end, index * 2 + 1, left, right));
    }

    private static long findMin(int start, int end, int index, int left, int right) {
        if (start > right || end < left) {
            return 1000000001L;
        }
        if (left <= start && right >= end) {
            return minTree[index];
        }

        int mid = (start + end) / 2;

        return Math.min(findMin(start, mid, index * 2, left, right), findMin(mid + 1, end, index * 2 + 1, left, right));
    }

    private static long minInit(int start, int end, int index) {
        if (start == end) {
            minTree[index] = nums[start];
            return minTree[index];
        }

        int mid = (start + end) / 2;

        minTree[index] = Math.min(minInit(start, mid, index * 2), minInit(mid + 1, end, index * 2 + 1));
        return minTree[index];
    }

    private static long maxInit(int start, int end, int index) {
        if (start == end) {
            maxTree[index] = nums[start];
            return maxTree[index];
        }

        int mid = (start + end) / 2;

        maxTree[index] = Math.max(maxInit(start, mid, index * 2), maxInit(mid + 1, end, index * 2 + 1));
        return maxTree[index];
    }
}
