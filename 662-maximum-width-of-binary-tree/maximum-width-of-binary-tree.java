class Pair{
    TreeNode node;
    long index;
    Pair(TreeNode node, long index){
        this.node = node;
        this.index = index;
    }
}
class Solution{
    public int widthOfBinaryTree(TreeNode root){
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        int ans = 0;
        while (!q.isEmpty()){
            int size = q.size();
            long first = q.peek().index;
            long last = first;
            for (int i = 0; i < size; i++){
                Pair cur = q.poll();
                long idx = cur.index - first;
                last = idx;
                if (cur.node.left != null){
                    q.offer(new Pair(cur.node.left, 2 * idx + 1));
                }
                if (cur.node.right != null){
                    q.offer(new Pair(cur.node.right, 2 * idx + 2));
                }
            }
            ans = Math.max(ans, (int)(last + 1));
        }
        return ans;
    }
}