class Solution {
    public int[] solution(int[] answers) {
        
        int[] score = new int[3];
        
        int[] guess0 = new int[]{1, 2, 3, 4, 5};
        int[] guess1 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] guess2 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        for (int i = 0; i < answers.length; i++) {
            int answer = answers[i];
            
            if (answer == guess0[i % 5]) {
                score[0] += 1;
            }
            
            if (answer == guess1[i % 8]) {
                score[1] += 1;
            }
            
            if (answer == guess2[i % 10]) {
                score[2] += 1;
            }
        }
        
        int max = 0;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, score[i]);
        }
        
        boolean[] result = new boolean[3];
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (score[i] == max) {
                result[i] = true;
                cnt += 1;
            }
        }
        
        int[] answer = new int[cnt];
        int idx = 0;
        for (int i = 1; i <= 3; i++) {
            if (result[i - 1]) {
                answer[idx] = i;
                idx += 1;
            }
        }
        
        return answer;
    }
}