class Solution{
    int k;
    int[] prod;
    int[][] cnt;
    public int[] resultArray(int[] nums,int k,int[][] queries){
        this.k=k;
        int n=nums.length;
        prod=new int[4*n];
        cnt=new int[4*n][k];
        build(1,0,n-1,nums);
        int[] ans=new int[queries.length];
        for(int q=0;q<queries.length;q++){
            int index=queries[q][0];
            int value=queries[q][1]%k;
            int start=queries[q][2];
            int x=queries[q][3];
            update(1,0,n-1,index,value);
            Node res=query(1,0,n-1,start,n-1);
            ans[q]=res.cnt[x];
        }
        return ans;
    }
    void build(int node,int l,int r,int[] nums){
        if(l==r){
            prod[node]=nums[l]%k;
            cnt[node][prod[node]]=1;
            return;
        }
        int mid=(l+r)/2;
        build(node*2,l,mid,nums);
        build(node*2+1,mid+1,r,nums);
        merge(node,node*2,node*2+1);
    }
    void update(int node,int l,int r,int index,int value){
        if(l==r){
            prod[node]=value;
            cnt[node]=new int[k];
            cnt[node][value]=1;
            return;
        }
        int mid=(l+r)/2;
        if(index<=mid) update(node*2,l,mid,index,value);
        else update(node*2+1,mid+1,r,index,value);
        merge(node,node*2,node*2+1);
    }
    void merge(int node,int left,int right){
        prod[node]=(int)((long)prod[left]*prod[right]%k);
        for(int x=0;x<k;x++) cnt[node][x]=cnt[left][x];
        for(int x=0;x<k;x++){
            int rr=(int)((long)prod[left]*x%k);
            cnt[node][rr]+=cnt[right][x];
        }
    }
    Node query(int node,int l,int r,int ql,int qr){
        if(ql<=l&&r<=qr){
            Node res=new Node(k);
            res.prod=prod[node];
            for(int i=0;i<k;i++) res.cnt[i]=cnt[node][i];
            return res;
        }
        int mid=(l+r)/2;
        if(qr<=mid) return query(node*2,l,mid,ql,qr);
        if(ql>mid) return query(node*2+1,mid+1,r,ql,qr);
        Node left=query(node*2,l,mid,ql,qr);
        Node right=query(node*2+1,mid+1,r,ql,qr);
        Node res=new Node(k);
        res.prod=(int)((long)left.prod*right.prod%k);
        for(int x=0;x<k;x++) res.cnt[x]=left.cnt[x];
        for(int x=0;x<k;x++){
            int rr=(int)((long)left.prod*x%k);
            res.cnt[rr]+=right.cnt[x];
        }
        return res;
    }
    static class Node{
        int prod;
        int[] cnt;
        Node(int k){
            cnt=new int[k];
        }
    }
}