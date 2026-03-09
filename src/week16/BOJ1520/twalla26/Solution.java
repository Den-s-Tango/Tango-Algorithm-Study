import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;
    
    static int M, N;
    static int[][] map, cache;
    static int[] dy = new int[]{-1, 0, 0, 1};
    static int[] dx = new int[]{0, -1, 1, 0};

    static int dfs(int m, int n) {

        if (m == M - 1 && n == N - 1)
            return 1;

        if (cache[m][n] != -1) 
            return cache[m][n];
        
        cache[m][n] = 0;
        for (int d = 0; d < 4; d++) {
            int ny = m + dy[d];
            int nx = n + dx[d];

            if (!isBound(ny, nx)) {
                continue;
            }

            if (map[m][n] > map[ny][nx]) {
                cache[m][n] += dfs(ny, nx);
            }
        }

        return cache[m][n];
    }

    static boolean isBound(int y, int x) {
        return (0 <= y && y < M) && (0 <= x && x < N);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cache = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(cache[i], -1);
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }
}