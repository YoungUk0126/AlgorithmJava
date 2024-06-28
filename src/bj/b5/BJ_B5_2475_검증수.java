package bj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B5_2475_검증수 {

  static StringTokenizer tokens;
  static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

  static int arr[] = new int[5];
  static int ans;
  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(input.readLine());

    for(int i=0; i<5; i++) {
      arr[i] = Integer.parseInt(tokens.nextToken());
    }

    for(int num: arr) {
      ans += num * num;
    }
    System.out.println(ans%10);
  }
}
