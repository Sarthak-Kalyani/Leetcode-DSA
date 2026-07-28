class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()){
            freq[c - 'a']++;
        }
        StringBuilder left = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        for (int i = 0; i < 26; i++){
            while (freq[i] >= 2){
                left.append((char) (i + 'a'));
                freq[i] -= 2;
            }
            if (freq[i] == 1) {
                mid.append((char) (i + 'a'));
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append(left);
        ans.append(mid);
        ans.append(left.reverse());
        return ans.toString();
    }
}