package bj.g5;
/**
@author 김영욱
@since 2023. 8. 3.
@see https://www.acmicpc.net/problem/2023
@git
@performance
@category #
@note

순서가 있는 중복 순열을 이용하는 문제라는 생각이 들었다.(출력을 보고)
그래서 N자리수만큼 재귀를 하여 숫자를 문자형 배열에 넣고
그 문자형 배열을 String으로 바꾼 후 1부터N까지 인덱싱하고, 이 인덱싱한 String을
int형으로 바꿔서 소수인지 검사해 준다.
각 자리 수의 isPrime을 다 통과하면 출력해준다.

이건 메모리 초과 나서 소연님한테 과외 받았음^^..
정수로 놀아야 가능함
중복순열 구하는건 같은데 값을 넘겨줄때 미리 소수인지 판별함
sum = num*10+i를 떠올리기 힘들었음.

시간복잡도 : 계산식이 안떠올라...
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_2023_신기한소수 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();
	
	static int N;
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		sum = 0;
		N = Integer.parseInt(input.readLine());
		makePermutation(0,0);
		System.out.println(builder);
		
	}
	
	static void makePermutation(int cnt, int num) {
		if(cnt == N) {
			builder.append(num).append("\n");
			return;
		}
		
		
		for(int i=1; i<10; i++) {
			sum = num*10 + i;
			if(isPrime(sum)) {
				makePermutation(cnt + 1, sum);
			}
		}
	}
	
	static boolean isPrime(int num) {
		if(num < 2) {
			return false;
		}
		else if(num == 2) {
			return true;
		} else {
			long max = (long)(Math.sqrt(num));
			for(int i=2; i<=max; i++) {
				if(num%i == 0) {
					return false;
				}
			}
			return true;
		}
	}

}
