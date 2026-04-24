class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0;
        int right = 0;
        int blank = 0;
        for (int i = 0; i < moves.length(); i++) {
            char ch = moves.charAt(i);
            if (ch == 'L') {
                left++;
            } else if (ch == 'R') {
                right++;
            } else {
                blank++;
            }
        }
        return Math.abs(left - right) + blank;
    }
}