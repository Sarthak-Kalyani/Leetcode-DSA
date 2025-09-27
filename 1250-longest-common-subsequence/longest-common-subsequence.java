class Solution {
    private Integer[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        memo = new Integer[text1.length()][text2.length()];
        return solve(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    private int solve(String text1, String text2, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int result;
        
        if (text1.charAt(i) == text2.charAt(j)) {
            result = 1 + solve(text1, text2, i - 1, j - 1);
        } else {
            result = Math.max(
                solve(text1, text2, i - 1, j), 
                solve(text1, text2, i, j - 1)
            );
        }

        memo[i][j] = result;
        return result;
    }
}