package org.hgq.bst;

import org.hgq.tree.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-08 19:30
 **/
public class BSTApp {
    public static void main(String[] args) {
        test3();
    }

    static void test3() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
               // 7, 4, 9, 2, 5, 8, 11
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
       // bst.inorderTraversal();
        System.out.println("----------------------------");
        /*BinaryTree.CustTreeNode<Integer> node = bst.successor(bst.getNode(12));
        System.out.println(node.element);*/
        System.out.println(bst.height());

    }

    static void test2() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        bst.inorderTraversal();

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst2.add(data[i]);
        }
        BinaryTrees.println(bst2);
        bst2.inorderTraversal();

    }

    static void test1() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        BST<Student> bst3 = new BST<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst3.add(new Student(data[i], "name-" + data[i]));
        }
        BinaryTrees.println(bst3);


        BST<Student> bst4 = new BST<>();
        bst4.add(new Student(10, "zhangsna"));
        bst4.add(new Student(12, "lisi"));
        bst4.add(new Student(9, "王五"));
        //bst4.add(new Student(10,"haha"));
        BinaryTrees.println(bst4);
    }
}
