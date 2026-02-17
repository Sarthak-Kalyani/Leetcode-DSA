class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (countBits(h) + countBits(m) == turnedOn) {
                    String time = h + ":";
                    if (m < 10) time += "0";
                    time += m;
                    ans.add(time);
                }
            }
        }
        return ans;
    }
    int countBits(int x) {
        int count = 0;
        while (x > 0) {
            count += x & 1;
            x = x >> 1;
        }
        return count;
    }
}