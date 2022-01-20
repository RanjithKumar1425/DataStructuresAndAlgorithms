package my.learning.dsa.p14;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println(lcp.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(lcp.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    /**
     * Horizontal comparison
     * Have first String as reference and compare the remaining string until chars matches
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix_1(String[] strs) {
        String refStr = strs[0];
        int index = refStr.length();
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < index) {
                String cStr = strs[i];
                if (!(j < cStr.length() && refStr.charAt(j) == cStr.charAt(j))) break;
                j++;
            }
            index = j;
        }
        return refStr.substring(0, index);
    }

    /**
     * Vertical Comparison
     * Compare the column wise from top to bottom until the chars didn't match.
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix_2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * Divide and Conquer approach. Time Complexity : O(N*M)
     * LCP of S1, S2 , S3 can be found by LCP(LCP (S1, S2), S3)
     * <p>
     * We recursively split and find LCP of the array
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix_3(String[] strs) {
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        }
        int mid = (start + end) / 2;
        String l_lcp = longestCommonPrefix(strs, start, mid);
        String r_lcp = longestCommonPrefix(strs, mid + 1, end);
        return commonPrefix(l_lcp, r_lcp);
    }

    private String commonPrefix(String l_lcp, String r_lcp) {
        int minLength = Math.min(l_lcp.length(), r_lcp.length());
        for (int i = 0; i < minLength; i++) {
            if (l_lcp.charAt(i) != r_lcp.charAt(i)) {
                return l_lcp.substring(0, i);
            }
        }
        return l_lcp.substring(0, minLength);
    }

    /**
     * Binary Search Approach. Time Complexity : O(N * log(min M))
     * <p>
     * We find LCP by applying Binary Search Approach
     * <p>
     * We apply the Binary Search
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix_4(String[] strs) {

        int minlength = Integer.MAX_VALUE;
        for (String str : strs) {
            minlength = Math.min(minlength, str.length());
        }

        int low = 1;
        int high = minlength;
        int mid = (low + high) / 2;
        String lcp = "";
        while (low <= high) {
            if (isLCP(strs, low, mid)) {
                lcp = strs[0].substring(0, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }

        return lcp;

    }

    private boolean isLCP(String[] strs, int start, int len) {
        String str = strs[0].substring(0, len);
        for (int i = 0; i < strs.length; i++) {
            for (int j = start - 1; j < len; j++) {
                if (str.charAt(j) != strs[i].charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Trie Approach - Time Complexity
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        return  null;
    }

}
