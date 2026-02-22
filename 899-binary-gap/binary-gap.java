class Solution {
    public int binaryGap(int n) {
        int lastPosition = -1;
        int maxDistance = 0;
        int position = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                if (lastPosition != -1) {
                    int distance = position - lastPosition;
                    maxDistance = Math.max(maxDistance, distance);
                }
                lastPosition = position;
            }
            n = n / 2;
            position++;
        }
        return maxDistance;
    }
}