package bj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_2239_스도쿠 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static int board[][];

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        board = new int [9][9];
        for(int i=0; i<9; i++) {
            String temp = input.readLine();
            for(int j=0; j<9; j++) {
            	board[i][j] = temp.charAt(j) - '0';
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
                    builder.append(board[i][j]);
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
