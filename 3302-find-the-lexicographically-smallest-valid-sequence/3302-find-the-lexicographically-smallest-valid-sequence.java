class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // dp[i] = number of characters of word2
        // that can be matched from word1[i...]
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        while (i < n && j < m) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Use our one allowed mismatch
            else if (dp[i + 1] >= m - j - 1) {
                ans[j] = i;
                j++;
                i++;

                // Match the remaining characters exactly
                while (i < n && j < m) {
                    if (word1.charAt(i) == word2.charAt(j)) {
                        ans[j] = i;
                        j++;
                    }
                    i++;
                }

                return j == m ? ans : new int[0];
            }

            i++;
        }

        return j == m ? ans : new int[0];
    }
}

        
    
