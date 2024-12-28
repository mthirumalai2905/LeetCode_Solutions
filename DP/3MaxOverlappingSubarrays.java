class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n=nums.length;
        
        int []lsum=new int[n];
        int []rsum=new int[n];
        int []psum=new int[n];
        
        psum[0]=nums[0];
        
        for(int i=1;i<n;i++){
            psum[i]=psum[i-1]+nums[i];
        }
        
        int sum=0;
        
        for(int i=0;i<n;i++){
            if(i<k){
                sum+=nums[i];
                lsum[i]=sum;
            }else{
                sum+=nums[i]-nums[i-k];
                lsum[i]=Math.max(lsum[i-1],sum);
            }
        }
        
        sum=0;
        
        for(int i=n-1;i>=0;i--){
            if(i+k>=n){
                sum+=nums[i];
                rsum[i]=sum;
            }else{
                sum+=nums[i]-nums[i+k];
                rsum[i]=Math.max(rsum[i+1],sum);
            }
        }
        
        int maxSum=0;
        int mainIdx=-1;
        int leftTot=0;
        int rightTot=0;
        
        for(int i=k;i<=n-2*k;i++){
            if(psum[i+k-1]-psum[i-1]+lsum[i-1]+rsum[i+k]>maxSum){
                maxSum=psum[i+k-1]-psum[i-1]+lsum[i-1]+rsum[i+k];
                mainIdx=i;
                leftTot=lsum[i-1];
                rightTot=rsum[i+k];
            }
        }
        
        int leftIdx=-1;
        int rightIdx=-1;
        
        for(int i=k-1;i<mainIdx;i++){
            sum=psum[i]-(i-k<0 ? 0:psum[i-k]);
            if(sum==leftTot){
                leftIdx=i-k+1;
                break;
            }
        }
        
        for(int i=mainIdx+(2*k)-1;i<n;i++){
            sum=psum[i]-psum[i-k];
            if(sum==rightTot){
                rightIdx=i-k+1;
                break;
            }
        }
        
        return new int[]{leftIdx, mainIdx, rightIdx};
    }
}
