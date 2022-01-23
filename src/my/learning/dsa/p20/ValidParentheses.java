package my.learning.dsa.p20;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid("()")); //true
        System.out.println(vp.isValid("()[]{}")); //true
        System.out.println(vp.isValid("(]")); //false
    }

    /**
     * Using Stack
     * 1. Loop through all chars in the String
     * 1.1. if char is '{', '(', '[' add to stack
     * 1.2. if otherwise remove the element from Stack and check if char is matching open of the Close brackets.
     * 2. return true if stack is empty else dasle
     *
     * @param s
     * @return
     */
    public boolean isValid_1(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                stack.add(c);
            } else {
                if (stack.empty())
                    return false;
                char open = stack.pop();
                switch (c) {
                    case '}':
                        if (open != '{')
                            return false;
                        break;
                    case ')':
                        if (open != '(')
                            return false;
                        break;
                    case ']':
                        if (open != '[')
                            return false;
                        break;
                }
            }
        }
        return stack.empty();
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                stack.add('}');
            } else if (c == '(') {
                stack.add(')');
            } else if (c == '[') {
                stack.add(']');
            } else if (stack.empty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.empty();
    }
}
