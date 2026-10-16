import java.util.*;
class Solution{
    static final long MOD=1000000007L;
    public int sumCounts(List<Integer> nums){
        int n=nums.size();
        int[] last=new int[100001];
        Arrays.fill(last,-1);
        SegmentTree st=new SegmentTree(n);
        long ans=0;
        for(int i=0;i<n;i++){
            int x=nums.get(i);
            st.update(1,0,n-1,last[x]+1,i,1);
            ans=(ans+st.sq[1])%MOD;
            last[x]=i;
        }
        return (int)ans;
    }
    public int sumCounts(int[] nums){
        List<Integer> list=new ArrayList<>();
        for(int x:nums) list.add(x);
        return sumCounts(list);
    }
    static class SegmentTree{
        long[] sum,sq,lazy;
        int n;
        SegmentTree(int n){
            this.n=n;
            sum=new long[4*n];
            sq=new long[4*n];
            lazy=new long[4*n];
        }
        void apply(int node,int l,int r,long d){
            long len=r-l+1;
            sq[node]=(sq[node]+2*d%MOD*sum[node]%MOD+d*d%MOD*len)%MOD;
            sum[node]=(sum[node]+d*len)%MOD;
            lazy[node]+=d;
        }
        void push(int node,int l,int r){
            if(lazy[node]==0||l==r) return;
            int mid=(l+r)/2;
            apply(node*2,l,mid,lazy[node]);
            apply(node*2+1,mid+1,r,lazy[node]);
            lazy[node]=0;
        }
        void update(int node,int l,int r,int ql,int qr,long d){
            if(ql<=l&&r<=qr){
                apply(node,l,r,d);
                return;
            }
            push(node,l,r);
            int mid=(l+r)/2;
            if(ql<=mid) update(node*2,l,mid,ql,qr,d);
            if(qr>mid) update(node*2+1,mid+1,r,ql,qr,d);
            sum[node]=(sum[node*2]+sum[node*2+1])%MOD;
            sq[node]=(sq[node*2]+sq[node*2+1])%MOD;
        }
    }
}