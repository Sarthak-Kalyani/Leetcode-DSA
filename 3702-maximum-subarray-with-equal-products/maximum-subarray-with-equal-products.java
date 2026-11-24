class Solution{
    public int maxLength(int[] nums){
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++){
            long prod = 1, lcm = 1;
            int gcd = 0;
            for (int j = i; j < n; j++){
                int x = nums[j];
                prod *= x;
                gcd = gcd(gcd, x);
                lcm = lcm / gcd((int)lcm, x) * x;
                if (prod == lcm * gcd) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
    private int gcd(int a, int b){
        while (b != 0){
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}