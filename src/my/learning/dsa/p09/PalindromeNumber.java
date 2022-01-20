package my.learning.dsa.p09;

public class PalindromeNumber {

    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        System.out.println(pn.isPalindrome(121)); //true
        System.out.println(pn.isPalindrome(123)); //false
        System.out.println(pn.isPalindrome(-121)); //false
    }

    /**
     *
     * 1. Negative Numbers are always not palindrome.
     * 2. All Number which end with zero and not zero is not palindrome.
     * 3. Convert the no and check if that matches.
     *
     * @param x
     * @return
     */
    public boolean isPalindrome_1(int x) {
        if (x < 0) {
            return false;
        }
        int sourceInt = x;
        int reverseInt = 0;
        while (sourceInt != 0) {
            int rem = sourceInt % 10;
            reverseInt = reverseInt * 10 + rem;
            sourceInt /= 10;
        }
        return reverseInt == x;
    }

    /**
     * Above approach converts entire string and doesn't consider overflow of int conversion.
     * We can reduce the execution to half.
     * 1. For Even digit if check if first half is equal ot not to second half
     *      To find the halfway mark, start reversing the int , until the reversedInt is less than remaining int
     *      If they are equal, then int is palindrome.
     * 3. For Odd Number discard the last digit of reversedInt and check with remaining int.
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int sourceInt = x;
        int reverseInt = 0;
        while (sourceInt > reverseInt) {
            reverseInt = reverseInt * 10 + sourceInt % 10;
            sourceInt /= 10;
        }
        return reverseInt == sourceInt || reverseInt / 10 == sourceInt;
    }
}
