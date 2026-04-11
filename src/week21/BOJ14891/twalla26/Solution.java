import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M, K;
    static boolean[] visited;
    static int[][] cogwheels, turns;

    static int solution() {

        for (int i = 0; i < K; i++) {
            int num = turns[i][0];
            int d = turns[i][1];

            visited = new boolean[N + 1];
            rotate(num, d);
            printCogwheels();
        }

        int score = getScore();
        return score;
    }

    static int getScore() {
        int score = 0;

        if (cogwheels[1][0] == 1) {
            score += 1;
        }

        if (cogwheels[2][0] == 1) {
            score += 2;
        }

        if (cogwheels[3][0] == 1) {
            score += 4;
        }

        if (cogwheels[4][0] == 1) {
            score += 8;
        }

        return score;
    }

    static void rotate(int num, int d) {

        visited[num] = true;

        if (num + 1 <= N && !visited[num + 1] && cogwheels[num][2] != cogwheels[num + 1][6]) {
            rotate(num + 1, -d);
        }

        if (num - 1 >= 1 && !visited[num - 1] && cogwheels[num - 1][2] != cogwheels[num][6]) {
            rotate(num - 1, -d);
        }

        int temp;
        if (d == -1) {
            temp = cogwheels[num][0];
            for (int i = 1; i < M; i++) {
                cogwheels[num][i - 1] = cogwheels[num][i];
            }
            cogwheels[num][M - 1] = temp;
        } else {
            temp = cogwheels[num][M - 1];
            for (int i = M - 1; i > 0; i--) {
                cogwheels[num][i] = cogwheels[num][i - 1];
            }
            cogwheels[num][0] = temp;
        }
    }

    static void printCogwheels() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(cogwheels[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        sb.setLength(0);
    }

    public static void main(String[] args) throws Exception {

        N = 4;
        M = 8;
        cogwheels = new int[N + 1][M];

        for (int i = 1; i < N + 1; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                cogwheels[i][j] = line.charAt(j) - '0';
            }
        }

        printCogwheels();

        K = Integer.parseInt(br.readLine());
        turns = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turns[i][0] = Integer.parseInt(st.nextToken());
            turns[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution();
        System.out.println(result);
    }
}