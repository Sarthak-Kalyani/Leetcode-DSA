class Solution{
    public int totalNumbers(int[] digits){
        int[] cnt=new int[10];
        for(int d:digits) cnt[d]++;
        int ans=0;
        for(int i=1;i<=9;i++){
            if(cnt[i]==0) continue;
            cnt[i]--;
            for(int j=0;j<=9;j++){
                if(cnt[j]==0) continue;
                cnt[j]--;
                for(int k=0;k<=8;k+=2){
                    if(cnt[k]>0) ans++;
                }
                cnt[j]++;
            }
            cnt[i]++;
        }
        return ans;
    }
}