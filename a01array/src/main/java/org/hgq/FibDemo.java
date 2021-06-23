package org.hgq;

/**
 * @description: 求第 n 个 斐波那契数
 * n:0 1 2 3 4 5 6 7
 * v:0 1 1 2 3 5 8 13...
 * f s s
 * f   s
 * f
 * fib = (n-2) +(n-1)
 * @author: huangguoqiang
 * @create: 2021-06-22 16:51
 **/
public class FibDemo {

    public static void main(String[] args) {
        System.out.println(fib2(70));
    }

    /**
     * 循环
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;

        // n=5 i<4  i=0  sum=1  f =1 s =1
        // n=5 i<4  i=1  sum=2  f =1 s =2
        // n=5 i<4  i=2  sum=3  f =2 s =3
        // n=5 i<4  i=3  sum=5  f =3 s =5
        // n=5 i<4  i=4  sum=3  f =2 s =3
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;

        }
        return second;

    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        return fib1(n - 2) + fib1(n - 1);
    }


    public static void test1(int n) {

        //1
        if (n > 10) {
            System.out.println("n > 10");
        } else if (n > 5) { // 2
            System.out.println("n > 5");
        } else {
            System.out.println("n <= 5");
        }

        // 1 + 4+4+4
        for (int i = 0; i < 4; i++) {
            System.out.println("test");
        }

        //14 >> O(1)
    }


    public static void test2(int n) {

        // 1+ n +n + n=1+3n >> O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }
    }

    public static void test3(int n) {

        // 1+ n +n + n * (1+3n)
        // 1+2n + n +3n^2
        // 1+ 3n+ 3n^2 >> O(n^2)
        for (int i = 0; i < n; i++) {
            //1+3n
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }


    public static void test4(int n) {

        // 1 + n + n + n * (1+15+15+15)
        // 1+2n +46n
        // 1+48n >> O(n)

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < 15; j++) {
                System.out.println("test");
            }
        }
    }

    //O(log n)
    public static void test5(int n) {
        // n = 8 >> 8/2=4 >> 4/2=2 >>2/2= 1 >> 0
        // 2^3 = 8
        //a ^ x = n  >> x =log a N  >> 3 = log 2 8  >> O(log 2 n) >> O(log n)

        while ((n = n / 2) > 0) {
            System.out.println("test");
        }
    }

    public static void test6(int n) {

        // O(log 5 n) >> O(log n)
        while ((n = n / 5) > 0) {
            System.out.println("test");
        }
    }

    public static void test7(int n) {

        // i+=i  <==> i=i+i <==> i = i * 2

        // n=8  i=1 <8 , i = 1*2
        // n=8  i=2 <8 , i = 2*2
        // n=8  i=4 <8 , i = 4*2
        // >> log 2 8

        // 1 + log n + log n + (log n * (1 + 3n))
        // 1+ log n + logn + logn +3nlogn
        // >> O(nlogn)
        for (int i = 1; i < n; i += i) {
            // 1 + 3n
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }

    //O(n)
    public static void test10(int n) {

        int a = 10;
        int b = 20;
        int c = a + b;
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + c);
        }
    }
}
