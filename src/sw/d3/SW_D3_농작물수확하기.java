package sw.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 김영욱
@since 2023. 8. 1.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV7GLXqKAWYDFAXB&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=++3+
@git
@performance
@category #
@note

정사각형의 N*N 크기의 농장을 가져온다. (N은 항상 홀수)
수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능하다.
규칙 => 0행은 N/2만 수확, 1행은 N/2 -1부터 N/2+1 수확, 2행은 N/2 -2부터...반복



*/
public class SW_D3_농작물수확하기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int[][] ladder;
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for(int tc=1; tc<=T; tc++) {
			sum = 0;
			int N = Integer.parseInt(input.readLine());
			ladder = new int[N][N];
			for(int i=0; i<N; i++) {
				String str = input.readLine();
				for(int j=0; j<N; j++) {
					ladder[i][j] = str.charAt(j) - '0';
				}
			}
			int k=2;
			for(int i=0; i<N; i++) {
				if(i <= N/2) {
					for(int j=N/2-i; j<=N/2+i; j++) {
						sum += ladder[i][j];
					}
				}
				else {
					// 늘어나는 i(k)에 맞춰서 양옆에 2k만큼 빼고 더해줌 k도 2씩 증가
					for(int j=(N/2-i)+k; j<=(N/2+i)-k;j++) {
						sum += ladder[i][j];
					}
					k+= 2;
				}
			}
			
			System.out.println("#" + tc + " " + sum);
			
		}
	}

}
