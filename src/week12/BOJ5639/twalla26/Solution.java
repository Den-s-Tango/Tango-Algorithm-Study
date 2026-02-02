import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static List<Integer> tree;

    static void postOrder(int start, int end) {

        if (start > end) {
            return;
        }

        int root = tree.get(start);
        int mid = start;

        while (mid <= end) {
            if (tree.get(mid) > root) {
                break;
            }

            mid += 1;
        }

        postOrder(start + 1, mid - 1);
        postOrder(mid, end);

        sb.append(root).append("\n");        
    }

    public static void main(String[] args) throws Exception {

        tree = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            tree.add(Integer.parseInt(line));
        }

        postOrder(0, tree.size() - 1);
        System.out.println(sb);
    }
}