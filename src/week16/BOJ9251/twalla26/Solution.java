import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int dp(String A, String B) {

        int lengthA = A.length();
        int lengthB = B.length();

        int[][] cache = new int[lengthA + 1][lengthB + 1];

        for (int i = 1; i < lengthA + 1; i++) {
            for (int j = 1; j < lengthB + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }

        return cache[lengthA][lengthB];
    }

    public static void main(String[] args) throws Exception {

        String A = br.readLine();
        String B = br.readLine();

        int result = dp(A, B);
        System.out.println(result);
        
    }
}