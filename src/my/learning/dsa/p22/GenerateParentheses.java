package my.learning.dsa.p22;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        System.out.println(gp.generateParenthesis(3)); //["((()))","(()())","(())()","()(())","()()()"]
        System.out.println(gp.generateParenthesis(1)); // ["()"]
    }

    /**
     * Brute Force
     * <p>
     * Generate all 2^(2*N) combination  and check if the combination is valid or not.
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_1(int n) {
        List<String> result = new ArrayList<>();
        backtrackingAll("", n, result);
        return result;
    }

    private void backtrackingAll(String str, int n, List<String> result) {
        if (str.length() == n * 2) {
            if (valid(str))
                result.add(str);
        } else {
            backtrackingAll(str + "(", n, result);
            backtrackingAll(str + ")", n, result);
        }
    }

    private boolean valid(String str) {
        int openCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                openCount++;
            } else if (openCount > 0) {
                openCount--;
            } else {
                return false;
            }
        }

        return openCount == 0;
    }


    /**
     * Backtracking
     * <p>
     * We keep track of no of Open and Close Brackets. And generate only valid combinations.
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtracking("", 0, 0, n, result);
        return result;
    }

    private void backtracking(String str, int opencnt, int closecnt, int n, List<String> result) {
        if (str.length() == n * 2) {
            if (valid(str))
                result.add(str);
        } else {
            if (opencnt < n)
                backtracking(str + "(", opencnt + 1, closecnt, n, result);
            if (closecnt < opencnt) // add close Bracket only when there is matching open bracket.
                backtracking(str + ")", opencnt, closecnt + 1, n, result);
        }
    }

}
