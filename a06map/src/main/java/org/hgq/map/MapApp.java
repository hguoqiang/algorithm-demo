package org.hgq.map;

import org.hgq.rbt.RBTree;
import org.hgq.tree.printer.BinaryTrees;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-18 19:54
 **/
public class MapApp {
    public static void main(String[] args) {
     /*   CustTreeMap<Integer, Integer> map = new CustTreeMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(3, 1);
        map.put(4, 1);
        System.out.println(map.size());*/

        test1();
    }


    static void test1() {
        Integer data[] = new Integer[]{
                // 7, 4, 9, 2, 5, 8, 11, 3, 12, 1
                //7, 4, 9, 2, 5, 8, 11, 3,  1
                // 7, 4, 9, 2,  1
                // 13, 14, 15,12,11,17,16
              //  85, 19, 69, 3, 7, 99, 95
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RBTree<Integer> rbt = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbt.add(data[i]);
        }
        BinaryTrees.println(rbt);
        System.out.println("====================================");
        CustTreeMap<Integer, Integer> map = new CustTreeMap<>();
        for (int i = 0; i < data.length; i++) {
            map.put(data[i],data[i]);
        }
        BinaryTrees.println(map);
    }
}
