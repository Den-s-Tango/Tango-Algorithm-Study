package week1.BOJ5430.twalla26;

import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    static void solution(String p, int[] arr) {
        boolean isReversed = false;
        int start = 0;
        int end = arr.length;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == 'D') {
                if (isReversed)
                    end -= 1;
                else
                    start += 1;

                if (start > end) {
                    sb.append("error").append("\n");
                    return;
                }
            } else
                isReversed = !isReversed;
        }

        StringBuilder tempSb = new StringBuilder();
        tempSb.append("[");
        if (start != end) {
            if (isReversed) {
                for (int i = end - 1; i >= start; i--)
                    tempSb.append(arr[i] + ",");
            } else {
                for (int i = start; i < end; i++)
                    tempSb.append(arr[i] + ",");
            }
            tempSb.deleteCharAt(tempSb.length() - 1);
        }
        tempSb.append("]");

        sb.append(tempSb).append("\n");
    }

    static int[] toArr(int n, String str) {
        String[] strArr = str.substring(1, str.length() - 1).split(",");

        int[] newArr = new int[n];
        for (int i = 0; i < n; i++)
            newArr[i] = Integer.parseInt(strArr[i]);

        return newArr;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();

            int[] arr = toArr(n, str);
            solution(p, arr);
        }

        System.out.println(sb);
    }
}
