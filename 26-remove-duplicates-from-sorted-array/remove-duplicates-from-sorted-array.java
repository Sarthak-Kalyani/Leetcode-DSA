class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0; // empty array edge case

        int k = 1; // first unique element is always at index 0

        // start from second element and compare with previous
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]; // place the unique element at index k
                k++;               // move k to next position
            }
        }

        return k; // number of unique elements
    }
}
