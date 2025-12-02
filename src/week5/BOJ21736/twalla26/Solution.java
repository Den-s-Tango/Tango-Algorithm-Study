import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int startI, startJ;
    static int cnt = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static String[][] campus;
    static boolean[][] visited;

    static void findI() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (campus[i][j].equals("I")) {
                    startI = i;
                    startJ = j;
                }
            }
        }
    }

    static void dfs(int i, int j) {

        if (!isBound(i, j)) return;

        if (visited[i][j]) return;

        visited[i][j] = true;

        if (campus[i][j].equals("X")) return;

        if (campus[i][j].equals("P"))
            cnt += 1;
    
        int ni, nj;
        for (int d = 0; d < 4; d++) {
            ni = i + dx[d];
            nj = j + dy[d];

            dfs(ni, nj);
        }
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N && 0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        campus = new String[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            campus[i] = line.split("");
        }

        findI();

        visited = new boolean[N][M];
        dfs(startI, startJ);

        if (cnt == 0) {
            System.out.println("TT");
        } else {
            System.out.println(cnt);
        }
    }
}