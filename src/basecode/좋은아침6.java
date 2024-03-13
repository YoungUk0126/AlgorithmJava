package basecode;

import java.util.Arrays;

public class 좋은아침6 {

    static char[] src = {'A', 'B', 'C', 'D'};
    public static void main(String[] args) {

//        makePermutation(0, new boolean[4], new char[3]);
//        makeCombination(0, 0, new char[3]);
        makeSubset(0, new boolean[4]);
    }

    static void makePermutation(int nthChoice, boolean[] visited, char[] choosed) {
//        기저 조건 2
        if(nthChoice == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }
//        재귀 처리 1
        for(int i=0; i<src.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                choosed[nthChoice] = src[i];
                //nthChoice같은 결정자는 전위,후위 연산자를 써주면 재귀에서 돌아왔을 때도 nthChoice가 증가된 채로 머물게됨.
                makePermutation(nthChoice+1, visited, choosed);
                visited[i] = false;
            }
        }

    }

    static void makeCombination(int nthChoice, int startIdx, char[] choosed) {
//        기저 조건 2
        if(nthChoice == choosed.length) {
            System.out.println(Arrays.toString(choosed));
            return;
        }
//        재귀 처리 1

        for(int i=startIdx; i<src.length; i++) {
            choosed[nthChoice] = src[i];
            makeCombination(nthChoice+1, i+1, choosed);
        }
    }

    static void makeSubset(int nthCheck, boolean[] status){
        if(nthCheck == status.length) {
            printSubset(status);
            return;
        }

        status[nthCheck] = true;
        makeSubset(nthCheck+1, status);
        status[nthCheck] = false;
        makeSubset(nthCheck+1, status);
    }

    static void printSubset(boolean[] status) {
        System.out.print("[");
        for(int i=0; i<status.length; i++) {
            if(status[i]) {
                System.out.print(src[i]);
            }
            else {

                System.out.print("0");
            }
        }
        System.out.println("]");
    }

}
