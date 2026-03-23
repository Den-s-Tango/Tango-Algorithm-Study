class Solution {
    
    int N, M, MOD = 1_000_000_007;
    int[][] cache;
    
    int dp() {
        
        cache[1][1] = 1;
        
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                
                if (cache[i][j] == -1) {
                    cache[i][j] = 0;
                    continue;
                }
                
                cache[i][j] = (cache[i - 1][j] + cache[i][j - 1]) % MOD;
            }
        }
        
        return cache[N][M];
    }
    
    public int solution(int m, int n, int[][] puddles) {
        
        N = n;
        M = m;
        cache = new int[n + 1][m + 1];
        
        for (int i = 0; i < puddles.length; i++) {
            
            if (puddles[0].length == 0) {
                break;
            }
            
            int pi = puddles[i][1];
            int pj = puddles[i][0];
            
            cache[pi][pj] = -1;
        }
        
        int result = dp();
        
        return result;
    }
}