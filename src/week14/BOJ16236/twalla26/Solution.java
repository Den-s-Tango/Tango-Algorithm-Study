import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static Node shark;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};
    static int[][] ocean;

    static int solution() {

        int sec = 0;
        int cntOfEat = 0;

        while (true) {

            Node fish = findFish();

            if (fish.i == -1) {
                break;
            }

            ocean[fish.i][fish.j] = 0;

            cntOfEat += 1;
            if (cntOfEat == shark.size) {
                shark.size += 1;
                cntOfEat = 0;
            }

            shark.i = fish.i;
            shark.j = fish.j;
            sec += fish.time;
        }

        return sec;
    }

    static Node findFish() {

        Node fish = new Node(-1, -1, 0);

        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        Node start = new Node(shark.i, shark.j, 0);
        q.add(start);
        visited[shark.i][shark.j] = true;

        while (!q.isEmpty()) {

            Node cur = q.poll();
            int i = cur.i;
            int j = cur.j;
            int time = cur.time;

            if (0 < ocean[i][j] && ocean[i][j] < shark.size) {
                fish.i = i;
                fish.j = j;
                fish.time = time;
                return fish;
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];

                if (!isBound(ni, nj) || visited[ni][nj]) {
                    continue;
                }

                if (ocean[ni][nj] > shark.size) {
                    continue;
                }

                visited[ni][nj] = true;
                Node next = new Node(ni, nj, ocean[ni][nj]);
                next.time = time + 1;
                q.add(next);
            }
        }

        return fish;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < N);
    }
    
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        ocean = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                ocean[i][j] = num;

                if (num == 9) {
                    shark = new Node(i, j, 2);
                    ocean[i][j] = 0;
                }
            }
        }
        
        int sec = solution();
        System.out.println(sec);
    }
}

class Node implements Comparable<Node> {
    int i;
    int j;
    int size;
    int time = 0;

    public Node(int i, int j, int size) {
        this.i = i;
        this.j = j;
        this.size = size;
    }

    @Override
    public int compareTo(Node o) {
        if (this.time == o.time) {
            if (this.i == o.i) {
                return this.j - o.j;
            }
            return this.i - o.i;
        }
        return this.time - o.time;
    }
}