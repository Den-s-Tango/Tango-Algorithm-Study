import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int M, N, H;
    static int target, current;
    static int[][][] boxes;
    static boolean[][][] visited;

    static Queue<int[]> q;

    static int[] dm = {0, 0, -1, 1, 0, 0};
    static int[] dn = {0, 0, 0, 0, -1, 1};
    static int[] dh = {-1, 1, 0, 0, 0, 0};

    static void bfs() {

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int m = cur[0];
            int n = cur[1];
            int h = cur[2];

            for (int i = 0; i < 6; i++) {
                int nm = m + dm[i];
                int nn = n + dn[i];
                int nh = h + dh[i];

                if (!isBound(nm, nn, nh)) {
                    continue;
                }

                if (boxes[nh][nn][nm] == 0) {
                    boxes[nh][nn][nm] = boxes[h][n][m] + 1;
                    q.add(new int[]{nm, nn, nh});
                }
            }
        }
        
    }

    static boolean isBound(int m, int n, int h) {
        return (0 <= m && m < M) && (0 <= n && n < N) && (0 <= h && h < H);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        target = M * N * H;
        current = 0;
        boxes = new int[H][N][M];

        q = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    int num = Integer.parseInt(st.nextToken());
                    boxes[h][n][m] = num;

                    if (num == -1) {
                        target -= 1;
                    } else if (num == 1) {
                        current += 1;
                        q.add(new int[]{m, n, h});
                    }
                }
            }
        }

        if (target == current) {
            System.out.println(0);
            return;
        }

        bfs();

        int max = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (boxes[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, boxes[h][n][m]);
                }
            }
        }
        
        System.out.println(max - 1);
    }
}