package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13268 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int totalDistance;
	static int now;
	static int cnt;
	static int rap = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		totalDistance = Integer.parseInt(input.readLine());
		
		outer: while( true ) {
			//4이상이면(20m가 꼬깔콘의 마지막 거리니까) 다시 1로 초기화
			rap = rap%4 + 1;
			//앞으로 5meter씩 전진
			//남은 거리 수가 5보다 작으면 멈추고 현재 위치 출력
			for(int i=0; i<rap; i++) {
				if(totalDistance >0) {
					totalDistance -= 5;
					now++;
				} else {
					break outer;
				}
			}
			//전진한 만큼 돌아옴
			for(int i=0; i<rap; i++) {
				if(totalDistance >=5) {
					totalDistance -= 5;
					now--;
				} else {
					break outer;
				}
			}
		}
		System.out.println(now);
		
		
	}
}
