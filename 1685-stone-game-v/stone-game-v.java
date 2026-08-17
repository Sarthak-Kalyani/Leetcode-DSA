class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n=stoneValue.length;
        int[] pre=new int[n+1];
        for(int i=0;i<n;i++) pre[i+1]=pre[i]+stoneValue[i];
        int[][] dp=new int[n][n];
        for(int len=2;len<=n;len++){
            for(int l=0;l+len<=n;l++){
                int r=l+len-1;
                int i=l;
                while(i<r){
                    int left=pre[i+1]-pre[l];
                    int right=pre[r+1]-pre[i+1];
                    if(left<right){
                        dp[l][r]=Math.max(dp[l][r],left+dp[l][i]);
                    }else if(left>right){
                        dp[l][r]=Math.max(dp[l][r],right+dp[i+1][r]);
                    }else{
                        dp[l][r]=Math.max(dp[l][r],left+Math.max(dp[l][i],dp[i+1][r]));
                    }
                    i++;
                }
            }
        }
        return dp[0][n-1];
    }
}