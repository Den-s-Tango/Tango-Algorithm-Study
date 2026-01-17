import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] numbers;
    static int[] arr;
    static boolean[] visited;

    static void solution(int length) {

        if (length == M) {
            printArr();
            return;
        }

        int prev = 0;
        for (int i = 0; i < N; i++) {
            int num = numbers[i];

            if (visited[i]) {
                continue;
            }

            if (prev == num) {
                continue;
            } 

            prev = num;
            arr[length] = num;
            visited[i] = true;
            solution(length + 1);
            visited[i] = false;
        }
    }

    static void printArr() {
        for (int i = 0; i < M; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        arr = new int[M];
        visited = new boolean[N];
        solution(0);
        System.out.println(sb);
    }
}