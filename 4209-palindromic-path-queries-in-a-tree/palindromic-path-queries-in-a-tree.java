import java.util.*;
class Solution{
    int LOG;
    int[][] up;
    int[] depth,tin,tout,pathMask;
    int timer;
    Fenwick bit;
    public List<Boolean> palindromePath(int n,int[][] edges,String s,String[] queries){
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());
        for(int[] e:edges){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        LOG=1;
        while((1<<LOG)<=n) LOG++;
        up=new int[LOG][n];
        depth=new int[n];
        tin=new int[n];
        tout=new int[n];
        pathMask=new int[n];
        timer=0;
        char[] chars=s.toCharArray();
        int[] parent=new int[n];
        Arrays.fill(parent,-1);
        parent[0]=0;
        int[] stack=new int[2*n+5];
        int top=0;
        stack[top++]=0;
        while(top>0){
            int cur=stack[--top];
            if(cur<0){
                int u=~cur;
                tout[u]=timer-1;
                continue;
            }
            int u=cur;
            tin[u]=timer++;
            pathMask[u]=(u==0?0:pathMask[parent[u]])^(1<<(chars[u]-'a'));
            stack[top++]=~u;
            for(int v:graph.get(u)){
                if(v==parent[u]) continue;
                parent[v]=u;
                depth[v]=depth[u]+1;
                up[0][v]=u;
                for(int j=1;j<LOG;j++) up[j][v]=up[j-1][up[j-1][v]];
                stack[top++]=v;
            }
        }
        bit=new Fenwick(n);
        for(int u=0;u<n;u++) bit.rangeXor(tin[u],tin[u],pathMask[u]);
        List<Boolean> ans=new ArrayList<>();
        for(String query:queries){
            String[] p=query.split(" ");
            int u=Integer.parseInt(p[1]);
            if(p[0].equals("update")){
                char c=p[2].charAt(0);
                int delta=(1<<(chars[u]-'a'))^(1<<(c-'a'));
                if(delta!=0) bit.rangeXor(tin[u],tout[u],delta);
                chars[u]=c;
            }else{
                int v=Integer.parseInt(p[2]);
                int ancestor=lca(u,v);
                int mask=bit.get(tin[u])^bit.get(tin[v])^(1<<(chars[ancestor]-'a'));
                ans.add((mask&(mask-1))==0);
            }
        }
        return ans;
    }
    private int lca(int a,int b){
        if(depth[a]<depth[b]){
            int t=a;
            a=b;
            b=t;
        }
        int diff=depth[a]-depth[b];
        for(int j=0;j<LOG;j++){
            if((diff&(1<<j))!=0) a=up[j][a];
        }
        if(a==b) return a;
        for(int j=LOG-1;j>=0;j--){
            if(up[j][a]!=up[j][b]){
                a=up[j][a];
                b=up[j][b];
            }
        }
        return up[0][a];
    }
    static class Fenwick{
        int n;
        int[] tree;
        Fenwick(int n){
            this.n=n;
            tree=new int[n+1];
        }
        void add(int index,int value){
            for(int i=index+1;i<=n;i+=i&-i) tree[i]^=value;
        }
        void rangeXor(int left,int right,int value){
            add(left,value);
            if(right+1<n) add(right+1,value);
        }
        int get(int index){
            int result=0;
            for(int i=index+1;i>0;i-=i&-i) result^=tree[i];
            return result;
        }
    }
}