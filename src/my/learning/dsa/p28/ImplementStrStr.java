package my.learning.dsa.p28;

public class ImplementStrStr {
    public static void main(String[] args) {
        ImplementStrStr is = new ImplementStrStr();
        System.out.println(is.strStr("hello", "ll"));
        System.out.println(is.strStr("a", "a"));
    }

    /**
     * Iterative approach
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int n_l = needle.length();
        if (n_l == 0) return 0;
        int h_L = haystack.length();
        if (h_L == 0) return -1;
        if (n_l > h_L) return -1;

        for (int i = 0; i <= h_L - n_l; i++) {
            int first = 0;
            int second = n_l - 1;
            boolean found = true;
            while (first <= second) {
                if (haystack.charAt(i + first) != needle.charAt(first) || haystack.charAt(i + second) != needle.charAt(second)) {
                    found = false;
                    break;
                }
                first++;
                second--;
            }
            if (found) {
                return i;
            }
        }
        return -1;
    }
}
