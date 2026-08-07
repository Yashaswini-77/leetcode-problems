import java.util.Arrays;

public class Solution {
    private int[][] dp = new int[60][40];

    private void precomputeDP() {
        for (int i = 0; i < 60; ++i) {
            Arrays.fill(dp[i], (int) 1e9);
        }
        dp[0][0] = 0;
        int[][] choices = {{1, 0}, {0, 1}, {2, 0}, {1, 1}, {3, 0}, {0, 2}};
        for (int i = 0; i < 60; ++i) {
            for (int j = 0; j < 40; ++j) {
                if (i == 0 && j == 0) continue;
                int ans = (int) 1e9;
                for (int[] choice : choices) {
                    int a = choice[0];
                    int b = choice[1];
                    if ((i > 0 && a > 0) || (j > 0 && b > 0)) {
                        ans = Math.min(ans, 1 + dp[Math.max(0, i - a)][Math.max(0, j - b)]);
                    }
                }
                dp[i][j] = ans;
            }
        }
    }

    private int minDigits(int r2, int r3, int r5, int r7) {
        return Math.max(0, r5) + Math.max(0, r7) + dp[Math.max(0, r2)][Math.max(0, r3)];
    }

    private void addFactors(int d, int[] counts) {
        if (d == 0) return;
        while (d % 2 == 0) { counts[0]++; d /= 2; }
        while (d % 3 == 0) { counts[1]++; d /= 3; }
        while (d % 5 == 0) { counts[2]++; d /= 5; }
        while (d % 7 == 0) { counts[3]++; d /= 7; }
    }

    private void removeFactors(int d, int[] counts) {
        if (d == 0) return;
        while (d % 2 == 0) { counts[0]--; d /= 2; }
        while (d % 3 == 0) { counts[1]--; d /= 3; }
        while (d % 5 == 0) { counts[2]--; d /= 5; }
        while (d % 7 == 0) { counts[3]--; d /= 7; }
    }

    private void fillGreedily(StringBuilder ans, int startIdx, int totalLen, int[] req, int[] curr) {
        int rem = totalLen - startIdx;
        for (int i = startIdx; i < totalLen; ++i) {
            for (int d = 1; d <= 9; ++d) {
                int r2 = req[0] - curr[0];
                int r3 = req[1] - curr[1];
                int r5 = req[2] - curr[2];
                int r7 = req[3] - curr[3];
                
                int td = d;
                while (td % 2 == 0) { r2--; td /= 2; }
                while (td % 3 == 0) { r3--; td /= 3; }
                while (td % 5 == 0) { r5--; td /= 5; }
                while (td % 7 == 0) { r7--; td /= 7; }
                
                if (minDigits(r2, r3, r5, r7) <= rem - 1) {
                    ans.append(d);
                    addFactors(d, curr);
                    break;
                }
            }
            rem--;
        }
    }

    public String smallestNumber(String num, long t) {
        int[] req = new int[4];
        long temp = t;
        while (temp % 2 == 0) { req[0]++; temp /= 2; }
        while (temp % 3 == 0) { req[1]++; temp /= 3; }
        while (temp % 5 == 0) { req[2]++; temp /= 5; }
        while (temp % 7 == 0) { req[3]++; temp /= 7; }
        if (temp > 1) return "-1";

        precomputeDP();

        int n = num.length();
        int firstZero = num.indexOf('0');
        if (firstZero == -1) firstZero = n;

        if (firstZero == n) {
            int[] current = new int[4];
            for (int i = 0; i < n; i++) {
                addFactors(num.charAt(i) - '0', current);
            }
            if (current[0] >= req[0] && current[1] >= req[1] && current[2] >= req[2] && current[3] >= req[3]) {
                return num;
            }
        }

        int[] prefixFactors = new int[4];
        for (int i = 0; i < firstZero; ++i) {
            addFactors(num.charAt(i) - '0', prefixFactors);
        }

        int bestK = -1;
        int bestD = -1;

        for (int k = firstZero; k >= 0; --k) {
            if (k == n) {
                if (k > 0) removeFactors(num.charAt(k - 1) - '0', prefixFactors);
                continue;
            }

            int startD = (k == firstZero) ? 1 : (num.charAt(k) - '0') + 1;
            for (int d = startD; d <= 9; ++d) {
                int r2 = req[0] - prefixFactors[0];
                int r3 = req[1] - prefixFactors[1];
                int r5 = req[2] - prefixFactors[2];
                int r7 = req[3] - prefixFactors[3];
                
                int td = d;
                while (td % 2 == 0) { r2--; td /= 2; }
                while (td % 3 == 0) { r3--; td /= 3; }
                while (td % 5 == 0) { r5--; td /= 5; }
                while (td % 7 == 0) { r7--; td /= 7; }

                if (minDigits(r2, r3, r5, r7) <= n - 1 - k) {
                    bestK = k;
                    bestD = d;
                    break;
                }
            }
            if (bestK != -1) break;
            if (k > 0) removeFactors(num.charAt(k - 1) - '0', prefixFactors);
        }

        if (bestK != -1) {
            StringBuilder ans = new StringBuilder(num.substring(0, bestK));
            ans.append(bestD);
            int[] current = new int[4];
            for (int i = 0; i < ans.length(); i++) {
                addFactors(ans.charAt(i) - '0', current);
            }
            fillGreedily(ans, ans.length(), n, req, current);
            return ans.toString();
        }

        int targetLen = Math.max(n + 1, minDigits(req[0], req[1], req[2], req[3]));
        StringBuilder ans = new StringBuilder();
        fillGreedily(ans, 0, targetLen, req, new int[4]);
        return ans.toString();
    }
}







   
   
  



  
       
   
        

   












    






      








 
           
         
   

                

      
  
           

                


          










     
   
      



















        

       














        
         
      
                













    



         


    










    



        

