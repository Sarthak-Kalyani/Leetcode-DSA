class Solution {
    static class Edge {
        int to;
        int cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n];
        int maxCost = 0;
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        int[] topo = new int[n];
        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (Edge e : graph[u]) {
                if (--indegree[e.to] == 0) {
                    q.offer(e.to);
                }
            }
        }
        int lo = 0;
        int hi = maxCost;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (can(mid, graph, topo, online, k, n)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    private boolean can(int limit, List<Edge>[] graph, int[] topo,
                        boolean[] online, long k, int n) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        for (int u : topo) {
            if (dist[u] == INF) continue;
            if (u != 0 && u != n - 1 && !online[u]) continue;
            for (Edge e : graph[u]) {
                if (e.cost < limit) continue;
                int v = e.to;
                if (v != 0 && v != n - 1 && !online[v]) continue;
                if (dist[v] > dist[u] + e.cost) {
                    dist[v] = dist[u] + e.cost;
                }
            }
        }
        return dist[n - 1] <= k;
    }
}