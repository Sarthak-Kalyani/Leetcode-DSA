class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long,Integer> map=new HashMap<>();
        for(int x:nums){
            map.put((long)x,map.getOrDefault((long)x,0)+1);
        }
        int ans=1;
        if(map.containsKey(1L)){
            int c=map.get(1L);
            ans=Math.max(ans,c%2==0?c-1:c);
        }
        for(long start:map.keySet()){
            if(start==1) continue;
            long cur=start;
            int len=0;
            while(map.getOrDefault(cur,0)>=2){
                len+=2;
                if(cur>1000000000L/cur){
                    cur=-1;
                    break;
                }
                cur*=cur;
            }
            if(cur!=-1 && map.getOrDefault(cur,0)>=1){
                len++;
            }else{
                len=Math.max(1,len-1);
            }
            ans=Math.max(ans,len);
        }
        return ans;
    }
}