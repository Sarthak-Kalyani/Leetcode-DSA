class Solution{
    public int numOfSubarrays(int[] arr){
        long odd=0,even=1,ans=0;
        int sum=0;
        int mod=1000000007;
        for(int num:arr){
            sum=(sum+num)%2;
            if(sum==1){
                ans+=even;
                odd++;
            }else{
                ans+=odd;
                even++;
            }
            ans%=mod;
        }
        return (int)ans;
    }
}