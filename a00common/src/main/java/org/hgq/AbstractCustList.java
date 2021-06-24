package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-24 10:56
 **/
public abstract class AbstractCustList<E> implements CustList<E> {
    /**
     * 容器内元素个数
     */
    protected int size;

    /**
     * 返回元素的数量
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 返回容器是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 返回是否包含指定元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }


    protected void outOfBounds(int index) {
        throw new ArrayIndexOutOfBoundsException("index :" + index + " , size:" + size);
    }

    protected void rangCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

}
