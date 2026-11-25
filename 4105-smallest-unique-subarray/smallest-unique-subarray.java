import java.util.*;
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        Integer[] sa = new Integer[n];
        int[] rank = new int[n], nextRank = new int[n];
        for (int i = 0; i < n; i++) sa[i] = i;
        Arrays.sort(sa, (a, b) -> Integer.compare(nums[a], nums[b]));
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[sa[i]] != nums[sa[i - 1]]) r++;
            rank[sa[i]] = r;
        }
        for (int len = 1; len < n; len <<= 1) {
            final int step = len;
            final int[] cr = rank;
            Arrays.sort(sa, (a, b) -> {
                if (cr[a] != cr[b]) return Integer.compare(cr[a], cr[b]);
                int x = a + step < n ? cr[a + step] : -1;
                int y = b + step < n ? cr[b + step] : -1;
                return Integer.compare(x, y);
            });
            nextRank[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int a = sa[i - 1], b = sa[i];
                int x = a + step < n ? rank[a + step] : -1;
                int y = b + step < n ? rank[b + step] : -1;
                nextRank[b] = nextRank[a] + ((rank[a] != rank[b] || x != y) ? 1 : 0);
            }
            int[] temp = rank;
            rank = nextRank;
            nextRank = temp;
            if (rank[sa[n - 1]] == n - 1) break;
        }
        int[] suffix = new int[n];
        for (int i = 0; i < n; i++) suffix[rank[i]] = i;
        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            int pos = rank[i];
            if (pos == n - 1) {
                h = 0;
                continue;
            }
            int j = suffix[pos + 1];
            while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) h++;
            lcp[pos] = h;
            if (h > 0) h--;
        }
        int ans = n;
        for (int i = 0; i < n; i++) {
            int common = lcp[i];
            if (i > 0) common = Math.max(common, lcp[i - 1]);
            int length = common + 1;
            if (length <= n - suffix[i]) ans = Math.min(ans, length);
        }
        return ans;
    }
}