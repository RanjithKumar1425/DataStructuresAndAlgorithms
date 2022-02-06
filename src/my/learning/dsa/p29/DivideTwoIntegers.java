package my.learning.dsa.p29;

public class DivideTwoIntegers {

    public static void main(String[] args) {
        DivideTwoIntegers dti = new DivideTwoIntegers();
        System.out.println(dti.divide(10, 3)); // 3
        System.out.println(dti.divide(7, -3)); // -2
        System.out.println(dti.divide(Integer.MAX_VALUE, -1)); // -2148473647
        System.out.println(dti.divide(Integer.MIN_VALUE, -1)); // 2148473647
    }

    /**
     * Boundary/Edge Case:
     * 1. When dividend == Integer.MIN_VALUE and  divisor is -1 then there is overflow can happen. return Integer.MAX_VALUE
     * 2. if dividend(after abs) greater than zero and divisor is zero then return MAX/MIN based on sign.
     *
     * Logic:
     *      1. Initialize result to ZERO
     *      2. Loop until dividend is greater than divisor
     *          2.1 Initialize counter to ZERO
     *          2.2 Loop  until Double the divisor is greater than dividend. Use Bitwise left shift operator to do doubling.
     *          2.3 Update result how many times' divisor is doubled
     *          2.4 Reduce dividend by recent doubled divisor
     *
     * dividend = 10
     * divisor = 3
     * result = 0
     * While Loop Iteration 1: (10-3) >= 0
     *      count = 0
     *          while loop Iteration 1: (10-(3<<1<<0)) >=0 // (10-6) >=0
     *              count = 1
     *          while Loop Iteration 2: (10-(3<<1<<2) >=0 // (10 - 12) >=0 // false
     *      result = 2 // result = result + 1<<2 // result =  0 + 2
     *      dividend = 4 // dividend = 10 - 3<<2  // divident = 10 - 6
     *
     * while loop Iteration 2 : (4-3 ) >= 0
     *      count = 0
     *          while loop Iteration 1: (4-(3<<1<<0)) >=0 // (4-6) >=0 //false
     *      result = 3 // result = result + 1<<0 // result = 2 + 1
     *      divident = 1 //  dividend =  4 - 3<<0 // result = 4-3
     *
     * while loop Iteration 3 : (1-3 ) >= 0 // false
     *
     * quotient = 3
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        boolean sign = dividend > 0 == divisor > 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (dividend > 0 && divisor == 0) return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        int result = 0;
        while (dividend - divisor >= 0) {
            int count = 0;
            while ((dividend - (divisor << 1 << count)) >= 0) {
                count++;
            }
            result += 1 << count;
            dividend -= divisor << count;
        }
        return sign ? result : -result;
    }

}
