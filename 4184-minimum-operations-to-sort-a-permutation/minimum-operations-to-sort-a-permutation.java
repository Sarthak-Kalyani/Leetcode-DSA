class Solution{
    public int minOperations(int[] nums){
        int n=nums.length;
        int zero=0;
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                zero=i;
                break;
            }
        }
        int ans=Integer.MAX_VALUE;
        if(isSortedRotation(nums,zero)){
            ans=Math.min(ans,Math.min(zero,n-zero+2));
        }
        int[] rev=new int[n];
        for(int i=0;i<n;i++) rev[i]=nums[n-1-i];
        int k=n-1-zero;
        if(isSortedRotation(rev,k)){
            ans=Math.min(ans,Math.min(k,n-k)+1);
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    private boolean isSortedRotation(int[] nums,int k){
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(nums[(i+k)%n]!=i) return false;
        }
        return true;
    }
}