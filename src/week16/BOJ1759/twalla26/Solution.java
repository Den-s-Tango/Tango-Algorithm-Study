import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int L, C;
    static char[] arr;
    static char[] result;

    static void backtracking(int index, int length) {

        if (length == L) {
            if (checkCondition(result)) {
                for (int i = 0; i < L; i++) {
                    sb.append(result[i]);
                }
                sb.append("\n");
            } 

            return;
        }

        for (int i = index; i < C; i++) {
            result[length] = arr[i];
            backtracking(i + 1, length + 1);
        }
    }

    static boolean checkCondition(char[] result) {

        int consonant = 0, vowel = 0;
        for (int i = 0; i < L; i++) {
            switch (result[i]) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    vowel += 1;
                    break;
                default:
                    consonant += 1;
            }
        }

        if (consonant >= 2 && vowel >= 1) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        result = new char[L];
        backtracking(0, 0);
        System.out.println(sb);
    }
}