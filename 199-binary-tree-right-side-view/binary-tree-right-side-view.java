class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null)
            q.offer(root);

        while (!q.isEmpty()) {
            TreeNode rightSide = null;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node != null) {
                    rightSide = node;
                    if (node.left != null)
                        q.offer(node.left);
                    if (node.right != null)
                        q.offer(node.right);
                }
            }

            if (rightSide != null) {
                res.add(rightSide.val);
            }
        }

        return res;
    }
}