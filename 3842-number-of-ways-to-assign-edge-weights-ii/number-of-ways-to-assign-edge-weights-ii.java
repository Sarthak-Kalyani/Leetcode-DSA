import java.util.*;

class Solution {

    static final long MOD = 1000000007L;

    List<Integer>[] tree;
    int[][] up;
    int[] depth;
    int LOG;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        LOG = 17;

        while ((1 << LOG) <= n) LOG++;

        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            tree[u].add(v);
            tree[v].add(u);
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        dfs(1, 0);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int lca = lca(u, v);

            int dist = depth[u] + depth[v] - 2 * depth[lca];

            if (dist == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) modPow(2, dist - 1);
            }
        }

        return ans;
    }

    private void dfs(int node, int parent) {

        up[node][0] = parent;

        for (int nxt : tree[node]) {

            if (nxt == parent) continue;

            depth[nxt] = depth[node] + 1;

            dfs(nxt, node);
        }
    }

    private int lca(int a, int b) {

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int i = LOG - 1; i >= 0; i--) {
            if ((diff & (1 << i)) != 0) {
                a = up[a][i];
            }
        }

        if (a == b) return a;

        for (int i = LOG - 1; i >= 0; i--) {

            if (up[a][i] != up[b][i]) {

                a = up[a][i];
                b = up[b][i];
            }
        }

        return up[a][0];
    }

    private long modPow(long a, long b) {

        long res = 1;

        while (b > 0) {

            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return res;
    }
}