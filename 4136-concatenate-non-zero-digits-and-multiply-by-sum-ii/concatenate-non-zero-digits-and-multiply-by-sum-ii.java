class Solution {
    static final int MOD = 1000000007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digit = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                pos.add(i);
                digit.add(c - '0');
            }
        }
        int m = digit.size();
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
        long[] prefNum = new long[m + 1];
        long[] prefSum = new long[m + 1];
        for (int i = 0; i < m; i++) {
            prefNum[i + 1] = (prefNum[i] * 10 + digit.get(i)) % MOD;
            prefSum[i + 1] = prefSum[i] + digit.get(i);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++){
            int l = queries[i][0];
            int r = queries[i][1];
            int left = lowerBound(pos, l);
            int right = upperBound(pos, r);
            if (left == right){
                ans[i] = 0;
                continue;
            }
            int len = right - left;
            long number = (prefNum[right] - prefNum[left] * pow10[len]) % MOD;
            if (number < 0) number += MOD;
            long sum = prefSum[right] - prefSum[left];
            ans[i] = (int)((number * sum) % MOD);
        }
        return ans;
    }
    private int lowerBound(ArrayList<Integer> arr, int target){
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    private int upperBound(ArrayList<Integer> arr, int target){
        int l = 0, r = arr.size();
        while (l < r){
            int mid = (l + r) / 2;
            if (arr.get(mid) > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}