import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[][] maze;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];

        pq.add(new Node(0, 0, 0));
        visited[0][0] = true;

        Node cur = new Node(0, 0, 0);
        while (!pq.isEmpty()) {

            cur = pq.poll();
            int i = cur.i;
            int j = cur.j;
            int cntOfBreak = cur.cntOfBreak;

            if (i == N - 1 && j == M - 1) {
                return cur.cntOfBreak;
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (!isBound(ni, nj) || visited[ni][nj]) {
                    continue;
                }

                if (maze[ni][nj] == 1) {
                    pq.add(new Node(ni, nj, cntOfBreak + 1));
                } else {
                    pq.add(new Node(ni, nj, cntOfBreak));
                }

                visited[ni][nj] = true;
            }
        }

        return cur.cntOfBreak;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j ++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        int result = dijkstra();
        System.out.println(result);
    }
}

class Node implements Comparable<Node> {
    int i;
    int j;
    int cntOfBreak;

    public Node(int i, int j, int cntOfBreak) {
        this.i = i;
        this.j = j;
        this.cntOfBreak = cntOfBreak;
    }

    @Override
    public int compareTo(Node o) {
        return this.cntOfBreak - o.cntOfBreak;
    }
}