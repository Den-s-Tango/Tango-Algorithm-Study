import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int dijkstra(int N, int[][] cave) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Node(0, 0, cave[0][0]));
        visited[0][0] = true;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (cur.i == N - 1 && cur.j == N - 1) {
                return cur.rupee;
            }

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if (!isBound(ni, nj, N) || visited[ni][nj]) {
                    continue;
                }

                pq.add(new Node(ni, nj, cur.rupee + cave[ni][nj]));
                visited[ni][nj] = true;
            }
        }

        return -1;
    }

    static boolean isBound(int i, int j, int N) {
        return (0 <= i && i < N) && (0 <= j && j < N);
    }

    public static void main(String[] args) throws Exception {

        int problem = 1;
        while (true) {

            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int minRupee = dijkstra(N, cave);
            sb.append("Problem ").append(problem).append(": ").append(minRupee).append("\n");
            problem += 1;
        }

        System.out.println(sb);
    }
}

class Node implements Comparable<Node> {

    int i;
    int j;
    int rupee;

    public Node(int i, int j, int rupee) {
        this.i = i;
        this.j = j;
        this.rupee = rupee;
    }

    @Override
    public int compareTo(Node o) {
        return this.rupee - o.rupee;
    }
}