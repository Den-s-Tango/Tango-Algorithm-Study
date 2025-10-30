import java.util.*;

public class Solution {
    public static int N, M, K;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int solution(boolean[][] grid) {
        boolean[][] visited = new boolean[N][M];

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || (grid[i][j] == false)) continue;

                Queue<int[]> que = new LinkedList<>();
                que.add(new int[] {i, j});
                visited[i][j] = true;
                
                while(!que.isEmpty()) {
                    int[] node = que.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = node[0] + dx[d];
                        int ny = node[1] + dy[d];

                        if (!isBound(nx,ny) || visited[nx][ny] || grid[nx][ny] == false) continue;

                        visited[nx][ny] = true;
                        que.add(new int[] {nx, ny});
                    }
                }
                ++res;
            }
        }

        return res;
    }

    public static boolean isBound(int x, int y) {
        return -1 < x && x < N && -1 < y && y < M;
    }

    public static void main(String[] args) throws Exception {
        int TC = read();
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            N = read();
            M = read();
            K = read();
            
            boolean[][] bat = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                int x = read();
                int y = read();

                bat[x][y] = true;
            } 
            sb.append(solution(bat)).append("\n");
        }

        System.out.println(sb);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}