package bj.s4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_S4_1620_나는야포켓몬마스터이다솜 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N, M;

    static HashMap<Integer, String> dic = new HashMap<>();
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        for(int i=1; i<=N; i++) {
            dic.put(i, input.readLine());
        }

        for(int i=0; i<M; i++) {
            String temp = input.readLine();
            // 입력이 숫자라면
            if(Character.isDigit(temp.charAt(0))){
                builder.append(dic.get(Integer.parseInt(temp))).append("\n");
            }
            else {
                for(Integer key: dic.keySet()){
                    if(dic.get(key).equals(temp)){
                        builder.append(key).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.println(builder);

    }
}
