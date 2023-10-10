package bj.g5;

/**
@author 김영욱
@since 2023. 10, 09
@see https://www.acmicpc.net/problem/29792
@git
@performance 
@category #
@note

N개의 캐릭터가 있다.
여러 캐릭터로 보스를 효율적으로 잡기 위해, 하루에 한 캐릭터 당 최대 15분씩,
최대 M개의 캐릭터만 보스를 잡기로 하였다.

(M개의 캐릭터 => 조합)

캐릭터가 보스에게 데미지를 넣으면 보스의 체력이 데미지 만큼 감소하여, 보스의
체력이 0 이하가 되면 보스를 잡게 된다.
데미지 계산은 매초 이루어짐

캐릭터마다 주어진 15분 동안은 매초 일정한 데미지를 넣을 수 있다.
캐릭터마다 각 보스는 1회씩만 처치할 수 있으며, 다른 캐릭터가 잡은 보스라도,
현재 캐릭터가 잡지 않은 보스라면 잡을 수 있다.

보스 몬스터의 체력, 보상 메소 정보와 캐릭터의 데미지 정보가 주어질 때,
카오가 계획에 맞추어 하루에 보스를 잡아 얻을 수 있는 최대 메소를 구하자.

데미지 1이면 15분동안 넣는 딜 : 900

아니 그냥 dp가 미리 캐릭터가 얻을 수 있는 최대 메소를 다 구해 놓고
조합으로 캐릭터 뭐 선택할지 고른다음에
그 메소들 더한 것 중에서 가장 큰거 반환하면 되는거 아님?

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G5_29792_규칙적인보스돌이 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder builder = new StringBuilder();

	static int N, M, K;
	static long[] damage;
	static long[] V, H;
	static long[] dp;
	static long ans;
	static int forCombi[];
	static long mAns;
	static long bossMin = Long.MAX_VALUE;
	static PriorityQueue<Long> pq = new PriorityQueue<Long>(Collections.reverseOrder());

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		damage = new long[N+1];
		H = new long[K+1];
		V = new long[K+1];

		for (int i = 1; i <= N; i++) {
			damage[i] = Long.parseLong(input.readLine());
		}
		for (int i = 0; i < K; i++) {
			tokens = new StringTokenizer(input.readLine());
			H[i] = Long.parseLong(tokens.nextToken());
			V[i] = Long.parseLong(tokens.nextToken());
		}
		
		// dp테이블
		// [보스종류][시간] = 메소
		
		
	}

	// 캐릭터가 넣을 수 있는 총 딜량으로 얻을 수 있는 최대 메소를 반환
//	static void killBoss(long secDamage, long remainDamage, long meso, int bossIdx) {
//		// 가지치기 조건
//		if(remainDamage < bossMin || remainDamage <= secDamage*2) {
//			mAns = Math.max(mAns, meso);
//			return;
//		}
//		// 기저 조건
//		if(bossIdx == K) {
////			System.out.println(meso);
//			mAns = Math.max(mAns, meso);
//			return;
//		}
//		// 데미지 남나 안남나 계산
//		long newRemainDamage = remainDamage - bossList[bossIdx][0];
//		if(newRemainDamage >= 0) {
//			// 잡아
//			killBoss(secDamage, newRemainDamage, meso+bossList[bossIdx][1], bossIdx+1);
//		}
//		// 안잡아
//		killBoss(secDamage,  remainDamage, meso, bossIdx+1);
//	}


}
