class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] x = new int[m][n];
        int[][] y = new int[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = (grid[i][j] == 'X') ? 1 : 0;
                y[i][j] = (grid[i][j] == 'Y') ? 1 : 0;

                if (i > 0) {
                    x[i][j] += x[i - 1][j];
                    y[i][j] += y[i - 1][j];
                }

                if (j > 0) {
                    x[i][j] += x[i][j - 1];
                    y[i][j] += y[i][j - 1];
                }

                if (i > 0 && j > 0) {
                    x[i][j] -= x[i - 1][j - 1];
                    y[i][j] -= y[i - 1][j - 1];
                }

                if (x[i][j] == y[i][j] && x[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}