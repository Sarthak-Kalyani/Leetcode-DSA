class Solution{
    public boolean hasValidPath(char[][] grid){
        int m=grid.length,n=grid[0].length;
        int len=m+n-1;
        if((len&1)==1||grid[0][0]==')'||grid[m-1][n-1]=='(') return false;

        boolean[][][] dp=new boolean[m][n][len+1];
        dp[0][0][1]=true;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0) continue;
                int add=grid[i][j]=='('?1:-1;
                for(int bal=0;bal<=len;bal++){
                    int prev=bal-add;
                    if(prev<0||prev>len) continue;
                    if(i>0&&dp[i-1][j][prev]) dp[i][j][bal]=true;
                    if(j>0&&dp[i][j-1][prev]) dp[i][j][bal]=true;
                }
            }
        }

        return dp[m-1][n-1][0];
    }
}