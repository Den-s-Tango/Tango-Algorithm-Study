import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, cheese;
    static int[][] map;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int melt() {

        int sec = 0;
        int[][] newMap;
        while (cheese > 0) {

            newMap = findCheese();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (newMap[i][j] >= 2) {
                        map[i][j] = 0;
                        cheese -= 1;
                    }
                }
            }
            
            sec += 1;
        }

        return sec;
    }

    static int[][] findCheese() {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new int[]{0, 0});
        visited[0][0] = true;

        int[][] newMap = new int[N][M];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (!isBound(ni, nj) || visited[ni][nj]) {
                    continue;
                }

                if (map[ni][nj] == 1) {
                    newMap[ni][nj] += 1;
                    continue;
                }

                visited[ni][nj] = true;
                q.add(new int[]{ni, nj});
            }
        }

        return newMap;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = 0;
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if (num == 1) {
                    cheese += 1;
                }
            }
        }

        int result = melt();
        System.out.println(result);
    }
}