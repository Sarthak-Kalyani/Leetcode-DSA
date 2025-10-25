class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Thread t1 = new Thread(() -> Arrays.sort(g));
        Thread t2 = new Thread(() -> Arrays.sort(s));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int count =0;
        int j=0;
		for(int i=0; i<s.length; i++) {
			if(j >= g.length) break;
			if(s[i] >= g[j]) {
				count++;
				j++;
			}
		}
        return count;
    }
}