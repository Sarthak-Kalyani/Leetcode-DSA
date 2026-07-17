class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;
        long[] cnt = new long[max + 1];

        for (int i = 1; i <= max; i++){
            long c = 0;
            for (int j = i; j <= max; j += i){
                c += freq[j];
            }
            cnt[i] = c * (c - 1) / 2;
        }

        long[] exact = new long[max + 1];
        for (int i = max; i >= 1; i--){
            exact[i] = cnt[i];
            for (int j = i * 2; j <= max; j += i){
                exact[i] -= exact[j];
            }
        }
        long[] pref = new long[max + 1];
        for (int i = 1; i <= max; i++){
            pref[i] = pref[i - 1] + exact[i];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++){
            long q = queries[i] + 1;
            int lo = 1;
            int hi = max;
            while (lo < hi){
                int mid = (lo + hi) / 2;
                if (pref[mid] >= q)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            ans[i] = lo;
        }
        return ans;
    }
}