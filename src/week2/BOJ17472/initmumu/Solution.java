import java.util.*;

public class Solution {

    static Reader rd = new Reader();

    static int N, M, INF = 0x7f7f7f7f;
    static int[][] grid, adjMatrix;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    
    static int[] parents;

    static List[] adj;

    static class Edge {
        int src, dest, cost;

        public Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        N = rd.nextInt();
        M = rd.nextInt();

        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = rd.nextInt();
            }
        }

        int idx = 0; boolean[][] vst = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vst[i][j] || grid[i][j] == 0) continue;
                numberingIsland(i, j, ++idx, vst);
            }
        }

        adjMatrix = new int[idx+1][idx+1];
        for (int i = 1; i <= idx; i++) {
            Arrays.fill(adjMatrix[i], INF);
            adjMatrix[i][i] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 0) continue;
                
                int curr = grid[i][j];
                for (int d = 0; d < 4; d++) {
                    int[] res = goAndFind(i, j, d, curr);

                    if (res[0] == -1) continue;

                    int other = res[0];
                    int cost = res[1];

                    if (cost < adjMatrix[curr][other]) {
                        adjMatrix[curr][other] = cost;
                        adjMatrix[other][curr] = cost;
                    }
                }
            }
        }
        adj = convertMatrixToList(adjMatrix);

        parents = new int[idx+1];
        for (int i = 1; i <= idx; i++) parents[i] = i;
        
        System.out.println(kruskal(idx));

    }

    public static int kruskal(int cnt) {
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int i = 1; i <= cnt ; i++) {
            pq.addAll(adj[i]);
        }

        int i = 1;
        int res = 0;
        while (i < cnt) {
            if (pq.isEmpty()) return -1;
            Edge curr = pq.poll();

            if (find(curr.dest) != find(curr.src)) {
                i++;
                res += curr.cost;
                union(curr.src, curr.dest);
            }
        }

        return res;
    }

    public static void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);

        if (n1 == n2) {}
        else if (n1 < n2) parents[n2] = n1;
        else parents[n1] = n2;
    }

    public static int find(int n) {
        if (n == parents[n]) return n;

        return find(parents[n]);
    }

    public static List[] convertMatrixToList(int[][] mat) {
        List[] list = new List[mat.length];
        for (int i = 1; i < mat.length; i++) list[i] = new ArrayList<Edge>();

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat.length; j++) {
                if (mat[i][j] == INF || mat[i][j] == 0) continue;

                list[i].add(new Edge(i, j, mat[i][j]));
            }
        }

        return list;
    }

    public static int[] goAndFind(int x, int y, int d, int idx) {
        int[] res = new int[2];

        int cnt = 0;
        while (true) {
            x += dx[d];
            y += dy[d];

            if (!isBound(x, y) || grid[x][y] == idx) break;

            ++cnt;
            if (grid[x][y] != 0 && grid[x][y] != idx) {
                if (cnt < 3) break;
                return new int[] {grid[x][y], cnt-1};
            }
        }

        return new int[] {-1, -1};
    }

    public static void numberingIsland(int x, int y, int idx, boolean[][] vst) {
        Queue<int[]> que = new LinkedList<>();
        vst[x][y] = true;
        que.add(new int[] {x, y});
        grid[x][y] = idx;

        while(!que.isEmpty()) {
            int[] node = que.poll();

            for (int d = 0; d < 4; d++) {
                int nx = node[0] + dx[d];
                int ny = node[1] + dy[d];

                if (!isBound(nx, ny) || grid[nx][ny] == 0 || vst[nx][ny]) continue;

                que.add(new int[] {nx, ny});
                vst[nx][ny] = true;
                grid[nx][ny] = idx;
            }
        }
    }

    public static boolean isBound(int x, int y) {
        return -1 < x && x < N && -1 < y && y < M;
    }

    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
            boolean neg = c == '-' ? true : false;
            if (neg)
                c = read();
            do
                n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return neg ? -n : n;
        }
        
        char nextChar() throws Exception {
            byte c;
            while ((c = read()) <= 32) ;
            return (char) c;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0)
                    buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}