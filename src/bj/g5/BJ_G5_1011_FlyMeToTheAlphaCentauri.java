package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note
 * 우현이가 우주선을 탈꺼고, 그 우주선에는 공간이동 장치가 있다.
 * 하지만 이 공간이동 장치는 이동 거리를 급격하게 늘릴 경우 기계에 심각한
 * 결함이 발생하는 단점이 있어서, 이전 작동시기에 k광년을 이동하였을 떄는,
 * k-1, k 혹은 k+1 광년 만을 다시 이동할 수 있다.
 * ex) 이 장치를 처음 작동시킬 경우 -1, 0, 1 광년을 이론상 이동할 수
 * 있으나 사실상 음수 혹은 0 거리만큼의 이동은 의미가 없으므로 1 광년을
 * 이동할 수 있으며, 그 다음에는 0,1,2 광년을 이동할 수 있는 것이다.
 * (여기서 다시 2광년을 이동한다면 다음 시기엔 1, 2, 3 광년을 이동할 수 있다.)
 * x에서 y지점을 향해 최소한의 작동 횟수로 이동하려 한다.
 * 하지만 y지점에 도착하기 바로 직전의 이동거리는 반드시 1광년
 *
 * 1. K광년 이동 후에는 K-1, K, K+1로만 이동 가능
 * 2. 최소한의 작동 횟수로 이동하려 한다.
 * 3. y지점에 도착하기 바로 직전의 이동거리는 반드시 1광년
 *
 * 입력의 첫 줄에는 테스트케이스의 개수 T가 주어진다. 각각의 테스트 케이스에 대해 현재 위치 x 와 목표 위치 y 가 정수로 주어지며,
 * x는 항상 y보다 작은 값을 갖는다. (0 ≤ x < y < 2^31)
 * 0 3 => 1, 1, 1 => 3
 * 1 5 => 2(1), 4(2), 5(1) => 3
 * 45 50 => 46(1), 48(2), 49(1), 50(1) => 4
 *
 * K값은 1씩 늘어나니까 도착할때 이동거리가 1이려면 Y-X거리의 절반까지 도착했을때까지만 이동 거리가 늘어나야하고,
 * 그 후로는 계속 줄어야 감당 가능함
 * 0 15
 * 7까지 늘어나야함? ㅇㅇ
 * 7전까지 늘어나야함. 다만, 그때의 K를 어느기간동안 해야하는건지를 구해야됨
 * 1, 3, 6, 9, 12, 14, 15
 * 1, 2, 3, 3, 3, 2, 1
 *
 * 0 30
 *
 * 15까지 잘 늘어났으면 그 절반도 바로 끝
 * 1, 3, 6, 10, 15, 20, 24, 27, 29, 30
 * 1, 2, 3, 4,  5,  5,  4,  3,  2,  1 => 10
 *
 * 0 40
 * 1, 3, 6, 10, 15, 20, 25, 30, 34, 37, 39, 40 // 15에서 6으로 늘어나면 21로 절반 이상이 되버림
 * 1, 2, 3,  4, 5,  5,  5,  5,  4,  3,  2,  1 => 12
 *
 * 1, 3, 6, 10, 15, 21, 26, 30, 34, 37, 39, 40
 * 1, 2, 3, 4,  5,  6,  5,  4,  4,  3,   2,  1
 * 40 - 0 = 40
 * 0 = 0 되네? 늘려
 * 40 - 1 = 39
 * 1 = 1 되네? 늘려
 * 40 - 3 = 37
 * 2+1 = 3 되네? 늘려
 * 40 - 6 = 34
 * 3+2+1 = 6
 * 40 - 21 = 19
 * 6+5+4+3+2+1 = 21 안되네? 줄여
 * 40 - 26 = 14
 * 5+4+3+2+1 = 15 안되네? 줄여
 * 40 - 30 = 10
 * 4+3+2+1 = 10 되네? 진행시켜
 * 40 - 34 = 6
 * 4+3+2+1 = 10 안되네? 줄여
 *
 *
 * 내 생각에 Z(늘어날때까지의 길이)를 구해뒀다가 최대값에서 Z를 뺀 값까지는 제자리 걸음을 해야할듯
 *
 * 0 19
 * 1, 3, 6, 9, 12, 15, 17, 18, 19
 * 1, 2, 3, 3, 3, 3, 2, 1, 1 => 9
 * 1, 3, 6, 10, 13, 16, 18, 19
 * 1, 2, 3, 4,  3, 3, 2, 1 => 8
 *
 * 0 21
 * 1, 3, 6, 10, 14, 17, 19, 20, 21
 * 1, 2, 3, 4, 4, 3, 2, 1, 1 => 9
 *
 * 0, 5
 * 1, 3, 4, 5
 * 1, 2, 1, 1
 *
 * 5 - 0 = 0
 * 0 = 0 되네? 늘려
 * 5 - 1 = 4
 * 1 = 1 되네? 늘려
 * 5 - 3 = 2
 * 2+1 = 3 안되네? 줄여
 * 5 - 4 = 1
 * 1 = 1 딱맞네 진행시켜
 *
 * 0, 3
 * 1, 2, 3
 * 1, 1, 1
 *
 * 3-0 = 0
 * 0=0 되네?늘려
 *
 * 3-1 = 2
 * 1= 1 되네? 늘려....지만 늘리면 안돼
 * 어떢하지 아
 * 아 아아 아 아 아 아 ㅇ ㅏㅇ ㅏ아 아 ㅇ ㅏ아 아
 *
 * @see
 * @since 2025. 08. 07
 */

public class BJ_G5_1011_FlyMeToTheAlphaCentauri {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();

    static int X,Y;
    static int T;


    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(input.readLine());
        for(int t=0; t<T; t++) {
            tokens = new StringTokenizer(input.readLine());
            X = Integer.parseInt(tokens.nextToken());
            Y = Integer.parseInt(tokens.nextToken());

            int ans = solution(0, Y-X);
            builder.append(ans).append("\n");

        }
        System.out.println(builder);

    }

    private static int solution(int x, int y) {

        int start = x;
        int end = y;
        int seqSum = 0;
        int k = 0;
        Stack<Integer> curSeq = new Stack<>();

        while(start < end) {
            if(end-start > seqSum) {
                // 늘려
                k++;
                seqSum += k;// 1, 3, 6, 10, 15, 21
            } else if(end-start < seqSum) {
                seqSum-=k;// (21-6), (15-5), (10-4), (6-3)
                k--;// 5, 4, 3, 2
            }
            start += k;//1,3,6,10,15,21,26,30,34,37,39,40
            curSeq.push(k);// 1,2,3,4,5,6,5,4,4,3,2,1
        }

        return curSeq.size();
    }

}
