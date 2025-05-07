package bj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 옷의 이름과 종류가 주어짐
 * 종류마다 하나씩 밖에 못입음
 * 해빈이가 발가벗지 않고, 옷을 입는 경우의 수
 * 청바지 바지
 * 반바지 바지
 * 후드티 상의
 * 반팔티 상의
 * 나시티 상의
 * 경우의 수
 *
 * 청바지 - NULL
 * 반바지 - NULL
 * 후드티 - NULL
 * 반팔티 - NULL
 * 나시티 - NULL
 * 청바지 - 후드티
 * 청바지 - 반팔티
 * 청바지 - 나시티
 * 반바지 - 후드티
 * 반바지 - 반팔티
 * 반바지 - 나시티
 * 11개
 * */

public class BJ_S3_9375_패션왕신해빈_모든조합 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static HashMap<String, ArrayList<String>> map;
    static ArrayList<ArrayList<String>> allCombinations;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(input.readLine());
            map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                tokens = new StringTokenizer(input.readLine());
                String name = tokens.nextToken();
                String kind = tokens.nextToken();
                if (map.containsKey(kind)) {
                    ArrayList<String> temp = map.get(kind);
                    temp.add(name);
                    map.put(kind, temp);
                } else {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(name);
                    map.put(kind, temp);
                }
            }
            allCombinations = new ArrayList<>();
            ArrayList<String> clothingTypes = new ArrayList<>(map.keySet());

            solution(clothingTypes, 0, new ArrayList<>(), allCombinations);
            System.out.println(allCombinations.size());
            System.out.println("옷 종류 조합의 개수 : " + allCombinations.size());
            for(List<String> combo : allCombinations) {
                System.out.println(combo);
            }

        }
    }

    private static void solution(ArrayList<String> clothingTypes, int typeIdx,
                                 ArrayList<String> currentCombination, ArrayList<ArrayList<String>> allCombinations) {
        if (clothingTypes.size() == typeIdx) {
            // 현재 조합이 비어있지 않으면 (옷을 최소한 하나라도 골랐으면), 결과 리스트에 추가
            // currentCombination을 그대로 넣으면 나중에 값이 바뀌니까, 새로운 ArrayList로 복사해서 넣어줘야 해!
            if (!currentCombination.isEmpty()) {
                allCombinations.add(new ArrayList<>(currentCombination));
            }
            return; // 재귀 호출 끝!
        }
        // 재귀 : typeIdx에 해당하는 옷 종류를 가져와
        String currentType = clothingTypes.get(typeIdx);// 현재 보고 있는 옷 종류
        ArrayList<String> clothes = map.get(currentType);// 해당 종류의 옷 목록

        // Option 1: 현재 옷 종류에서 하나의 옷을 선택
        // 해당 종류의 모든 옷에 대해 반복
        for (String cloth : clothes) {
            currentCombination.add(cloth);// 현재 옷을 조합에 추가
            // 다음 옷 종류로 넘어가서 재귀 호출
            solution(clothingTypes, typeIdx + 1, currentCombination, allCombinations);
            // 백트래킹: 현재 추가했던 옷을 다시 제거해서, 다른 선택지 탐색이나 이 종류를 건너뛰는 경우를 고려할 수 있게 함
            currentCombination.remove(currentCombination.size() - 1); // 다시 고려 안하게 마지막꺼 제거
        }
        // Option 2: 현재 옷 종류의 옷을 아무것도 선택하지 않음
        // currentCombination에 아무것도 추가하지 않은 채로 다음 옷 종류로 넘어가서 재귀 호출
        solution(clothingTypes, typeIdx + 1, currentCombination, allCombinations);
    }
}
