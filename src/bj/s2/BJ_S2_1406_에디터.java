package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_1406_에디터 {
    static class node {
        char value;
        node next;
        node prev;

        public node(char value, node next, node prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokens;

    static String word;
    static int M;

    public static void main(String[] args) throws IOException {
        word = input.readLine();
        M = Integer.parseInt(input.readLine());
        node head = new node(' ',null,null);
        node pos = head;
        for(int i=0; i<word.length(); i++){
            node n = new node(word.charAt(i), null, pos);
            pos.next = n;
            pos = n;
        }
        node tail = new node(' ', null, pos);
        pos = tail;

        for(int i=0; i<M; i++) {
            tokens = new StringTokenizer(input.readLine());
            if(tokens.nextToken() == "P"){
                node n = new node(tokens.nextToken().charAt(0), pos, pos.prev);
                node prev = n.prev;
                prev.next = n;
                pos = n;
            }
            else if(tokens.nextToken() == "L"){
                if(pos.prev != null){
                    pos = pos.prev;
                } else {
                    continue;
                }
            }
            else if(tokens.nextToken() == "D"){
                if(pos.next != null){
                    pos = pos.next;
                } else{
                    continue;
                }
            }
            else if(tokens.nextToken() == "B"){
                pos = pos.prev;
            }


        }

    }
}
