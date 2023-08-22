package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
@author 김영욱
@since 2023.08.22
@see https://www.acmicpc.net/problem/1759
@git
@performance 
@category #
@note
시간 2초

암호를 만들건데 최소 한 개의 모음(a,e,i,o,u)와 최소 두 개의 자음으로 구성되어 있대
알파벳들을 입력 받은 배열을 정렬한 후에 조합을 통해 4개를 뽑아주면 된다.
이게 최소 한 개의 모음이고 최소 두 개의 자음으로 구성되면 정렬한 후 print해주면 될 듯

L은 암호 길이, C는 주어지는 알파벳 개수

*/
public class BJ_G5_1759_암호만들기 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int L,C;
	static char[] pw;
	static char[] mo = {'a', 'e', 'i', 'o', 'u'};
	public static void main(String[] args) throws IOException{
		tokens = new StringTokenizer(input.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		pw = new char[C];
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<C; i++) {
			pw[i] = tokens.nextToken().charAt(0);
		}
		Arrays.sort(pw);
		
		makeCombination(0, 0, new char[L]);
		System.out.println(builder);
	}
	static void makeCombination(final int nth, final int startIndex, char[] choosed) {
        // 기저 조건
        if (nth == choosed.length) {
        	int moCnt = 0;
        	int jaCnt = 0;
        	for(char word: choosed) {
        		if(isMo(word)) moCnt++;
        		else jaCnt++;
        	}
        	if(moCnt>=1 && jaCnt>=2) {
        		for(char word: choosed) {
        			builder.append(word);
        		}
        		builder.append("\n");
        	}
            return;
        }

        for (int i = startIndex; i < pw.length; i++) {
            choosed[nth] = pw[i];
            makeCombination(nth + 1, i + 1, choosed);
        }
    }
	
	static boolean isMo(char word) {
		for(char m: mo) {
			if(word == m) {
				return true;
			}
		}
		return false;
		
	}

}
