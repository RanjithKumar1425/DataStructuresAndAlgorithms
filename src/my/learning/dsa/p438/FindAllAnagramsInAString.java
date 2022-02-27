package my.learning.dsa.p438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        FindAllAnagramsInAString sol = new FindAllAnagramsInAString();
        System.out.println(sol.findAnagrams("cbaebabacd", "abc")); // [0,6]
        System.out.println(sol.findAnagrams("abab", "ab")); // [0,1,2]
        System.out.println(sol.findAnagrams("baa", "aa")); //  [1]
    }

    /**
     * Brute Force approach
     * <p>
     * Generate Each anagram and check if that exit in string.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams_1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> chars = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (chars.containsKey(c)) {
                chars.put(c, chars.get(c) + 1);
            } else {
                chars.put(c, 1);
            }
        }
        findAnagrams(s, p.length(), chars, "", result);
        return result;
    }

    private void findAnagrams(String s, int pn, HashMap<Character, Integer> p, String currentAnagram, List<Integer> result) {
        if (currentAnagram.length() == pn) {
            checkAndAddIndexToResult(s, currentAnagram, result);
            return;
        }
        for (HashMap.Entry<Character, Integer> entry : p.entrySet()) {
            Integer count = entry.getValue();
            if (count == 0) continue;

            currentAnagram = currentAnagram + entry.getKey();
            p.put(entry.getKey(), count - 1);
            findAnagrams(s, pn, p, currentAnagram, result);
            p.put(entry.getKey(), count);
            currentAnagram = currentAnagram.substring(0, currentAnagram.length() - 1);

        }
    }

    /**
     * KMP Search algorithm for pattern search
     *
     * @param s
     * @param currentAnagram
     * @param result
     */
    private void checkAndAddIndexToResult(String s, String currentAnagram, List<Integer> result) {
        int[] lps = constructKMPArray(currentAnagram);
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == currentAnagram.charAt(j)) {
                i++;
                j++;
                if (j == currentAnagram.length()) {
                    result.add(i - j);
                    j = lps[j - 1];
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    private int[] constructKMPArray(String currentAnagram) {
        int[] lps = new int[currentAnagram.length()];
        int length = 0, index = 1;
        while (index < currentAnagram.length()) {
            if (currentAnagram.charAt(length) == currentAnagram.charAt(index)) {
                lps[index] = ++length;
                index++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[index] = length;
                    index++;
                }
            }
        }
        return lps;
    }

    /**
     * Sliding Window approach
     * <p>
     * Construct two array of size 26 one for Pattern and String each.
     * <p>
     * Process the Pattern and update the array.
     * <p>
     * Initially process the String until the Pattern's length and update the array.
     * Then compare both the array of they same , if match then Anagram is present.
     * <p>
     * Loop the String until the end of the length
     * Remove first element and add the last element
     * compare both array and check if the match.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams_2(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        if (p == null || p.length() == 0) {
            return result;
        }

        if (p.length() > s.length()) {
            return result;
        }

        int[] pattern = new int[26];
        int[] string = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pattern[p.charAt(i) - 'a']++;
        }
        int start = 0;
        int end = 0;

        for (end = 0; end < p.length(); end++) {
            string[s.charAt(end) - 'a']++;
        }

        // Note: Time complexity for Arrays.equals is O(26)
        if (Arrays.equals(pattern, string)) {
            result.add(start);
        }

        //sliding window part
        while (end < s.length()) {
            // remove char at index start
            string[s.charAt(start) - 'a']--;
            //add char at index end
            string[s.charAt(end) - 'a']++;
            start++;
            end++;
            if (Arrays.equals(pattern, string)) {
                result.add(start);
            }
        }

        return result;
    }

    /**
     * Sliding Window Approach
     * Similar to above method but we use single array use that to find the anagram by using a tracking variable to have
     * matching no of chars
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()) {
            return result;
        }

        int[] chars = new int[26];

        for (int i = 0; i < p.length(); i++) {
            chars[p.charAt(i) - 'a']++;
        }

        int start = 0;
        int end = 0;
        int pattrenLength = p.length();
        int diff = pattrenLength; // variable to keep track of no of mismatch characters

        // preparing the first sliding Window
        for (; end < pattrenLength; end++) {
            char c = s.charAt(end);
            chars[c - 'a']--;

            if (chars[c - 'a'] >= 0) {
                diff--;
            }

        }
        //if diff is zero the there is a matching anagram in initial window.
        if (diff == 0) {
            result.add(0);
        }

        while (end < s.length()) {
            char c = s.charAt(start);
            // if char has value greater than zero then its part of the anagram we found.
            // since are moving the window we need to increment the diff.
            if (chars[c - 'a'] >= 0) {
                diff++;
            }

            // Reset the chars so that it is removed from window.
            chars[c - 'a']++;

            c = s.charAt(end);

            //Char is added to the window.
            chars[c - 'a']--;

            if (chars[c - 'a'] >= 0) {
                diff--;
            }

            start++;
            end++;

            if (diff == 0) {
                result.add(start);
            }
        }

        return result;
    }


}
