class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String str = s + s;
        int diff1 = 0; 
        int diff2 = 0; 
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < str.length(); i++) {
            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';
            if (str.charAt(i) != expected1) diff1++;
            if (str.charAt(i) != expected2) diff2++;
            if (i >= n) {
                char left1 = ((i - n) % 2 == 0) ? '0' : '1';
                char left2 = ((i - n) % 2 == 0) ? '1' : '0';

                if (str.charAt(i - n) != left1) diff1--;
                if (str.charAt(i - n) != left2) diff2--;
            }
            if (i >= n - 1) {
                ans = Math.min(ans, Math.min(diff1, diff2));
            }
        }
        return ans;
    }
}