package bj.g4;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note 시간 : 2초
 * 첫째 줄에 수열의 크기 N이 주어진다. N은 50보다 작은 자연수이다.
 * 둘째 줄부터 N개의 줄에 수열의 각 수가 주어진다.
 * 수열의 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 *
 * 수열이 주어지고 어떤 두 수를 묶어서 최대값을 구해라( 여러개 묶어도 됨 )
 * 10 13 8 4 2 0
 *
 * 0 + 2 + (8 * 4) + (13 * 10)
 *
 * 우선 오름차순으로 정렬
 * 음수랑 양수 따로 배열에 저장하고
 * 음수는 오름차순
 * 양수는 내림차순
 *
 * 각자 2개씩 묶어서 곱하고 더한 다음에 남는 것들은 더하면 끝
 *
 * 예외 0이면 그냥 수열에서 뺄 것( 더해봤자 의미 없고 곱하면 큰일남 )
 * 예외 모든 수열안의 숫자가 1이라면?(더하는게 베스트)
 * 예외 모든 수열안의 숫자가 -1이라면?(곱하는게 베스트)
 *
 * 25퍼 컷났는데 GPT가 이렇게 분석해줬다.(미친 개똑똑해)
 *
 * 숫자 1: 코드에서 양수에 1을 포함시켜서 tiedNum 함수로 처리하거나 yangSum에 더했는데,
 * 1은 어떤 수랑 곱해도 자기 자신보다 커지지 않아요 (1X = X). 심지어 11 = 1인데, 1+1=2니까 1끼리도 더하는 게 낫죠.
 * 그래서 1은 무조건 곱하지 않고 최종 합계에 따로 더해주는 게 최선이에요.
 * 숫자 0: 코드에서 0은 아예 처리되지 않고 버려지고 있어요.
 * 0은 양수와 곱하면 0이라 손해지만, 음수와 곱하면 0이 되죠. 만약 음수가 홀수 개 남았을 경우,
 * tiedNum 함수는 마지막 남은 음수를 그대로 더하게 되는데, 이때 0이 있다면 그 음수를 0과 곱해서 0으로 만드는 게 더 나은 경우가 생겨요
 * (음수 + 0 = 음수, 음수 * 0 = 0). 따라서 0의 개수를 세어두고,
 * 음수가 홀수 개 남았을 때 0이 있다면 그 마지막 음수를 더하는 대신 0을 더하도록 (즉, 아무것도 안 더하도록) 처리해야 해요.
 * @see
 * @since 2025. 05. 28
 */

import java.util.*;
import java.io.*;

public class BJ_G4_1744_수묶기 {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static ArrayList<Integer> yang;
    static ArrayList<Integer> um;
    static int answer;
    static boolean zeroFlag = false;


    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(input.readLine());
        yang = new ArrayList<>();
        um = new ArrayList<>();

        answer = 0;
        int oneSum = 0;

        for(int i=0; i<N; i++) {
            int temp = Integer.parseInt(input.readLine());
            if(temp > 0) {
                if(temp == 1) oneSum++;
                else yang.add(temp);
            } else if(temp < 0) {
                um.add(temp);
            } else zeroFlag = true;
        }
        Collections.sort(yang, Collections.reverseOrder());
        Collections.sort(um);
        int yangAns = tiedNum(yang, false);
        yangAns += oneSum;
        int umAns = tiedNum(um, true);
        answer = yangAns + umAns;
        System.out.println(answer);

    }
    private static int tiedNum(ArrayList<Integer> list, boolean umYangFlag) {
        boolean flag = false;
        int temp = 0;
        int answer = 0;
        for(int num: list) {
            if (flag) {
                answer += temp * num;
                flag = false;
            } else {
                flag = true;
                temp = num;
            }
        }
        if(umYangFlag) {
            if(flag) {
                if(!zeroFlag) answer += temp;
            }
        } else {
            if(flag) answer += temp;
        }
        return answer;
    }
}
