package org.hgq;

/**
 * @description: 双端队列
 * @author: huangguoqiang
 * @create: 2021-07-02 13:48
 **/
public interface IDeque<E> {
    int size();// 元素的数量

    boolean isEmpty();//是否是空

    void clear();//清空元素

    void enQueueFront(E element);//从队头入队

    void enQueueRear(E element);//从队尾入队

    E deQueueFront();//从队头出队

    E deQueueRear();//从队尾出队

    E front();//查看队头元素

    E rear();//查看队尾元素
}
