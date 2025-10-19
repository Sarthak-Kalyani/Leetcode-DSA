class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;

        // k could be greater than n, so take modulo
        k = k % n;

        if (k == 0) return; // no rotation needed

        // Step 1: reverse the whole array
        reverse(nums, 0, n - 1);

        // Step 2: reverse first k elements
        reverse(nums, 0, k - 1);

        // Step 3: reverse remaining n-k elements
        reverse(nums, k, n - 1);
    }

    // Helper function to reverse elements between start and end
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
