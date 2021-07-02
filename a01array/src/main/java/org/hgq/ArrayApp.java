package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-23 13:36
 **/
public class ArrayApp {
    public static void main(String[] args) {

       /* ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(12);*/


/*        System.out.println(2 << 1);
        System.out.println(2 << 2);*/

        DynamicArray<Integer> array = new DynamicArray<Integer>();
        array.add(34);
        array.add(35);
        array.add(36);
        array.add(37);
        array.add(1, 44);
        System.out.println(array);
        System.out.println("array.remove: " + array.remove(array.size-1));
        System.out.println("array.remove: " + array.remove(1));
        System.out.println("array " + array);
/*        for (int i = 0; i < 30; i++) {
            array.add(i);
        }*/


        array.clear();
        array.add(38);
        array.add(39);
        System.out.println("array.remove: " + array.remove(1));
        array.add(40);
        System.out.println(array.isEmpty());
        System.out.println(array.contains(34));
        System.out.println(array);

    }
}
