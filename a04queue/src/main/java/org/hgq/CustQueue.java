package org.hgq;


public class CustQueue<E> implements IQueue<E> {

    private CustLinkedList<E> list;


    public CustQueue() {
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
     * 入队(从队尾位置入队)
     */
    @Override
    public void enQueue(E element) {
        list.add(element);
    }

    /**
     * 出队(从队头位置出队)
     *
     * @return
     */
    @Override
    public E deQueue() {
        return list.remove(0);
    }

    /**
     * 查看对头元素
     *
     * @return
     */
    @Override
    public E front() {
        return list.get(0);
    }

    /**
     * 清空元素
     */
    @Override
    public void clear() {
        list.clear();
    }
}