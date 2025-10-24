class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList();
        char ch1[] = s.toCharArray();
        char ch2[] = p.toCharArray();
        Arrays.sort(ch2);
        for (int i = 0; i <= ch1.length - ch2.length; i++) {
            char arr1[] = new char[ch2.length];
            int ind = 0, times = 0;
            for (int j = i; times < ch2.length; j++) {
                times++;
                arr1[ind++] = ch1[j];
            }
            Arrays.sort(arr1);
            int flag = 0;
            for (int k = 0; k < ch2.length; k++) {
                if (arr1[k] != ch2[k]) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                list.add(i);
                flag = 0;
            }
        }
        return list;
    }
}