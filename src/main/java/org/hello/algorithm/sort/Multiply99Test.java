package org.hello.algorithm.sort;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-07 21:29
 **/
public class Multiply99Test {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " * " + i + " =" + i * j + "\t");
            }
            System.out.println();
        }
    }

}
