import java.util.*;
class Solution{
    public long countNonDecreasingSubarrays(int[] nums,int k){
        int n=nums.length;
        Deque<int[]> dq=new ArrayDeque<>();
        long cost=0,ans=0;
        int j=n-1;
        for(int i=n-1;i>=0;i--){
            int count=1;
            while(!dq.isEmpty()&&dq.peekLast()[0]<nums[i]){
                int[] p=dq.pollLast();
                count+=p[1];
                cost+=(long)(nums[i]-p[0])*p[1];
            }
            dq.offerLast(new int[]{nums[i],count});
            while(cost>k){
                int[] p=dq.pollFirst();
                int value=p[0],cnt=p[1];
                cost-=(long)value-nums[j];
                j--;
                if(cnt>1){
                    dq.offerFirst(new int[]{value,cnt-1});
                }
            }
            ans+=j-i+1;
        }
        return ans;
    }
}