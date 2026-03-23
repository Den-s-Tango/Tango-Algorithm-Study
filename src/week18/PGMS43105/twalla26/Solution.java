class Solution {
    public int solution(int[][] triangle) {
        
        int N = triangle.length;
        
        int[][] cache = new int[N][N];
        cache[0][0] = triangle[0][0];
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    cache[i][j] = cache[i - 1][j];
                } else if (j == i) {
                    cache[i][j] = cache[i - 1][j - 1];
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j - 1], cache[i - 1][j]);
                }
                cache[i][j] += triangle[i][j];
            }
        }
        
        int max = 0;
        for (int j = 0; j < N; j++) {
            max = Math.max(max, cache[N - 1][j]);
        }
        
        return max;
    }
}