import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static char[][] map;
    static int[] di = new int[]{-1, 0, 1, 0};
    static int[] dj = new int[]{0, -1, 0, 1};

    static int solution() {

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    int dist = getDist(i, j);
                    max = Math.max(max, dist);
                }
            }
        }

        return max;
    }

    static int getDist(int i, int j) {

        int[] cur = new int[]{i, j, 0};

        Queue<int[]> q = new LinkedList<>();
        q.add(cur);

        boolean[][] visited = new boolean[N][M];
        visited[i][j] = true;

        while (!q.isEmpty()) {

            cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (!isBound(ni, nj) || visited[ni][nj] || map[ni][nj] == 'W') {
                    continue;
                }

                q.add(new int[]{ni, nj, cd + 1});
                visited[ni][nj] = true;
            }
        }

        return cur[2];
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int result = solution();
        System.out.println(result);
    }
}