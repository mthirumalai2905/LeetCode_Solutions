class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int[] memo = new int[days.length];
        Arrays.fill(memo, -1);
        return solve(days,costs,0,memo);
    }
    private int solve(int[] days, int[] costs, int idx, int[] memo){
        if(idx >= days.length){
            return 0;
        }

        if(memo[idx] != -1){
            return memo[idx];
        }

        int cost1 = costs[0] + solve(days,costs,idx+1,memo);

        int i = idx;
        while(i < days.length && days[i] < days[idx] + 7){
            i++;
        }
        int cost2 = costs[1] + solve(days,costs,i,memo);


        int j = idx;
        while(j < days.length && days[j] < days[idx] + 30){
            j++;
        }
        int cost3 = costs[2] + solve(days,costs,j,memo);

        return memo[idx] = Math.min(cost1, Math.min(cost2, cost3));
    }
}
