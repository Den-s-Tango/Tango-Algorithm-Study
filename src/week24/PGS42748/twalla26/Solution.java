import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int N = array.length;
        int M = commands.length;
        
        int[] answer = new int[M];
        
        for (int i = 0; i < M; i++) {
            
            int[] command = commands[i];
            
            int s = command[0];
            int e = command[1];
            int index = command[2];
            
            int[] temp = Arrays.copyOfRange(array, s - 1, e);
            Arrays.sort(temp);
            answer[i] = temp[index - 1];
        }
        
        return answer;
    }
}