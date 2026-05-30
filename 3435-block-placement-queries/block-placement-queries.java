class Solution {
    class SegmentTree {
        int[] tree;
        int n;
        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }
        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid)
                update(node * 2, start, mid, idx, val);
            else
                update(node * 2 + 1, mid + 1, end, idx, val);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];
            int mid = (start + end) / 2;
            return Math.max(
                query(node * 2, start, mid, l, r),
                query(node * 2 + 1, mid + 1, end, l, r)
            );
        }
    }
    public List<Boolean> getResults(int[][] queries) {
        int maxX = 0;
        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(maxX + 1);
        for (int[] q : queries) {
            if (q[0] == 1) {
                set.add(q[1]);
            }
        }
        SegmentTree st = new SegmentTree(maxX + 2);
        Integer prev = null;
        for (int pos : set) {
            if (prev != null) {
                st.update(1, 0, maxX + 1, pos, pos - prev);
            }
            prev = pos;
        }
        ArrayList<Boolean> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];
                Integer left = set.floor(x);
                int best = st.query(1, 0, maxX + 1, 0, x);
                int lastGap = x - left;
                ans.add(Math.max(best, lastGap) >= sz);
            } else {
                int x = q[1];
                Integer l = set.lower(x);
                Integer r = set.higher(x);
                st.update(1, 0, maxX + 1, r, r - l);
                set.remove(x);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}