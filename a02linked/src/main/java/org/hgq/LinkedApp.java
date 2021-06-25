package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-24 11:34
 **/
public class LinkedApp {
    public static void main(String[] args) {
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
       /* System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(list.size-2));
        System.out.println(list.get(4));*/
        System.out.println("--------------------");
     /*   System.out.println(list.remove(4));
        System.out.println(list);
        System.out.println(list.remove(list.size-1));
        System.out.println(list);*/

        System.out.println(list.indexOf(985));
    }
}
