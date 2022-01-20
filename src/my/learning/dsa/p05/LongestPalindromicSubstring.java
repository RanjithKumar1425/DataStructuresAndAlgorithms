package my.learning.dsa.p05;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring sol = new LongestPalindromicSubstring();
        System.out.println(sol.longestPalindrome("babad")); //bab
        System.out.println(sol.longestPalindrome("cbbd")); //bb
        System.out.println(sol.longestPalindrome("a")); //a
        System.out.println(sol.longestPalindrome("ac")); //a
        System.out.println(sol.longestPalindrome("bb")); //bb
        System.out.println(sol.longestPalindrome("caba")); //aba
    }

    /**
     * Brute Force approach where we check if each substing is palindrome or not.
     * O(N^2) for finding all Substring combination and O(N) for check is Substring is palindrome or not.
     * Overall Complexity O(N^3)
     *
     * @param s
     * @return
     */
    public String longestPalindrome_BruteForce(String s) {
        if (s.length() == 1) {
            return s;
        }
        String longestPal = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                String sub = s.substring(i, j);
//               System.out.println(sub);
                if (isPalindrom(sub)) {
                    if (sub.length() > longestPal.length()) {
                        longestPal = sub;
                    }
                }
            }
        }
        return longestPal;
    }

    private boolean isPalindrom(String sub) {
        int l = sub.length();
        for (int i = 0; i < l / 2; i++) {
            if (sub.charAt(i) != sub.charAt(l - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Dynamic Programming Approach where we use the palindroming property that if Substring of N is a palindrome
     * then substring N-2(first and last char removed) is also palindrome.
     * <p>
     * i.e If ABBCBBA is palindrome the BBCBB is also palindrome.
     * <p>
     * We store if smaller substring palindrome is not to compute bigger substring palindrome.
     * <p>
     * Time Complexity of this approach in O(N^2) and space complexity is also O(N^2).
     * <p>
     * Start by Checking the if substirng of size 1 .. n is palindrom or not. Save the result in DP array.
     *
     * @param s
     * @return
     */
    public String longestPalindrome_DynamicProgramming_1(String s) {

        boolean[][] dp = new boolean[s.length()][s.length()];

        int li = 0, ri = 0;
        int maxlen = 0;

        for (int l = 1; l <= s.length(); l++) {
            for (int i = 0; i < s.length() - l + 1; i++) {
                int j = i + l - 1;
                boolean b = s.charAt(i) == s.charAt(j);
                if (b) {
                    if (l <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (l > maxlen) {
                            li = i;
                            ri = j;
                            maxlen = l;
                        }
                    }
                }
            }
        }
        return s.substring(li, ri + 1);

    }


    /**
     * We can reduce the O(N^2) Space complexity in above approach by tweaking the looping logic in previous DP logic.
     *
     * In previous DP approach we looped for every 1 .. N length substring from index 0 .. (N -L +1).
     * Now we will loop from i,j index from N-1 .. 0
     *
     * By doing so we store the DP of last row alone in 1D array.
     *
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        boolean[] dp = new boolean[s.length()];

        int li = 0, ri = 0;
        int maxlen = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = s.length() - 1; j >= i; j--) {
                int l = j -i + 1;
                boolean b = s.charAt(i) == s.charAt(j);
                dp[j] = b && (l <= 2 || dp[j - 1]);
                if (dp[j]) {
                    if (l >= maxlen) {
                        li = i;
                        ri = j;
                        maxlen = l;
                    }
                }
            }
        }
        return s.substring(li, ri + 1);
    }
}
