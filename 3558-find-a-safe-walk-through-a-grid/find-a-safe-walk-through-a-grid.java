class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[0][0] = grid.get(0).get(0);
        pq.offer(new int[]{dist[0][0], 0, 0});
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int cost = cur[0];
            int r = cur[1];
            int c = cur[2];
            if (cost > dist[r][c]) continue;
            for (int[] d : dir){
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n){
                    int newCost = cost + grid.get(nr).get(nc);
                    if (newCost < dist[nr][nc]) {
                        dist[nr][nc] = newCost;
                        pq.offer(new int[]{newCost, nr, nc});
                    }
                }
            }
        }
        return dist[m - 1][n - 1] < health;
    }
}