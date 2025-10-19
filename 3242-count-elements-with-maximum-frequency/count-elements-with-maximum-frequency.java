import java.util.*;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Map to store frequency of each number
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // Count frequency of each element
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Find the maximum frequency
        int maxFreq = 0;
        for (int freq : freqMap.values()) {
            if (freq > maxFreq) {
                maxFreq = freq;
            }
        }

        // Count total elements with maximum frequency
        int count = 0;
        for (int num : nums) {
            if (freqMap.get(num) == maxFreq) {
                count++;
            }
        }

        return count;
    }
}
