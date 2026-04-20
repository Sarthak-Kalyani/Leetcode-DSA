class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (colors[i] != colors[j]) {
                    max = Math.max(max, j - i);
                    break; // furthest for this i found
                }
            }
        }
        return max;
    }
}