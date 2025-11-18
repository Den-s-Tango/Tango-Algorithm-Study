import java.util.*;

public class Solution {

    static Reader rd = new Reader();
    static int M, N, H;
    static int[][][] box;
    static Queue<Point> queue = new LinkedList<>();

    static int[] dh = {-1, 1, 0, 0, 0, 0};
    static int[] dn = {0, 0, 0, 0, -1, 1};
    static int[] dm = {0, 0, -1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        M = rd.nextInt();
        N = rd.nextInt();
        H = rd.nextInt();

        box = new int[H][N][M];
        int unripeCount = 0;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    int state = rd.nextInt();
                    box[h][n][m] = state;
                    if (state == 1) {
                        queue.add(new Point(h, n, m));
                    } else if (state == 0) {
                        unripeCount++;
                    }
                }
            }
        }

        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        int days = 0;
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            
            days = box[p.h][p.n][p.m];

            for (int i = 0; i < 6; i++) {
                int nextH = p.h + dh[i];
                int nextN = p.n + dn[i];
                int nextM = p.m + dm[i];

                if (nextH >= 0 && nextH < H && nextN >= 0 && nextN < N && nextM >= 0 && nextM < M) {
                    if (box[nextH][nextN][nextM] == 0) {
                        box[nextH][nextN][nextM] = days + 1;
                        queue.add(new Point(nextH, nextN, nextM));
                    }
                }
            }
        }

        int maxDay = 0;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    maxDay = Math.max(maxDay, box[h][n][m]);
                }
            }
        }

        System.out.println(maxDay - 1);
    }

    static class Point {
        int h, n, m;

        Point(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }

    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
            boolean neg = c == '-' ? true : false;
            if (neg)
                c = read();
            do
                n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return neg ? -n : n;
        }
        
        char nextChar() throws Exception {
            byte c;
            while ((c = read()) <= 32) ;
            return (char) c;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0)
                    buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}