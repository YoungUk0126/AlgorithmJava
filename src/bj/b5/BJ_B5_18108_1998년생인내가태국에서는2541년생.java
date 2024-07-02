package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_B5_18108_1998년생인내가태국에서는2541년생 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int Y = Integer.parseInt(input.readLine());
        System.out.println(Y - 543);
    }
}
