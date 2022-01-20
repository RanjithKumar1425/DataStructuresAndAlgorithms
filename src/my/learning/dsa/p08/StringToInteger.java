package my.learning.dsa.p08;

public class StringToInteger {
    public static void main(String[] args) {
        StringToInteger si = new StringToInteger();
        System.out.println(si.myAtoi("    12345")); // 12345
        System.out.println(si.myAtoi("    +12345asada3232")); // 12345
        System.out.println(si.myAtoi("    -12345")); // -12345
        System.out.println(si.myAtoi("    +-12345")); // 0
        System.out.println(si.myAtoi("98765432102134")); // 2147483647
        System.out.println(si.myAtoi("-98765432102134")); // -2147483648
        System.out.println(si.myAtoi("2147483648")); // 2147483647
        System.out.println(si.myAtoi("-2147483649")); //-2147483648
        System.out.println(si.myAtoi("   +-2147483649")); //0
    }

    /**
     * Steps:
     * 1. Ignore initial spaces until non-space char is found
     * 2. Check if +/_ is found after initial spaces. Not found consider +ve.
     * 3. Loop until end of chars or until not '0'...'9'
     *      3.1  To check for Overflow/Underflow .
     *           Check if INT_MAX % 10 < Result,  then adding Next digit will overflow/underflow.
     *           Check if INT_MAX % 10 == Result, then adding next greater than 7/8 will make overflow/underflow for +ve/-ve numbers.
     *              We can avoid the no of condition check by just checking INT_MAX % 10 == Result and number > 7 and decide overflow/underflow.
     * 4. finally, return result based in +/- sign found.
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int index = 0;
        boolean isPositiveNo = true;
        int result = 0;

        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        char c = s.charAt(index);
        if (c == '+') {
            isPositiveNo = false;
        } else if (c == '-') {
            isPositiveNo = false;
        }
        while (index < n) {
            c = s.charAt(index);
            if (c >= '0' && c <= '9') {
                int num = Integer.parseInt("" + c);
                if (Integer.MAX_VALUE / 10 < result || (Integer.MAX_VALUE / 10 == result && num > 7)) {
                    return isPositiveNo ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                result = result * 10 + num;
            } else {
                break;
            }
            index++;
        }
        return isPositiveNo ? result : -result;
    }
}
