import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int cnt = 1;
    static boolean[] visited;
    static int[] parents;
    static int[][] map;
    static HashMap<String, Integer> edges;

    static int solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    cnt += 1;
                    color(i, j, cnt);
                }
            }
        }

        visited = new boolean[cnt + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    makeBridge(i, j, map[i][j]);
                }
            }
        }

        int result = kruskal();
        return result;
    }

    static void color(int i, int j, int cnt) {
        if (!isBound(i, j)) {
            return;
        }

        if (map[i][j] == 1) {
            map[i][j] = cnt;
            color(i - 1, j, cnt);
            color(i, j + 1, cnt);
            color(i + 1, j, cnt);
            color(i, j - 1, cnt);
        }
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    static void makeBridge(int i, int j, int start) {
        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        int ti, tj;
        int length, dest;
        for (int d = 0; d < 4; d++) {
            ti = i + di[d];
            tj = j + dj[d];

            length = dest = 0;
            while (true) {
                if (!isBound(ti, tj) || map[ti][tj] == start) {
                    break;
                }

                if (map[ti][tj] != 0) {
                    dest = map[ti][tj];
                    break;
                }

                length += 1;
                ti += di[d];
                tj += dj[d];
            }

            if (length < 2 || dest == 0) {
                continue;
            }

            String edge;
            if (start < dest) {
                edge = start + "-" + dest;
            } else {
                edge = dest + "-" + start;
            }

            if (edges.containsKey(edge)) {
                if (edges.get(edge) > length) {
                    edges.put(edge, length);
                }
            } else {
                visited[start] = true;
                visited[dest] = true;
                edges.put(edge, length);
            }
        }
    }

    static int kruskal() {
        int cost = 0;
        int limit = cnt - 2;

        if (edges.size() < limit) {
            return -1;
        }

        for (int i = 2; i < cnt + 1; i++) {
            if (!visited[i]) {
                return -1;
            }
        }

        List<String> keySet = new ArrayList<>(edges.keySet());

        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return edges.get(o1).compareTo(edges.get(o2));
            }
        });

        parents = new int[cnt + 1];
        for (int i = 0; i < cnt + 1; i++) {
            parents[i] = i;
        }

        for (String key : keySet) {
            String[] edge = key.split("-");
            int start = Integer.parseInt(edge[0]);
            int dest = Integer.parseInt(edge[1]);

            if (find(start) != find(dest)) {
                union(start, dest);
                cost += edges.get(key);
                limit -= 1;
            }

            if (limit == 0) {
                break;
            }
        }

        if (limit != 0) {
            return -1;
        }

        return cost;
    }

    static int find(int n) {
        if (parents[n] != n) {
            parents[n] = find(parents[n]);
        }

        return parents[n];
    }

    static void union(int start, int dest) {
        int startP = find(start);
        int destP = find(dest);

        if (startP != destP) {
            parents[destP] = startP;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        edges = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();
        System.out.println(result);
    }
}