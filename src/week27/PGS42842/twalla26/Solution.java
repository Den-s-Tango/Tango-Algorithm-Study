class Solution {
    public int[] solution(int brown, int yellow) {
        
        for (int i = 1; i <= yellow; i++) {
            
            if (yellow % i != 0) {
                continue;
            }
            
            int width = Math.max(i, yellow / i);
            int height = Math.min(i, yellow / i);
            
            int outline = (width + 1) * 2 + (height + 1) * 2;
            
            if (outline == brown) {
                return new int[]{width + 2, height + 2};
            }
        }
        
        return new int[]{};
    }
}