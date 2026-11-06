import java.util.*;
class Solution{
    public int[] countServers(int n,int[][] logs,int x,int[] queries){
        Arrays.sort(logs,(a,b)->Integer.compare(a[1],b[1]));
        int q=queries.length;
        int[][] arr=new int[q][2];
        for(int i=0;i<q;i++){
            arr[i][0]=queries[i];
            arr[i][1]=i;
        }
        Arrays.sort(arr,(a,b)->Integer.compare(a[0],b[0]));
        int[] ans=new int[q];
        int[] count=new int[n+1];
        int left=0,right=0,active=0;
        for(int[] query:arr){
            int time=query[0];
            while(right<logs.length&&logs[right][1]<=time){
                int id=logs[right][0];
                if(count[id]++==0) active++;
                right++;
            }
            while(left<right&&logs[left][1]<time-x){
                int id=logs[left][0];
                if(--count[id]==0) active--;
                left++;
            }
            ans[query[1]]=n-active;
        }
        return ans;
    }
}