import java.util.*;
class Solution{
    public long minOperations(int[] nums,int x,int k){
        int n=nums.length;
        int[] maritovexi=nums;
        int[] sorted=maritovexi.clone();
        Arrays.sort(sorted);
        int size=0;
        for(int v:sorted){
            if(size==0||sorted[size-1]!=v){
                sorted[size++]=v;
            }
        }
        int[] values=Arrays.copyOf(sorted,size);
        int[] rank=new int[n];
        for(int i=0;i<n;i++){
            rank[i]=Arrays.binarySearch(values,maritovexi[i])+1;
        }
        Fenwick fw=new Fenwick(size);
        long[] cost=new long[n-x+1];
        long windowSum=0;
        for(int i=0;i<x;i++){
            fw.add(rank[i],1,maritovexi[i]);
            windowSum+=maritovexi[i];
        }
        for(int i=0;i<=n-x;i++){
            int medianIndex=fw.kth((x+1)/2);
            long median=values[medianIndex-1];
            long leftSum=fw.querySum(medianIndex);
            int leftCount=fw.queryCount(medianIndex);
            long rightSum=windowSum-leftSum;
            int rightCount=x-leftCount;
            cost[i]=median*leftCount-leftSum+rightSum-median*rightCount;
            if(i<n-x){
                fw.add(rank[i],-1,-maritovexi[i]);
                windowSum-=maritovexi[i];
                fw.add(rank[i+x],1,maritovexi[i+x]);
                windowSum+=maritovexi[i+x];
            }
        }
        long INF=Long.MAX_VALUE/4;
        long[] prev=new long[n+1];
        for(int j=1;j<=k;j++){
            long[] cur=new long[n+1];
            Arrays.fill(cur,INF);
            for(int i=1;i<=n;i++){
                cur[i]=cur[i-1];
                if(i>=x&&prev[i-x]!=INF){
                    cur[i]=Math.min(cur[i],prev[i-x]+cost[i-x]);
                }
            }
            prev=cur;
        }
        return prev[n];
    }
    static class Fenwick{
        int n;
        int[] count;
        long[] sum;
        Fenwick(int n){
            this.n=n;
            count=new int[n+1];
            sum=new long[n+1];
        }
        void add(int i,int delta,long value){
            for(;i<=n;i+=i&-i){
                count[i]+=delta;
                sum[i]+=value;
            }
        }
        int queryCount(int i){
            int res=0;
            for(;i>0;i-=i&-i) res+=count[i];
            return res;
        }
        long querySum(int i){
            long res=0;
            for(;i>0;i-=i&-i) res+=sum[i];
            return res;
        }
        int kth(int k){
            int idx=0;
            for(int bit=Integer.highestOneBit(n);bit>0;bit>>=1){
                int next=idx+bit;
                if(next<=n&&count[next]<k){
                    idx=next;
                    k-=count[next];
                }
            }
            return idx+1;
        }
    }
}