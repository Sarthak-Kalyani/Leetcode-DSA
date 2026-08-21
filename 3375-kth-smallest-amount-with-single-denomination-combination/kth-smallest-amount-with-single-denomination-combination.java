class Solution{
    public long findKthSmallest(int[] coins,int k){
        long low=1;
        long high=(long)coins[0]*k;
        for(int c:coins){
            high=Math.min(high,(long)c*k);
        }
        while(low<high){
            long mid=low+(high-low)/2;
            if(count(mid,coins)>=k){
                high=mid;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    private long count(long x,int[] coins){
        int n=coins.length;
        long total=0;
        for(int mask=1;mask<(1<<n);mask++){
            long lcm=1;
            int bits=0;
            boolean ok=true;
            for(int i=0;i<n;i++){
                if((mask&(1<<i))!=0){
                    bits++;
                    long g=gcd(lcm,coins[i]);
                    lcm=(lcm/g)*coins[i];
                    if(lcm>x){
                        ok=false;
                        break;
                    }
                }
            }
            if(!ok) continue;
            long cur=x/lcm;
            if((bits&1)==1){
                total+=cur;
            }else{
                total-=cur;
            }
        }
        return total;
    }
    private long gcd(long a,long b){
        while(b!=0){
            long t=a%b;
            a=b;
            b=t;
        }
        return a;
    }
}