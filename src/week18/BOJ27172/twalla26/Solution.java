import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] game(int N, boolean[] cards) {
        int[] result = new int[1_000_001];

        for (int i = 1; i < 1_000_001; i++) {
            
            if (!cards[i]) {
                continue;
            }

            int j = 2;
            while (true) {
                if (i * j > 1_000_000) {
                    break;
                }

                if (cards[i * j]) {
                    result[i] += 1;
                    result[i * j] -= 1;
                }

                j += 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] input = new int[N];
        boolean[] cards = new boolean[1_000_001];
        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(st.nextToken());
            cards[card] = true;
            input[i] = card;
        }

        int[] result = game(N, cards);

        for (int i = 0; i < N; i++) {
            sb.append(result[input[i]]).append(" ");
        }
        sb.append("\n");
        System.out.println(sb);
    }
}