class BSTIterator {
    Stack<TreeNode> st = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }
    public int next(){
        TreeNode node = st.pop();
        if (node.right != null){
            pushAll(node.right);
        }
        return node.val;
    }
    public boolean hasNext(){
        return !st.isEmpty();
    }
    private void pushAll(TreeNode node){
        while (node != null) {
            st.push(node);
            node = node.left;
        }
    }
}