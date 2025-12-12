import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int n, m, startX, startY;
    static int[][] map, distance;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void solution() {

        bfs(startX, startY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dist = distance[i][j];

                if (map[i][j] == 0) {
                    dist = 0;
                }

                sb.append(dist).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int startX, int startY) {
        
        Queue<int[]> q = new LinkedList<>();
        distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], -1);
        }
        distance[startX][startY] = 0;

        q.add(new int[]{startX, startY});

        int[] current;
        int x, y, nx, ny;
        while (!q.isEmpty()) {
            current = q.poll();
            x = current[0];
            y = current[1];

            for (int d = 0; d < 4; d++) {
                nx = x + dx[d];
                ny = y + dy[d];

                if (!isBound(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    continue;
                }

                if (distance[nx][ny] > -1) {
                    continue;
                }

                distance[nx][ny] = distance[x][y] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }

    static boolean isBound(int x, int y) {
        return (0 <= x && x < n) && (0 <= y && y < m);
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if (num == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        solution();
    }
}