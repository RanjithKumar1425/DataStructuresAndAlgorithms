package my.learning.dsa.p32;

import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses lvp = new LongestValidParentheses();
        System.out.println(lvp.longestValidParentheses("(()")); // 2
        System.out.println(lvp.longestValidParentheses(")()())")); // 4
        System.out.println(lvp.longestValidParentheses("")); // 0
        System.out.println(lvp.longestValidParentheses("()(()")); // 2
        System.out.println(lvp.longestValidParentheses(")()())()()(")); // 4
    }

    /**
     * With Stack : Time Complexity : O(N) Space Complexity : O(N)
     *
     * We have to identify the index at which the brackets are at wrong position.
     * Loop through the string 0 .. n-1
     *  if s[i] == '(' push the index to stack
     *  if s[i] == ')'
     *      check if s[index at yop of stack] == '('
     *          // it means that there is matching close bracket. remove the top index fromstack
     *      else this bracket is at wrong index . add that to stack
     *
     * end of loop
     *
     * Now the Stack contains the index where th brackets ar at wrong position.
     * if stack is empty then entire string is valid.
     *
     * Loop until the stack is empty
     *  find the difference in index value and find the max.
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_1(String s) {
        if (s.length() <= 1) return 0;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    if (s.charAt(stack.peek()) == '(') {
                        stack.pop();
                    } else {
                        stack.push(i);
                    }
                } else {
                    stack.push(i);
                }
            }
        }

        if (stack.isEmpty()) {
            max = s.length();
        } else {
            int a = s.length(), b = 0;
            while (!stack.empty()) {
                b = stack.pop();
                max = Math.max(max, a - b - 1);
                a = b;
            }
            max = Math.max(max, a);
        }


        return max;
    }

    /**
     * With Stack : Time Complexity : O(N) Space Complexity : O(N)
     *
     * Approach is same as above , we calculate the max while iterating the string
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_2(String s) {
        if (s.length() <= 1) return 0;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }

    /**
     * Without Stack : Time Complexity : O(N) Space Complexity : O(1)
     *
     * String can become Invalid if there is extrac Open/close Bracket which make them invalid.
     * To Identify  extra Close Bracket :
     * Initialize sum and len to 0
     * Loop from 0 .. n-1
     *  increase sum if s[i] = '(' else decrease sum
     *  if sum is -ve then we have found the  position where there is additional Closing Bracket
     *  if sum is +ve then we could find a valid Parentheses. going forward
     *      increment len
     *      If sum is zero , we have found valid Parentheses so far, check and update max.
     *
     * To Identify  extra Open Bracket :
     * Loop from  n-1 .. 0
     *  increase sum if s[i] = ')' else decrease sum
     *  if sum is -ve then we have found the  position where there is additional open Bracket
     *  if sum is +ve then we could find a valid Parentheses. going forward
     *      increment len
     *      If sum is zero , we have found valid Parentheses so far, check and update max.
     *
     * s = ")()())()()("
     * max = 0 ;
     * sum = 0 ;
     * currentvalidLen = 0 ;
     *
     * First Loop :
     * ---------------------------------
     * i =0
     * 	s[0] = ')'
     * 	sum = -1 // sum--
     * 		sum less than zero
     * 			sum = 0  currentvalidLen = 0
     *
     * i = 1
     * 	s[1] = '('
     * 	sum = 1 // sum++
     * 		sum greater than zero
     * 			currentvalidLen = 1 // currentvalidLen++
     *
     * i = 2
     * 	s[2] = ')'
     * 	sum = 0 // sum--
     * 		//sum greater than zero
     * 			currentvalidLen = 2 // currentvalidLen++
     * 				// sum = 0
     * 					max = 2
     *
     * i = 3
     * 	s[3] = '('
     * 	sum = 1 // sum++
     * 		sum greater than zero
     * 			currentvalidLen = 3 // currentvalidLen++
     *
     * i = 4,
     * 	s[4] = ')'
     * 	sum = 0 // sum--
     * 		//sum greater than zero
     * 			currentvalidLen = 4 // currentvalidLen++
     * 				// sum = 0
     * 					max = 4
     *
     * i = 5
     * 	s[5] = ')'
     * 	sum = -1 // sum--
     * 		sum less than zero
     * 			sum = 0  currentvalidLen = 0
     *
     * i = 6
     * 	s[6] = '('
     * 	sum = 1 // sum++
     * 		sum greater than zero
     * 			currentvalidLen = 1 // currentvalidLen++
     *
     * i = 7
     * 	s[7] = ')'
     * 	sum = 0 // sum--
     * 		//sum greater than zero
     * 			currentvalidLen = 2 // currentvalidLen++
     * 				// sum = 0
     * 					max = 4
     *
     * i =8
     * 	s[8] = '('
     * 	sum = 1 // sum++
     * 		sum greater than zero
     * 			currentvalidLen = 3 // currentvalidLen++
     *
     * i = 9
     * 	s[9] = ')'
     * 	sum = 0 // sum--
     * 		//sum greater than zero
     * 			currentvalidLen = 4 // currentvalidLen++
     * 				// sum = 0
     * 					max = 4
     *
     * i = 10
     * 	s[10]='('
     * 	sum = 1 // sum++
     * 		sum greater than zero
     * 			currentvalidLen = 4 // currentvalidLen++
     *
     * //
     * sum = 0 ;
     * currentvalidLen = 0 ;
     *
     * Second Loop : Traverse from Last
     * ---------------------------------
     * i = 10
     * 	s[10]='('
     * 	sum = -1 // sum--
     * 		sum less than zero
     * 			sum = 0  currentvalidLen = 0
     *
     * i = 9
     * 	s[9] = ')'
     * 	sum = 1 // sum++
     * 		//sum greater than zero
     * 			currentvalidLen = 1 // currentvalidLen++
     *
     * i = 8
     * 	s[8] = '('
     * 	sum = 0 // sum--
     * 		sum greater than zero
     * 			currentvalidLen = 2 // currentvalidLen++
     * 			// sum = 0
     * 					max = 4
     *
     * i = 7
     * 	s[7] = ')'
     * 	sum = 1 // sum++
     * 		//sum greater than zero
     * 			currentvalidLen = 3 // currentvalidLen++
     *
     * i = 6
     * 	s[6] = '('
     * 	sum = 0 // sum--
     * 		//sum greater than zero
     * 			currentvalidLen = 4 // currentvalidLen++
     * 			// sum = 0
     * 					max = 4
     *
     * i = 5,
     * 	s[5] = ')'
     * 	sum = 1 // sum++
     * 		//sum greater than zero
     * 			currentvalidLen = 5 // currentvalidLen++
     *
     *
     * i = 4
     * 	s[4] = ')'
     * 	sum = 2 // sum++
     * 		//sum greater than zero
     * 			currentvalidLen = 6 // currentvalidLen++
     *
     * i = 3
     * 	s[3] = '('
     * 	sum = 1 // sum--
     * 		sum greater than zero
     * 			currentvalidLen = 7 // currentvalidLen++
     *
     * i = 2
     * 	s[2] = ')'
     * 	sum = 2 // sum++
     * 		//sum greater than zero
     * 			currentvalidLen = 8 // currentvalidLen++
     *
     * i = 1
     * 	s[1] = '('
     * 	sum = 1 // sum--
     * 		sum greater than zero
     * 			currentvalidLen = 9 // currentvalidLen++
     *
     * i =0
     * 	s[0] = ')'
     * 	sum = 2 // sum++
     * 		sum greater than zero
     * 			currentvalidLen = 10 // currentvalidLen++
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_3(String s) {
        if (s.length() <= 1) return 0;
        int max = 0, sum = 0, currentValidlen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                sum++;
            } else {
                sum--;
            }

            if (sum < 0) {
                sum = currentValidlen = 0;
            } else {
                currentValidlen++;
                if (sum == 0)
                    max = Math.max(max, currentValidlen);
            }
        }

        sum = 0;
        currentValidlen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                sum++;
            } else {
                sum--;
            }

            if (sum < 0) {
                sum = currentValidlen = 0;
            } else {
                currentValidlen++;
                if (sum == 0)
                    max = Math.max(max, currentValidlen);
            }
        }

        return max;
    }

    /**
     * Dynamic Programming Approach. Time Complexity = O(N) Space Complexity = O(N)
     *
     * Create DP array of size of string length
     * if s[i] = '(' then we can't form a valid parentheses which end with '(', so dp[i] = 0
     * if s[i] = ')' then there are two possibilities
     *  Case 1 :
     *     if s[i-1] = '(' , then it forms a valid parentheses , also the s[i-2] can be valid sp dp[i] = dp[i-2] + 2
     *  Case 2 :
     *     if s[i-1] = ')', then this might enclose valid parentheses ending at i-1
     *                      if there is a '(' at i- dp[i-1]-1 position. dp[i-1] is lenght of valid parentheses
     *                      dp[i] = dp[i- dp[i-1]-2] + dp[i-1] + 2
     *                          dp[i-dp[i-1] -2] is take into consideration any valid parenthesis before '('
     *
     * s = ")()())()()("
     * dp[] = [0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 1
     * 	s[1] = '('
     * 	dp[] = [0,0,0,0,0,0,0,0,0,0,0]
     *
     * i = 2
     * 	s[2] = ')'
     * 	s[1] = '('
     * 	dp[] = [0,0,2,0,0,0,0,0,0,0,0]
     *
     * i = 3
     * 	s[3] = '('
     * 	dp[] = [0,0,2,0,0,0,0,0,0,0,0]
     *
     * i = 4,
     * 	s[4,] = ')'
     * 	s[3] = '('
     * 	dp[] = [0,0,2,0,4,0,0,0,0,0,0]
     *
     * i = 5
     * 	s[5] = ')'
     * 	s[4] = ')'
     * 	i-dp[i-1]-1 ==> 5-4-1 = 0
     * 	s[0] != '('
     * 	dp[] = [0,0,2,0,4,0,0,0,0,0,0]
     *
     *
     * i = 6
     * 	s[6] = '('
     * 	dp[] = [0,0,2,0,4,0,0,0,0,0,0]
     *
     * i = 7
     * 	s[7] = ')'
     * 	s[6] = '('
     * 	dp[] = [0,0,2,0,4,0,0,2,0,0,0]
     *
     * i =8
     * 	s[8] = '('
     * 	dp[] = [0,0,2,0,4,0,0,2,0,0,0]
     *
     * i = 9
     * 	s[9] = ')'
     * 	s[8] = '('
     * 	dp[] = [0,0,2,0,4,0,0,2,0,4,0]
     *
     * i= 10
     * 	s[10]='('
     * 	dp[] = [0,0,2,0,4,0,0,2,0,4,0]
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s.length() <= 1) return 0;
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                dp[i] = 0;
            } else {
                char c1 = s.charAt(i - 1);
                if (c1 == '(') {
                    dp[i] = (i - 2 > 0 ? dp[i - 2] : 0) + 2;
                    max = Math.max(max, dp[i]);
                } else {
                    if ((i - dp[i - 1] - 1) > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = i - (dp[i - 1] - 2 > 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                        max = Math.max(max, dp[i]);
                    }
                }
            }
        }
        return max;
    }

}
