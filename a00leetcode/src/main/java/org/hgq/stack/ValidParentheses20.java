package org.hgq.stack;

import org.hgq.CustStack;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author: huangguoqiang
 * @create: 2021-06-30 16:43
 **/
public class ValidParentheses20 {

    public static void main(String[] args) {
        ValidParentheses20 t = new ValidParentheses20();
        String s = "()";
        s = "()[]{}";
        s = "([)]";
        s = "{[]}";
        s = "";
        System.out.println(t.isValid(s));
    }

    public boolean isValid(String s) {
        // '('，')'，'{'，'}'，'['，']'
        CustStack<Character> stack = new CustStack<Character>();
        List<Character> leftCharacters = Arrays.asList('(', '{', '[');
        List<Character> rightCharacters = Arrays.asList(')', '}', ']');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (leftCharacters.contains(c)) {
                stack.push(c);
            } else if (rightCharacters.contains(c)) {
                //匹配
                Character pop = stack.pop();
                if (pop == null) {
                    return false;
                } else if (!pattern(pop, c)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean pattern(char source, char target) {
        if (source == '(') {
            return target == ')';
        } else if (source == '{') {
            return target == '}';
        } else if (source == '[') {
            return target == ']';
        }
        return false;
    }
}
