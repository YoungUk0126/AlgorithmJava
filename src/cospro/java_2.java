package cospro;

public class java_2 {

    static int money = 100000;
    static int[][] cost = {{50, 5000}, {20, 1000}, {20, 5000}, {50, 1000}};
    static String[] name = {"A", "B", "C", "D"};

    public static void main(String[] args) {
        int ansIdx = 0;
        int ans = 0;

        for (int i = 0; i < cost.length; i++) {
            int oilTemp = money / cost[i][1];
            int disTemp = oilTemp * cost[i][0];
            if(disTemp > ans) {
                ans = disTemp;
                ansIdx = i;
            }
        }
        System.out.println(name[ansIdx]);
    }
}
