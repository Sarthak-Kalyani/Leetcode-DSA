class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    int postIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder){
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return build(postorder, 0, inorder.length - 1);
    }
    private TreeNode build(int[] postorder, int left, int right){
        if (left > right){
            return null;
        }
        int val = postorder[postIndex--];
        TreeNode root = new TreeNode(val);
        int index = map.get(val);
        root.right = build(postorder, index + 1, right);
        root.left = build(postorder, left, index - 1);
        return root;
    }
}