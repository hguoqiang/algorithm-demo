package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-24 11:34
 **/
public class LinkedApp {

    public static void main(String[] args) {
         SinglyCircleLinkedList<Integer> list = new SinglyCircleLinkedList<>();
        // LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.add(5);
        System.out.println(list);
        list.add(0,111);
        System.out.println(list);
        list.add(4,444);
        System.out.println(list);
        list.add(2,232);
        System.out.println(list);
        list.add(list.size(), 999);
        list.add(0, 0);
        System.out.println(list);
        System.out.println(list.remove(0));
        System.out.println(list);
        System.out.println(list.remove(list.size()-1));
        System.out.println(list);
        //size:5 [1,2,3,4,5]
        //size:6 [111,1,2,3,4,5]
        //size:7 [111,1,2,3,444,4,5]
        //size:8 [111,1,232,2,3,444,4,5]
        //size:10 [0,111,1,232,2,3,444,4,5,999]
    }

    /*public static void main(String[] args) {
        //    LinkedList<Integer> list = new LinkedList<>();
        CustLinkedList<Integer> list = new CustLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        list.add(0, 100);
        list.add(1, 200);
        list.add(3, 333);
        list.add(6, 666);
        list.add(list.size(), 777);
        list.add(list.size(), 888);

        System.out.println(list);

        list.set(0, 111);
        list.set(1, 222);
        list.set(6, 611);
        list.set(7, 711);
        list.set(list.size() - 1, 811);
        System.out.println(list);
        System.out.println(list.indexOf(611));
        list.clear();
        System.out.println(list);
        //size:5 [1,2,3,4,5]
        //size:11 [100,200,1,333,2,3,666,4,5,777,888]
        //size:11 [111,222,1,333,2,611,666,711,5,777,811]
        //size:5 [1,2,3,4,5]
        //size:11 [100,200,1,333,2,3,666,4,5,777,888]
        //size:11 [111,222,1,333,2,3,611,711,5,777,811]
    }*/
 /*   public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(99);
        list.add(88);
        list.add(77);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        list.add(1, 100);
        list.add(1, 200);
        list.add(list.size, 777);
        System.out.println(list);
        System.out.println(list.set(4, 666));
        System.out.println(list.set(0, 9999));
        System.out.println(list.set(list.size-1, 888));
        System.out.println(list);
       *//* System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(list.size-2));
        System.out.println(list.get(4));*//*
        System.out.println("--------------------");
     *//*   System.out.println(list.remove(4));
        System.out.println(list);
        System.out.println(list.remove(list.size-1));
        System.out.println(list);*//*

        System.out.println(list.indexOf(985));
    }*/
}
