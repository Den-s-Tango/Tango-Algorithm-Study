import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        
        List<Set<Integer>> cache = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cache.add(new HashSet<>());
        }
        
        if (N == number) {
            return 1;
        }
        
        cache.get(1).add(N);
        for (int i = 2; i < 9; i++) {
            
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += N * Math.pow(10, j);
            }
            cache.get(i).add(sum);
            
            for (int j = 1; j < i; j++) {
                int k = i - j;
                
                for (int num1: cache.get(j)) {
                    for (int num2: cache.get(k)) {
                        cache.get(i).add(num1 + num2);
                        cache.get(i).add(num1 - num2);
                        cache.get(i).add(num1 * num2);
                        if (num2 != 0)
                            cache.get(i).add(num1 / num2);
                    }
                }
            }
 
            if (cache.get(i).contains(number)) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}