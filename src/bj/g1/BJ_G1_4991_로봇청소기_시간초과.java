package bj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 시간 1초
 * 로봇 청소기가 모든 먼지를 빨아들이는 최소 시간을 구하래
 * 시작 지점이 주어진다.
 * <p>
 * 그럼 만약 시작지점까지 포함해서 가야할 곳이 4곳이라면
 * 미리 각각 서로의 위치를 가는데 걸리는 시간을 구한 후, 순열을 통해
 * 다 더해서 가장 작은 값을 찾으면 될 것 같다.
 *
 * 하... 10퍼에서 시간초과...
 * 뭐가 문제일까
 *
 * 이렇게 풀면 안되고 map의 x,y -> nx,ny로 가는 경로의 시간을 전부 미리 기록하고 풀어야 시간 초과가 안난댄다
 * 아니 테스트 케이스가 총 몇개인지 정보도 안주면서 뭔 시간 초과 문제를 만드냐
 *
 * N이 최대값인 10이라면 순열 구하는데만 350만번
 *
 *
 * @see https://www.acmicpc.net/problem/4991
 * @since 2025.01.14
 */

public class BJ_G1_4991_로봇청소기_시간초과 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[][] map;
    static int N, M;
    static ArrayList<int[]> dustCordList;
    static int[][] timeBoard;
    static int answer;
    static int[] indexList;

    public static void main(String[] args) throws IOException {
        N = 1;
        M = 1;
        while (true) {
            answer = Integer.MAX_VALUE;
            tokens = new StringTokenizer(input.readLine());
            M = Integer.parseInt(tokens.nextToken());
            N = Integer.parseInt(tokens.nextToken());
            map = new char[N][M];
            if (N == 0 && M == 0) break;
            for (int i = 0; i < N; i++) {
                String temp = input.readLine();
                map[i] = temp.toCharArray();
            }
            dustCordList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '*') {
                        dustCordList.add(new int[]{i, j});
                    } else if (map[i][j] == 'o') {
                        if (dustCordList.isEmpty()) dustCordList.add(new int[]{i, j});
                        else {
                            int[] temp = dustCordList.get(0);
                            dustCordList.add(0, new int[]{i, j});
                            dustCordList.add(temp);
                        }
                    }
                }
            }
            timeBoard = new int[dustCordList.size()][dustCordList.size()];
            for (int i = 0; i < dustCordList.size() - 1; i++) { // 이게 불필요하게 더 돌아가고 있긴 하네
                for (int j = i + 1; j < dustCordList.size(); j++) {
                    timeBoard[i][j] = bfs(dustCordList.get(i), dustCordList.get(j));
                    timeBoard[j][i] = timeBoard[i][j];
                }
            }
//            테스트 코드
//            System.out.println("timeBoard");
//            for(int i=0; i<timeBoard.length; i++) {
//                for(int j=0; j<timeBoard[i].length; j++) {
//                    System.out.print(timeBoard[i][j] + " ");
//                }
//                System.out.println();
//            }
            indexList = new int[dustCordList.size()-1];
            for(int i=0; i<indexList.length; i++) indexList[i] = i+1;
            permutation(0,new int[indexList.length], new boolean[indexList.length]);
            if (answer == Integer.MAX_VALUE) builder.append("-1").append("\n");
            else builder.append(answer).append("\n");
        }
        System.out.println(builder);
    }

    private static int bfs(int[] start, int[] goal) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int count = now[2];
            if(x == goal[0] && y == goal[1]) return count;
            for (int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];
                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 'x') {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, count+1});
                }
            }
        }
        return -1;

    }

    private static void permutation(final int nthChoice, int[] choosed, boolean[] visited) {
        if (nthChoice == choosed.length) {
//            choosed에는 0,1,2가 들어가지만 timeBoard에는 0이 시작지점이니까 먼지 위치를 알기 위해선 +1을 해줘야함
            if(timeBoard[0][choosed[0]] == -1) return;
            int temp = timeBoard[0][choosed[0]];
            for(int i=1; i<choosed.length; i++) {
//                ToDo 여기부터 작성할 것
                if(timeBoard[choosed[i-1]][choosed[i]] == -1) return;
                temp += timeBoard[choosed[i-1]][choosed[i]];
            }
            answer = Math.min(answer, temp);
            return;
        }
        for (int i = 0; i < indexList.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                choosed[nthChoice] = indexList[i];
                permutation(nthChoice + 1, choosed, visited);
                visited[i] = false;
            }
        }
    }

    private static boolean isIn(int row, int col) {
        return (row >= 0 && row < N) && (col >= 0 && col < M);
    }
}
