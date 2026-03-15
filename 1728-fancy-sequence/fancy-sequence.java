import java.util.*;
class Fancy {
    long MOD = 1000000007;
    List<Long> list;
    long mul;
    long add;
    public Fancy() {
        list = new ArrayList<>();
        mul = 1;
        add = 0;
    }
    public void append(int val) {
        long inv = modPow(mul, MOD - 2);
        long x = ((val - add + MOD) % MOD * inv) % MOD;
        list.add(x);
    }
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }
    public int getIndex(int idx) {
        if (idx >= list.size()) return -1;
        long val = list.get(idx);
        return (int)((val * mul + add) % MOD);
    }
    long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}