package my.learning.dsa.p10;

public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();
        System.out.println(rem.isMatch("aa", "a")); // false
        System.out.println(rem.isMatch("aa", "a.")); // true
        System.out.println(rem.isMatch("as", ".*")); // true
        System.out.println(rem.isMatch("aa", "a*")); // true
        System.out.println(rem.isMatch("aab", "c*a*b")); //true
        System.out.println(rem.isMatch("aaa", "a*a")); //true
    }

    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length()];
        return checkMatch(0, s, 0, p, memo);
    }

    /**
     * Recursive & BackTracking Approach with memoization
     *
     * @param si string index
     * @param s string
     * @param pi pattern index
     * @param p pattern
     * @return
     */
    public boolean checkMatch(int si, String s, int pi, String p, Boolean[][] memo) {
        if (si >= s.length() && pi >= p.length()) {
            return true;
        }
        if (pi >= p.length()) {
            return false;
        }
        if (memo[si][pi] != null) {
            return memo[si][pi];
        }

        boolean match = si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.');
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
            memo[si][pi] = checkMatch(si, s, pi + 2, p, memo) || (match && checkMatch(si + 1, s, pi, p, memo));
            return memo[si][pi];
        } else if (match) {
            memo[si][pi] = checkMatch(si + 1, s, pi + 1, p, memo);
            return memo[si][pi];
        }
        memo[si][pi] = false;
        return memo[si][pi];
    }

    /**
     * Recursive & BackTracking Approach
     *
     * Exit Conditions :
     *  1. if Both String Index and Pattern Index length are greater than their respective length.
     *     Pattern is matched since all index are processed. return true.
     *  2. if pattern index is greater than its length. Still part of String is left . return false
     *
     * Matching Conditions :
     *  1. checking char and '.' are straight forward.
     *     If String at si and Pattern at pi matches , continue checking the next indices.
     *  2.
     *
     * @param si string index
     * @param s string
     * @param pi pattern index
     * @param p pattern
     * @return
     */
    public boolean checkMatch_1(int si, String s, int pi, String p) {
        if (si >= s.length() && pi >= p.length()) {
            return true;
        }
        if (pi >= p.length()) {
            return false;
        }

        boolean match = si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.');
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
            return checkMatch_1(si, s, pi + 2, p) || (match && checkMatch_1(si + 1, s, pi, p));
        } else if (match) {
            return checkMatch_1(si + 1, s, pi + 1, p);
        }
        return false;
    }


    public boolean isMatch_failed(String s, String p) {

        int i = 0;
        int pi = 0;
        while (i < s.length() && pi < p.length()) {
            char pc = p.charAt(pi);
            if (pc == '.') {
                i++;
                pi++;
            } else if (pc == '*') {
                char prs = s.charAt(i - 1);
                char prp = p.charAt(pi - 1);
                pi++;
                if (pi == p.length()) {
                    return true;
                }
                if (prp == '.') {
                    while (i < s.length() && s.charAt(i) == p.charAt(pi)) {
                        i++;
                    }
                } else {
                    while (i < s.length() && s.charAt(i) == prs && prs != p.charAt(pi)) {
                        i++;
                    }
                }
            } else if (s.charAt(i) == pc) {
                i++;
                pi++;
            } else if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
                pi += 2;
            } else {
                return false;
            }
        }

        return i == s.length() && pi == p.length();

    }
}
