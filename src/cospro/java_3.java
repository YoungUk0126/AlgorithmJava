package cospro;

import java.util.Arrays;

public class java_3 {


    static int[][] projects = {{5, 90, 90}, {1, 90, 70}, {3, 95, 70}, {2, 85, 85}, {4, 70, 90}};
    static group[] groups = new group[projects.length];
    static int[] ans = new int[groups.length];

    public static void main(String[] args) {

        for(int i=0; i< projects.length; i++) {
            groups[i] = new group(projects[i][0], projects[i][1], projects[i][2]);
        }
        Arrays.sort(groups);
        for(int i=0; i< groups.length; i++) {
            ans[i] = groups[i].groupNumber;
        }
        for(int num: ans) System.out.print(num + " ");
    }

    static class group implements Comparable<group>{
        int groupNumber; // 모둠 번호
        int designScore;  // 프로젝트 설계 점수
        int implScore; // 프로젝트 구현 점수

        public group(int groupNumber, int designScore, int implScore) {
            this.groupNumber = groupNumber;
            this.designScore = designScore;
            this.implScore = implScore;
        }

        @Override
        public int compareTo(group g) {
            if(this.designScore == g.designScore ) {
                if(this.implScore == g.implScore) {
                    return Integer.compare(g.groupNumber,this.groupNumber);
                }
                else {
                    return Integer.compare(g.implScore, this.implScore);
                }
            }
            else {
                return Integer.compare(g.designScore, this.designScore);
            }
        }
    }
}
