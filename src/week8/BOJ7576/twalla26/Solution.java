import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int M, N;
    static int[] dx, dy;
    static int[][] box;

    static void bfs() {

        Queue<int[]> q = new LinkedList<>();
        dx = new int[]{1, -1, 0, 0};
        dy = new int[]{0, 0, 1, -1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isBound(nx, ny)) {
                    continue;
                }

                if (box[nx][ny] == 0) {
                    box[nx][ny] = box[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    static boolean isBound(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                max = Math.max(box[i][j], max);
            }
        }

        System.out.println(max - 1);
    }
}