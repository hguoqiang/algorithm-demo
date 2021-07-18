package org.hgq.rbt;

import org.hgq.tree.printer.BinaryTrees;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-13 14:13
 **/
public class RBTreeTest {
    public static void main(String[] args) {

        TreeSet<String> treeset = new TreeSet<>();
        treeset.add("ddd");
        treeset.add("aaa");
        treeset.add("aaa");
        treeset.add("bbb");
        treeset.add("ccc");
        treeset.add("eee");

        Iterator iter = treeset.iterator();
        while (iter.hasNext()) {
            System.out.printf("asc : %s\n", iter.next());
        }

        // test3();
    }


    static void test1() {
        Integer data[] = new Integer[]{
                // 7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
                // 13, 14, 15,12,11,17,16
                85, 19, 69, 3, 7, 99, 95
        };

        RBTree<Integer> rbt = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbt.add(data[i]);
        }
        BinaryTrees.println(rbt);
        System.out.println("====================================");


    }

    static void test2() {
        Integer data[] = new Integer[]{
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {

            System.out.println("【" + data[i] + "】");
            rb.add(data[i]);
            BinaryTrees.println(rb);
            System.out.println("---------------------------------------");
        }
    }

    static void test3() {
        Integer data[] = new Integer[]{
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };
        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }
        BinaryTrees.println(rb);
        System.out.println("---------------------------------------");


        for (int i = 0; i < data.length; i++) {

            System.out.println("【" + data[i] + "】");
            rb.remove(data[i]);
            BinaryTrees.println(rb);
            System.out.println("---------------------------------------");
        }
    }
}
