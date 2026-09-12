import java.util.*;
class Solution{
    public int[] maximumWeight(List<List<Integer>> intervals){
        int n=intervals.size();
        int[][] a=new int[n][4];
        for(int i=0;i<n;i++){
            a[i][0]=intervals.get(i).get(0);
            a[i][1]=intervals.get(i).get(1);
            a[i][2]=intervals.get(i).get(2);
            a[i][3]=i;
        }
        Arrays.sort(a,(x,y)->Integer.compare(x[1],y[1]));
        int[] prev=new int[n];
        for(int i=0;i<n;i++){
            int lo=0,hi=i-1;
            while(lo<=hi){
                int mid=(lo+hi)/2;
                if(a[mid][1]<a[i][0]) lo=mid+1;
                else hi=mid-1;
            }
            prev[i]=hi;
        }
        long[][] dp=new long[n+1][5];
        List<Integer>[][] paths=new ArrayList[n+1][5];
        for(int i=0;i<=n;i++){
            for(int j=0;j<5;j++) paths[i][j]=new ArrayList<>();
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<=4;j++){
                dp[i][j]=dp[i-1][j];
                paths[i][j]=new ArrayList<>(paths[i-1][j]);
                if(j>0){
                    int p=prev[i-1]+1;
                    long val=dp[p][j-1]+a[i-1][2];
                    List<Integer> cand=new ArrayList<>(paths[p][j-1]);
                    cand.add(a[i-1][3]);
                    Collections.sort(cand);
                    if(val>dp[i][j]||(val==dp[i][j]&&compare(cand,paths[i][j])<0)){
                        dp[i][j]=val;
                        paths[i][j]=cand;
                    }
                }
            }
        }
        List<Integer> best=new ArrayList<>();
        for(int j=0;j<=4;j++){
            if(dp[n][j]>dp[n][best.size()]||(dp[n][j]==dp[n][best.size()]&&compare(paths[n][j],best)<0)){
                best=new ArrayList<>(paths[n][j]);
            }
        }
        int[] ans=new int[best.size()];
        for(int i=0;i<best.size();i++) ans[i]=best.get(i);
        return ans;
    }
    private int compare(List<Integer> a,List<Integer> b){
        int n=Math.min(a.size(),b.size());
        for(int i=0;i<n;i++){
            if(!a.get(i).equals(b.get(i))) return Integer.compare(a.get(i),b.get(i));
        }
        return Integer.compare(a.size(),b.size());
    }
}