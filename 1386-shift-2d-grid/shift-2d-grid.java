class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++){
                ans.get(i).add(0);
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int curr = i * n + j;
                int next = (curr + k) % total;
                int r = next / n;
                int c = next % n;
                ans.get(r).set(c, grid[i][j]);
            }
        }
        return ans;
    }
}