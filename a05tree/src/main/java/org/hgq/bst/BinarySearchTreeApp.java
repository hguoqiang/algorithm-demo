package org.hgq.bst;

import org.hgq.tree.printer.BinaryTrees;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-03 12:54
 **/
public class BinarySearchTreeApp {
   /* public static void main(String[] args) {
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


        BinarySearchTree<Student> bst3 = new BinarySearchTree<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge()-o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst3.add(new Student(data[i],null));
        }
        BinaryTrees.println(bst3);



        BinarySearchTree<Student> bst4 = new BinarySearchTree<>();
        bst4.add(new Student(10,"zhangsna"));
        bst4.add(new Student(12,"lisi"));
        bst4.add(new Student(9,"王五"));
        bst4.add(new Student(10,"haha"));
        BinaryTrees.println(bst4);

    }*/


    public static void main(String[] args) {
        preorderTraversalTest1();
    }

    static void preorderTraversalTest1() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4,  2,  1
                //7
        };

       // BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
       /* bst.levelOrderTraversal();
        System.out.println(bst.isCompleteBinaryTree2());
        bst.invertTree();
        BinaryTrees.println(bst);*/
        System.out.println(bst.remove(9));
//        System.out.println(bst.remove(3));
//        System.out.println(bst.remove(12));
        BinaryTrees.println(bst);
    }
}
