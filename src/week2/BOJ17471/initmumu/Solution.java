import java.util.*;

public class Solution {

    public static int N, TOTAL_POPULATION, ANSWER = 0x7f7f7f7f;
    public static int[] populations;
    public static List[] adj;

    public static void dfs(int idx, int curr, int state) {
        if (idx == N-1) return;

        if (check(state) && check(state ^ ((1 << N) - 1))) {
            int remainPopulation = TOTAL_POPULATION - curr;
            int diff = Math.abs(remainPopulation - curr);

            ANSWER = Math.min(ANSWER, diff);
        }

        int next = idx + 1;
        int nextPopulation = populations[next];
        int nextState = state | (1 << next);
        dfs(next, curr + nextPopulation, nextState);
        dfs(next, curr, state);
    }

    public static boolean check(int state) {
        List<Integer> remains = getRemainCities(state);
        
        if (remains.size() == N) return false;

        Queue<Integer> que = new LinkedList<>();
        que.add(remains.get(0));

        Set<Integer> remainSet = new HashSet<>(remains);
        remainSet.remove(que.peek());

        boolean[] vst = new boolean[N];
        vst[que.peek()] = true;

        while (!que.isEmpty()) {
            int city = que.poll();

            List<Integer> edges = adj[city];

            for (int edge: edges) {
                if (vst[edge] || !remainSet.contains(edge)) continue;

                que.add(edge);
                remainSet.remove(edge);
                vst[edge] = true;
            }
        }

        return remainSet.isEmpty();
    }

    public static List<Integer> getRemainCities(int state) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int tmp = state & (1 << i);
            if (tmp == 0) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        N = read();
        populations = new int[N];
        adj = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            populations[i] = read();
            TOTAL_POPULATION += populations[i];
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N; i++) {
            int cnt = read();
            for (int j = 0; j < cnt; j++) {
                int node = read() - 1;
                adj[i].add(node);
            }
        }

        dfs(-1, 0, 0);

        System.out.println((ANSWER!=0x7f7f7f7f) ? ANSWER : -1);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}