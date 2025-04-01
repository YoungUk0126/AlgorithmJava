package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 2번 유형 설계
1) 4가지 수로 뽑아낼 수 있는 순열은 몇 개 일까?
1 2 3 4
1 2 4 3

등등 4자리이기 때문에 4!일 것이다. 이거에 집중해서 2번 유형을 설계해보자!
*
4가지 숫자로 1 3 2 4가 몇 번째 순열인지 구하려면 어떻게 해야할까?

1-1) 만약 첫 번째 자리에 1번을 고른다면 어떨까?

1번 이전에 오는 경우의 수는 없을 것이다.
이게 무슨소리냐? 만약 첫 번째 자리에 2번을 고른다고 생각해보자.

1-2) 만약 첫 번째 자리에 2번을 고른다면 어떨까?

2 _ _ _

2번이 오면 첫 번째 자리에 1번이 오는 경우의 수를 모두 센 다음에 2번이 올 수 있다.

첫 번째 자리에 1번이 오는 경우의 수 (1 _ _ _ => 3!)

이말은 즉, 지금 나의 수 대비 올 수 있는 작은 수의 경우를 다 따져줘야 한다는 것이다.

1-1) 만약 첫 번째 자리에 3번을 고른다면 어떨까?

첫 번째 자리에 3번이 오면 1 _ _ _ 과 2 _ _ _ 으로 올 수 있는 모든 경우를 다 따져준 다음에 3번이 올 수 있는 것이다.

그렇기 때문에 2X3! = 12가지 다음 13번째 부터 3 _ _ _ 이 될 수 있는 것이다.
*
* https://tksgk2598.tistory.com/421
* */

public class BJ_G5_1722_순열의순서 {

    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static long[] fac = new long[21];// N이 1이상 20이하니까
    static int N, num;
    static boolean[] sel;


    public static void main(String[] args) throws IOException {


        N = Integer.parseInt(input.readLine());

        fac = new long[N + 1];
        Factorial(N);

        sel = new boolean[N + 1];

        tokens = new StringTokenizer(input.readLine());
        num = Integer.parseInt(tokens.nextToken());


        if (num == 1) {
            long order = Long.parseLong(tokens.nextToken());
            numberInfo[] numbers = new numberInfo[N + 1];

            for (int i = 1; i <= N; i++) {//몇번째 자리?
                for (int j = N; j >= 1; j--) {//무슨 숫자? 뒤에서부터 살펴주다가 처음 알맞은 숫자 pick!

                    if (sel[j]) continue;

                    int prev_num = prev_num_check(j);//전에 있는 숫자 몇개 있는지 체크
                    // ex) 4면 4전에 1,2,3을 체크하겠지?(1,2,3이 쓰인적이 없다면)
                    long prevCount = prev_num * fac[N - i];
                    // ex) 첫번째 자리고, 4를 선택했고, 1,2,3을 사용한 적이 없다면 3 * 3! = 3 * 6이겠지요

                    if (prevCount < order) { // 해당 숫자는 들어갈 수 있음
                        // ex) 4면 18이 3보다 크니까 들어갈 수 없겠지요
                        // ex) 이 과정을 반복해서 1을 찾는다면
                        numbers[i] = new numberInfo(j, order - prevCount);
                        // 첫번째 자리에 1을넣고, remainNum에 3을 넣겠지요
                        // remainNum은 
                        sel[j] = true;
                        order = order - prevCount;
                        break;
                    }
                }
            }

        } else if (num == 2) {
            for (int i = 0; i < N; i++) {

            }
        }

    }

    private static void Factorial(int N) {
        fac[0] = 1;
        fac[1] = 1;

        for (int i = 2; i <= N; i++) {
            fac[i] = fac[i - 1] * i;
        }
    }

    private static int prev_num_check(int target) {
        int count = 0;
        for (int i = 1; i < target; i++) {
            if (!sel[i]) count++;
        }

        return count;
    }

    private static class numberInfo {
        int num;
        long remainNum;

        public numberInfo(int num, long remainNum) {
            this.num = num;
            this.remainNum = remainNum;
        }
    }
}
