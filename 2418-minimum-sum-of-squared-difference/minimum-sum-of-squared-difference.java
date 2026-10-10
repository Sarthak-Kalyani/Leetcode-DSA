class Solution{
    public long minSumSquareDiff(int[] nums1,int[] nums2,int k1,int k2){
        int n=nums1.length;
        int[] diff=new int[n];
        int max=0;
        long total=0;
        for(int i=0;i<n;i++){
            diff[i]=Math.abs(nums1[i]-nums2[i]);
            max=Math.max(max,diff[i]);
            total+=diff[i];
        }
        long k=(long)k1+k2;
        if(k>=total) return 0;
        int low=0,high=max;
        while(low<high){
            int mid=low+(high-low)/2;
            long need=0;
            for(int d:diff){
                if(d>mid) need+=d-mid;
            }
            if(need<=k) high=mid;
            else low=mid+1;
        }
        long need=0;
        for(int d:diff){
            if(d>low) need+=d-low;
        }
        long extra=k-need;
        long ans=0;
        for(int d:diff){
            int x=Math.min(d,low);
            if(d>=low&&extra>0){
                x--;
                extra--;
            }
            ans+=(long)x*x;
        }
        return ans;
    }
}