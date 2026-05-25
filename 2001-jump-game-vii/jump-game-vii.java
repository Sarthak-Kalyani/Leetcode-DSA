class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        q.offer(0);
        vis[0] = true;
        int farthest = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (i == n - 1) {
                return true;
            }
            int start = Math.max(i + minJump, farthest + 1);
            int end = Math.min(i + maxJump, n - 1);
            for (int j = start; j <= end; j++) {
                if (s.charAt(j) == '0' && !vis[j]) {
                    vis[j] = true;
                    q.offer(j);
                }
            }
            farthest = end;
        }
        return false;
    }
}