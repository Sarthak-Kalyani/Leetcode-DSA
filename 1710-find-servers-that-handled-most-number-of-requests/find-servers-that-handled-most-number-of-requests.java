import java.util.*;
class Solution{
    public List<Integer> busiestServers(int k,int[] arrival,int[] load){
        TreeSet<Integer> free=new TreeSet<>();
        PriorityQueue<int[]> busy=new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        int[] count=new int[k];
        for(int i=0;i<k;i++) free.add(i);
        for(int i=0;i<arrival.length;i++){
            while(!busy.isEmpty()&&busy.peek()[0]<=arrival[i]){
                free.add(busy.poll()[1]);
            }
            if(free.isEmpty()) continue;
            int server=i%k;
            Integer chosen=free.ceiling(server);
            if(chosen==null) chosen=free.first();
            free.remove(chosen);
            count[chosen]++;
            busy.offer(new int[]{arrival[i]+load[i],chosen});
        }
        int max=0;
        for(int x:count) max=Math.max(max,x);
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<k;i++){
            if(count[i]==max) ans.add(i);
        }
        return ans;
    }
}