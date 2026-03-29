import java.util.*;

class Solution {
    
    int[] parent;
    
    int find(int u) {
        if (parent[u] == u) {
            return u;
        }
        
        return parent[u] = find(parent[u]);
    }
    
    void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        
        if (pu != pv) {
            if (pu < pv) {
                parent[pv] = pu;
            } else {
                parent[pu] = pv;
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || computers[i][j] != 1) {
                    continue;
                }
                
                union(i, j);
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i));
        }
                
        return set.size();
    }
}