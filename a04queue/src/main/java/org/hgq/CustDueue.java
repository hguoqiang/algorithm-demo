package org.hgq;

/**
 * @description: 双端队列
 * @author: huangguoqiang
 * @create: 2021-07-01 12:14
 **/
public class CustDueue<E> implements IDeque<E> {
    private CustLinkedList<E> list;


    public CustDueue() {
        this.list = new CustLinkedList<>();
    }

    /**
     * 元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * 是否是空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * 从队头入队
     *
     * @param element
     */
    @Override
    public void enQueueFront(E element) {
        list.addFirst(element);
    }

    /**
     * 从队尾入队
     *
     * @param element
     */
    @Override
    public void enQueueRear(E element) {
        list.addLast(element);
    }

    /**
     * 从队头出队
     *
     * @return
     */
    @Override
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    @Override
    public E deQueueRear() {
        return list.remove(list.size()-1);
    }

    /**
     * 查看队头元素
     *
     * @return
     */
    @Override
    public E front() {
        return list.get(0);
    }

    /**
     * 查看队尾元素
     *
     * @return
     */
    @Override
    public E rear() {
        return list.get(size() - 1);
    }


    public static void main(String[] args) {
        CustDueue<Integer> queue = new CustDueue<>();
        queue.enQueueFront(11);
        queue.enQueueFront(22);
        queue.enQueueRear(33);
        queue.enQueueRear(44);

        // 头  22  11  33 44 尾

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueRear());
        }
    }
}
