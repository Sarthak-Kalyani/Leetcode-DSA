class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];
        for (int i = 0; i < items.length; i++) {
            int price = items[i][1];
            int free = 0;
            for (int j = 0; j < items.length; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) free++;
            }
            int[] old = dp.clone();
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
                dp[b] = Math.max(dp[b], old[b - price] + 1 + free);
            }
        }
        int ans = 0;
        for (int x : dp) ans = Math.max(ans, x);
        return ans;
    }
}