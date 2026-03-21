import java.io.*;
import java.util.*;

public class Solution1 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int T, N, M;
    static int[] A, B;

    static long solution() {

        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();

        for (int leftA = 0; leftA < N; leftA++) {
            int sumA = 0;
            for (int rightA = leftA; rightA < N; rightA++) {
                sumA += A[rightA];

                if (mapA.containsKey(sumA)) {
                    mapA.put(sumA, mapA.get(sumA) + 1);
                } else {
                    mapA.put(sumA, 1);
                }
            }
        }

        for (int leftB = 0; leftB < M; leftB++) {
            int sumB = 0;
            for (int rightB = leftB; rightB < M; rightB++) {
                sumB += B[rightB];

                if (mapB.containsKey(sumB)) {
                    mapB.put(sumB, mapB.get(sumB) + 1);
                } else {
                    mapB.put(sumB, 1);
                }
            }
        }

        long result = 0;
        for (int sumA : mapA.keySet()) {
            int sumB = T - sumA;
            if (mapB.containsKey(sumB)) {
                result += (long) mapA.get(sumA) * mapB.get(sumB);
            }
        }

        return result;
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