package bj.b2;
/**
@author 김영욱
@since 2023. 08. 11
@see https://www.acmicpc.net/problem/3040
@git
@performance
@category #
@note
시간 1초, 답은 항상 유일함
난쟁이가 일을 하는 동안 백설공주는 밥지음 ㅋ
의자 7, 접시7, 나이프 7

원래 일곱 난쟁이였는데 아홉 난쟁이가 돌아옴 ㅋㅋㅋ엌ㅋㅋㅋㅋ
이런 일을 대비해서 난쟁이가 쓰고 다니는 모자에 100보다 작은 정수를 적어 놓음
일곱 난쟁이의 모자의 합은 100임 ㅋㅋㅋㅋ
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_B2_3040_백설공주와일곱난쟁이 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	
	static int dwarf[];
	static int answer[];
	static int sum;
	static int flag1, flag2;

	public static void main(String[] args) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		dwarf = new int[9];
		answer = new int[7];
		for(int i=0; i<9; i++) {
			dwarf[i] = Integer.parseInt(input.readLine());
			sum += dwarf[i];
		}
		int diff = sum - 100;
		outer : for(int i=0; i<dwarf.length; i++) {
			for(int j=i+1; j<dwarf.length; j++) {
				if(100 == sum - (dwarf[i]+dwarf[j])) {
					flag1 = dwarf[i];
					flag2 = dwarf[j];
					break outer;
				}
			}
		}
		
		for(int i=0; i<9; i++) {
			if(dwarf[i] == flag1 || dwarf[i] == flag2) continue;
			builder.append(dwarf[i]).append("\n");
		}
		
		System.out.println(builder);
		
	}


}
