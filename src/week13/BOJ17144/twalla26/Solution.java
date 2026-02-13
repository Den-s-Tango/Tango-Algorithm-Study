import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int R, C;
    static int purifier;
    static int[] di = new int[]{-1, 0, 0, 1};
    static int[] dj = new int[]{0, -1, 1, 0};

    static int purify(int[][] room, int T) {

        for (int t = 0; t < T; t++) {
            room = spreadDust(room);
            room = air(room);
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    result += room[i][j];
                }
            }
        }

        return result;
    }

    static int[][] spreadDust(int[][] room) {

        int[][] newRoom = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newRoom[i][j] += room[i][j];
                if (room[i][j] > 0) {
                    int amount = room[i][j];
                    int spread = amount / 5;

                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];

                        if (!isBound(ni, nj)) {
                            continue;
                        }

                        if (isPurifier(ni, nj)) {
                            continue;
                        }

                        newRoom[ni][nj] += spread;
                        newRoom[i][j] -= spread;
                    }
                }
            }
        }
        
        return newRoom;
    }

    static int[][] air(int[][] room) {

        int up, down, i, j;

        up = purifier;
        i = up;
        j = 0;

        while (i != 0 || j != 0) {
            i -= 1;
            if (isPurifier(i + 1, j)) {
                continue;
            }
            room[i + 1][j] = room[i][j];
        }

        while (i != 0 || j != C - 1) {
            j += 1;
            room[i][j - 1] = room[i][j];
        }

        while (i != up || j != C - 1) {
            i += 1;
            room[i - 1][j] = room[i][j];
        }

        while (i != up || j != 0) {
            j -= 1;
            if (isPurifier(i, j)) {
                room[i][j + 1] = 0;
                continue;
            }
            room[i][j + 1] = room[i][j];
        }

        down = purifier + 1;
        i = down;
        j = 0;

        while (i != R - 1 || j != 0) {
            i += 1;
            if (isPurifier(i - 1, j)) {
                continue;
            }
            room[i - 1][j] = room[i][j];
        }

        while (i != R - 1 || j != C - 1) {
            j += 1;
            room[i][j - 1] = room[i][j];
        }

        while (i != down || j != C - 1) {
            i -= 1;
            room[i + 1][j] = room[i][j];
        }

        while (i != down || j != 0) {
            j -= 1;
            if (isPurifier(i, j)) {
                room[i][j + 1] = 0;
                break;
            }
            room[i][j + 1] = room[i][j];
        }

        return room;
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < R) && (0 <= j && j < C);
    }

    static boolean isPurifier(int i, int j) {

        if (i == purifier && j == 0) {
            return true;
        } 
        
        if (i == purifier + 1 && j == 0) {
            return true;
        } 

        return false;
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
    
        int[][] room = new int[R][C];
        purifier = -1;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                room[i][j] = num;

                if (num == -1 && purifier == -1) {
                    purifier = i;
                }
            }
        }

        int result = purify(room, T);
        System.out.println(result);
    }
}