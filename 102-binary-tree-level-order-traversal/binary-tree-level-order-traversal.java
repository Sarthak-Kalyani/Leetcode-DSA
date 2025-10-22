class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, 0, res);
        return res;
    }

    public void traversal(TreeNode root, int lvl, List<List<Integer>> res) {
        if (root == null) return;
        if (res.size() <= lvl) {
            res.add(new ArrayList());
        }
        
        res.get(lvl).add(root.val);
        traversal(root.left, lvl + 1, res);
        traversal(root.right, lvl + 1, res);
    }
}