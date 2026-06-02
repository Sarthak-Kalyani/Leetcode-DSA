class Solution{
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration){
        int ans = Integer.MAX_VALUE;
        int n = landStartTime.length;
        int m = waterStartTime.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int landFinish = landStartTime[i] + landDuration[i];
                int waterStart =
                        Math.max(landFinish, waterStartTime[j]);
                int finish1 = waterStart + waterDuration[j];
                ans = Math.min(ans, finish1);
                int waterFinish =
                        waterStartTime[j] + waterDuration[j];
                int landStart =
                        Math.max(waterFinish, landStartTime[i]);
                int finish2 = landStart + landDuration[i];
                ans = Math.min(ans, finish2);
            }
        }
        return ans;
    }
}