import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, min;
    static List<int[]> cctvs;
    static int[] di = new int[]{-1, 0, 1, 0};
    static int[] dj = new int[]{0, -1, 0, 1};

    static int solution(int[][] room) {

        min = N * M;

        dfs(0, room);

        return min;
    }

    static void dfs(int index, int[][] room) {

        if (index == cctvs.size()) {
            min = Math.min(min, cntBlindSpot(room));
            return;
        }

        int[] cctv = cctvs.get(index);
        int ci = cctv[0];
        int cj = cctv[1];

        int[][] xroom;
        if (room[ci][cj] == 1) {
            for (int d = 0; d < 4; d++) {
                xroom = watch(ci, cj, d, room);
                dfs(index + 1, xroom);
            }
        } else if (room[ci][cj] == 2) {
            for (int d = 0; d < 2; d++) {
                xroom = watch(ci, cj, d, room);
                xroom = watch(ci, cj, d + 2, xroom);
                dfs(index + 1, xroom);
            }
        } else if (room[ci][cj] == 3) {
            for (int d = 0; d < 4; d++) {
                xroom = watch(ci, cj, d, room);
                xroom = watch(ci, cj, (d + 1) % 4, xroom);
                dfs(index + 1, xroom);
            }
        } else if (room[ci][cj] == 4) {
            for (int d = 0; d < 4; d++) {
                xroom = watch(ci, cj, d, room);
                xroom = watch(ci, cj, (d + 1) % 4, xroom);
                xroom = watch(ci, cj, (d + 2) % 4, xroom);
                dfs(index + 1, xroom);
            }
        } else {
            xroom = room;
            for (int d = 0; d < 4; d++) {
                xroom = watch(ci, cj, d, xroom);
            }
            dfs(index + 1, xroom);
        }
    }

    static int[][] watch(int i, int j, int d, int[][] room) {

        int[][] xroom = new int[N][M];
        for (int n = 0; n < N; n++) {
            xroom[n] = room[n].clone();
        }

        int ni = i, nj = j;
        while (true) {
            ni += di[d];
            nj += dj[d];

            if (!isBound(ni, nj) || room[ni][nj] == 6) {
                break;
            }

            if (0 < room[ni][nj] && room[ni][nj] < 6) {
                continue;
            }

            xroom[ni][nj] = -1;
        }

        return xroom;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    static int cntBlindSpot(int[][] nroom) {

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M;j ++) {
                if (nroom[i][j] == 0) {
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] room = new int[N][M];
        cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                room[i][j] = num;

                if (0 < num && num < 6) {
                    cctvs.add(new int[]{i, j});
                }
            }
        }

        int result = solution(room);
        System.out.println(result);
        
    }
}