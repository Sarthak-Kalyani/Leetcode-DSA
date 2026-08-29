class Solution{
    public int[] lexicographicallySmallestArray(int[] nums,int limit){
        int n=nums.length;
        int[][] a=new int[n][2];
        for(int i=0;i<n;i++){
            a[i][0]=nums[i];
            a[i][1]=i;
        }
        Arrays.sort(a,(x,y)->Integer.compare(x[0],y[0]));
        int[] ans=new int[n];
        int i=0;
        while(i<n){
            int j=i;
            while(j+1<n&&a[j+1][0]-a[j][0]<=limit) j++;
            ArrayList<Integer> pos=new ArrayList<>();
            ArrayList<Integer> val=new ArrayList<>();
            for(int p=i;p<=j;p++){
                pos.add(a[p][1]);
                val.add(a[p][0]);
            }
            Collections.sort(pos);
            for(int p=0;p<pos.size();p++){
                ans[pos.get(p)]=val.get(p);
            }
            i=j+1;
        }
        return ans;
    }
}