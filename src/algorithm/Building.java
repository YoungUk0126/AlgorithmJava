package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Building {
	
	static int[][]deltasG = {{0,-1}, {-1, -1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			char[][] chr = new char[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					chr[i][j] = st.nextToken().charAt(0);
				}
			}
			int max = 0;
			for(int i=0; i<N; i++) {
				Loop:
				for(int j=0; j<N; j++) {
					if(chr[i][j] != 'G') {
						int temp = 0;
						for(int d=0; d<8; d++) {
							int dx = i + deltasG[d][0];
							int dy = j + deltasG[d][1];
							if (0 <=dx && dx<N && 0<=dy && dy<N) {
								if(chr[dx][dy] == 'G') {
									max = Math.max(max, 2);
									continue Loop;
								}
							}
						}
						for(int x=0; x<N; x++) {
							if (chr[x][j] != 'G') {
								temp += 1;
							}
						}
						for(int y=0; y<N; y++) {
							if (chr[i][y] != 'G') {
								temp += 1;
							}
						}
						max = Math.max(max, temp-1);
					}
				}
			}
			System.out.println("#"+t+" "+max);
		}
	}
}

