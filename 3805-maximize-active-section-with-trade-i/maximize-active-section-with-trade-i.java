class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int active = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') active++;
        }
        String t = "1" + s + "1";
        int m = t.length();
        char[] type = new char[m];
        int[] len = new int[m];
        int runs = 0;
        int i = 0;
        while (i < m){
            char ch = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == ch) j++;
            type[runs] = ch;
            len[runs] = j - i;
            runs++;
            i = j;
        }
        int ans = active;
        for (int r = 1; r < runs - 1; r++){
            if (type[r] == '1' && type[r - 1] == '0' && type[r + 1] == '0'){
                ans = Math.max(ans, active + len[r - 1] + len[r + 1]);
            }
        }
        return ans;
    }
}