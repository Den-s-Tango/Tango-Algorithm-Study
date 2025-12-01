
public class Solution {

    static Reader rd = new Reader();
    static int N, M, ANSWER;
    static int[][] board;

    static int[][][] tetrominoes = {
        // ㅡ모양
        {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
        {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
        
        // ㅁ모양
        {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
        
        // L모양
        {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
        {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
        {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
        {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
        {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
        {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
        {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
        {{0, 2}, {1, 0}, {1, 1}, {1, 2}},
        
        // ㅗ모양
        {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
        {{0, 1}, {1, 1}, {2, 1}, {1, 0}},
        {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
        {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
        
        // z모양
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
        {{1, 0}, {0, 1}, {1, 1}, {0, 2}},
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
        {{1, 0}, {2, 0}, {0, 1}, {1, 1}}  
    };

    public static void main(String[] args) throws Exception {
        N = rd.nextInt(); M = rd.nextInt();
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = rd.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] tetromino: tetrominoes) {
                    int sum = 0;
                    for (int d = 0; d < 4; d++) {
                        int[] pos = tetromino[d];

                        int dx = i + pos[0];
                        int dy = j + pos[1];
                        if (-1 < dx && dx < N && -1 < dy && dy < M) {
                            sum += board[dx][dy];
                        } else {
                            break;
                        }

                        if (d == 3) ANSWER = Math.max(ANSWER, sum);
                    }
                }
            }
        }

        System.out.println(ANSWER);
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