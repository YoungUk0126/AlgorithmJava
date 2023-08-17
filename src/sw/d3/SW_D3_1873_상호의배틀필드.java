package sw.d3;

/**
@author 김영욱
@since 
@see 
@git
@performance
@category #
@note
전차를 움직인다.

문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

다음 표는 사용자가 넣을 수 있는 입력의 종류를 나타낸다.
 
문자	동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

게임 맵 밖이라면 전차 이동 못함
포탄을 발사하면, 포탄은 벽에 충돌하거나 맵밖으로 나감
포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌이라면 벽은 파괴되어 평지가 됨
강철이면 아무런 일이 없음

사용자의 입력을 수행하면 맵의 상태가 어떻게 되는지 구해라

H W ( H*X크기의 맵 )
맵을 입력받음
N ( 사용자 명령 길이 )
사용자 명령들
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Tank {
	int x;
	int y;
	int tankStatus;
	public Tank(int x, int y, int tankStatus) {
		super();
		this.x = x;
		this.y = y;
		this.tankStatus = tankStatus;
	}
}

public class SW_D3_1873_상호의배틀필드 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokens;

	static char map[][];
	static int N, h, w;
	static char orders[];
	static Tank tank;

	static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			tokens = new StringTokenizer(input.readLine());
			h = Integer.parseInt(tokens.nextToken());
			w = Integer.parseInt(tokens.nextToken());

			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				map[i] = input.readLine().toCharArray();
			}
			// 탱크 상태 초기화
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j] == '<') {
						tank = new Tank(i,j,2);
					}
					else if(map[i][j] == '>') {
						tank = new Tank(i,j,3);
					}
					else if(map[i][j] == 'v') {
						tank = new Tank(i,j,1);
					}
					else if(map[i][j] == '^') {
						tank = new Tank(i,j,0);
					}
				}
			}
			
			N = Integer.parseInt(input.readLine());
			orders = new char[N];
			orders = input.readLine().toCharArray();

			for (char order : orders) {
				switch (order) {
				case 'S':
					shooting();
					break;
				case 'U':
					tank.tankStatus = 0;
					if(isIn(tank.x+1, tank.y) && moveCheck(tank.x+1, tank.y)) {
						tank.x += 1;
					}
					break;
				case 'D':
					tank.tankStatus = 1;
					if(isIn(tank.x-1, tank.y) && moveCheck(tank.x-1, tank.y)) {
						tank.x -= 1;
					}
					break;
				case 'L':
					tank.tankStatus = 2;
					if(isIn(tank.x, tank.y-1) && moveCheck(tank.x, tank.y-1)) {
						tank.y -= 1;
					}
					break;
				case 'R':
					tank.tankStatus = 3;
					if(isIn(tank.x, tank.y+1) && moveCheck(tank.x, tank.y+1)) {
						tank.y += 1;
					}
					break;
				}

			}
		}
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void shooting() {
		int x = tank.x;
		int y = tank.y;
		while(isIn(x, y) && hitCheck(x,y)) {
			x = x + deltas[tank.tankStatus][0];
			y = y + deltas[tank.tankStatus][1];
		}
	}
	
	static boolean isIn(int x, int y) {
		return 0<=x && x<h && 0<=y && y<w;
	}
	static boolean hitCheck(int x, int y) {
		if(map[x][y] == '*') {
			map[x][y] = '.';
			return false;
		}
		else if(map[x][y] == '#') {
			return false;
		}
		return true;
		
	}
	static boolean moveCheck(int x, int y) {
		if(map[x][y] == '-') {
			return false;
		}
		else {
			return true;
		}
	}

}
