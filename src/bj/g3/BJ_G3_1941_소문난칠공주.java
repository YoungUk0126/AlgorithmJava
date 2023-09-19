package bj.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023.09.19
@see https://www.acmicpc.net/problem/1941
@git
@performance
@category #
@note
시간: 2초
총 25명의 여학생들로 이루어진 여학생반은 5*5의 정사각형 격자 형태로 자리가 배치,
'이다솜'과 '임도연' 두 파로 갈라지게 되었으며 얼마 지나지 않아 '임도연'파가 세력을 확장시키며
'이다솜'파를 위협하기 시작했다.

'이다솜'파는 소문난 칠공주를 결성하려고 한다.
규칙
1. 7명의 여학생들로 구성되어야 한다.
2. 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
3. 화합과 번영을 위해, 반드시 '이다솜파'일 필요는 없지만, '이다솜파'가 4명 이상은 포함되어야 한다.

소문난 칠공주를 결성할 수 있는 모든 경우의 수를 구해라

S가 4개 이상, 모두 인접한, 7개의 배열
*/
public class BJ_G3_1941_소문난칠공주 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static char[][] students;
	static int[][] deltas;
	
	public static void main(String[] args) {
		

	}

}
