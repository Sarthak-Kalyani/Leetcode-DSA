class Solution {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] result = new int[n]; // array to store running sum

        int sum = 0; // variable to keep track of running sum
        for (int i = 0; i < n; i++) {
            sum += nums[i]; // add current number to sum
            result[i] = sum; // store the running sum in result array
        }

        return result; // return the final running sum array
    }
}
