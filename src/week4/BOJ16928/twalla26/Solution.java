import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] board;
    static HashMap<Integer, Integer> ladders, snakes;

    static void solution() {

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        board[1] = 0; 
        
        int current, dest, next, cnt;
        while (!q.isEmpty()) {
            current = q.poll();
            cnt = board[current];

            if (current == 100) {
                break;
            }

            if (snakes.containsKey(current)) {
                dest = snakes.get(current);
                if (board[dest] > cnt) {
                    board[dest] = cnt;
                    q.add(dest);
                }
                continue;
            }

            if (ladders.containsKey(current)) {
                dest = ladders.get(current);
                if (board[dest] > cnt) {
                    board[dest] = cnt;
                    q.add(dest);
                }
            }

            for (int k = 1; k < 7; k++) {
                next = current + k;

                if (next > 100) {
                    break;
                }

                if (board[next] < cnt + 1) {
                    continue;
                }

                board[next] = cnt + 1;
                q.add(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ladders = new HashMap<>();
        snakes = new HashMap<>();

        int start, dest;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            ladders.put(start, dest);
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            snakes.put(start, dest);
        }

        board = new int[101];
        Arrays.fill(board, Integer.MAX_VALUE);

        solution();
        System.out.println(board[100]);
    }
}