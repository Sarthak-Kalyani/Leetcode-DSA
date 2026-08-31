class Solution{
    public int[] nodesBetweenCriticalPoints(ListNode head){
        int first=-1;
        int prev=-1;
        int min=Integer.MAX_VALUE;
        int pos=1;
        ListNode a=head;
        ListNode b=head.next;
        while(b!=null&&b.next!=null){
            ListNode c=b.next;
            if((b.val>a.val&&b.val>c.val)||(b.val<a.val&&b.val<c.val)){
                if(first==-1){
                    first=pos;
                }
                if(prev!=-1){
                    min=Math.min(min,pos-prev);
                }
                prev=pos;
            }
            a=b;
            b=c;
            pos++;
        }
        if(first==-1||first==prev) return new int[]{-1,-1};
        return new int[]{min,prev-first};
    }
}