import java.io.*;
import java.util.*;

public class Solution1 {

    // TreeSet으로 구현

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static TreeSet<Integer> set;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        set = new TreeSet<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            Integer ceil = set.ceiling(cur);
            if (ceil != null) {
                set.remove(ceil);
            }

            set.add(cur);
        }
        
        System.out.println(set.size());
    }
}