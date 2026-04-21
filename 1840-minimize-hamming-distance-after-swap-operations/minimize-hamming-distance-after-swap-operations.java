class Solution {
    int[] parent;
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            groups.putIfAbsent(root, new ArrayList<>());
            groups.get(root).add(i);
        }
        int ans = 0;
        for (List<Integer> list : groups.values()) {
            HashMap<Integer, Integer> count = new HashMap<>();
            for (int idx : list) {
                count.put(source[idx], count.getOrDefault(source[idx], 0) + 1);
            }
            for (int idx : list) {
                int val = target[idx];

                if (count.getOrDefault(val, 0) > 0) {
                    count.put(val, count.get(val) - 1);
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}