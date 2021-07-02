package org.hgq.circle;

import org.hgq.IDeque;

/**
 * @description: 双端循环队列
 * @author: huangguoqiang
 * @create: 2021-07-02 13:31
 **/
public class CircleDeque<E> implements IDeque<E> {

    private E[] array;

    /**
     * 队列的元素个数
     */
    private int size;

    /**
     * 队头元素的位置index
     */
    private int front;

    public CircleDeque() {
        this.array = (E[]) new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        front = 0;
    }

    /**
     * 从队头入队
     *
     * @param element
     */
    @Override
    public void enQueueFront(E element) {
        //保证容量
        ensureCapacity(size + 1);
        // 队头减去 1 就是新的队头
        front = realIndex(-1);
        array[front] = element;
        size++;
    }

    /**
     * 从队尾入队
     *
     * @param element
     */
    @Override
    public void enQueueRear(E element) {
        //保证容量
        ensureCapacity(size + 1);
        array[realIndex(size)] = element;
        size++;
    }

    /**
     * 从队头出队
     *
     * @return
     */
    @Override
    public E deQueueFront() {
        E e = array[front];
        array[front] = null;
        front = realIndex(1);
        size--;
        return e;
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    @Override
    public E deQueueRear() {
        E rear = array[realIndex(size - 1)];
        array[realIndex(size - 1)] = null;
        size--;
        return rear;
    }

    @Override
    public E front() {
        return array[front];
    }

    /**
     * 查看队尾元素
     *
     * @return
     */
    @Override
    public E rear() {
        return array[realIndex(size - 1)];
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
     * 获取 index 对应的真实索引
     *
     * @param index
     * @return
     */
    private int realIndex(int index) {
        //return (front + index) % array.length;
        //(n%m) = (n>m)?(n-m):n;
        index += front;
        if (index < 0) {
            return index + array.length;
        }
        return index >= array.length ? (index - array.length) : index;
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

        CircleDeque<Integer> queue = new CircleDeque<>();
        // 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾

        // 前 5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 后
        // 前 8 7 6 5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 n n 10 9 后
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }
        System.out.println(queue);


        // 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾

        // 前 n 7 6 5 4 3 2 1  100 101 102 103 104 105 106 n n n n n n n 后
        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        System.out.println(queue);

        // 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾

        // 前 11 7 6 5 4 3 2 1  100 101 102 103 104 105 106 n n n n n n 12 后
        queue.enQueueFront(11);
        queue.enQueueFront(12);
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueFront());
        }

    }
}
