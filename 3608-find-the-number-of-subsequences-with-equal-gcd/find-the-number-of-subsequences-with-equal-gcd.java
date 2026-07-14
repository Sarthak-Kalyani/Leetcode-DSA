class Solution {
    static final int MOD = 1_000_000_007;
    int[] nums;
    int n;
    Integer[][][] dp;
    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;
        dp = new Integer[n + 1][201][201];
        return solve(0, 0, 0);
    }
    private int solve(int i, int g1, int g2){
        if (i == n){
            return (g1 != 0 && g2 != 0 && g1 == g2) ? 1 : 0;
        }
        if (dp[i][g1][g2] != null){
            return dp[i][g1][g2];
        }
        long ans = solve(i + 1, g1, g2);
        int ng1 = (g1 == 0) ? nums[i] : gcd(g1, nums[i]);
        ans += solve(i + 1, ng1, g2);
        int ng2 = (g2 == 0) ? nums[i] : gcd(g2, nums[i]);
        ans += solve(i + 1, g1, ng2);
        ans %= MOD;
        return dp[i][g1][g2] = (int) ans;
    }
    private int gcd(int a, int b){
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}