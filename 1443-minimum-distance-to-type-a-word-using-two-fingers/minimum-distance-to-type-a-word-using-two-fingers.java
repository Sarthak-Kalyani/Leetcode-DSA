class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        int[] dp = new int[26];
        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            int cost = dist(curr, next);
            total += cost;

            for (int j = 0; j < 26; j++) {
                dp[curr] = Math.max(dp[curr], dp[j] + cost - dist(j, next));
            }
        }
        int maxSave = 0;
        for (int val : dp) {
            maxSave = Math.max(maxSave, val);
        }
        return total - maxSave;
    }
    private int dist(int a, int b) {
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}