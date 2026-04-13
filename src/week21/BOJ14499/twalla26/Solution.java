import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, x, y, K;
    static int[] orders;
    static int[][] map, dice;

    static void solution() {

        for (int i = 0; i < K; i++) {

            int d = orders[i];

            if (!isBound(d)) {
                continue;
            }

            roll(d);

            if (map[x][y] == 0) {
                map[x][y] = dice[3][1];
            } else {
                dice[3][1] = map[x][y];
                map[x][y] = 0;
            }

            int up = dice[1][1];
            sb.append(up).append("\n");
        }
    }

    static boolean isBound(int d) {

        int ni = x, nj = y;
        if (d == 1) {
            nj += 1;
        } else if (d == 2) {
            nj -= 1;
        } else if (d == 3) {
            ni -= 1;
        } else {
            ni += 1;
        }

        return (0 <= ni && ni < N) && (0 <= nj && nj < M);
    }

    static void roll(int d) {
        int temp = dice[1][1];
        if (d == 1) {
            dice[1][1] = dice[1][0];
            dice[1][0] = dice[3][1];
            dice[3][1] = dice[1][2];
            dice[1][2] = temp;
            y += 1;
        } else if (d == 2) {
            dice[1][1] = dice[1][2];
            dice[1][2] = dice[3][1];
            dice[3][1] = dice[1][0];
            dice[1][0] = temp;
            y -= 1;
        } else if (d == 3) {
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
            dice[3][1] = dice[0][1];
            dice[0][1] = temp;
            x -= 1;
        } else {
            dice[1][1] = dice[0][1];
            dice[0][1] = dice[3][1];
            dice[3][1] = dice[2][1];
            dice[2][1] = temp;
            x += 1;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        orders = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[4][3];

        solution();
        System.out.println(sb);
    }
}