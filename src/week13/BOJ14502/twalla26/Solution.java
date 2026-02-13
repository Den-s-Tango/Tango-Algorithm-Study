import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, max;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};
    static List<List<Integer>> viruses;

    static void backtracking(int[][] map, int start, int cnt) {

        if (cnt == 3) {
            int[][] copy = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                copy[i] = map[i].clone();
            }

            for (List<Integer> virus : viruses) {
                infect(copy, virus.get(0), virus.get(1));
            }

            max = Math.max(max, getSafety(copy));

            return;
        }

        for (int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            if (map[r][c] != 0) {
                continue;
            }

            map[r][c] = 1;
            backtracking(map, i + 1, cnt + 1);
            map[r][c] = 0;
        }
    }

    static void infect(int[][] map, int i, int j) {

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (!isBound(ni, nj)) {
                continue;
            }

            if (map[ni][nj] != 0) {
                continue;
            } 

            map[ni][nj] = 2;
            infect(map, ni, nj);
        }
    }

    static int getSafety(int[][] map) {

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        viruses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(line[j]);
                map[i][j] = num;

                if (num == 2) {
                    List<Integer> virus = new ArrayList<>();
                    virus.add(i);
                    virus.add(j);
                    viruses.add(virus);
                }
            }
        }

        backtracking(map, 0, 0);
        System.out.println(max);
    }
}