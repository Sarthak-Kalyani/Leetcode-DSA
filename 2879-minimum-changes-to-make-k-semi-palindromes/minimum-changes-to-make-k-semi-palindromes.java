class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                cost[i][j] = Integer.MAX_VALUE;
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;
                    int changes = 0;
                    for (int r = 0; r < d; r++) {
                        int l = i + r;
                        int right = i + r + (len / d - 1) * d;
                        while (l < right) {
                            if (s.charAt(l) != s.charAt(right)) changes++;
                            l += d;
                            right -= d;
                        }
                    }
                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }
        int inf = 1000000000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, inf);
        dp[0][0] = 0;
        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int t = 2 * (p - 1); t <= i - 2; t++) {
                    dp[p][i] = Math.min(dp[p][i], dp[p - 1][t] + cost[t][i - 1]);
                }
            }
        }
        return dp[k][n];
    }
}