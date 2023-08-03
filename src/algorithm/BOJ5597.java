package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ5597 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N = 28;
	static int V;
	static int[][] arr = new int[31][1];
	static ArrayList<Integer> ansStu = new ArrayList<>();
	static int min_ans = 1000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		for(int i=0; i<N; i++) {
			V = Integer.parseInt(input.readLine());
			arr[V][0] += 1;
		}
		for(int i=1; i<arr.length; i++) {
			if(arr[i][0] > 0) {
				continue;
			} else {
				ansStu.add(i);
			}
		}
		Collections.sort(ansStu);
		
		for(int ans: ansStu) {
			System.out.println(ans);
		}
	}

}
