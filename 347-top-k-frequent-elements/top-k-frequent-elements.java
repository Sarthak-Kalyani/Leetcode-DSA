class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> resa = new ArrayList<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int num = 0;
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        for (int i : list) {
            if (num++ < k) {
                resa.add(i);
            }
        }
        int count = 0;
        int res[] = new int[resa.size()];
        for (int i : resa) {
            res[count++] = i;
        }
        return res;
    }
}