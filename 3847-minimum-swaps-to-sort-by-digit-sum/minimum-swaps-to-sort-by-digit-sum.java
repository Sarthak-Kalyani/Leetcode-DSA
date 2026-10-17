import java.util.*;
class Solution{
    public int minSwaps(int[] nums){
        int n=nums.length;
        int[][] arr=new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0]=nums[i];
            arr[i][1]=i;
        }
        Arrays.sort(arr,(a,b)->{
            int x=digitSum(a[0]),y=digitSum(b[0]);
            if(x!=y) return Integer.compare(x,y);
            return Integer.compare(a[0],b[0]);
        });
        boolean[] vis=new boolean[n];
        int swaps=0;
        for(int i=0;i<n;i++){
            if(vis[i]||arr[i][1]==i) continue;
            int j=i,size=0;
            while(!vis[j]){
                vis[j]=true;
                j=arr[j][1];
                size++;
            }
            if(size>1) swaps+=size-1;
        }
        return swaps;
    }
    private int digitSum(int x){
        int sum=0;
        while(x>0){
            sum+=x%10;
            x/=10;
        }
        return sum;
    }
}