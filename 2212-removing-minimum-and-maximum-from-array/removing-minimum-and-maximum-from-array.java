class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        // if there is only one element
        if (n == 1)
            return 1;

        // variables to store index of smallest and largest number
        int minIndex = 0;
        int maxIndex = 0;

        // find where minimum and maximum elements are
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // make sure minIndex is always before maxIndex
        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // case 1: remove elements only from front
        int deleteFromFront = maxIndex + 1;

        // case 2: remove elements only from back
        int deleteFromBack = n - minIndex;

        // case 3: remove some from front (for min) and some from back (for max)
        int deleteFromBoth = (minIndex + 1) + (n - maxIndex);

        // return the smallest number of deletions among the three
        return Math.min(deleteFromFront, Math.min(deleteFromBack, deleteFromBoth));
    }
}
