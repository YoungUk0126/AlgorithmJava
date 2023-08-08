package sw.d3;
/**
@author 김영욱
@since 2023. 08. 08
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AW8Wj7cqbY0DFAXN&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=3
@git
@performance 0.1s
@category #
@note
2초 이내에
테케 273개
N : 1000

N M 봉지 개수, 들고갈 수 있는 최대무게
N개의 봉지 무게
한빈이는 두개를 들고 갈 수 있으니까
M보다 작은 봉지 두개의 가장 무거운 무게의 합을 구해

두개를 뽑아내는 조합을 사용하면 될듯
근데 뽑아낼 개수가 고정이므로 이중 반복문으로도 가능할듯

정렬하고 맨 끝의 수랑 처음의 수랑 더해주면서 반복하는데
그 값이 M을 넘어가면 바로 break하고 맨 끝 수 인덱스 --
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_D3_한빈이와SpotMart {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;
	
	static int T, N, M, ans, max;
	static int[] arr;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
//		input = new BufferedReader(new StringReader(str));
		T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			builder.append("#").append(tc).append(" ");
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			max = -1;
			arr = new int[N];
			tokens = new StringTokenizer(input.readLine());
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(tokens.nextToken());
			}
			Arrays.sort(arr);
			for(int right=N-1; right>=0; right--) {
				for(int left=0; left<right; left++) {
					int sum = arr[left] + arr[right];
					if(sum <= M) {
						ans = arr[left] + arr[right];
						max = Math.max(max, ans);
						continue;
					}
					else if(sum > M) {
						break;
					}
				}
			}
			builder.append(max).append("\n");
			
		}
		System.out.println(builder);
	}
	
//	static String str = "4\r\n" + 
//			"3 45\r\n" + 
//			"20 20 20\r\n" + 
//			"6 10\r\n" + 
//			"1 2 5 8 9 11\r\n" + 
//			"4 100\r\n" + 
//			"80 80 60 60\r\n" + 
//			"4 20\r\n" + 
//			"10 5 10 16";

}
