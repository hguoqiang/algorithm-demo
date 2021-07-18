package org.hgq.map;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-07-18 17:58
 **/
public interface CustMap<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V removet(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);
}
