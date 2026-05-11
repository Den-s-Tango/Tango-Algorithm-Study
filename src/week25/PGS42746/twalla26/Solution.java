import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        
        String answer = "";
        
        int N = numbers.length;
        String[] num = new String[N];
        
        for (int i = 0; i < N; i++) {
            num[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(num, (o1, o2) -> {
            return (o2 + o1).compareTo(o1 + o2);
        });
        
        if (num[0].equals("0")) {
            return "0";
        }
        
        for (int i = 0; i < N; i++) {
            answer += num[i];
        }
        
        return answer;
    }
}