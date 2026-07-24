class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];
        for (int x : nums){
            present[x] = true;
        }
        boolean[] dp = new boolean[2048];
        dp[0] = true;
        for (int step = 0; step < 3; step++){
            boolean[] next = new boolean[2048];
            for (int xr = 0; xr < 2048; xr++){
                if (!dp[xr]) continue;
                for (int v = 1; v < 2048; v++){
                    if (present[v]) {
                        next[xr ^ v] = true;
                    }
                }
            }
            dp = next;
        }
        int ans = 0;
        for (boolean ok : dp){
            if (ok) ans++;
        }
        return ans;
    }
}