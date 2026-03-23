class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
        }
        
        int width = 0, length = 0;
        for (int i = 0; i < sizes.length; i++) {
            width = Math.max(width, sizes[i][0]);
            length = Math.max(length, sizes[i][1]);
        }
        
        answer = width * length;
        
        return answer;
    }
}