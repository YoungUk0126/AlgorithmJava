package bj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_S4_1764_듣보잡 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N,M;
    static HashMap<String, Integer> map = new HashMap<>();
    static ArrayList<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        String temp;


        for(int i=0; i<N; i++) {
            map.put(input.readLine(), 0);
        }
        for(int i=0; i<M; i++) {
            temp = input.readLine();
            if(map.containsKey(temp)){
                answer.add(temp);
            }
        }
        Collections.sort(answer);
        builder.append(answer.size()+"\n");
        for(String name: answer) {
            builder.append(name+"\n");
        }
        System.out.print(builder);
    }
}
