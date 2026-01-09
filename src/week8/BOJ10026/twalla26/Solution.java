import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};
    static char[][] painting;
    static int[][] area;

    static void bfs(int i, int j) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        char target = painting[i][j];

        int[] pos;
        int x, y, nx, ny;
        while (!q.isEmpty()) {
            pos = q.poll();
            x = pos[0];
            y = pos[1];

            for (int d = 0; d < 4; d++) {
                nx = x + dx[d];
                ny = y + dy[d];

                if (!isBound(nx, ny)) {
                    continue;
                }

                if (area[nx][ny] == 1) {
                    continue;
                }

                if (painting[nx][ny] != target) {
                    continue;
                }

                q.add(new int[]{nx, ny});
                area[nx][ny] = 1;
            }
        }
    }

    static boolean isBound(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < N);
    }

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());

        painting = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                painting[i][j] = line.charAt(j);
            }
        }

        int cnt = 0;
        area = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (area[i][j] == 0) {
                    bfs(i, j);
                    cnt += 1;
                }
            }
        }
        sb.append(cnt).append(" ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (painting[i][j] == 'G') {
                    painting[i][j] = 'R';
                }
            }
        }

        cnt = 0;
        area = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (area[i][j] == 0) {
                    bfs(i, j);
                    cnt += 1;
                }
            }
        }
        sb.append(cnt);
        System.out.println(sb);
    }
}