class Solution {
    static final long MOD = 1000000007L;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int sz = 2 * m;
        long[][] T = new long[sz][sz];
        for (int v = 0; v < m; v++) {
            for (int u = 0; u < v; u++) {
                T[v][m + u] = 1;
            }
        }
        for (int v = 0; v < m; v++) {
            for (int u = v + 1; u < m; u++) {
                T[m + v][u] = 1;
            }
        }
        long[] base = new long[sz];
        for (int v = 0; v < m; v++) {
            base[v] = v;
            base[m + v] = m - 1 - v;
        }
        long[][] P = matPow(T, n - 2);
        long ans = 0;
        for (int i = 0; i < sz; i++) {
            long cur = 0;
            for (int j = 0; j < sz; j++) {
                cur = (cur + P[i][j] * base[j]) % MOD;
            }
            ans = (ans + cur) % MOD;
        }
        return (int) ans;
    }
    private long[][] matPow(long[][] A, long p) {
        int n = A.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;
        while (p > 0) {
            if ((p & 1) == 1) res = multiply(res, A);
            A = multiply(A, A);
            p >>= 1;
        }
        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}