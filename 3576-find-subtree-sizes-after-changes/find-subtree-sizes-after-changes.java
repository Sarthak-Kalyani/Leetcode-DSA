import java.util.*;
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] head = new int[n], to = new int[n], next = new int[n];
        Arrays.fill(head, -1);
        int e = 0;
        for (int i = 1; i < n; i++) {
            to[e] = i;
            next[e] = head[parent[i]];
            head[parent[i]] = e++;
        }
        int[] last = new int[26], old = new int[n], newParent = new int[n];
        Arrays.fill(last, -1);
        int[] stack = new int[2 * n + 5], order = new int[n];
        int top = 0, idx = 0;
        stack[top++] = 1;
        while (top > 0) {
            int event = stack[--top];
            if (event > 0) {
                int u = event - 1;
                int c = s.charAt(u) - 'a';
                old[u] = last[c];
                newParent[u] = old[u] == -1 ? parent[u] : old[u];
                last[c] = u;
                order[idx++] = u;
                stack[top++] = -(u + 1);
                for (int j = head[u]; j != -1; j = next[j]) {
                    stack[top++] = to[j] + 1;
                }
            } else {
                int u = -event - 1;
                last[s.charAt(u) - 'a'] = old[u];
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            if (newParent[u] != -1) {
                ans[newParent[u]] += ans[u];
            }
        }
        return ans;
    }
}