class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // store indices for every divisor
        for (int i = 0; i < n; i++) {
            List<Integer> factors = getPrimeFactors(nums[i]);

            for (int f : factors) {
                map.putIfAbsent(f, new ArrayList<>());
                map.get(f).add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        HashSet<Integer> usedPrime = new HashSet<>();
        q.offer(0);
        vis[0] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int i = q.poll();
                if (i == n - 1) return steps;

                // left
                if (i - 1 >= 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    q.offer(i - 1);
                }

                // right
                if (i + 1 < n && !vis[i + 1]) {
                    vis[i + 1] = true;
                    q.offer(i + 1);
                }

                // teleport
                if (isPrime(nums[i]) && !usedPrime.contains(nums[i])) {
                    usedPrime.add(nums[i]);
                    List<Integer> list = map.get(nums[i]);
                    if (list != null) {
                        for (int idx : list) {
                            if (!vis[idx]) {
                                vis[idx] = true;
                                q.offer(idx);
                            }
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    public boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
    public List<Integer> getPrimeFactors(int x) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                list.add(i);
                while (x % i == 0) {
                    x /= i;
                }
            }
        }
        if (x > 1) list.add(x);
        return list;
    }
}