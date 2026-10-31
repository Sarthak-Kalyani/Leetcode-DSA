class Solution{
    public int uniquePaths(int[][] grid){
        int m=grid.length,n=grid[0].length;
        int mod=1000000007;
        long[][][] dp=new long[m][n][2];
        dp[0][0][0]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                long right=dp[i][j][0];
                long down=dp[i][j][1];
                if(grid[i][j]==0){
                    long total=(right+down)%mod;
                    if(j+1<n) dp[i][j+1][0]=(dp[i][j+1][0]+total)%mod;
                    if(i+1<m) dp[i+1][j][1]=(dp[i+1][j][1]+total)%mod;
                }else{
                    if(i+1<m) dp[i+1][j][1]=(dp[i+1][j][1]+right)%mod;
                    if(j+1<n) dp[i][j+1][0]=(dp[i][j+1][0]+down)%mod;
                }
            }
        }
        return (int)((dp[m-1][n-1][0]+dp[m-1][n-1][1])%mod);
    }
}