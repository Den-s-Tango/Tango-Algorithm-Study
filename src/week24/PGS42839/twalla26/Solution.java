import java.util.*;

class Solution {
    
    int N, cnt;
    HashSet<Integer> set;
    String[] arr;
    
    void backtracking(String cur, boolean[] visited) {
        
        if (!cur.equals("")) {
            set.add(Integer.parseInt(cur));
        }
                
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            backtracking(cur + arr[i], visited);
            visited[i] = false;
        }
    }
    
    int countPrime() {
        
        int cnt = 0;
        for (int elem : set) {
            if (elem < 2) {
                continue;
            }
            
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(elem); i++) {
                if (elem % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if (isPrime) {
                cnt += 1;
            }
        }
                
        return cnt;
    } 
    
    public int solution(String numbers) {
        
        N = numbers.length();
    
        arr = numbers.split("");
        set = new HashSet<>();
        
        boolean[] visited = new boolean[N];
        backtracking("", visited);
        int cnt = countPrime();
    
        return cnt;
    }
}