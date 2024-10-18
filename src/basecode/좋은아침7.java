package basecode;

import java.util.Arrays;

public class 좋은아침7 {

    static char[] src = {'a', 'b', 'c', 'd'};
    public static void main(String[] args) {

//        makePermutation(0, new char[3], new boolean[src.length]);
        makeCombination(0, 0, new char[3]);

    }

    private static void makePermutation(final int nthChoice, char[] choosen, boolean[] visited) {
//        기저 조건
        if(nthChoice == choosen.length) {
            System.out.println(Arrays.toString(choosen));
            return;
        }


        for(int i=0; i<src.length; i++) {
            if(!visited[i]) {
                choosen[nthChoice] = src[i];
                visited[i] = true;
                makePermutation(nthChoice+1, choosen, visited);
                visited[i] = false;
            }
        }
    }

    private static void makeCombination(final int nthChoice, final int startIdx, char[] choosen) {
        if(nthChoice == choosen.length) {
            System.out.println(Arrays.toString(choosen));
            return;
        }

        for(int i=startIdx; i<src.length; i++) {
            System.out.println("nthChoice : " + nthChoice);
            System.out.println("startIdx : " + startIdx);
            System.out.println("i : " + i);
            choosen[nthChoice] = src[i];
            makeCombination(nthChoice+1, i+1, choosen);
        }

    }

}
