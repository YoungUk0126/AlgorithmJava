package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 시간 : 1초
 * 이 게임에서 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것이다.
 * 이때, 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다.
 * 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다.
 *
 * 2 4 8 2
 * 2 4 2
 * 2
 * 2
 * 위로 이동 하면
 *
 * 4 8 8 2
 * 4   2
 * <그림 12>의 경우에 위로 블록을 이동시키면 <그림 13>의 상태가 되는데,
 * 그 이유는 한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없기 때문이다.
 *
 * 2
 * 2 2
 * 2
 * 위로 이동 후
 * 4 2
 * 2
 * 마지막으로, 똑같은 수가 세 개가 있는 경우에는 이동하려고 하는 쪽의 칸이 먼저 합쳐진다.
 * 예를 들어, 위로 이동시키는 경우에는 위쪽에 있는 블록이 먼저 합쳐지게 된다.
 *
 * 합칠 때 그 방향으로 검사해줘야 하는 것들이 꽤 있는 까다로운 문제이다.
 * <그림 14>의 경우에 위로 이동하면 <그림 15>를 만든다.
 * @see https://www.acmicpc.net/problem/12100
 * @since 2024. 11. 22
 */

public class BJ_G1_12100_2048 {



    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }


    }
}
