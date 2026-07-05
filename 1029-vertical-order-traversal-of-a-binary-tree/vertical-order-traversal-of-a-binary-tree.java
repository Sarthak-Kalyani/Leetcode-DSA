class Pair {
    TreeNode node;
    int row, col;
    Pair(TreeNode node, int row, int col) {
        this.node = node;
        this.row = row;
        this.col = col;
    }
}
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, 0));
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            map.putIfAbsent(cur.col, new TreeMap<>());
            map.get(cur.col).putIfAbsent(cur.row, new PriorityQueue<>());
            map.get(cur.col).get(cur.row).offer(cur.node.val);
            if (cur.node.left != null) {
                q.offer(new Pair(cur.node.left, cur.row + 1, cur.col - 1));
            }
            if (cur.node.right != null) {
                q.offer(new Pair(cur.node.right, cur.row + 1, cur.col + 1));
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> list = new ArrayList<>();
            for (PriorityQueue<Integer> pq : rows.values()) {
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }
            ans.add(list);
        }
        return ans;
    }
}