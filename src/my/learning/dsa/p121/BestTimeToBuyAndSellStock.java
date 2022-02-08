package my.learning.dsa.p121;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock sol = new BestTimeToBuyAndSellStock();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1})); // 0
    }

    /**
     * Calculate Min array from start and max array from end.
     * Find the Max difference between the min and Max array at each index which give the max profit
     * @param prices
     * @return
     */
    public int maxProfit_1(int[] prices) {
        int result = 0;

        int length = prices.length;
        int[] minArray = new int[length];
        int[] maxArray = new int[length];

        minArray[0] = prices[0];
        maxArray[length - 1] = prices[length - 1];
        for (int i = 1; i < length; i++) {
            minArray[i] = Math.min(prices[i], minArray[i-1]);
            maxArray[length - i - 1] = Math.max(prices[length - i-1], maxArray[length - i]);
        }

        for (int i = 0; i < length-1; i++) {
            result = Math.max(result, maxArray[i]-minArray[i]);
        }

        return result;
    }

    /**
     * Instead of finding Max and Min, we can just keep track of Min value and subtract with value after that to
     * check if that is max or not.
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        int length = prices.length;
        int low = prices[0];

        for (int i = 1; i < length; i++) {
            low = Math.min(prices[i], low);
            result= Math.max(result , prices[i]-low);
        }
        return result;
    }
}
