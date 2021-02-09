package org.hello.algorithm.sort;

/**
 * @description: 选择排序  时间复杂度 O(n^2) 不稳定
 * 选择排序原理
 * <p>
 * 假设有10个数，第一轮循环，第一个数和第二个数比较，如果第一个数大，第一个数和第二个数交换位置，否则不动；
 * 接着第一个数和第三个数比较，如果第一个数大，第一个数和第三个数交换位置，否则不动…第一个数和第十个数比较，如果第一个数大，第一个数和第十个数交换位置，否则不动。
 * 第一轮循环结束，最小的数挪到了第一个数的位置，比较进行了9次。
 * 第二轮循环，第二个数和第三个数比较，如果第二个数大，第而个数和第三个数交换位置，否则不动…第二个数和第十个数比较，如果第二个数大，第二个数和第十个数交换位置，否则不动。
 * 第二轮循环结束，第二小的数挪到了第二个数的位置，比较进行了8次。
 * …
 * 第九轮循环，第九个数和第十个数比较，如果第九个数大，第九个数和第十个数交换位置，否则不动。第九轮循环结束，倒数第二小的数挪到了倒数第二个的位置，比较进行了1次。
 * @author: huangguoqiang
 * @create: 2021-02-07 19:05
 **/
public class SelectionSort {

  /*  public static void main(String[] args) {
        //int arr[] = {8, 5, 7, 3, 6, 9, 1};
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
        sort2(arrCopy);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arrCopy[i]) {
                System.out.println("不相等");
            }

        }


    }*/

    public static void main(String[] args) {
        int[] arr = {5, 9, 6, 1, 3};

        //int[] arr = {1, 9, 6, 5, 3}; i=0 ,minIndex =3
        //int[] arr = {1, 3, 6, 5, 9}; i=1, minIndex =4
        //int[] arr = {1, 3, 5, 6, 9}; i=2, minIndex =3   j=4
        //int[] arr = {1, 3, 5, 6, 9}; i=3, minIndex =3   j=4


        sort2(arr);
    }


    // 内循环只负责找出最小数的index ，外循环只交换一次，复杂度低
    private static void sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            //最小数的位置
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //找出最小数的位置
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                System.out.println("外循环第" + i + "次,最小数位置：minIndex: " + minIndex + "结果：");
                //交换位置 把最小数拿到最前面
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            for (int j = 0; j < arr.length; j++) {
                System.out.print(+arr[j] + " ");
            }
            System.out.println();
        }
    }


    //这个复杂度要高，因为内循环每次要交换位置
    private static void sort2(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    System.out.println("内循环第" + i + "次循环 交换了");
                }

            }
            System.out.println("外循环第" + i + "次循环 结果：");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + " ");
            }
            System.out.println();
            System.out.println("-------------------");
        }


    }
}
