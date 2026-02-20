import java.util.*;
class Solution {
    public String makeLargestSpecial(String s) {
        List<String> list = new ArrayList<>();
        int count = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
            else count--;
            if (count == 0) {
                String inner = s.substring(start + 1, i);
                String processed = makeLargestSpecial(inner);
                list.add("1" + processed + "0");
                start = i + 1;
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        String result = "";
        for (String str : list)
            result += str;
        return result;
    }
}