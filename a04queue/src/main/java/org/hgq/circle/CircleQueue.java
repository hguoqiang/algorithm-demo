package org.hgq.circle;

import org.hgq.IQueue;

/**
 * @description: 循环队列
 * <p>
 * (n - 1) & hash 的结果与 hash 对 n 取模 的结果是一样的
 * @author: huangguoqiang
 * @create: 2021-07-01 12:21
 **/
public class CircleQueue<E> implements IQueue<E> {

    private E[] array;

    /**
     * 队列的元素个数
     */
    private int size;

    /**
     * 队头元素的位置index
     */
    private int front;

    public CircleQueue() {
        this.array = (E[]) new Object[10];
    }

    /**
     * 元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 是否是空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队(从队尾位置入队)
     */
    @Override
    public void enQueue(E element) {
        //保证容量
        ensureCapacity(size + 1);
        array[realIndex(size)] = element;
        size++;
    }

    /**
     * 保证有capacity个容量
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = array.length;
        if (capacity <= oldCapacity) {
            return;
        }
        //扩1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newArray = (E[]) new Object[newCapacity];

        int count = size;
        for (int i = 0; i < count; i++) {
            newArray[i] = array[realIndex(i)];
        }
        array = newArray;
        front = 0;
    }

    /**
     * 出队(从队头位置出队)
     *
     * @return
     */
    @Override
    public E deQueue() {
        E e = array[front];
        array[front] = null;
        front = realIndex(1);
        size--;
        return e;
    }

    /**
     * 获取 index 对应的真实索引
     *
     * @param index
     * @return
     */
    private int realIndex(int index) {
        //return (front + index) % array.length;
        //(n%m) = (n>m)?(n-m):n;
        index += front;
        return index >= array.length ? (index - array.length) : index;
    }

    /**
     * 查看对头元素
     *
     * @return
     */
    @Override
    public E front() {
        return array[front];
    }

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity:")
                .append(array.length)
                .append(",size:")
                .append(size)
                .append(",front:")
                .append(front)
                .append(" [");
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(array[i]);
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

        CircleQueue queue = new CircleQueue();
     /*   for (int i = 100; i < 103; i++) {
            queue.enQueue(i);
        }*/
        System.out.println(queue);
        for (int i = 0; i < 25; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        queue.enQueue(88);
        System.out.println(queue);
        for (int i = 0; i < 6; i++) {
            queue.deQueue();
        }
        System.out.println(queue);
        for (int i = 15; i < 68; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        System.out.println(queue.deQueue());
        System.out.println(queue);
        queue.enQueue(999);
        System.out.println(queue);
        queue.enQueue(888);
        System.out.println(queue);
        /* queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        queue.deQueue();
        queue.deQueue();

        queue.enQueue(55);
        queue.enQueue(66);
        queue.enQueue(77);

        queue.enQueue(88);
        queue.enQueue(99);

        queue.enQueue(100);

        System.out.println();*/

        //(front + index) % array.length;
        // n>=0,m>0,n<2m

        // front = 9 , index =9
        int n = 18;
        //m = array.length =10
        int m = 10;
        System.out.println(n % m);


        //front=2 ,index =3,m = array.length =7
        n = 5;
        m = 7;
        System.out.println(n % m);//结果是5

        //capacity:10,size:0,front:0 [null,null,null,null,null,null,null,null,null,null]
        //capacity:10,size:10,front:0 [0,1,2,3,4,5,6,7,8,9]
        //capacity:15,size:11,front:0 [0,1,2,3,4,5,6,7,8,9,88,null,null,null,null]
        //capacity:15,size:6,front:5 [null,null,null,null,null,5,6,7,8,9,88,null,null,null,null]
        //capacity:22,size:21,front:0 [5,6,7,8,9,88,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,null]
        //5
        //capacity:22,size:20,front:1 [null,6,7,8,9,88,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,null]
    }

}
