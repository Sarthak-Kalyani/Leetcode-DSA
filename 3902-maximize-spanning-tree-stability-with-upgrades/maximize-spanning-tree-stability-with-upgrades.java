import java.util.*;
class Solution {
    class DSU {
        int[] parent;
        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;
            parent[pa] = pb;
            return true;
        }
    }
    public int maxStability(int n, int[][] edges, int k) {
        int low = 1, high = 200000, ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canBuild(n, edges, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    boolean canBuild(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        int used = 0;

        // mandatory edges first
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < target) return false;
                if (!dsu.union(u, v)) return false;
                used++;
            }
        }
        List<int[]> normal = new ArrayList<>();
        List<int[]> upgrade = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0) {
                if (s >= target) {
                    normal.add(e);
                } else if (2 * s >= target) {
                    upgrade.add(e);
                }
            }
        }
        // normal edges first
        for (int[] e : normal) {
            if (dsu.union(e[0], e[1])) used++;
        }
        // upgraded edges if needed
        for (int[] e : upgrade) {
            if (k == 0) break;
            if (dsu.union(e[0], e[1])) {
                used++;
                k--;
            }
        }
        return used == n - 1;
    }
}