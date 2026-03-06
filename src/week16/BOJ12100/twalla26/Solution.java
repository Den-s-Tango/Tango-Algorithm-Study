import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, result = 0;

    static void backtracking(int[][] board, int cnt) {

        result = Math.max(result, findMax(board));

        if (cnt == 5) {
            return;
        }

        backtracking(up(board), cnt + 1);
        backtracking(down(board), cnt + 1);
        backtracking(left(board), cnt + 1);
        backtracking(right(board), cnt + 1);
    }
    
    static int[][] up(int[][] board) {

        int[][] newBoard = new int[N][N]; 

        for (int j = 0; j < N; j++) {

            List<Integer> cur = new ArrayList<>();
            List<Integer> next = new ArrayList<>();

            // arr -> list
            for (int i = 0; i < N; i++) {
                if (board[i][j] > 0) {
                    cur.add(board[i][j]);
                }
            }

            // 같은 수 합치기
            for (int k = 0; k < cur.size(); k++) {
                if (k + 1 < cur.size() && cur.get(k).equals(cur.get(k + 1))) {
                    next.add(cur.get(k) * 2);
                    k += 1;
                } else {
                    next.add(cur.get(k));
                }
            }

            // list -> arr
            int ni = 0;
            for (int num : next) {
                newBoard[ni][j] = num;
                ni += 1;
            }
        }

        return newBoard;
    }

    static int[][] down(int[][] board) {

        int[][] newBoard = new int[N][N]; 

        for (int j = 0; j < N; j++) {

            List<Integer> cur = new ArrayList<>();
            List<Integer> next = new ArrayList<>();

            // arr -> list
            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] > 0) {
                    cur.add(board[i][j]);
                }
            }

            // 같은 수 합치기
            for (int k = 0; k < cur.size(); k++) {
                if (k + 1 < cur.size() && cur.get(k).equals(cur.get(k + 1))) {
                    next.add(cur.get(k) * 2);
                    k += 1;
                } else {
                    next.add(cur.get(k));
                }
            }

            // list -> arr
            int ni = N - 1;
            for (int num : next) {
                newBoard[ni][j] = num;
                ni -= 1;
            }
        }

        return newBoard;
    }

    static int[][] left(int[][] board) {

        int[][] newBoard = new int[N][N]; 

        for (int i = 0; i < N; i++) {

            List<Integer> cur = new ArrayList<>();
            List<Integer> next = new ArrayList<>();

            // arr -> list
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) {
                    cur.add(board[i][j]);
                }
            }

            // 같은 수 합치기
            for (int k = 0; k < cur.size(); k++) {
                if (k + 1 < cur.size() && cur.get(k).equals(cur.get(k + 1))) {
                    next.add(cur.get(k) * 2);
                    k += 1;
                } else {
                    next.add(cur.get(k));
                }
            }

            // list -> arr
            int nj = 0;
            for (int num : next) {
                newBoard[i][nj] = num;
                nj += 1;
            }
        }

        return newBoard;
    }

    static int[][] right(int[][] board) {

        int[][] newBoard = new int[N][N]; 
        
        for (int i = 0; i < N; i++) {

            List<Integer> cur = new ArrayList<>();
            List<Integer> next = new ArrayList<>();

            // arr -> list
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] > 0) {
                    cur.add(board[i][j]);
                }
            }

            // 같은 수 합치기
            for (int k = 0; k < cur.size(); k++) {
                if (k + 1 < cur.size() && cur.get(k).equals(cur.get(k + 1))) {
                    next.add(cur.get(k) * 2);
                    k += 1;
                } else {
                    next.add(cur.get(k));
                }
            }

            // list -> arr
            int nj = N - 1;
            for (int num : next) {
                newBoard[i][nj] = num;
                nj -= 1;
            }
        }

        return newBoard;
    }

    static int findMax(int[][] board) {

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }

        return max;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(board, 0);
        System.out.println(result);
    }
}