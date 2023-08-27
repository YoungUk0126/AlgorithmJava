/**
 @author 김영욱
 @since 2023.08.16
 @see https://www.acmicpc.net/problem/2580
 @git
 @performance
 @category #
 @note
 시간 : 1초
 스도쿠를 푸는 문제다.
 2차원 배열을 주고 빈칸은 0으로 표시한다.
 DFS를 이용하되 스도쿠 규칙을 이용하여 가지치기를 하는게 핵심이다.
 입력을 받을 때 미리 한 개 밖에 들어갈 수 없는 곳은 체크를 해줘서 입력부터 숫자를 넣어주자.
 가로를 체크하는 배열 1개
 세로를 체크하는 배열 1개
 3*3을 체크하는 배열 1개씩 일단 만들어보자

 흠... 0을 찾을때 0을 찾으려고 계속 2차원 배열을 도는게 너무 비효율 적인거 같아.
 입력받을때 0이면 스택에 좌표값을 미리미리 넣어둘까
 그 입력받을때 0혼자면 채워주니까 그땐 스택에서 pop해주는거지

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_G4_2580_스도쿠 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int board[][];

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        board = new int [9][9];
        for(int i=0; i<9; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        check(0, 0);

    }
    private static void check(int row, int col) {

        // 열 다 찾았으면 다음 행으로
        if(col == 9){
            check(row + 1, 0);
            return;
        }

        // 기저 조건
        if(row == 9){
            // 맵 출력
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    builder.append(board[i][j]).append(" ");
                }
                builder.append("\n");
            }
            System.out.println(builder);
            System.exit(0);
        }
        // 재귀 조건
        if(board[row][col] == 0){
            // 가지 치기
            for(int i=1; i<=9; i++){
                // 1부터 9까지 숫자 중 뭐를 넣을 수 있는지 확인하고 넣음
                if(tripleCheck(row, col, i)){
                    board[row][col] = i;
                    // 넣고 다음 열로 진행해
                    check(row, col+1);
                }
            }
            // 재귀를 타고 갔을 때 돌아왔다는건 실패한거니까 다시 0을 넣고 return해줌
            board[row][col] = 0;
            return;
        }

        check(row, col+1);
    }

	private static boolean tripleCheck(int row, int col, int value) {
        for(int i=0; i<9; i++){
            if(board[row][i] == value) return false;
            else if(board[i][col] == value) return false;
        }
        int tempRow = row/3 * 3;
        int tempCol = col/3 * 3;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) {
                if(board[tempRow+i][tempCol+j] == value) return false;
            }
        }
        return true;
	}


}
