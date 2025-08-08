package bj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author 김영욱
 * @git
 * @performance
 * @category #
 * @note * 전깃줄이 교차하지 않기 위해 최소한으로 전깃줄을 제거해야함
 * * 전깃줄의 개수는 100 이하의 자연수이다.
 * *
 * * 전깃줄은 100개 이하이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없음
 * 번호는 500 이하임
 * 1 8
 * 3 9
 * 2 2
 * 4 1
 * 6 4
 * 10 10
 * 9 7
 * 7 6
 * <p>
 * 1 8
 * 3 9
 * 4 1
 * 을 제거하면 안꼬임
 * <p>
 * 생각 1.
 * A와 B차이가 큰 순서대로 정렬함
 * 1 - 8이면
 * 다른 전깃줄 A나 B가 1-8 범위안에 들어있으면 현재 전깃줄 제거
 * 단 A와B나 B와A는 숫자가 같아도 인정
 * <p>
 * 틀렸음 ㅅㅂ
 * 반례
 * 10
 * 1 6
 * 2 8
 * 3 2
 * 4 9
 * 5 5
 * 6 10
 * 7 4
 * 8 1
 * 9 7
 * 10 3
 * <p>
 * 3 2, 7 4, 8 1, 5 5, 9 7, 10 3 총 6개 없애야 하는데
 * <p>
 * 8 1, 10 3, 2 8, 1 6, 4 9, 6 10, 7 4 총 7개 없앰....
 * <p>
 * 걍 무조건 LIS로 풀어야 한대
 * <p>
 * 두 전깃줄 (A1, B1)과 (A2, B2)가 있다고 해보자. 여기서 A는 왼쪽 전봇대의 번호, B는 오른쪽 전봇대의 번호라고 할게.
 * <p>
 * 만약 A1 < A2 인데 B1 > B2 라면, 이 두 전깃줄은 반드시 교차하게 돼. (예: (1, 8)과 (3, 5) -> 1<3 이고 8>5 이므로 꼬임)
 * 교차하지 않으려면, A1 < A2 일 때 B1 < B2 여야 해. 그래야만 선이 꼬이지 않고 쭉 이어지겠지. (예: (1, 5)와 (3, 8) -> 1<3 이고 5<8 이므로 꼬이지 않음)
 * (밑에 핵심 로직)
 * 자, 이걸 다시 생각해보면 우리가 전깃줄들을 A 번호 순서대로 정렬했을 때, B 번호들도 오름차순으로 되어 있다면
 * 그 전깃줄들은 절대 꼬이지 않는다는 거야.
 *
 * A 기준으로 정렬해놓고, B의 가장 긴 부분 수열을 찾으면 됨
 * 근데 이래도 반례가 있음
 * 가장 긴 수열이 끊겼을 때 그 뒤를 찾아서 계속 이 수열을 이어나갈 수 있다면 앞에 수열을 제거해줘야함
 * 맞아 이분 탐색 법이 있었지
 * @see
 * @since 2025. 08. 08
 */
public class BJ_G5_2565_전깃줄 {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();

    static int N;
    static ArrayList<line> lines = new ArrayList<>();
    static ArrayList<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int ans = 0;
        int cnt = 0;
        N = Integer.parseInt(input.readLine());
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            lines.add(new line(a, b));
        }
        Collections.sort(lines);
        for(line cur: lines) {
            binarySearch(cur);
        }
        System.out.println(lines.size() - lis.size());
    }

    private static void binarySearch(line line) {
        if(lis.isEmpty()) lis.add(line.b);
        // lis 처음 값보다 현재 들어갈 값이 더 작다면 바꿔준다.
        if(lis.get(0) >= line.b) {
            lis.set(0, line.b);
        }
        // lis 끝 값보다 현재 들어갈 값이 더 크다면 끝에 추가한다.
        else if(lis.get(lis.size()-1) < line.b) {
            lis.add(line.b);
        }
        else {
            int left = 0;
            int right = lis.size()-1;
            int mid = (left + right) / 2;

            while(left < right) {
                if(line.b > lis.get(mid)) {
                    left = mid+1;
                } else {
                    right = mid;
                }
                mid = (left + right) / 2;
            }
            lis.set(mid, line.b);
        }
    }


    private static class line implements Comparable<line> {
        int a, b;

        public line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "line{" +
                           "a=" + a +
                           ", b=" + b +
                           '}';
        }

        @Override
        public int compareTo(line o) {

            return this.a - o.a;
        }
    }
}
