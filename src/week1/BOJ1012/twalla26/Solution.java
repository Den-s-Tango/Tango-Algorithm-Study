import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int M, N;
    static int[][] field;

    static int solution() {
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] == 1) {
                    visit(i, j);
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    static void visit(int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isBound(nx, ny))
                continue;

            if (field[nx][ny] == 1) {
                field[nx][ny] = 0;
                visit(nx, ny);
            }
        }
    }

    static boolean isBound(int x, int y) {
        if (x < 0 || M - 1 < x)
            return false;

        if (y < 0 || N - 1 < y)
            return false;

        return true;
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            field = new int[M][N];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                field[x][y] = 1;
            }

            int result = solution();
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}