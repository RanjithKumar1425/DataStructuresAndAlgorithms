package my.learning.dsa.p17;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber lcpn = new LetterCombinationsOfAPhoneNumber();
        System.out.println(lcpn.letterCombinations("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    }

    /**
     * using Queue
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations_1(String digits) {

        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        while (queue.peek() != null) {
            String s = queue.poll();
            if (s.length() == digits.length()) {
                result.add(s);
            } else {
                List<String> letters = getNums2Letter(digits.charAt(s.length()));
                for (String letter : letters) {
                    queue.add(s + letter);
                }
            }
        }
        return result;
    }

    /**
     * Depth First and recursive approach
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations_2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        dfs(0, digits, "", result);
        return result;
    }

    public void dfs(int i, String digits, String currentStr, List<String> result) {
        if (currentStr.length() == digits.length()) {
            result.add(currentStr);
            return;
        }
        List<String> letters = getNums2Letter(digits.charAt(i));
        for (String letter : letters) {
            dfs(i + 1, digits, currentStr + letter, result);
        }
    }

    /**
     * Iterative and recursive
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        iterative("", digits, result);
        return result;
    }

    private void iterative(String currentStr, String digits, List<String> result) {
        if (digits.length() == 0) {
            result.add(currentStr);
            return;
        }
        Character currentDigitToExplore = digits.charAt(0);
        List<String> letters = getNums2Letter(currentDigitToExplore);
        for (String letter : letters) {
            currentStr = currentStr + letter;
            iterative(currentStr, digits.substring(1),result);
            currentStr = currentStr.substring(0, currentStr.length() - 1);

        }
    }


    public List<String> getNums2Letter(Character num) {
        HashMap<Character, List<String>> num2Letter = new HashMap<>();
        num2Letter.put('1', new ArrayList<>());
        num2Letter.put('2', Arrays.asList("a", "b", "c"));
        num2Letter.put('3', Arrays.asList("d", "e", "f"));
        num2Letter.put('4', Arrays.asList("g", "h", "i"));
        num2Letter.put('5', Arrays.asList("j", "k", "l"));
        num2Letter.put('6', Arrays.asList("m", "n", "o"));
        num2Letter.put('7', Arrays.asList("p", "q", "r", "s"));
        num2Letter.put('8', Arrays.asList("t", "u", "v"));
        num2Letter.put('9', Arrays.asList("w", "x", "y", "z"));
        return num2Letter.get(num);
    }

}
