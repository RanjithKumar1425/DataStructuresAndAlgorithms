package my.learning.dsa.p07;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(123)); //321
        System.out.println(ri.reverse(120)); //21
        System.out.println(ri.reverse(-120)); //-21
        System.out.println(ri.reverse(1534236469)); //0
    }

    /**
     * Steps:
     * 1. Loop until number not zero.
     *      2.1 calculate the remainder and temporary_result
     *      2.3 to check overflow , check if (temporary_result -remainder)/10 not eq result. return zero
     *      2.4. assign result to temporary_result
     * 3. return result
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int rem = x % 10;
            int tempResult = result * 10 + rem;
            if ((tempResult - rem) / 10 != result) {
                return 0;
            }
            result = tempResult;
            x = x / 10;
        }
        return result;
    }
}
