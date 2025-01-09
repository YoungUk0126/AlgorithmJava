package bj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G3_2457_공주님의정원 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int N;
    static ArrayList<Flower> flowers;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        flowers = new ArrayList<>();
        Stack<Flower> answerList = new Stack<>();
        for(int i=0; i<N; i++) {
            tokens = new StringTokenizer(input.readLine());
            int startMonth = Integer.parseInt(tokens.nextToken());
            int startDay = Integer.parseInt(tokens.nextToken());
            int endMonth = Integer.parseInt(tokens.nextToken());
            int endDay = Integer.parseInt(tokens.nextToken());

            int start = startMonth * 100 + startDay;
            int end = endMonth * 100 + endDay;

            flowers.add(new Flower(start, end));
        }
        Collections.sort(flowers);

        int start = 301;
        int end = 1201;
        int nextSearchIndex = 0;
        int choosen = 0;
        int choosenStart = 0;
        int answer = 0;
        while(start < end) {
            boolean isFind = false;

            for(int i=nextSearchIndex; i<flowers.size(); i++) {
                Flower now = flowers.get(i);
                if(now.start > start) break;

                if(choosen < now.end) {
                    choosen = now.end;
                    choosenStart = now.start;
                    isFind = true;
                }
            }
            if(isFind) {
                start = choosen;
                answer++;
                answerList.push(new Flower(choosenStart, choosen));
            }
            else {
                break;
            }
        }
        if(start >= end) {
            for (Flower now: answerList) System.out.println(now);
            System.out.println(answer);
        } else {
            System.out.println(0);
        }


    }

    private static class Flower implements Comparable<Flower> {
        int start;
        int end;

        @Override
        public String toString() {
            return "Flower{" +
                           "start=" + start +
                           ", end=" + end +
                           '}';
        }

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 시작일이 낮은 순
        // 종료일이 높은 순
        @Override
        public int compareTo(Flower f) {
            if(this.start == f.start) {
                return f.end - this.start;
            } else {
                return this.start - f.start;
            }
        }
    }
}
