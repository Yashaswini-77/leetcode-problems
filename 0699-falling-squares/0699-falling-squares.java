import java.util.*;

class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> result = new ArrayList<>();
        List<int[]> intervals = new ArrayList<>();

        int maxHeight = 0;

        for (int[] square : positions) {
            int left = square[0];
            int size = square[1];
            int right = left + size;

            int baseHeight = 0;


            for (int[] interval : intervals) {
                int l = interval[0];
                int r = interval[1];
                int h = interval[2];

                if (left < r && right > l) {
                    baseHeight = Math.max(baseHeight, h);
                }
            }

            int currentHeight = baseHeight + size;

            intervals.add(new int[]{left, right, currentHeight});

            maxHeight = Math.max(maxHeight, currentHeight);
            result.add(maxHeight);
        }

        return result;
    }
}

        

