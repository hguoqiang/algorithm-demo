package org.hello.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 冒泡排序(Bubble Sort) 时间复杂度 O(n^2) 稳定
 * 冒泡排序是一种简单的排序算法。它重复地遍历要排序的序列，依次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 遍历序列的工作是重复地进行直到没有再需要交换为止，此时说明该序列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * <p>
 * 算法步骤
 * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3.针对所有的元素重复以上的步骤，除了最后一个；
 * 4.重复步骤1~3，直到排序完成。
 * <p>
 * 冒泡排序原理
 * <p>
 * 假设有10个数，第一轮循环，第一个数和第二个数比较，如果第一个数大，第一个数和第二个数交换位置，否则不动；接着第二个数和第三个数比较，如果第二个数大，第二个数和第三个数交换位置，否则不动
 * …第九个数和第十个数比较，如果第九个数大，第九个数和第十个数交换位置，否则不动。第一轮循环结束，最大的数挪到了第十个数的位置，比较进行了9次。
 * 第二轮循环，第一个数和第二个数比较，如果第一个数大，第一个数和第二个数交换位置，否则不动…第八个数和第九个数比较，如果第八个数大，第八个数和第九个数交换位置，否则不动。
 * 第二轮循环结束，第二大的数挪到了第九个数的位置，比较进行了8次。
 * …
 * 第九轮循环，第一个数和第二个数比较，如果第一个数大，第一个数和第二个数交换位置，否则不动。第九轮循环结束，倒数第二大的数挪到了第二个数的位置，比较进行了1次。
 * 总体原理：每轮比较找到最大的数。
 * @author: huangguoqiang
 * @create: 2021-02-07 20:58
 **/
public class BubbleSort {

    public static void main(String[] args) {
       /* int[] arr = {5, 9, 6, 1, 3};
        sort(arr);*/
        int arr[] = new int[10000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000);
        }
        int[] arrCopy = new int[arr.length];
        System.arraycopy(arr, 0, arrCopy, 0, arr.length);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arrCopy[i]) {
                System.out.println("不相等");
            }
        }

        Arrays.sort(arr);
        sort(arrCopy);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arrCopy[i]) {
                System.out.println("不相等");
            }

        }
    }
@Test
public void test(){
        int arr [] = {1,2,3,4};
        sort(arr);
    }
    //此处对代码做了一个小优化，加入了is_sorted Flag，目的是将算法的最佳时间复杂度优化为O(n)，即当原输入序列就是排序好的情况下，该算法的时间复杂度就是O(n)。
    private static void sort(int[] arr) {

        boolean is_sorted = false;
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("外循环进入 第" +  i + " 次交换");
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    System.out.println("内循环第" + j + " 次交换");
                } else {
                    is_sorted = true;

                }
            }
            if (is_sorted) {
                System.out.println("已经排好序");
                break;
            }
            System.out.println("外循环 第" + i + " 次交换结果:");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(+arr[j] + " ");
            }
            System.out.println();
        }

    }


}
