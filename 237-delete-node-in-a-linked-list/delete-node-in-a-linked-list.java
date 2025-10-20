class Solution {
    public void deleteNode(ListNode node) {
        // Copy data from next node to current node
        node.val = node.next.val;
        // Remove next node
        node.next = node.next.next;
    }
}
