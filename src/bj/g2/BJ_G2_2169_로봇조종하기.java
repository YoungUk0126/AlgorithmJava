package bj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 문제 1초
 * NASA에서는 화성 탐사를 위해 화성에 무선 조종 로봇을 보냈다. 실제 화성의 모습은 굉장히 복잡하지만,
 * 로봇의 메모리가 얼마 안 되기 때문에 지형을 N×M 배열로 단순화 하여 생각하기로 한다.
 * <p>
 * 지형의 고저차의 특성상, 로봇은 움직일 때 배열에서 왼쪽, 오른쪽, 아래쪽으로 이동할 수 있지만, 위쪽으로는 이동할 수 없다.
 * 또한 한 번 탐사한 지역(배열에서 하나의 칸)은 탐사하지 않기로 한다.
 * <p>
 * 각각의 지역은 탐사 가치가 있는데, 로봇을 배열의 왼쪽 위 (1, 1)에서 출발시켜 오른쪽 아래 (N, M)으로 보내려고 한다.
 * 이때, 위의 조건을 만족하면서, 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램을 작성하시오.
 * <p>
 * 입력
 * 첫째 줄에 N, M(1≤N, M≤1,000)이 주어진다. 다음 N개의 줄에는 M개의 수로 배열이 주어진다.
 * 배열의 각 수는 절댓값이 100을 넘지 않는 정수이다. 이 값은 그 지역의 가치를 나타낸다.
 * <p>
 * 출력
 * 첫째 줄에 최대 가치의 합을 출력한다.
 * <p>
 * 오른쪽, 아래, 왼쪽 밖에 못가고, 한 번 간 곳은 못가니까 3차원 dp를 사용하여 오 아 왼 방향대로 갔을 때 값을 기억하여
 * 가장 많이 먹을 수 있는 길을 찾아야 한다
 *
 * N,M에 도착했을 때 dp에서 세 방향 중 가장 큰 값을 출력
 *
 * https://lotuslee.tistory.com/45
 * @see https://www.acmicpc.net/problem/2169
 * @since 2025. 04. 03
 */

public class BJ_G2_2169_로봇조종하기 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N, M;
    static int[][] map;
    static int[][] dp; // 오른쪽, 아래, 왼쪽
    static boolean[][] visited;
    static int[][] deltas = {{0,1}, {1,0}, {0,-1}};
    static final int INF = -987654321;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
//        왼쪽에서 오는거 더하고, 오른쪽에서 오는거 더하고, 둘 중에 큰거를 dp에 넣는다
//        2행부터는 위랑 왼쪽에서 오는거 비교해서 더하고, 오른쪽도 마찬가지, 둘 중에 큰거 dp에 넣는다
        dp[0][0] = map[0][0];
//      0,0부터 시작이라 왼쪽부터 오는 게 제일 큰 값
        for(int j=1; j<M; j++) {
            dp[0][j] = map[0][j] + dp[0][j-1];
        }

        for(int i=1; i<N; i++) {
            int[] leftToRight = new int[M];
            int[] rightToLeft = new int[M];

            leftToRight[0] = dp[i-1][0] + map[i][0];
            for(int j=1; j<M; j++) {
                leftToRight[j] = Math.max(dp[i-1][j], leftToRight[j-1]) + map[i][j];
            }

            rightToLeft[M-1] = dp[i-1][M-1] + map[i][M-1];
            for(int j=M-2; j>=0; j--) {
                rightToLeft[j] = Math.max(dp[i-1][j], rightToLeft[j+1]) + map[i][j];
            }

            for(int j=0; j<M; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}
