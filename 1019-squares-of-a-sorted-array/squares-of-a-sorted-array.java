class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int squaredArr[] = new int[n];
        for (int i = 0; i<n; i++){
            squaredArr[i] = nums[i] * nums[i];
            }
            Arrays.sort(squaredArr);
            return squaredArr;
    }
}