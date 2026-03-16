import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                set.add(grid[i][j]);
                int size = 1;
                while (i - size >= 0 && i + size < m && j - size >= 0 && j + size < n) {
                    int sum = 0;
                    for (int k = 0; k < size; k++) {
                        sum += grid[i - size + k][j + k];
                    }

                    for (int k = 0; k < size; k++) {
                        sum += grid[i + k][j + size - k];
                    }

                    for (int k = 0; k < size; k++) {
                        sum += grid[i + size - k][j - k];
                    }

                    for (int k = 0; k < size; k++) {
                        sum += grid[i - k][j - size + k];
                    }
                    set.add(sum);
                    size++;
                }
            }
        }
        int len = Math.min(3, set.size());
        int[] ans = new int[len];
        int index = 0;
        for (int num : set) {
            if (index == 3) break;
            ans[index++] = num;
        }
        return ans;
    }
}