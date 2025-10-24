class Solution {
    public int[] countBits(int n) {
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        ans.add(0);

        for (int i = 1; i <= n; i++) {
            String b = Integer.toBinaryString(i);
            count = 0;
            for (char c : b.toCharArray()) {
                if (c == '1') {
                    count++;
                }
            }

            ans.add(count);

        }

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}