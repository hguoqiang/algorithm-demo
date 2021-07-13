package org.hgq.avl;

import org.hgq.bst.BST;
import org.hgq.tree.printer.BinaryTrees;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-13 14:13
 **/
public class AVLTreeTest {
    public static void main(String[] args) {
        test1();
    }



    static void test1() {
        Integer data[] = new Integer[]{
               // 7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
                 13, 14, 15,12,11,17,16,8,9,1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        System.out.println("====================================");

        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }
        BinaryTrees.println(avlTree);


    }
}
