class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads){
            graph[road[0]].add(new int[]{road[1], road[2]});
            graph[road[1]].add(new int[]{road[0], road[2]});
        }
        boolean[] vis = new boolean[n+1];
        return dfs(1, graph, vis, Integer.MAX_VALUE);
    }
    private int dfs(int node, List<int[]>[] graph, boolean[] vis, int ans){
        vis[node] = true;
        for (int[] next : graph[node]){
            ans = Math.min(ans, next[1]);
            if (!vis[next[0]]){
                ans = dfs(next[0], graph, vis, ans);
            }
        }
        return ans;
    }
}