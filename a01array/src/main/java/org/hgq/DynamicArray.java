package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-23 13:16
 **/
public class DynamicArray<E> extends AbstractCustList<E> {
    /**
     * 容器存放所有元素
     */
    private Object[] elements;

    /**
     * 容器默认容量
     */
    private static final int DEFAULT_CAPACITY = 2;


    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = new Object[capacity];

    }


    /**
     * 在容器尾部添加元素
     * O(1)
     * @param element
     */
    @Override
    public void add(E element) {

        enlarge(size + 1);

        elements[size++] = element;
    }

    /**
     * 在指定位置添加元素，此位置及后面所有元素位置向后面移动一个
     * 最好O(1)，最后一个位置添加
     * 最坏O(n)，第一个元素位置添加
     * 平均O(n)
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangCheckForAdd(index);
        if (index == size) {
            add(element);
        } else {
            enlarge(size + 1);
            for (int i = size - 1; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
            elements[index] = element;
            size++;
        }
    }

    private void enlarge(int minCapacity) {
        //容器当前实际容量
        int oldCapacity = elements.length;

        if (oldCapacity <= minCapacity) {
            //扩容 2倍
            Object[] newElements = new Object[(oldCapacity << 1)];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
            System.out.println("扩容了，新容量：" + elements.length);
        }
    }


    /**
     * 在指定位置设置元素，覆盖原来的元素，返回原来的元素
     * O(1)
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        rangCheck(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 查找指定位置的元素
     * O(1)
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangCheck(index);
        return (E) elements[index];
    }

    /**
     * 删除指定位置的元素
     * 最好O(1)，删除最后一个元素
     * 最坏O(n)，删除第一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangCheck(index);
        E old = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return old;
    }

    /**
     * 返回指定元素的第一次索引位置
     * 如果没有指定的元素，返回-1
     *
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 清空容器内所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:")
                .append(size)
                .append(" [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }

        sb.append("]");
        return sb.toString();
    }
}
