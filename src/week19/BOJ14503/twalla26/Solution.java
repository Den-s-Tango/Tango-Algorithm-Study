import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, r, c, d;
    static int[] dr = new int[]{-1, 0, 1, 0}; // 북 서 남 동
    static int[] dc = new int[]{0, -1, 0, 1};
    static int[][] room;

    static int solution() {

        int cnt = 0;
        while (true) {

            if (room[r][c] == 0) {
                room[r][c] = 2;
                cnt += 1;
            }

            boolean findDirty = false;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!isBound(nr, nc)) {
                    continue;
                }

                if (room[nr][nc] == 0) {
                    findDirty = true;
                }
            }

            if (findDirty) {
                d = (d + 1) % 4;

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (room[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                }

                continue;
            } 

            int nr = r - dr[d];
            int nc = c - dc[d];

            if (room[nr][nc] == 1) {
                break;
            }

            r = nr;
            c = nc;
        }

        return cnt;
    }

    static boolean isBound(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        if (d == 1) {
            d = 3;
        } else if (d == 3) {
            d = 1;
        }

        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();
        System.out.println(result);
    }
}