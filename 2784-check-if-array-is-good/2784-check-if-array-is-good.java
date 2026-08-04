class Solution {
    public boolean isGood(int[] nums) {
        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        if (nums.length != max + 1)
            return false;

        int[] freq = new int[max + 1];

        for (int num : nums) {
            if (num < 1 || num > max)
                return false;

            freq[num]++;
        }

        for (int i = 1; i < max; i++) {
            if (freq[i] != 1)
                return false;
        }

        return freq[max] == 2;
    }
}

        
    
