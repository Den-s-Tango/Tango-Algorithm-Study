import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, L, R;
    static int[][] land, border;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int solution() {

        HashMap<Integer, Integer> population;

        int result = 0;
        while (true) {

            border = new int[N][N];
            population = new HashMap<>();
            boolean changed = false;

            int country = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (border[i][j] == 0) {
                        int avg = bfs(i, j, country);
                        population.put(country, avg);
                        country += 1;
                    }
                }
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (land[i][j] != population.get(border[i][j])) {
                        land[i][j] = population.get(border[i][j]);
                        changed = true;
                    }
                }
            }

            if (!changed) {
                break;
            }

            result += 1;
        }

        return result;
    }

    static int bfs(int i, int j, int country) {

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{i, j});
        border[i][j] = country;

        int num = 1, sum = land[i][j];

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 4; d++) {

                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (!isBound(ni, nj)) {
                    continue;
                }

                int diff = Math.abs(land[ci][cj] - land[ni][nj]);
                if (border[ni][nj] == 0 && L <= diff && diff <= R) {
                    border[ni][nj] = country;
                    q.add(new int[]{ni, nj});
                    num += 1;
                    sum += land[ni][nj];
                }
            }
        }

        return sum / num;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < N);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        land = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();
        System.out.println(result);
    }
}