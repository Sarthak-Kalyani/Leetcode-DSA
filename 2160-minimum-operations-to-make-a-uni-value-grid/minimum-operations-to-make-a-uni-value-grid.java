class Solution {
    public int minOperations(int[][] grid, int x) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        int rem = list.get(0) % x;
        for (int num : list) {
            if (num % x != rem) return -1;
        }
        Collections.sort(list);
        int mid = list.get(list.size() / 2);
        int ans = 0;
        for (int num : list) {
            ans += Math.abs(num - mid) / x;
        }
        return ans;
    }
}