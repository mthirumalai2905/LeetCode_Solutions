class Solution {
    public int minimizeCost(int k, int arr[]) {
        // code here
        int n = arr.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return solve(arr,dp,k,n,0);
    }
    public int solve(int[] arr, int[] dp, int k, int n, int z){
        if(z == n-1){
            return 0;
        }
        
        if(dp[z] != -1){
            return dp[z];
        }
        
        int minCost = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++){
            if(z + i < n){
                int cost = Math.abs(arr[z] - arr[z + i]) + solve(arr,dp,k,n,z+i);
                minCost = Math.min(minCost,cost);
            }
        }
        
        return dp[z] = minCost;
    }
}
