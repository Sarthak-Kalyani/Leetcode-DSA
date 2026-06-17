class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++)
        list.add(i);
        int []fact = new int[n+1];
        fact[0] = 1;
        for(int i=1;i<=n;i++)
        fact[i] = fact[i-1]*i;
        k--;
        while(list.size() > 0){
            int idx = k/fact[n-1];
            res.append(list.remove(idx));
            k = k%fact[n-1];
            n--;
        }
        return res.toString();
    }
}