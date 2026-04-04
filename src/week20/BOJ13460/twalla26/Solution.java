import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static char[][] board;
    static int[] hole;
    static int min = 11;
    static int[] di = new int[]{-1, 0, 1, 0}; // up left down right
    static int[] dj = new int[]{0, -1, 0, 1};
    static boolean redWin, blueWin, redMoved, blueMoved;

    static void solution(int[] red, int[] blue, int cnt) {

        printBoard(red, blue, cnt);

        if (cnt > 10 || cnt >= min) {
            return;
        }

        int up = (red[0] < blue[0] ? 1 : 0);
        int left = (red[1] < blue[1] ? 1 : 0);

        int[] nred, nblue;
        for (int d = 0; d < 4; d++) {

            System.out.println(d);

            redWin = false;
            blueWin = false;
            redMoved = false;
            blueMoved = false;

            if ((d == 0 && up == 1) ||
                (d == 1 && left == 1) || 
                (d == 2 && up == 0) ||
                (d == 3 && left == 0)) {
                    nred = move(red, blue, true, d);
                    nblue = move(nred, blue, false, d);
                } else {
                    nblue = move(red, blue, false, d);
                    nred = move(red, nblue, true, d);
                }

            if (!redMoved && !blueMoved) {
                continue;
            }

            if (blueWin) {
                continue;
            }

            if (redWin) {
                min = Math.min(min, cnt);
                return;
            }

            solution(nred, nblue, cnt + 1);
        }
    }

    static void printBoard(int[] red, int[] blue, int cnt) {
        sb.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == red[0] && j == red[1]) {
                    sb.append('R');
                } else if (i == blue[0] && j == blue[1]) {
                    sb.append('B');
                } else {
                    sb.append(board[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
        sb.setLength(0);
    }

    static int[] move(int[] red, int[] blue, boolean isRed, int d) {

        int bi, bj;
        if (isRed) {
            bi = red[0];
            bj = red[1];
        } else {
            bi = blue[0];
            bj = blue[1];
        }

        int ni, nj;
        while (true) {
            ni = bi + di[d];
            nj = bj + dj[d];

            if (board[ni][nj] == '#') {
                break;
            } 

            if (isRed && ni == blue[0] && nj == blue[1]) {
                break;
            } else if (!isRed && ni == red[0] && nj == red[1]) {
                break;
            }

            if (isRed) {
                redMoved = true;
            } else {
                blueMoved = true;
            }

            if (board[ni][nj] == 'O') {
                if (isRed) {
                    redWin = true;
                } else {
                    blueWin = true;
                }
                return new int[]{-1, -1};
            }

            bi = ni;
            bj = nj;
        }

        return new int[]{bi, bj};
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        int[] red = new int[2];
        int[] blue = new int[2];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char input = line.charAt(j);
                board[i][j] = input;

                if (input == 'R') {
                    red = new int[]{i, j};
                    board[i][j] = '.';
                } else if (input == 'B') {
                    blue = new int[]{i, j};
                    board[i][j] = '.';
                } else if (input == 'O') {
                    hole = new int[]{i, j};
                }
            }
        }

        solution(red, blue, 1);

        if (min > 10)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}