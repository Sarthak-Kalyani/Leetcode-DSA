class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) return 0;
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        int minutes = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            boolean rotten = false;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] d : dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n &&
                        grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        q.offer(new int[]{nr, nc});
                        rotten = true;
                    }
                }
            }
            if (rotten) minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }
}