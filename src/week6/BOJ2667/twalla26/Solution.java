import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, complex;
    static int[] di, dj;
    static int[][] map;
    static boolean[][] visited;
    static HashMap<Integer, Integer> result;

    static void solution() {

        di = new int[]{-1, 0, 1, 0};
        dj = new int[]{0, 1, 0, -1};

        result = new HashMap<>();

        complex = 101;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    findComplex(i, j);
                    complex += 1;
                }
            }
        }
        
        int size = result.size();
        sb.append(size).append("\n");

        List<Integer> values = new ArrayList<>(result.values());
        Collections.sort(values);
        for (int i = 0; i < size; i++) {
            sb.append(values.get(i)).append("\n");
        }
        
        System.out.println(sb);
    }



    static void findComplex(int i, int j) {

        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][N];

        q.add(new int[]{i, j});
        visited[i][j] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            map[cur[0]][cur[1]] = complex;
            cnt += 1;

            int ni, nj;
            for (int d = 0; d < 4; d++) {
                ni = cur[0] + di[d];
                nj = cur[1] + dj[d];

                if (!isBound(ni, nj) || visited[ni][nj]) {
                    continue;
                }

                if (map[ni][nj] == 0) {
                    continue;
                }

                q.add(new int[]{ni, nj});
                visited[ni][nj] = true;
            }
        }

        result.put(complex, cnt);
    }
    
    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < N);
    }


    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        solution();
    }
}