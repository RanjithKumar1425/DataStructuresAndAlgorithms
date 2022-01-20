package my.learning.dsa.p03;

import java.util.LinkedHashSet;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
 */

class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters t = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(t.lengthOfLongestSubstring("pwwkew"));
        System.out.println(t.lengthOfLongestSubstring("qrsvbspk"));
    }

    /**
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        LinkedHashSet<Character> chars = new LinkedHashSet<>();

        int maxSeq = 0;
        for (int i = 0; i < s.length(); i++) {
            int t = chars.size();
            while (i < s.length() && !chars.contains(s.charAt(i))) {
                chars.add(s.charAt(i));
                t++;
                i++;
            }
            if (t > maxSeq) {
                maxSeq = t;
            }
            int rm = 0;
            if (i < s.length()) {
                while (chars.contains(s.charAt(i))) {
                    chars.remove(s.charAt(i - t + rm));
                    rm++;
                }
                chars.add(s.charAt(i));
            }
        }
        return maxSeq;
    }
}