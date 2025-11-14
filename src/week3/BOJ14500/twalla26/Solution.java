import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[][] paper;

    static int[][][] tetromino = {
        // "-" 모양
        {{0, 0}, {0, 1,}, {0, 2}, {0, 3}},
        {{0, 0}, {1, 0}, {2, 0}, {3, 0}},

        // "ㅁ" 모양
        {{0, 0}, {0, 1}, {1, 0}, {1, 1}},

        // "L" 모양
        {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
        {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
        {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
        {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
        {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
        {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
        {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
        {{0, 2}, {1, 0}, {1, 1}, {1, 2}},

        // "ㅗ" 모양
        {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
        {{0, 1}, {1, 1}, {2, 1}, {1, 0}},
        {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
        {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
        
        // "z" 모양
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
        {{1, 0}, {0, 1}, {1, 1}, {0, 2}},
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
        {{1, 0}, {2, 0}, {0, 1}, {1, 1}}  
    };

    static int max;

    static void solution(int i, int j) {

        for (int t = 0; t < 19; t++) {
            int cur = 0;
            boolean complete = true;
            for (int cnt = 0; cnt < 4; cnt++) {
                int ni = i + tetromino[t][cnt][0];
                int nj = j + tetromino[t][cnt][1];

                if (!isBound(ni, nj)) {
                    complete = false;
                    break;
                }

                cur += paper[ni][nj];
            }
            
            if (complete) {
                max = Math.max(max, cur);
            }
        }
    }

    static boolean isBound(int i, int j) {
        return (0 <= i && i < N) && (0 <= j && j < M);
    }

    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                solution(i, j);
            }
        }

        System.out.println(max);
    }
}