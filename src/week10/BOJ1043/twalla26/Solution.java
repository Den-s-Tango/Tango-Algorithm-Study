import java.io.*;
import java.util.*;

public class Solution {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int[] parent;

    static int solution(int N, int M, List<List<Integer>> parties) {

        for (List<Integer> party : parties) {
            int prev = -1;
            for (int people : party) {
                if (prev > 0) {
                    union(prev, people);
                } 
                prev = people;
            }
        }

        int cnt = 0;
        for (List<Integer> party : parties) {
            for (int people : party) {
                if (find(people) != 0) {
                    cnt += 1;
                    break;
                }
            }
        }

        return cnt;
    }

    static int find(int node) {

        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    static void union(int u, int v) {

        int fu = find(u);
        int fv = find(v);

        if (fu == fv) {
            return;
        } else if (fu < fv) {
            parent[fv] = fu;
        } else {
            parent[fu] = fv;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int numOfTruth = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numOfTruth; i++) {
            int truth = Integer.parseInt(st.nextToken());
            parent[truth] = 0;
        }

        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int numOfPeople = Integer.parseInt(st.nextToken());
            parties.add(new ArrayList<>());
            for (int j = 0; j < numOfPeople; j++) {
                parties.get(i).add(Integer.valueOf(st.nextToken()));
            }
        }

        int result = solution(N, M, parties);
        System.out.println(result);
    }
}