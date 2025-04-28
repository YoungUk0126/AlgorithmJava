package bj.g5;

import java.io.*;
import java.util.*;


/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 * 함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다.
 * 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 * 함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
 * <p>
 * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.
 * <p>
 * R은 순서를 뒤집는 함수니까 flag를 만들어서 R이 true면 그 뒤에 D가오면 뒤에꺼부터 숫자를 지우면 되고,
 * R이 false면 앞에꺼부터 숫자를 지우면 될꺼같다.
 * <p>
 * 배열은 덱에 넣고 앞뒤를 빼주면 될꺼야
 * while(deque.size()>1) {
 *      builder.append(deque.pollLast()).append(",");
 * }
 * if(!deque.isEmpty()) builder.append(deque.pollLast());
 * <p>
 * 마지막 출력하는 부분에서 deque empty체크 안해주면 []이렇게 비어야하는 답에서 [null]이 들어감
 * @see https://www.acmicpc.net/problem/5430
 * @since 2025. 04. 29
 */

public class BJ_G5_5430_AC {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N;
    static char[] commands;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            boolean rFlag = false;
            commands = input.readLine().toCharArray();
            N = Integer.parseInt(input.readLine());
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            tokens = new StringTokenizer(input.readLine(), "[],");

            while (tokens.hasMoreTokens()) {
                deque.add(Integer.parseInt(tokens.nextToken()));
            }
            boolean errorFlag = false;
            for (char command : commands) {
                if (command == 'R') {
                    rFlag = !rFlag;
                } else {
                    if (!deque.isEmpty()) {
                        if (rFlag) deque.pollLast();
                        else deque.pollFirst();

                    } else {
                        builder.append("error").append("\n");
                        errorFlag = true;
                        break;
                    }

                }
            }
            if (!errorFlag) {
                builder.append("[");
                if (rFlag) {
                    while (deque.size() > 1) {
                        builder.append(deque.pollLast()).append(",");
                    }
                    if (!deque.isEmpty()) builder.append(deque.pollLast());
                } else {
                    while (deque.size() > 1) {
                        builder.append(deque.pollFirst()).append(",");
                    }
                    if (!deque.isEmpty()) builder.append(deque.pollFirst());
                }
                builder.append("]").append("\n");
            }
        }
        System.out.println(builder);
    }
}
