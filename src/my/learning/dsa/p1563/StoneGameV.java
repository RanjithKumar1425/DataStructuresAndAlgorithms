package my.learning.dsa.p1563;

public class StoneGameV {
    public static void main(String[] args) {
        StoneGameV sg = new StoneGameV();
        System.out.println(sg.stoneGameV(new int[]{6, 2, 3, 4, 5, 5})); // 18
        System.out.println(sg.stoneGameV(new int[]{7, 7, 7, 7, 7, 7, 7})); // 28
    }

    /**
     * Backtracking and Dynamic Programming.
     *
     * 1. Calculate the prefix sum
     * dp (start, end)
     * 2. Loop through 1 to n
     *   2.1 find right and left sum
     *   2.2 if left is small call recursively dp (start, i)
     *   2.3 if right is small call recursively dp(i, end)
     *   2.4 if left == right take max of dp(start , i) and dp(i, end)
     *
     * Input:
     * ---------------
     * [6,2,3,4,5,5]
     *
     * PrefixNum:
     * ---------------
     * [6,8,11,15,20,25]
     *
     * dp(0,5) => max( 6, 10, 18, 15, 5)
     * 	i = 0 // Value = 6
     * 		leftSum = 6* , rightSum = 19
     * 			dp(0,0) // 0
     * 	i = 1 // Value = 10
     * 		leftSum = 8* ,rightSum = 19
     * 			dp(0,1) // 2
     * 				leftSum = 6 , rightSum = 2
     *
     * 	i = 2 // Value = 18 (11 + 7)
     * 		leftSum = 11* , rightSum = 14
     * 			dp(0,2) => max(7,3)
     * 				i = 0 // Value 7 (5+ 2)
     * 					leftSum =  6, rightSum =  5*
     * 						db(1,2) => 2
     * 							i = 1 // Value = 2
     * 								leftSum = 2* , rightSum = 3
     * 				i = 1 // Value 3
     * 					leftSum = 8, rightSum = 3*
     *
     * 	i = 3 // Value =  15 (10+5)
     * 		leftSum = 15, rightSum = 10*
     * 			dp(4,5) // 5
     *
     * 	i = 4 // Value = 5
     * 		leftSum = 20 , rightSum = 5*
     * 			dp(5,5) // 5
     *
     * @param stoneValue
     * @return
     */
    public int stoneGameV(int[] stoneValue) {
        int length = stoneValue.length;
        if (length == 0) return 0;
        int[] prefixSum = new int[length];
        prefixSum[0] = stoneValue[0];
        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + stoneValue[i];
        }
        Integer[][] memo = new Integer[length][length];
        return dp(prefixSum, 0, length - 1, memo);
    }

    private int dp(int[] prefixSum, int start, int end, Integer[][] memo) {
        if (end == start) return 0;

        if (memo[start][end] != null) return memo[start][end];

        int value = 0;
        for (int i = start; i < end; i++) {
            int temp = 0;
            int leftSum;
            if (start == 0) {
                leftSum = prefixSum[i];
            } else {
                leftSum = prefixSum[i] - prefixSum[start - 1];
            }
            int rightSum = prefixSum[end] - prefixSum[i];
            if (leftSum == rightSum) {
                temp = temp + rightSum;
                int leftSum1 = dp(prefixSum, start, i, memo);
                int rightSum1 = dp(prefixSum, i + 1, end, memo);
                temp = temp + Math.max(leftSum1, rightSum1);
            } else if (leftSum > rightSum) {
                temp = temp + rightSum;
                int rightSum1 = dp(prefixSum, i + 1, end, memo);
                temp = temp + rightSum1;
            } else {
                temp = temp + leftSum;
                int leftSum1 = dp(prefixSum, start, i, memo);
                temp = temp + leftSum1;
            }
            value = Math.max(temp, value);
        }
        memo[start][end] = value;
        return value;
    }
}
