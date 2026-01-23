import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, numOfHouse, numOfChicken;
    static List<List<Integer>> houses, chickens;
    static int min = Integer.MAX_VALUE;

    static void combination(boolean[] visited, int start, int cnt) {
        if (cnt == M) {
            getDistance(visited);
            return;
        }

        for (int i = start; i < numOfChicken; i++) {
            visited[i] = true;
            combination(visited, i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    static void getDistance(boolean[] visited) {

        int houseI, houseJ, chickenI, chickenJ;

        int total = 0;
        for (int i = 0; i < numOfHouse; i++) {
            houseI = houses.get(i).get(0);
            houseJ = houses.get(i).get(1);

            int d = Integer.MAX_VALUE;
            for (int j = 0; j < numOfChicken; j++) {
                if (!visited[j]) {
                    continue;
                }
                chickenI = chickens.get(j).get(0);
                chickenJ = chickens.get(j).get(1);

                d = Math.min(d, Math.abs(chickenI - houseI) + Math.abs(chickenJ - houseJ));
            }
            total += d;
        }
        min = Math.min(min, total);
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        List<Integer> list;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int info = Integer.parseInt(st.nextToken());

                if (info == 1) {
                    list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    houses.add(list);
                } else if (info == 2) {
                    list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    chickens.add(list);
                }
            }
        } 

        numOfHouse = houses.size();
        numOfChicken = chickens.size();

        boolean[] visited = new boolean[numOfChicken];
        combination(visited, 0, 0);

        System.out.println(min);
    }
}