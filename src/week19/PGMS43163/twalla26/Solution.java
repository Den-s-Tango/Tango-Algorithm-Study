import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        
        int N = words.length;
        int L = begin.length();
        boolean[] visited = new boolean[N];
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));
        
        int answer = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (cur.str.equals(target)) {
                return cur.stage;
            }
            
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    continue;
                }
                
                String nextStr = words[i];
                int cnt = 0;
                for (int j = 0; j < L; j++) {
                    if (cur.str.charAt(j) != nextStr.charAt(j)) {
                        cnt += 1;
                    }
                    
                    if (cnt > 1) {
                        break;
                    }
                }
                
                if (cnt == 1) {
                    q.add(new Node(nextStr, cur.stage + 1));
                    visited[i] = true;
                }
            }
        }
        
        return answer;
    }
}

class Node {
    String str;
    int stage;
    
    public Node(String str, int stage) {
        this.str = str;
        this.stage = stage;
    }
}