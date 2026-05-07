class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        int[] ans = new int[n];
        int start = 0;
        int max = nums[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            boolean split =
                (i == n - 1) ||
                (prefixMax[i] <= suffixMin[i + 1]);
            if (split) {
                for (int j = start; j <= i; j++) {
                    ans[j] = max;
                }
                start = i + 1;
                if (start < n) {
                    max = nums[start];
                }
            }
        }
        return ans;
    }
}