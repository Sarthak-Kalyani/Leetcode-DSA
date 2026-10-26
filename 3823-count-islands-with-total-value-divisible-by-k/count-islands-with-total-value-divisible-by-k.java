class Solution{
    public int countIslands(int[][] grid,int k){
        int m=grid.length,n=grid[0].length;
        int[] stack=new int[m*n];
        int ans=0;
        int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0) continue;
                int top=0;
                long sum=grid[i][j];
                stack[top++]=i*n+j;
                grid[i][j]=0;
                while(top>0){
                    int pos=stack[--top];
                    int r=pos/n,c=pos%n;
                    for(int[] d:dirs){
                        int nr=r+d[0],nc=c+d[1];
                        if(nr>=0&&nr<m&&nc>=0&&nc<n&&grid[nr][nc]>0){
                            sum+=grid[nr][nc];
                            stack[top++]=nr*n+nc;
                            grid[nr][nc]=0;
                        }
                    }
                }
                if(sum%k==0) ans++;
            }
        }
        return ans;
    }
}