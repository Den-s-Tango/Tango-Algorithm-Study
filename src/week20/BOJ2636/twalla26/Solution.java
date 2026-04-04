import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[][] board;
    static int cheese, meltAmt, hour;

    static int[] di = new int[]{-1, 0, 1, 0};
    static int[] dj = new int[]{0, -1, 0, 1};

    static void solution() {
        
        hour = 0;
        while (true) {
            hour += 1;

            meltAmt = melt();
            cheese -= meltAmt;
            
            if (cheese <= 0) {
                return;
            } 
        }
    }

    static int melt() {

        int[][] nboard = new int[N][M];
        for (int i = 0; i < N; i++) {
            nboard[i] = board[i].clone();
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        int cnt = 0;
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

                if (board[ni][nj] == 1) {
                    if (nboard[ni][nj] == 1) {
                        nboard[ni][nj] = 0;
                        cnt += 1;
                    }
                    continue;
                }

                q.add(new int[]{ni, nj});
                visited[ni][nj] = true;
            }
        }

        board = nboard;

        return cnt;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        cheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                board[i][j] = input;

                if (input == 1) {
                    cheese += 1;
                }
            }
        }


        solution();
        System.out.println(hour);
        System.out.println(meltAmt);
    }
}