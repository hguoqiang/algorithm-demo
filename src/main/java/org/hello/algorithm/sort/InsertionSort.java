package org.hello.algorithm.sort;

/**
 * @description: 插入排序  时间复杂度 O(n^2) 稳定
 * 类似于抓扑克牌
 * 算法步骤
 * 1.从第一个元素开始，该元素可以认为已经被排序；
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5.将新元素插入到该位置后；
 * 重复步骤2~5。
 * @author: huangguoqiang
 * @create: 2021-02-08 14:58
 **/
public class InsertionSort {
    public static void main(String[] args) {

        int[] arr = {9, 6, 1, 3, 5};
        sort(arr);
    }


    private static void sort(int[] arr) {
        for (int j = 1; j < arr.length; j++) {

            int current = arr[j];
            int preIndex = j - 1;

            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex -= 1;
            }
            arr[preIndex + 1] = current;
            System.out.println("外循环 第" + j + " 次交换结果:");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(+arr[k] + " ");
            }
            System.out.println();
        }


    }

}
