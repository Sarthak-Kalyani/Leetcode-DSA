class Solution {
    class BIT{
        int[] bit;
        BIT(int n){
            bit=new int[n+2];
        }
        void update(int idx,int val){
            while(idx<bit.length){
                bit[idx]+=val;
                idx+=idx&-idx;
            }
        }
        int query(int idx){
            int sum=0;
            while(idx>0){
                sum+=bit[idx];
                idx-=idx&-idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums,int target){
        int n=nums.length;
        int offset=n+2;
        BIT bit=new BIT(2*n+5);
        int prefix=0;
        long ans=0;
        bit.update(offset,1);
        for(int x:nums){
            if(x==target) prefix++;
            else prefix--;
            ans+=bit.query(prefix+offset-1);
            bit.update(prefix+offset,1);
        }
        return ans;
    }
}