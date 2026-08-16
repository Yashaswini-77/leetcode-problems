class Solution {
    public boolean stoneGameIX(int[] stones) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int stone : stones) {
            int rem = stone % 3;

            if (rem == 0) {
                count0++;
            } else if (rem == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        if (count0 % 2 == 0) {
            return count1 > 0 && count2 > 0;
        }

        return Math.abs(count1 - count2) >= 3;
    }
}

        
    
