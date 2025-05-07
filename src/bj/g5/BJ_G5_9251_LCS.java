package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* 최장 공통 문자열
* A와 B를 구함( 0번 인덱스는 비워둬 )
* i와 j가 같다면 ij = i-1j-1 + 1
* 전에꺼가 겹친다면 자연스레 연속된다고 판단이 될꺼고 아니라면 0+1이니까 노상관
*
* 최장 공통 부분 수열
* i와 j가 같다면 [i][j] = [i-1][j-1] + 1
* 다르다면 [i][j] =
*
* 1. LCS[i - 1][j]와 LCS[i][j - 1]는 어떤 의미인가?
* 부분수열은 연속된 값이 아닙니다. 때문에 현재의 문자를 비교하는 과정 이전의 최대 공통 부분수열은 계속해서 유지됩니다.
* '현재의 문자를 비교하는 과정' 이전의 과정이 바로 LCS[i - 1][j]와 LCS[i][j - 1]가 됩니다.
* AB와 GBC의 최대 공통 부분수열이 B라는 것을 알기 위해서는 문자열 A와 GBC를 비교하는 과정, 문자열 AB와 GB를 비교하는 과정이 필요합니다.
* 문자열 AB와 GB의 비교 과정에서 최대 공통 부분수열이 B임을 확인했기 때문에 문자열 AB와 GBC의 최대 공통 부분수열 역시 B가 되는 것입니다.
* 2. 왜 문자가 같으면 LCS[i][j] = LCS[i - 1][j - 1] + 1인가?
최대 공통 문자열을 구할 때 비교하는 문자가 같으면 LCS[i][j] = LCS[i - 1][j - 1] + 1의 과정을 거쳤습니다.
* 이 과정이 어떻게 최대 공통 부분수열에도 똑같이 적용될까요? 부분수열이 연속될 필요가 없음을 위 과정에서 여러번 보았습니다.
* 그렇다면 답은 간단합니다. 두 문자가 같은 상황이 오면 지금까지의 최대 공통 부분수열에 1을 더해주는 것 입니다.
*
* => 어짜피 1번 과정 때문에 계속 최장 부분 수열인 부분을 끌고오기 때문이다.
* */

public class BJ_G5_9251_LCS {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static String A, B;
    static char[] Aarr, Barr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        A = input.readLine();
        B = input.readLine();
        Aarr = new char[A.length()+1];
        for(int i=1; i<=A.length(); i++) {
            Aarr[i] = A.charAt(i-1);
        }
        Barr = new char[B.length()+1];
        for(int i=1; i<=B.length(); i++) {
            Barr[i] = B.charAt(i-1);
        }
        dp = new int[A.length()+1][B.length()+1];

        for(int i=1; i<=A.length(); i++) {
            for(int j=1; j<=B.length(); j++) {
                if(Aarr[i] == Barr[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[A.length()][B.length()]);
    }
}
