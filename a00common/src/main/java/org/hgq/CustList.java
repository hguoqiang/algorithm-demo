package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-24 10:54
 **/
public interface CustList<E> {

    /**
     * 返回元素的数量
     *
     * @return
     */
    int size() ;

    /**
     * 返回容器是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 返回是否包含指定元素
     *
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 在容器尾部添加元素
     *
     * @param element
     */
    void add(E element) ;

    /**
     * 在指定位置添加元素，此位置及后面所有元素位置向后面移动一个
     *
     * @param index
     * @param element
     */
    void add(int index, E element) ;



    /**
     * 在指定位置设置元素，覆盖原来的元素，返回原来的元素
     *
     * @param index
     * @param element
     * @return
     */
    E set(int index, E element) ;

    /**
     * 查找指定位置的元素
     *
     * @param index
     * @return
     */
    E get(int index) ;

    /**
     * 删除指定位置的元素
     *
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 返回指定元素的第一次索引位置
     * 如果没有指定的元素，返回-1
     *
     * @param element
     * @return
     */
    int indexOf(E element) ;

    /**
     * 清空容器内所有元素
     */
    public void clear();





}
