import java.io.*;
import java.util.*;

public class Solution2 {

    // 이분 탐색으로 구현

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int solution(int N, int[] A) {

        int[] arr = new int[N];
        arr[0] = A[0];
        int len = 1;
        for (int i = 1; i < N; i++) {
            if (A[i] > arr[len - 1]) {
                arr[len] = A[i];
                len += 1;
                continue;
            }
            int targetIndex = binarySearch(arr, len, A[i]);
            arr[targetIndex] = A[i];
        }

        return len;
    }

    static int binarySearch(int[] arr, int len, int target) {
        int low = 0;
        int high = len;

        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution(N, A);
        System.out.println(result);
    }
}