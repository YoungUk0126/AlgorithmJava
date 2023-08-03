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

하.. 역시 시간초과가 난다.
슬라이딩 윈도우가 내가 알던 방식이 아니였나보다.
검색해보고 슬라이딩 윈도우와 다찬님이 말한
해시맵을 사용해 보겠다.
슬라이딩 윈도우는 원래 검색 해보았던 자료를 버리지 않는 것이 핵심인 것 같다.
이를 통해서 연산 속도를 줄이는 것이 관건인 알고리즘이다.



*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_S2_12891_DNA비밀번호 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int S, P, a,c,g,t, ans;
	static String DNA;
	static String slideDNA;
	static char temp;
	static HashMap<Character, int[]> map = new HashMap<>();
	static char[] checkDNA = {'A', 'C', 'G', 'T'};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		tokens = new StringTokenizer(input.readLine());
		S = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		
		DNA = input.readLine();
		
		tokens = new StringTokenizer(input.readLine());
		
		for(char c: checkDNA) { // 현재 문자 수 카운트, 꼭 서야하는 목표 카운트
			map.put(c, new int[] {0, Integer.parseInt(tokens.nextToken())});
		}
		
		for(int i=0; i<P; i++) {
			//DNA의 문자를 읽어와서 이에 해당하는 인덱스를 ++
			map.get(DNA.charAt(i))[0]+=1;
		}
		if(countCheck()) {
			ans++;
		}
		// 왼쪽으로는 하나씩 깎고 오른쪽으로 하나씩 전진
		
		for(int i=0; i<S-P; i++) {
			map.get(DNA.charAt(i))[0]-=1;
			map.get(DNA.charAt(P+i))[0]+=1;
			if(countCheck()) {
				ans++;
			}
		}
		System.out.println(ans);
		
//		for(int i = 0; i <= S-P; i++) {
//			int tA = a, tC = c, tG = g, tT = t;
//			slideDNA = DNA.substring(i, P+i); // 잘 잘리네^^
//			for(int j=0; j<slideDNA.length(); j++) {
//				temp = slideDNA.charAt(j);
//				switch (temp) {
//				case 'A':
//					tA--;
//					break;
//				case 'C':
//					tC--;
//					break;
//				case 'G':
//					tG--;
//					break;
//				case 'T':
//					tT--;
//					break;
//
//				default:
//					break;
//				}
//			}
//			if(tA<=0 && tC<=0 && tG<=0 && tT<=0) {
//				ans++;
//			}
//		}
//		System.out.println(ans);
		 
		
	}
	public static boolean countCheck() {
		for(char word: checkDNA) {
			if(map.get(word)[0] < map.get(word)[1]) {
				return false;
			}
		}
		return true;
	}

}
