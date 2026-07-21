class Solution {
    public int search(int[] nums, int target) {
        int a=0;
        int b=nums.length-1;
        while(a<=b){
            int m=a+(b-a)/2;
            if(nums[m]==target){
                return m;
            }else if(nums[m]<target){
                a=m+1;
            }else{
                b=m-1;
            }
        }
        return -1;
        
    }
}