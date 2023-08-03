package bj.s2;
/**
@author 김영욱
@since 2023. 8. 3.
@see https://www.acmicpc.net/problem/12891
@git
@performance
@category #
@note

DNA 문자열은 모든 문자열에 등장하는 문자가 A,C,G,T인 문자열을 말한다.
ex) ACCA, ACKA => 부분조합인듯
부분문자열에서 등장하는 문자의 개수가 특정 개수 이상이여야하는 조건
ex) AAACCTGCCAA, 뽑을 부분문자열 길이 4
조건 A는 1이상, C는 1이상, G는 1이상, T는 0이상
ACCT는 G가없어서 조건만족못해, GCCA는 사용 가능


입력
S P			S: 임의로 만든 DNA 문자열 길이, P: 부분문자열 길이
CCTGGATTG	문자열
2 0 1 1		A C G T 최소 개수

A C G T 최소 개수 입력 받을 int형 a,c,g,t
문자열 받을 String
뚝뚝 끊을 분자열 String
for문을 S - P만큼 돌아
substring(i, S-P+i)으로 문자열 끊어서 계산
A, C, G, T 각각 문자열 돌면서 숫자 세
a, c, g, t 보다 cnt가 적다면 false;
많다면 ans += 1;

해시맵써가지고 하나하나 인덱스에 넣어놓으면 된다 ㅎ
get했을때 바로 인덱스 나옴 ㅎ
아;; 아직 정답은 아닌듯;;



*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_12891_DNA비밀번호 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int S, P, a,c,g,t;
	static String DNA;
	static String slideDNA;
	static char temp;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		S = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		
		DNA = input.readLine();
		
		tokens = new StringTokenizer(input.readLine());
		a = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		g = Integer.parseInt(tokens.nextToken());
		t = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i <= S-P; i += S-P) {
			slideDNA = DNA.substring(i, P+i); // 잘 잘리네^^
			for(int j=0; j<slideDNA.length(); j++) {
				temp = slideDNA.charAt(j);
				if(temp == 'A') {
					
				}
			}
		}
		 
		
	}

}
