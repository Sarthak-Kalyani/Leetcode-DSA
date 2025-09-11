class Solution {
    public String sortVowels(String s) {
        // Step 1: Collect all vowels
        String vowels = "AEIOUaeiou";
        StringBuilder sb = new StringBuilder(s);
        java.util.List<Character> list = new java.util.ArrayList<>();

        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) { // check if vowel
                list.add(c);
            }
        }

        // Step 2: Sort vowels
        java.util.Collections.sort(list);

        // Step 3: Replace vowels in original string
        int idx = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (vowels.indexOf(sb.charAt(i)) != -1) {
                sb.setCharAt(i, list.get(idx));
                idx++;
            }
        }

        return sb.toString();
    }
}
