package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-01 11:04
 **/
public class StackApp {

    public static void main(String[] args) {
        CustStack<Integer> stack = new CustStack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
        }
    }
}
