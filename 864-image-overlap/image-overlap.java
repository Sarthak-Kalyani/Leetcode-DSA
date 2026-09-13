class Solution{
    public int largestOverlap(int[][] img1,int[][] img2){
        int n=img1.length;
        int ans=0;
        for(int dr=-(n-1);dr<=n-1;dr++){
            for(int dc=-(n-1);dc<=n-1;dc++){
                int count=0;
                for(int i=0;i<n;i++){
                    int j=i+dr;
                    if(j<0||j>=n) continue;
                    for(int k=0;k<n;k++){
                        int l=k+dc;
                        if(l<0||l>=n) continue;
                        if(img1[i][k]==1&&img2[j][l]==1) count++;
                    }
                }
                ans=Math.max(ans,count);
            }
        }
        return ans;
    }
}