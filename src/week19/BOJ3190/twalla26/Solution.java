import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, K, L;
    static int[][] board;
    static HashMap<Integer, Character> turn;

    static int[] di = new int[]{0, 1, 0, -1};
    static int[] dj = new int[]{1, 0, -1, 0};

    static int solution() {

        int curD = 0;
        int sec = 1;

        int i = 1, j = 1;
        board[i][j] = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        while (true) {

            i += di[curD];
            j += dj[curD];
 
            if (!isBound(i, j) || board[i][j] == 1) {
                return sec;
            }
            
            if (board[i][j] != 2) {
                int[] tail = q.poll();
                board[tail[0]][tail[1]] = 0;
            }

            board[i][j] = 1;
            q.add(new int[]{i, j});

            if (turn.containsKey(sec)) {

                char nd = turn.get(sec);
                if (nd == 'L') {
                    curD = (curD + 3) % 4;
                } else {
                    curD = (curD + 1) % 4;
                }
            }

            sec += 1;
        }
    }

    static boolean isBound(int i, int j) {
        return (0 < i && i <= N) && (0 < j && j <= N);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        board = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            board[r][c] = 2;
        }

        L = Integer.parseInt(br.readLine());

        turn = new HashMap<>();
        for (int i = 0; i < L; i++) {
            String[] directionInfo = br.readLine().split(" ");
            int time = Integer.parseInt(directionInfo[0]);
            char direction = directionInfo[1].charAt(0);

            turn.put(time, direction);
        }

        int result = solution();
        System.out.println(result);
    }
}