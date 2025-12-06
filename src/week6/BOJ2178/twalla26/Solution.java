import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] di, dj;
    static int[][] maze;

    static void bfs() {

        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[N][M];

        q.add(new int[]{0, 0});
        dist[0][0] = 1;

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            if (ci == N - 1 && cj == M - 1) {
                System.out.println(dist[ci][cj]);
                return;
            }

            int ni, nj;
            for (int d = 0; d < 4; d++) {
                ni = ci + di[d];
                nj = cj + dj[d];

                if (!isBound(ni, nj)) {
                    continue;
                }

                if (maze[ni][nj] == 0) {
                    continue;
                }

                if (dist[ni][nj] != 0) {
                    continue;
                }

                dist[ni][nj] = dist[ci][cj] + 1;
                q.add(new int[]{ni, nj});
            }
        }
    }
    
    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        di = new int[]{0, 1, 0, -1};
        dj = new int[]{1, 0, -1, 0};

        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            for (int m = 0; m < M; m++) {
                maze[n][m] = line.charAt(m) - '0';
            }
        }

        bfs();
    }
}