class Solution {
    int[][] grid;
    boolean[][] vis;
    int m, n;
    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        vis = new boolean[m][n];
        return dfs(0, 0);
    }
    public boolean dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) return true;
        vis[x][y] = true;
        int[][] moves = getDir(grid[x][y]);
        for (int[] d : moves) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (vis[nx][ny]) continue;
            if (check(nx, ny, -d[0], -d[1])) {
                if (dfs(nx, ny)) return true;
            }
        }
        return false;
    }
    public boolean check(int x, int y, int dx, int dy) {
        int[][] moves = getDir(grid[x][y]);
        for (int[] d : moves) {
            if (d[0] == dx && d[1] == dy) return true;
        }
        return false;
    }
    public int[][] getDir(int t) {
        if (t == 1) return new int[][]{{0,-1},{0,1}};
        if (t == 2) return new int[][]{{-1,0},{1,0}};
        if (t == 3) return new int[][]{{0,-1},{1,0}};
        if (t == 4) return new int[][]{{0,1},{1,0}};
        if (t == 5) return new int[][]{{0,-1},{-1,0}};
        return new int[][]{{0,1},{-1,0}};
    }
}