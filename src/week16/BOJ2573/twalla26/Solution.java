import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[][] iceberg, cntOfIce;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int solution() {        

        int year = 0;
        while (hasIce()) {

            meltIce();
            year += 1;

            int cnt = 0;
            cntOfIce = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (iceberg[i][j] > cnt && cntOfIce[i][j] == 0) {
                        cnt += 1;
                        countIce(i, j, cnt);
                    }

                    if (cnt == 2) {
                        return year;
                    }
                }
            }
        }

        return 0;
    }

    static boolean hasIce() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (iceberg[i][j] > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    static void meltIce() {

        int[][] meltAmount = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (iceberg[i][j] > 0) {
                    int seaCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];

                        if (iceberg[ni][nj] == 0) {
                            seaCount += 1;
                        }
                    }
                    meltAmount[i][j] = seaCount;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (iceberg[i][j] > 0) {
                    iceberg[i][j] -= meltAmount[i][j];
                    if (iceberg[i][j] < 0)
                        iceberg[i][j] = 0;
                }
            }
        }
    }

    static void countIce(int i, int j, int cnt) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        cntOfIce[i][j] = cnt;

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int d = 0; d < 4; d++) {

                int ni = ci + di[d];
                int nj = cj + dj[d];

                if (iceberg[ni][nj] > 0 && cntOfIce[ni][nj] == 0) {
                    cntOfIce[ni][nj] = cnt;
                    q.add(new int[]{ni, nj});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceberg = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();
        System.out.println(result);
    }
}