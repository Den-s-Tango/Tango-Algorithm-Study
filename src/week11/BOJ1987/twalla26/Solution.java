import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int R, C, max;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, -1, 1, 0};
    static char[][] board;
    static boolean[] visited;

    static void dfs(int i, int j, int cnt) {

        max = Math.max(max, cnt);

        int ni, nj;
        for (int d = 0; d < 4; d++) {

            ni = i + di[d];
            nj = j + dj[d];

            if (!isBound(ni, nj) || visited[board[ni][nj] - '0']) {
                continue;
            }

            visited[board[ni][nj] - '0'] = true;
            dfs(ni, nj, cnt + 1);
            visited[board[ni][nj] - '0'] = false;
        }
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < R) && (0 <= j && j < C);
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[1000];
        visited[board[0][0] - '0'] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }
}