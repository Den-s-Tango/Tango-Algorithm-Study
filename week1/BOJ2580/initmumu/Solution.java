import java.util.*;

public class Solution {
    public static int[][] board = new int[10][10];
    public static StringBuilder sb = new StringBuilder();
    public static boolean STOP_FLAG = false;
    public static List<int[]> emptyPos = new ArrayList<>();
    public static boolean[][] usedRow = new boolean[9][10];
    public static boolean[][] usedCol = new boolean[9][10];
    public static boolean[][] usedSquare = new boolean[9][10];

    public static void bt(int x, int y, int idx, int num) {
        if (STOP_FLAG) return;

        if (emptyPos.size() == idx) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            STOP_FLAG = true;
            return;
        }

        int[] nextPos = emptyPos.get(idx);

        int nx = nextPos[0];
        int ny = nextPos[1];

        for (int i = 1; i < 10; i++) {
            if (usedRow[nx][i] || usedCol[ny][i] || usedSquare[square(nx, ny)][i]) continue;
            
            board[nx][ny] = i;
            usedRow[nx][i] = true;
            usedCol[ny][i] = true;
            usedSquare[square(nx, ny)][i] = true;
            
            bt(nx, ny, idx+1, i);

            board[nx][ny] = 0;
            usedRow[nx][i] = false;
            usedCol[ny][i] = false;
            usedSquare[square(nx, ny)][i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int curr = read();
                board[i][j] = curr;

                if (curr == 0) emptyPos.add(new int[] {i, j});
                else {
                    usedRow[i][curr] = true;
                    usedCol[j][curr] = true;
                    usedSquare[square(i, j)][curr] = true;
                }
            }
        }

        int[] pos = emptyPos.get(0);
        int x = pos[0];
        int y = pos[1];
        for (int i = 1; i < 10; i++) {
            if (usedRow[x][i] || usedCol[y][i] || usedSquare[square(x, y)][i]) continue;

            board[x][y] = i;
            usedRow[x][i] = true;
            usedCol[y][i] = true;
            usedSquare[square(x, y)][i] = true;

            bt(x, y, 1, i);

            board[x][y] = 0;
            usedRow[x][i] = false;
            usedCol[y][i] = false;
            usedSquare[square(x, y)][i] = false;
        }

        System.out.println(sb);
    }

    private static int square(int x, int y) {
        return 3* (x / 3) + (y / 3);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}