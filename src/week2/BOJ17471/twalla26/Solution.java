import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int N;
    static int[] population;
    static List<List<Integer>> graph;

    static int min = Integer.MAX_VALUE;
    static boolean[] selected;

    static void solution(int idx) {

        if (idx < N + 1) {
            selected[idx] = true;
            solution(idx + 1);
            selected[idx] = false;
            solution(idx + 1);
            return;
        }

        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            if (selected[i]) {
                A.add(i);
            } else {
                B.add(i);
            }
        }

        if (A.isEmpty() || B.isEmpty()) {
            return;
        }

        if (isConnected(A) && isConnected(B)) {
            int sumA = getPopulation(A);
            int sumB = getPopulation(B);

            min = Math.min(min, Math.abs(sumA - sumB));
        }
    }

    static boolean isConnected(List<Integer> group) {
        
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        
        int start = group.get(0);

        q.add(start);
        visited[start] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> adjGroup = graph.get(cur);

            for (int i = 0; i < adjGroup.size(); i++) {

                int next = adjGroup.get(i);
                if (group.contains(next) && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    count += 1;
                }
            }
        }

        return count == group.size();
    }

    static int getPopulation(List<Integer> group) {

        int sum = 0;
        for (int i = 0; i < group.size(); i++) {
            sum += population[group.get(i)];
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                temp.add(Integer.parseInt(st.nextToken()));
            }
            graph.add(temp);
        }

        selected = new boolean[N + 1];

        solution(1);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}