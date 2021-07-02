package org.hgq;

/**
 * @description: 队列
 * @author: huangguoqiang
 * @create: 2021-07-02 13:46
 **/
public interface IQueue<E> {
    int size();// 元素的数量

    boolean isEmpty();//是否是空

    void enQueue(E element);//入队

    E deQueue();//出队

    E front();//查看队头元素

    void clear();//清空元素
}
