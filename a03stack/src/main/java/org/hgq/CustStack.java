package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-30 15:40
 **/
public class CustStack<E> {
    private DynamicArray<E> array;

    public CustStack() {
        this.array = new DynamicArray();
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return array.size();
    }

    /**
     * 是否是空
     *
     * @return
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入栈
     */
    public void push(E element) {
        array.add(element);
    }

    /**
     * 出栈
     *
     * @return
     */
    public E pop() {
        return array.remove(array.size() - 1);
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    public E top() {
        return array.get(array.size() - 1);
    }

    /**
     * 清空元素
     */
    public void clear() {
        array.clear();
    }

}
