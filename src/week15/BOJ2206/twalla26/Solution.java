import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int bfs(int[][] map) {

        Queue<int[]> q = new LinkedList<>();
        int[][][] dist = new int[N][M][2]; // 0: clean, 1: broken

        q.add(new int[]{0, 0, 0});
        dist[0][0][0] = 1;

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int i = cur[0];
            int j = cur[1];
            int broken = cur[2];

            if (i == N - 1 && j == M - 1) {
                return dist[i][j][broken];
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (!isBound(ni, nj)) {
                    continue;
                }

                if (map[ni][nj] == 0) { // 벽이 아닐 때
                    if (dist[ni][nj][broken] == 0) { // 방문한 적 없다면
                        dist[ni][nj][broken] = dist[i][j][broken] + 1;
                        q.add(new int[]{ni, nj, broken});
                    }
                }

                else if (map[ni][nj] == 1 && broken == 0) { // 벽인데 내가 아직 안 부순 상태일 때
                    if (dist[ni][nj][1] == 0) { // 방문한 적 없다면
                        dist[ni][nj][1] = dist[i][j][0] + 1;
                        q.add(new int[]{ni, nj, 1});
                    }
                }
            }
        }

        return -1;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }
    
    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = bfs(map);
        System.out.println(result);
    }
}