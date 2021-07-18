package org.hgq.map;

import org.hgq.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @description: treeMap 就是一颗红黑树，红黑树存储的元素必须是可比较的
 * @author: huangguoqiang
 * @create: 2021-07-18 17:59
 **/
public class CustTreeMap<K, V> implements CustMap<K, V>, BinaryTreeInfo {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;

    private CustEntry<K, V> root;
    /**
     * 二叉搜索树是必须可比较的
     * 要么构建二叉搜索树时传入一个比较器
     * 要么二叉树所存放的元素就是可比较的
     */
    protected Comparator<? super K> comparator;

    public CustTreeMap() {
        this(null);
    }

    public CustTreeMap(Comparator comparator) {
        this.comparator = comparator;
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
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);

        //开始存储元素
        if (root == null) {
            //第一个元素
            CustEntry<K, V> newEntry = new CustEntry<>(key, value, null);
            root = newEntry;
            size++;
            afterAdd(newEntry);
            return null;
        }

        CustEntry<K, V> entry = root;
        //找到插入位置的父节点
        CustEntry<K, V> parent = root;
        //找到插入位置的方向
        int cmp = 0;

        if (this.comparator != null) {
            do {
                parent = entry;
                cmp = this.comparator.compare(key, entry.key);

                if (cmp > 0) {
                    entry = entry.right;
                } else if (cmp < 0) {
                    entry = entry.left;
                } else {
                    //key相等，值覆盖
                    entry.key = key;
                    V oldVal = entry.value;
                    entry.value = value;
                    return oldVal;
                }


            } while (entry != null);

        } else {
            Comparable<? super K> cmpKey = (Comparable<? super K>) key;
            do {
                parent = entry;
                cmp = cmpKey.compareTo(entry.key);

                if (cmp > 0) {
                    entry = entry.right;
                } else if (cmp < 0) {
                    entry = entry.left;
                } else {
                    //key相等，值覆盖
                    entry.key = key;
                    V oldVal = entry.value;
                    entry.value = value;
                    return oldVal;
                }

            } while (entry != null);
        }

        //插入新节点
        CustEntry<K, V> newEntry = new CustEntry<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newEntry;
        } else {
            parent.left = newEntry;
        }

        size++;
        afterAdd(newEntry);
        return null;
    }

    /**
     * 添加节点后维护红黑树的性质
     *
     * @param entry 新添加的节点
     */
    private void afterAdd(CustEntry<K, V> entry) {

        // 如果添加到是根节点，只需要把根节点颜色染黑
        if (entry.parent == null) {
            blackColor(entry);
            return;
        }

        //父节点
        CustEntry<K, V> parent = entry.parent;
        //如果 新添加节点的 父节点是黑色节点，不处理
        if (isBlack(parent)) {
            return;
        }

        // 新添加节点的 父节点是 红色节点，有八种添加情况，但是八种添加情况实际上是分成2类，
        // 一类是 叔叔节点是红色，造成B树节点上溢，新添加节点的祖父节点必须要向上合并，可能继续发生上溢，如果一直持续到根节点，只需要将根节点染成黑色
        // 另一类是叔叔节点是黑色，只需要旋转

        //祖父节点
        CustEntry<K, V> grand = parent.parent;

        //拿到叔叔节点
        CustEntry<K, V> uncle = parent.sibling();

        // 叔叔节点是红色
        if (isRed(uncle)) {
            //父亲染成黑色
            blackColor(parent);
            //叔叔染成黑色
            blackColor(uncle);

            //祖父向上合并 ,把祖父节点染成红色，然后当成 新添加的元素 加入进去
            afterAdd(redColor(grand));

        }

        //叔叔节点是黑色，这种情况叔叔节点只可能是个空节点
        else if (isBlack(uncle)) {
            //祖父节点染成红色
            redColor(grand);
            if (parent.isLeftChild()) {
                if (entry.isLeftChild()) {
                    //LL
                    //父亲染成黑色
                    blackColor(parent);
                } else {
                    //LR
                    //把自己染成黑色
                    blackColor(entry);
                    //父亲节点左旋转，变成LL
                    rotateLeft(parent);
                }
                //祖父节点右旋转，
                rotateRight(grand);
            } else {
                if (entry.isRightChild()) {
                    //RR
                    //父亲染成黑色
                    blackColor(parent);
                } else {
                    //RL
                    //把自己染成黑色
                    blackColor(entry);
                    //父亲节点左旋转，变成LL
                    rotateRight(parent);
                }
                //祖父节点右旋转
                rotateLeft(grand);
            }
        }
    }

    /**
     * 左旋转
     *
     * @param grand
     */
    private void rotateLeft(CustEntry<K, V> grand) {
        CustEntry<K, V> parent = grand.right;
        CustEntry<K, V> child = parent.left;

        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            // grand.parent==null
            root = parent;
        }

        grand.right = child;

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
        parent.left = grand;
    }


    /**
     * 右旋转
     *
     * @param grand
     */
    private void rotateRight(CustEntry<K, V> grand) {
        CustEntry<K, V> parent = grand.left;
        CustEntry<K, V> child = parent.right;

        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            // grand.parent==null
            root = parent;
        }

        grand.left = child;

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
        parent.right = grand;

    }


    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V removet(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    protected void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }


    /**
     * 查看节点是什么颜色，空节点默认是黑色
     *
     * @param entry
     * @return
     */
    private boolean colorOf(CustEntry<K, V> entry) {
        return entry == null ? BLACK : entry.color;
    }

    /**
     * 返回节点是否是红色
     *
     * @param entry
     * @return
     */
    private boolean isRed(CustEntry<K, V> entry) {

        return colorOf(entry) == RED;
    }

    /**
     * 返回节点是否是黑色
     *
     * @param entry
     * @return
     */
    private boolean isBlack(CustEntry<K, V> entry) {

        return colorOf(entry) == BLACK;
    }

    /**
     * 给指定的节点 染 指定的颜色
     *
     * @param entry
     * @param color
     */
    private CustEntry color(CustEntry<K, V> entry, boolean color) {
        if (entry != null) {
            entry.color = color;
        }
        return entry;
    }

    /**
     * 染红色
     *
     * @param entry
     * @return
     */
    private CustEntry redColor(CustEntry<K, V> entry) {
        return color(entry, RED);
    }

    /**
     * 染黑色
     *
     * @param entry
     * @return
     */
    private CustEntry blackColor(CustEntry<K, V> entry) {
        return color(entry, BLACK);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((CustEntry<K, V>) node).left;
    }


    @Override
    public Object right(Object node) {
        return ((CustEntry<K, V>) node).right;
    }

    @Override
    public Object string(Object node) {
        CustEntry<K, V> myNode = (CustEntry<K, V>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.key.toString();
        }
        String c = myNode.color == RED ? "R" : "";
        return c + "_" + myNode.key + "_p(" + parentString + ")";
    }

    /**
     * treeMap 的节点
     *
     * @param <K>
     * @param <V>
     */
    public static class CustEntry<K, V> {
        boolean color = RED;
        public K key;
        public V value;
        public CustEntry<K, V> parent;
        public CustEntry<K, V> left;
        public CustEntry<K, V> right;

        public CustEntry(K key, V value, CustEntry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * 判断当前节点是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        /**
         * 判断当前节点是否是父节点的左子树
         *
         * @return
         */
        public boolean isLeftChild() {
            return this.parent != null && this.parent.left == this;
        }

        /**
         * 判断当前节点是否是父节点的右子树
         *
         * @return
         */
        public boolean isRightChild() {
            return this.parent != null && this.parent.right == this;
        }

        /**
         * 返回当前节点的兄弟节点
         *
         * @return
         */
        public CustEntry<K, V> sibling() {
            if (isLeftChild()) {
                return this.parent.right;
            } else if (isRightChild()) {
                return this.parent.left;
            }
            return null;
        }
    }
}
