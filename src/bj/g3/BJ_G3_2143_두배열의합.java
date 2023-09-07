package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 09 07
@see https://www.acmicpc.net/problem/2143
@git
@performance
@category #
@note
두 배열 A랑 B가 주어지고 목표 숫자인 T가 주어진다
A,B 배열에 있는 요소를 합쳐서 목표 숫자인 T가 되는 경우의 수를 구해라

T(5) = A[1] + B[1] + B[3] 이게 한 경우 (인덱스 아닌 그냥 요소를 나타냈음 합이 5인거)
*/

public class BJ_G3_2143_두배열의합 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;
    
    static int T, N, M;
    static int A[], B[];

	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(input.readLine());
		N = Integer.parseInt(input.readLine());
		A = new int[N];
		tokens = new StringTokenizer(input.readLine());
		
		

	}

}
