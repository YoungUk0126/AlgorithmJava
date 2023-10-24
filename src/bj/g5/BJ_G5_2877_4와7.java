package bj.g5;
/**
@author 김영욱
@since 2023. 10. 25.
@see https://www.acmicpc.net/problem/2877
@git
@performance 
@category #
@note
4와 7로 이루어진 수를 좋아하는데
K가 주어진다 ( K = 1,000,000,000 )
K번째 작은 수를 출력해라
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_2877_4와7 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static String K;

	public static void main(String[] args) throws IOException{
		K = Integer.toBinaryString(Integer.parseInt(input.readLine())+1)
				.replace('0', '4').replace('1', '7');
		for(int i=1; i<K.length(); i++) {
			builder.append(K.charAt(i));
		}
		
		System.out.println(builder);
		

	}

}
