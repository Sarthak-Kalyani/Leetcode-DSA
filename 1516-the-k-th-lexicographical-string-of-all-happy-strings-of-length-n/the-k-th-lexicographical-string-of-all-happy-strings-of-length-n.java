class Solution {
    int count = 0;
    String ans = "";
    public String getHappyString(int n, int k) {
        generate("", n, k);
        return ans;
    }
    void generate(String curr, int n, int k) {
        if (curr.length() == n) {
            count++;
            if (count == k) {
                ans = curr;
            }
            return;
        }
        for (char ch : new char[]{'a', 'b', 'c'}) {
            if (curr.length() > 0 && curr.charAt(curr.length() - 1) == ch) {
                continue;
            }
            generate(curr + ch, n, k);
            if (!ans.equals("")) return;
        }
    }
}