package my.learning.dsa.p214;

public class ShortestPalindrome {

    public static void main(String[] args) {
        ShortestPalindrome sp = new ShortestPalindrome();
        System.out.println(sp.shortestPalindrome("aacecaaa")); // aaacecaaa
        System.out.println(sp.shortestPalindrome("abcd")); // dcbabcd
    }

    /**
     * Iterative Approach
     *
     * @param s
     * @return
     */
    public String shortestPalindrome_1(String s) {
        if (s == null || s.length() == 0) return "";

        int i = 0;
        for (i = s.length() - 1; i >= 0; i--) {
            int start = 0;
            int end = i;
            while (start <= end) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                start++;
                end--;
            }

            if (start > end) {
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int j = s.length() - 1; j >= i + 1; j--) {
            sb.append(s.charAt(j));
        }

        return sb + s;
    }


    /**
     * Dynamic Programming
     *
     * @param s
     * @return
     */
    public String shortestPalindrome_2(String s) {

        boolean[][] dp = new boolean[s.length()][s.length()];

        int maxlen = 0;
        for (int l = 1; l <= s.length(); l++) {
            for (int i = 0; i < s.length() - l + 1; i++) {
                int j = l + i - 1;
                boolean b = s.charAt(i) == s.charAt(j);
                if (b) {
                    if (l <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (i == 0) {
                            maxlen = l;
                        }
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = s.length() - 1; i > maxlen - 1; i--) {
            sb.append(s.charAt(i));
        }
        return sb + s;
    }

    /**
     * KMP(Knuth–Morris–Pratt) algorithm.
     *
     * Use the KMP Table to identity the Longest Prefix which is also Suffix.
     *
     * We construct  temporary string which is actual string delimiter by # and reversed string.
     * We construct KMP Table for the temporary String. The last index value is the Longest prefix which is suffix
     *
     * Then we construct the Shortest Palindrome by revering the remaining chars after Longest prefix and adding to original String.
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {

        String rs = new StringBuilder(s).reverse().toString();
        String newStr = s + "#" + rs;
        int[] kmp_f = constructKMPArray(newStr);
        return new StringBuilder(s.substring(kmp_f[kmp_f.length - 1])).reverse() + s;
    }

    /**
     * Construct KMP Lookup array
     *
     * temp = aacecaaa#aaacecaa
     * kmp []  = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * n = 17
     * index = 0
     *
     *
     * i = 1
     *     // 'a' s.charAt(i->1)
     *     // 'a' s.charAt(index->0)
     *     // s.charAt(i) == s.charAt(index)
     *     index = 1
     *     kmp []  = [0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 2
     *     // 'c' s.charAt(i->2)
     *     // 'a' s.charAt(index->1)
     *     // s.charAt(i) != s.charAt(index)
     *         // while loop
     *             Iteration 1
     *                 index = 0 // index = kmp[index - 1]
     *         // s.charAt(i) != s.charAt(index)
     *     kmp []  = [0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 3
     *     // 'e' s.charAt(i ->3)
     *     // 'a' s.charAt(index->0)
     *     // s.charAt(i) != s.charAt(index)
     *         // while loop
     *                 Iteration 1
     *                     index = 0 // index = kmp[index - 1]
     *         // s.charAt(i) != s.charAt(index)
     *     kmp []  = [0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 4
     *     // 'c' s.charAt(i ->4)
     *     // 'a' s.charAt(index->0)
     *     // s.charAt(i) != s.charAt(index)
     *         // while loop
     *             Iteration 1
     *                 index = 0 // index = kmp[index - 1]
     *         // s.charAt(i) != s.charAt(index)
     *     kmp []  = [0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 5
     *     // 'a' s.charAt(i ->5)
     *     // 'a' s.charAt(index->0)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 1
     *     kmp []  = [0,1,0,0,9,1,0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 6
     *     // 'a' s.charAt(i ->6)
     *     // 'a' s.charAt(index->1)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 2
     *     kmp []  = [0,1,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 7
     *     // 'a' s.charAt(i ->7)
     *     // 'c' s.charAt(index->2)
     *     // s.charAt(i) != s.charAt(index)
     *          // while loop
     *                 Iteration 1
     *                     index = 1 // index = kmp[index - 1]
     *         //'a' s.charAt(index->1)
     *         // s.charAt(i) == s.charAt(index)
     *             index = 2
     *     kmp []  = [0,1,0,0,0,1,2,2,0,0,0,0,0,0,0,0,0]
     *
     * i = 8
     *     // '#' s.charAt(i ->8)
     *     // 'c' s.charAt(index->2)
     *     // s.charAt(i) != s.charAt(index)
     *          // while loop
     *                 Iteration 1
     *                     index = 1 // index = kmp[index - 1]
     *                 Iteration 2 //
     *                     index = 0 // index = kmp[index - 1]
     *         //'a' s.charAt(index->0)
     *         // s.charAt(i) != s.charAt(index)
     *     kmp []  = [0,1,0,0,0,1,2,2,0,0,0,0,0,0,0,0,0]
     *
     * i = 9
     *     // 'a' s.charAt(i ->8)
     *     // 'a' s.charAt(index->0)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 1
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,0,0,0,0,0,0,0]
     *
     * i = 10
     *     // 'a' s.charAt(i ->10)
     *     // 'a' s.charAt(index->1)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 2
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,0,0,0,0,0,0]
     *
     * i = 11
     *     // 'a' s.charAt(i ->11)
     *     // 'c' s.charAt(index->2)
     *     // s.charAt(i) != s.charAt(index)
     *          // while loop
     *                 Iteration 1
     *                     index = 1 // index = kmp[index - 1]
     *         //'a' s.charAt(index->1)
     *         // s.charAt(i) == s.charAt(index)
     *             index = 2
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,2,0,0,0,0,0]
     *
     * i = 12
     *     // 'c' s.charAt(i ->12)
     *     // 'c' s.charAt(index->2)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 3
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,2,3,0,0,0,0]
     *
     * i = 13
     *     // 'e' s.charAt(i ->13)
     *     // 'e' s.charAt(index->3)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 4
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,2,3,4,0,0,0]
     *
     * i = 14
     *     // 'c' s.charAt(i ->14)
     *     // 'c' s.charAt(index->4)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 5
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,2,3,4,5,0,0]
     *
     *
     * i = 15
     *     // 'a' s.charAt(i ->15)
     *     // 'a' s.charAt(index->5)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 6
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,2,3,4,5,6,0]
     *
     * i = 16
     *     // 'a' s.charAt(i ->16)
     *     // 'a' s.charAt(index->6)
     *     // s.charAt(i) == s.charAt(index)
     *         index = 7
     *     kmp []  = [0,1,0,0,0,1,2,2,0,1,2,2,3,4,5,6,7]
     *
     * @param s
     * @return
     */
    private int[] constructKMPArray(String s) {
        int[] kmp_f = new int[s.length()];
        kmp_f[0] = 0;

        for (int i = 1; i < s.length() ; i++) {
            int preI = kmp_f[i - 1];
            if (s.charAt(i) == s.charAt(preI)) {
                preI++;
            } else {
                while (preI > 0 && s.charAt(i) != s.charAt(preI)) {
                    preI = kmp_f[preI - 1];
                }
                if (s.charAt(preI) == s.charAt(i)) {
                    preI++;
                }
            }
            kmp_f[i] = preI;
        }

        return kmp_f;
    }


}
