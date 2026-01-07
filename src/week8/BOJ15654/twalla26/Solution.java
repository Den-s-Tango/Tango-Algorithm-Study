import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N, M;
    static int[] inputArr, arr;
    static boolean[] visited;

    static void dfs(int length) {

        if (length == M) {
            printArr();
            return;
        }

        for (int i = 0; i < N; i++) {
            Integer target = inputArr[i];

            if (visited[i]) {
                continue;
            }

            arr[length] = target;
            visited[i] = true;
            dfs(length + 1);
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

        inputArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputArr);

        arr = new int[M];
        visited = new boolean[N];
        dfs(0);

        System.out.println(sb);
    }
}