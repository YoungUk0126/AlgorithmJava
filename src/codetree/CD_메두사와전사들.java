package codetree;

import java.util.*;
import java.io.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 시간 1초
 * N * N 크기의 마을에 메두사가 살고 있다.
 * 메두사는 오직 "도로"만을 따라 최단 경로로 공원까지 이동한다.
 * 이때 집과 공원은 항상 도로위에 있으며, 집과 공원의 좌표는 다름을 가정해도 좋다.
 * <p>
 * M명의 전사들이 메두사를 잡기 위해 마을에 도착
 * 이들은 메두사를 향해 최단 경로로 이동하고, 도로와 비도로를 구분하지 않고 이동
 * 메두사의 집에 전사들이 초기부터 위치하는 경우는 없다.
 * <p>
 * 1. 메두사의 이동
 * 메두사는 도로를 따라 한 칸 이동하며, 공원까지 최단 경로를 따른다.
 * 이동한 칸에 전사가 있을 경우 메두사에게 공격을 받고 사라진다.
 * 최단 경로의 우선 순위는 상,하,좌,우(delta)이고, 집에서 공원까지 도달하는 경로가
 * 없을 수도 있다.
 * <p>
 * 2. 메두사의 시선
 * 상,하,좌,우 90도의 시야각을 가지며, 1,3,5,7,9처럼
 * 점점 늘어나는 시야 거리를 가진다. 메두사의 시야각 안에 들어와있지만 다른 전사에 가려진 전사의 경우
 * 메두사에게 보이지 않는다.
 * <p>
 * 상하좌우 대각선 8방향으로 나누었을 때, 메두사로부터
 * 8방향 중 한 방향에 전사가 위치해있다면, "그 전사"가 동일한 방향으로 바라본 범위에
 * 포함된 모든 칸은 메두사에게 보이지 않는다.
 * <p>
 * 메두사가 본 전사들은 모두 돌로 변해 현재 턴에는 움직일 수 없으며, 이번 턴이 종료됐을 때 이동
 * 두 명 이상의 전사들이 같은 칸에 위치해있다면 해당 칸의 전사들은 모두 돌로 변함
 * <p>
 * 메두사는 상,하,좌,우 중 전사를 가장 많이 볼 수 있는 방향을 바라본다.
 * 같은 수의 전사를 볼 수 있는 방향이 여러 개라면, 상하좌우의 우선순위를 가짐
 * <p>
 * 3. 전사들의 이동
 * 최대 두 칸까지 이동한다. 전사들은 이동 중 같은 칸을 공유할 수 있다.
 * 첫 번째 이동
 * - 메두사와 거리를 줄일 수 있는 방향으로 한 칸 이동. 역시 상하좌우 우선순위
 * - 격자의 바깥으로 나갈 수 없으며, 메두사의 시야에 들어오는 곳으로는 이동할 수 없다.
 * <p>
 * 두 번째 이동
 * - 메두사와 거리를 줄일 수 있는 방향으로 한 칸 이동. 이 때는 좌우상하의 우선순위
 * - 격자의 바깥으로 나갈 수 없으며, 메두사의 시야에 들어오는 곳으로는 이동할 수 없다.
 * <p>
 * 4. 전사의 공격
 * 메두사와 같은 칸에 도달한 전사는 메두사에게 죽어서 사라짐.
 * 위의 네 단계에서 최단경로를 계산할 때는 맨해튼 거리를 기준
 * <p>
 * 위의 네 단계가 반복되어 메두사가 공원에 도달할 때까지
 * 매 턴마다 해당 턴에서 보든 전사가 이동한 거리의 합, 메두사로 인해 돌이 된 전사의 수,
 * 메두사를 공격한 전사의 수를 공백을 사이에 두고 차례대로 출력하는 프로그램을 작성
 * 단, 메두사가 공원에 도착하는 턴에는 0을 출력하고 프로그램을 종료
 * <p>
 * 마을의 크기 N, 전사의 수 M
 * 메두사의 집의 위치 정보, 공원의 위치 정보
 * M명의 전사들의 좌표
 * 마을 정보( 도로는 0, 비도로는 1 )
 * <p>
 * 입력
 * 4 4
 * 1 3 3 3
 * 3 1 0 3 1 0 2 2
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 1 1
 * 1 0 0 0
 * <p>
 * 출력
 * 4 2 2
 * 0 2 0
 * 1 1 1
 * 0 0 0
 * 0 0 0
 * 0
 * @see https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/medusa-and-warriors/description
 * @since 2025.08.14
 */


public class CD_메두사와전사들 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N, M;
    static int parkX, parkY, houseX, houseY;
    static ArrayList<Warrior> warriors;
    static int totalWarriorDist, stonedWarrior, attackedWarrior;
    // 시계방향
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] secDeltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int[][] map;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][N];
        warriors = new ArrayList<>();


        tokens = new StringTokenizer(input.readLine());
        houseX = Integer.parseInt(tokens.nextToken());
        houseY = Integer.parseInt(tokens.nextToken());
        parkX = Integer.parseInt(tokens.nextToken());
        parkY = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < M; i++) {
            int wx = Integer.parseInt(tokens.nextToken());
            int wy = Integer.parseInt(tokens.nextToken());
            warriors.add(new Warrior(wx, wy));
        }

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        Stack<int[]> shortestPathStack = findShortestPath(houseX, houseY);
        if (!shortestPathStack.isEmpty()) {
            game(shortestPathStack);
            System.out.println(builder);
        }


    }


    private static void game(Stack<int[]> shortestPathStack) {
        int[] start = shortestPathStack.pop();
        Medusa medusa = new Medusa(start[0], start[1], new int[N][N]);

        while (!shortestPathStack.isEmpty()) {
            totalWarriorDist = 0;
            stonedWarrior = 0;
            attackedWarrior = 0;

            for (Warrior w : warriors) w.stone = false;// 언거 풀고
            int[] nextDestination = shortestPathStack.pop();
            int nx = nextDestination[0];
            int ny = nextDestination[1];
            // 이동
            medusa.move(nx, ny);//굿
            if (medusa.curX == parkX && medusa.curY == parkY) {
                System.out.println(0);
                return;
            }
            //전사 있는지 체크하고 없애
            // 시선
            stonedWarrior = medusa.visionArea();//병사들 얼려

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == medusa.curX && j == medusa.curY)
                        System.out.print("3 ");
                    else System.out.print(medusa.vision[i][j] + " ");
                }
                System.out.println();
            }
            System.out.print("남은 전사들 좌표: ");
            for(Warrior w: warriors) System.out.print(w.curX+","+w.curY+" ");
            System.out.println();
            System.out.println();

            int beforeDied = warriors.size();
            int diedAfterMoveCount = 0;


//            첫번째 이동
            for (Warrior w : warriors) if (!w.stone) totalWarriorDist += w.firstMove(medusa);
//            메두사 만나면 삭제
            warriors.removeIf(w -> medusa.curX == w.curX && medusa.curY == w.curY);

//            두번째 이동
            for (Warrior w : warriors) if (!w.stone) totalWarriorDist += w.secondMove(medusa);
            warriors.removeIf(w -> medusa.curX == w.curX && medusa.curY == w.curY);

            diedAfterMoveCount = warriors.size();// 메두사 만나서 얼마나 삭제됐는지 비교한 후 더해줌
            attackedWarrior += beforeDied - diedAfterMoveCount;

            builder.append(totalWarriorDist).append(" ").append(stonedWarrior).append(" ").append(attackedWarrior).append("\n");
        }
    }

    private static Stack<int[]> findShortestPath(int x, int y) {
        // 메두사가 공원까지 갈 수 있는 경로가 없을 수도 없음을 꼭 고려할 것
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        int[][][] parentMap = new int[N][N][2];
        boolean pathExist = false;
        Stack<int[]> shortestPathStack = new Stack<>();


        q.offer(new int[]{x, y});
        visited[x][y] = true;
        // 시작점의 parent는 자기 자신으로 (역추적 시 끝임을 알리기 위함)
        parentMap[x][y][0] = x;
        parentMap[x][y][1] = y;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int dx = now[0];
            int dy = now[1];

            if (dx == parkX && dy == parkY) {
                pathExist = true;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = dx + deltas[d][0];
                int ny = dy + deltas[d][1];

                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true; // 방문처리
                    // 부모 노드 기록
                    parentMap[nx][ny][0] = dx;
                    parentMap[nx][ny][1] = dy;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        if (pathExist) { // 공원까지 가는 경로가 존재
            int traceX = parkX;
            int traceY = parkY;

            // 공원부터 시작점까지 역으로 스택에 추가
            while (true) {
                shortestPathStack.push(new int[]{traceX, traceY});
                // 현재 위치가 시작점이라면 루프 종료
                if (traceX == x && traceY == y) {
                    break;
                }

                int prevX = parentMap[traceX][traceY][0];
                int prevY = parentMap[traceX][traceY][1];
                traceX = prevX;
                traceY = prevY;
            }
        } else { // 경로가 존재하지 않는 경우
            System.out.println(-1);
        }
        return shortestPathStack;
    }


    private static class Medusa {
        // 움직이는거
        // 바라보는거
        // 바라본 곳에 전사들이 있는지 체크
        // 메두사 현재 좌표랑 병사랑 비교해서 하면 될듯

        int curX;
        int curY;
        int[][] vision;
        char direction;

        public Medusa(int curX, int curY, int[][] vision) {
            this.curX = curX;
            this.curY = curY;
            this.direction = '하';// 임시로
            this.vision = vision;
        }

        public void move(int nx, int ny) {
            if (isIn(nx, ny)) {
                curX = nx;
                curY = ny;
            }
            warriors.removeIf(w -> w.curX == curX && w.curY == curY);
        }

        public int visionArea() {
            char[] directionArr = {'상', '하', '좌', '우'};
            int maxCount = 0;
            for (char now : directionArr) { // 일단 범위 켜놓고, 석화 범위에 맞춰서 count
                int count = 0;
                int[][] copyVision = findWarriorInRange(now);
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (copyVision[i][j] == -9) count++;
                    }
                }
                if (maxCount < count) {
                    maxCount = count;
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            vision[i][j] = copyVision[i][j];
                        }
                    }
                    direction = now;
                }
            } // 애들 다 얼린 Vision 나왔음
            for (Warrior w : warriors) if (vision[w.curX][w.curY] == -9) w.stone = true;
            return maxCount;
        }

        public void findUnstonedWarrior(int nowX, int nowY, int[][] copyVision) {
            //8방향 검사해줘야함
            if (nowX == curX) {// 세로 위치가 같다
                if (nowY > curY) {// 병사가 메두사 오른쪽에 있다
                    for (Warrior w : warriors) {
                        if (nowX == w.curX && nowY < w.curY) { // 병사보다 오른쪽에 있는 놈은 얼지 않습니다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }
                } else {// 병사가 메두사 왼쪽에 있다
                    for (Warrior w : warriors) {
                        if (nowX == w.curX && nowY > w.curY) { // 병사보다 왼쪽에 있는 놈은 얼지 않습니다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }
                }

            } else if (nowY == curY) {// 가로 위치가 같다
                if (nowX > curX) {// 병사가 메두사 아래에 있다
                    for (Warrior w : warriors) {
                        if (nowY == w.curY && nowX < w.curX) { // 병사보다 더 밑에 있는 놈들은 얼지 않습니다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }
                } else {// 병사가 메두사 위에 있다
                    for (Warrior w : warriors) {
                        if (nowY == w.curY && nowX > w.curX) { // 병사보다 더 위에 있는 놈은 얼지 않습니다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }
                }

            } else {// 대각선
                if (nowX <= curX && nowY > curY) {// 상,우
                    for (Warrior w : warriors) {
                        if (nowX > w.curX && nowY < w.curY) { // 병사보다 상,우 대각선에 있는 병사는 얼지 않는다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }

                } else if (nowX >= curX && nowY > curY) {// 하,우
                    for (Warrior w : warriors) {
                        if (nowX < w.curX && nowY < w.curY) { // 병사보다 하,우 대각선에 있는 병사는 얼지 않는다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }

                } else if (nowX >= curX && nowY < curY) {// 하,좌
                    for (Warrior w : warriors) {
                        if (nowX < w.curX && nowY > w.curY) { // 병사보다 하,좌 대각선에 있는 병사는 얼지 않는다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }

                } else if (nowX <= curX && nowY < curY) {// 상,좌
                    for (Warrior w : warriors) {
                        if (nowX > w.curX && nowY > w.curY) { // 병사보다 상,좌 대각선에 있는 병사는 얼지 않는다.
                            copyVision[w.curX][w.curY] = -2;// -1은 병사니까 안어는 병사는 -2로^^
                        }
                    }
                }
            }
        }

        public int[][] findWarriorInRange(char direction) {
            int[][] copyVision = new int[N][N];
            for (Warrior w : warriors) copyVision[w.curX][w.curY] = -1;
            int weight = 1;
            if (direction == '상' && curX > 0) {
                for (int i = curX - 1; i >= 0; i--) {
                    for (int j = 0; j < N; j++) {
                        int leftRange = curY - weight;
                        int rightRange = curY + weight;
                        if (leftRange <= j && rightRange >= j) {
                            if (copyVision[i][j] == -1) { // 병사 위치임
                                findUnstonedWarrior(i, j, copyVision);
                                copyVision[i][j] = -9;
                            }
                        }
                    }
                    weight++;
                }
            } else if (direction == '하' && curX < N) {
                for (int i = curX + 1; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int leftRange = curY - weight;
                        int rightRange = curY + weight;
                        if (leftRange <= j && rightRange >= j) {
                            if (copyVision[i][j] == -1) { // 병사 위치임
                                findUnstonedWarrior(i, j, copyVision);
                                copyVision[i][j] = -9;
                            }
                        }
                    }
                    weight++;
                }
            } else if (direction == '좌' && curY > 0) {
                for (int j = curY - 1; j >= 0; j--) {
                    for (int i = 0; i < N; i++) {
                        int topRange = curX - weight;
                        int bottomRange = curX + weight;
                        if (topRange <= i && bottomRange >= i) {
                            if (copyVision[i][j] == -1) { // 병사 위치임
                                findUnstonedWarrior(i, j, copyVision);
                                copyVision[i][j] = -9;
                            }
                        }
                    }
                    weight++;
                }
            } else if (direction == '우' && curY < N) {
                for (int j = curY + 1; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        int topRange = curX - weight;
                        int bottomRange = curX + weight;
                        if (topRange <= i && bottomRange >= i) {
                            if (copyVision[i][j] == -1) { // 병사 위치임
                                findUnstonedWarrior(i, j, copyVision);
                                copyVision[i][j] = -9;
                            }
                        }
                    }
                    weight++;
                }
            }
            return copyVision;
        }
    }

    private static class Warrior {
        int curX;
        int curY;
        boolean stone;

        public Warrior(int curX, int curY) {
            this.curX = curX;
            this.curY = curY;
            this.stone = false;
        }

        public int firstMove(Medusa medusa) {

            int curManhattan = 0;
            int nextManhattan = 0;

            for (int d = 0; d < 4; d++) {
                int nx = curX + deltas[d][0];
                int ny = curY + deltas[d][1];

                if (isIn(nx, ny)) {
                    curManhattan = manhattan(medusa.curX, curX, medusa.curY, curY);
                    nextManhattan = manhattan(medusa.curX, nx, medusa.curY, ny);
                    if (nextManhattan < curManhattan) {
                        curX = nx;
                        curY = ny;

                        return 1;
                    }
                }
            }
            return 0;
        }

        public int secondMove(Medusa medusa) {
            int curManhattan = 0;
            int nextManhattan = 0;

            for (int d = 0; d < 4; d++) {
                int nx = curX + secDeltas[d][0];
                int ny = curY + secDeltas[d][1];

                if (isIn(nx, ny)) {
                    curManhattan = manhattan(medusa.curX, curX, medusa.curY, curY);
                    nextManhattan = manhattan(medusa.curX, nx, medusa.curY, ny);
                    if (nextManhattan < curManhattan) {
                        curX = nx;
                        curY = ny;
                        return 1;
                    }
                }
            }
            return 0;
        }
    }


    private static boolean isIn(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N;
    }

    private static int manhattan(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
