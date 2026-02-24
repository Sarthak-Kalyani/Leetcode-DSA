class Solution {
    int total = 0;
    public int sumRootToLeaf(TreeNode root) {
        find(root, "");
        return total;
    }
    public void find(TreeNode node, String path) {
        if (node == null)
            return;
        path = path + node.val;
        if (node.left == null && node.right == null) {
            int number = Integer.parseInt(path, 2);
            total += number;
            return;
        }
        find(node.left, path);
        find(node.right, path);
    }
}