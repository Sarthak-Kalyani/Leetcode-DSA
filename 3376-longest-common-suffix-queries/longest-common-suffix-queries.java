class Solution {
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int index = -1;
        int len = Integer.MAX_VALUE;
    }
    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        for (int i = 0; i < wordsContainer.length; i++) {
            String s = new StringBuilder(wordsContainer[i]).reverse().toString();
            insert(s, i, wordsContainer[i].length());
        }
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String s = new StringBuilder(wordsQuery[i]).reverse().toString();
            ans[i] = search(s);
        }
        return ans;
    }

    public void insert(String s, int idx, int length) {
        TrieNode node = root;
        update(node, idx, length);
        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }
            node = node.child[c];
            update(node, idx, length);
        }
    }

    public void update(TrieNode node, int idx, int length) {
        if (length < node.len) {
            node.len = length;
            node.index = idx;
        }
    }

    public int search(String s) {
        TrieNode node = root;
        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            if (node.child[c] == null) {
                break;
            }
            node = node.child[c];
        }
        return node.index;
    }
}