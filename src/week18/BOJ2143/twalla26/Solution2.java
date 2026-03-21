import java.io.*;
import java.util.*;

public class Solution2 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int T, N, M;
    static int[] A, B;

    static long solution() {

        List<Integer> sumA = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }

        List<Integer> sumB = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }

        Collections.sort(sumA);
        Collections.sort(sumB);

        long answer = 0;
        int left = 0;
        int right = sumB.size() - 1;

        while (left < sumA.size() && right >= 0) {
            int curA = sumA.get(left);
            int curB = sumB.get(right);
            int sum = curA + curB;

            if (sum == T) {

                int countA = 0;
                int countB = 0;

                while (left < sumA.size() && sumA.get(left) == curA) {
                    countA += 1;
                    left += 1;
                }

                while (right >= 0 && sumB.get(right) == curB) {
                    countB += 1;
                    right -= 1;
                }

                answer += (long) countA * countB;

            } else if (sum > T) {
                right -= 1;
            } else {
                left += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }   

        long result = solution();
        System.out.println(result);
    }
}