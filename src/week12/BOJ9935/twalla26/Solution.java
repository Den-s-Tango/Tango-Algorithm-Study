import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static String slidingWindow(String str, String bomb) {
        
        int sbLength = sb.length();
        int bombLength = bomb.length();

        int cur = 0;
        while (cur < str.length()) {

            sb.append(str.charAt(cur));
            sbLength += 1;

            if (sb.charAt(sbLength - 1) == bomb.charAt(bombLength - 1)) {
                if (sbLength < bombLength) {
                    cur += 1;
                    continue;
                } else {
                    boolean isSame = true;
                    for (int i = 1; i <= bombLength; i++) {
                        if (sb.charAt(sbLength - i) != bomb.charAt(bombLength - i)) {
                            isSame = false;
                            break;
                        }
                    }

                    if (isSame) {
                        sb.delete(sbLength - bombLength, sbLength);
                        sbLength -= bombLength;
                    }
                }
            }
            cur += 1;
        }
        
        if (sbLength == 0) {
            return "FRULA";
        } else {
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        String str = br.readLine();
        String bomb = br.readLine();

        String result = slidingWindow(str, bomb);
        System.out.println(result);
    }
}