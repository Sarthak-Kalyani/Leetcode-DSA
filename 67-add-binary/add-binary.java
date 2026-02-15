class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        String answer = "";

        while (i >= 0 || j >= 0) {
            int x = 0;
            int y = 0;

            if (i >= 0) {
                x = a.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                y = b.charAt(j) - '0';
                j--;
            }

            int total = x + y + carry;
            if (total == 0) {
                answer = "0" + answer;
                carry = 0;
            }
            else if (total == 1) {
                answer = "1" + answer;
                carry = 0;
            }
            else if (total == 2) {
                answer = "0" + answer;
                carry = 1;
            }
            else { // total == 3
                answer = "1" + answer;
                carry = 1;
            }
        }

        if (carry == 1)
            answer = "1" + answer;
        return answer;
    }
}