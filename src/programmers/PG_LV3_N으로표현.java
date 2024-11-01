package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PG_LV3_N으로표현 {

    public static void main(String[] args) {

        System.out.println(solution(1, 11));
    }

    private static int solution(int N, int number) {
        //        중복 값은 없애주려고 HashSet을 사용
        List<HashSet<Integer>> set = new ArrayList<>();

        for(int i=0; i<9; i++) {
            set.add(new HashSet<>());
        }
//        숫자 한 개 쓴거는 지 혼자 밖에 없음(DP초기값)
        set.get(1).add(N);
        if(N == number) return 1;

//        8 이상이면 -1 출력하랬음
        for(int i=2; i<9; i++) {
            //나올 숫자들을 저장할 해쉬셋을 리스트에서 가져옴
            HashSet<Integer> total = set.get(i);
            total.add(Integer.parseInt(String.valueOf(N).repeat(i)));//33같은 숫자를 넣으려고

            for(int j=1; j<i; j++) {
//                i이 3이라면 1(+-*/)2, 2(+-*/)1 이니까
//                i가 4라면 1(+-*/)3, 2(+-*/)2, 3(+-*/)1 이니까
                HashSet<Integer> left = set.get(j);
                HashSet<Integer> right = set.get(i-j);

                for(int l: left) {
                    for(int r: right) {
                        total.add(l + r);
                        total.add(l - r);
                        total.add(l * r);
                        if(l != 0 && r != 0) total.add(l / r);//나누기 할 때 0으로 나누면 에러나서
                    }
                }

            }

            if(total.contains(number)) return i;
        }
        return -1;
    }

}
