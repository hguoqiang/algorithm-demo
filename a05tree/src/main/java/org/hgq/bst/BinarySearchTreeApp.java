package org.hgq.bst;

import org.hgq.bst.printer.BinaryTrees;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-03 12:54
 **/
public class BinarySearchTreeApp {
    public static void main(String[] args) {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        BinarySearchTree<Student> bst2 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Student(data[i],null));
        }
        BinaryTrees.println(bst2);

    }
}
