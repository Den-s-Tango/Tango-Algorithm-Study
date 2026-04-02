import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] solution(int N, int[] arr) {

        int[] cache = new int[N];
        int[] trace = new int[N];
        Arrays.fill(cache, 1);
        Arrays.fill(trace, -1);

        int maxLength = 1;
        int maxIndex = 0;
        for (int i = 1; i < N; i++) {
            int cur = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < cur && cache[i] < cache[j] + 1) {
                    cache[i] = cache[j] + 1;
                    trace[i] = j;
                }
            }

            if (maxLength < cache[i]) {
                maxLength = cache[i];
                maxIndex = i;
            }
        }

        // for (int i = 0; i < N; i++) {
        //     sb.append(cache[i]).append(" ");
        // }
        // sb.append("\n");

        // System.out.println(sb);
        // sb.setLength(0);

        Stack<Integer> stack = new Stack<>();
        int curIndex = maxIndex;

        while (curIndex != -1) {
            stack.push(arr[curIndex]);
            curIndex = trace[curIndex];
        }

        int[] result = new int[maxLength];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i] = stack.pop();
            i += 1;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = solution(N, arr);
        
        int length = result.length;
        sb.append(length).append("\n");
        for (int i = 0; i < length; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }
}