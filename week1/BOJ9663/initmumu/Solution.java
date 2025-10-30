public class Solution {
    public static int N, ANSWER;
    public static boolean[] col, upper, lower;

    public static void dfs(int row) {
        if (row == N) {
            ANSWER++;
            return;
        }

        for (int i = 0; i < N; i++) {
            int upperIdx = getUpperIdx(row, i);
            int lowerIdx = getLowerIdx(row, i);
            if (col[i] || upper[upperIdx] || lower[lowerIdx]) continue;

            col[i] = true;
            upper[upperIdx] = true;
            lower[lowerIdx] = true;

            dfs(row+1);

            col[i] = false;
            upper[upperIdx] = false;
            lower[lowerIdx] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        N = read();
        col = new boolean[N];
        upper = new boolean[2 * N - 1];
        lower = new boolean[2 * N - 1];

        dfs(0);

        System.out.println(ANSWER);
    }

    private static int getLowerIdx(int x, int y) {
        return (N-1-x) + y;
    }

    private static int getUpperIdx(int x, int y) {
        return x + y;
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}