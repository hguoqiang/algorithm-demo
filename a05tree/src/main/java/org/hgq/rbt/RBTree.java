package org.hgq.rbt;

import org.hgq.bbst.BBST;

import java.util.Comparator;

/**
 * @description: 红黑树
 * @author: huangguoqiang
 * @create: 2021-07-15 12:08
 **/
public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public RBTree() {
        this(null);
    }

    public RBTree(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    protected void afterAdd(CustTreeNode<E> node) {

        //父亲节点
        CustTreeNode<E> parent = node.parent;

        //如果 node 是根节点，把node染成黑色
        if (parent == null) {
            blackColor(node);
            return;
        }

        //如果父节点是黑色，不做任何处理
        if (isBlack(parent)) {
            return;
        }

        //叔叔节点
        CustTreeNode<E> uncle = parent.sibling();

        //祖父节点
        CustTreeNode<E> grand = parent.parent;

        //如果父节点是红色，并且叔父节点也是红色，可能产生上溢，染色 和 向上合并，向上合并也可能一直传递到根节点
        if (isRed(uncle)) {
            //把 父亲节点 和 叔叔节点 都染成黑色，然后把 祖父节点向上合并 ，就是把祖父节点染成红色，然后当成新添加的元素加入进去
            blackColor(parent);
            blackColor(uncle);
            //把祖父节点染成红色，然后当成 新添加的元素 加入进去
            afterAdd(redColor(grand));
        } else {
            //如果父节点是红色，并且叔父节点是黑色，只需要 染色和旋转两种操作

            //祖父节点变成红色
            redColor(grand);

            //旋转四种情况 （LL、RR、LR、RL）
            if (parent.isLeftChild()) {
                if (node.isLeftChild()) {
                    //LL 右旋转， 父节点变成这颗子树的父节点
                    //父节点变成黑色
                    blackColor(parent);
                } else {
                    //LR  首先把 parent 左旋转变成 LL ，然后把grand右旋转， 让node 变成这颗子树的父节点，
                    //自己变成黑色
                    blackColor(node);
                    leftRotation(parent);
                }
                rightRotation(grand);
            } else {
                if (node.isRightChild()) {
                    //RR 左旋转
                    //父节点变成黑色
                    blackColor(parent);
                } else {
                    //RL  首先把 parent 右旋转变成 RR ，然后把grand 左旋转， 让node 变成这颗子树的父节点，
                    //自己变成黑色
                    blackColor(node);
                    rightRotation(parent);
                }
                leftRotation(grand);
            }

        }

    }
 @Override
    protected void afterRemove(CustTreeNode<E> node) {
        //如果删除的节点是红色，或者用来取代删除节点的 替代者 是红色 ，那把红色节点染黑，保证红黑树的性质
        if (isRed(node)) {
            blackColor(node);
            return;
        }


        //下面删除黑色节点，有三种情况，删除黑色叶子节点，删除带有一个红色子节点的黑色节点，删除带有两个红色子节点的黑色节点（找前驱或者后继）

        //删除带有一个红色子节点的黑色节点，这个被删除的黑色节点是度为1的节点，它的子节点是代替者，这个子节点是红色，需要把红色子节点染黑，保证红黑树的性质
      /*  if (isRed(node)) {
            blackColor(node);
            return;
        }*/


        //如果这个 被删除的黑色叶子节点是 根节点
        CustTreeNode<E> parent = node.parent;
        if (parent == null) {
            return;
        }

        //删除黑色叶子节点，会导致下溢（4阶B树非叶子节点必须至少有两个子节点，解决下溢就要向兄弟节点借元素，父节点下来代替被删除节点的位置，兄弟节点出一个元素上去代替父节点的位置）

        //判断被删除的node是左子树还是右子树
        boolean left = parent.left == null || node.isLeftChild();
        CustTreeNode<E> sibling = left ? parent.right : parent.left;

        if (left) {
            //被删除的节点是左子树，兄弟节点是右子树

            //兄弟节点是红色，那这个红色兄弟节点的子节点一定是黑色（不能有两个连续的红色子节点），那就把兄弟节点的子节点变成兄弟节点，旋转
            if (isRed(sibling)) {
                blackColor(sibling);
                redColor(parent);
                //对父节点进行右旋转
                leftRotation(parent);
                //把兄弟节点的子节点 变成兄弟节点
                sibling = parent.right;
            }

            //这里被删除的节点一定是黑色，它的兄弟节点必然是黑色

            //如果兄弟节点没有子节点，这种情况 不能 借元素，如果父节点是黑色的，父节点向下与兄弟节点合并
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                redColor(sibling);
                if (isRed(parent)) {
                    blackColor(parent);
                } else {
                    //父节点是黑色，向下合并，
                    afterRemove(parent);
                }
            } else {
                //如果兄弟节点至少拥有一个红色节点，这种情况可以借元素
                if (isBlack(sibling.right)) {
                    //说明兄弟节点右子树是空的  RL 情况 ，兄弟左旋转,转成LL情况
                    //RL
                    leftRotation(sibling);
                    sibling = parent.right;
                }

                //RR
                //新的父节点继承原来父节点的颜色
                color(sibling, colorOf(parent));
                //左右子节点的颜色变成黑色
                blackColor(sibling.right);
                blackColor(parent);
                leftRotation(parent);
                return;
            }

        } else {
            //被删除的节点是右子树，兄弟节点是左子树

            //兄弟节点是红色，那这个红色兄弟节点的子节点一定是黑色（不能有两个连续的红色子节点），那就把兄弟节点的子节点变成兄弟节点，旋转
            if (isRed(sibling)) {
                blackColor(sibling);
                redColor(parent);
                //对父节点进行右旋转
                rightRotation(parent);
                //把兄弟节点的子节点 变成兄弟节点
                sibling = parent.left;
            }

            //这里被删除的节点一定是黑色，它的兄弟节点必然是黑色

            //如果兄弟节点没有子节点，这种情况 不能 借元素，如果父节点是黑色的，父节点向下与兄弟节点合并
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                redColor(sibling);
                if (isRed(parent)) {
                    blackColor(parent);
                } else {
                    afterRemove(parent);
                }
            } else {
                //如果兄弟节点至少拥有一个红色节点，这种情况可以借元素
                if (isBlack(sibling.left)) {
                    //说明兄弟节点左子树是空的  LR 情况 ，兄弟左旋转,转成LL情况
                    //LR
                    leftRotation(sibling);
                    sibling = parent.left;
                }

                //LL
                //新的父节点继承原来父节点的颜色
                color(sibling, colorOf(parent));
                //左右子节点的颜色变成黑色
                blackColor(sibling.left);
                blackColor(parent);
                rightRotation(parent);
                return;
            }
        }

    }

   /* @Override
    protected void afterRemove(CustTreeNode<E> node, CustTreeNode<E> replacement) {
        //删除红色叶子节点，直接删除
        if (isRed(node)) {
            return;
        }


        //下面删除黑色节点，有三种情况，删除黑色叶子节点，删除带有一个红色子节点的黑色节点，删除带有两个红色子节点的黑色节点（找前驱或者后继）

        //删除带有一个红色子节点的黑色节点，这个被删除的黑色节点是度为1的节点，它的子节点是代替者，这个子节点是红色，需要把红色子节点染黑，保证红黑树的性质
        if (isRed(replacement)) {
            blackColor(replacement);
            return;
        }


        //如果这个 被删除的黑色叶子节点是 根节点
        CustTreeNode<E> parent = node.parent;
        if (parent == null) {
            return;
        }

        //删除黑色叶子节点，会导致下溢（4阶B树非叶子节点必须至少有两个子节点，解决下溢就要向兄弟节点借元素，父节点下来代替被删除节点的位置，兄弟节点出一个元素上去代替父节点的位置）

        //判断被删除的node是左子树还是右子树
        boolean left = parent.left == null || node.isLeftChild();
        CustTreeNode<E> sibling = left ? parent.right : parent.left;

        if (left) {
            //被删除的节点是左子树，兄弟节点是右子树

            //兄弟节点是红色，那这个红色兄弟节点的子节点一定是黑色（不能有两个连续的红色子节点），那就把兄弟节点的子节点变成兄弟节点，旋转
            if (isRed(sibling)) {
                blackColor(sibling);
                redColor(parent);
                //对父节点进行右旋转
                leftRotation(parent);
                //把兄弟节点的子节点 变成兄弟节点
                sibling = parent.right;
            }

            //这里被删除的节点一定是黑色，它的兄弟节点必然是黑色

            //如果兄弟节点没有子节点，这种情况 不能 借元素，如果父节点是黑色的，父节点向下与兄弟节点合并
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                redColor(sibling);
                if (isRed(parent)) {
                    blackColor(parent);
                } else {
                    //父节点是黑色，向下合并，
                    afterRemove(parent, null);
                }
            } else {
                //如果兄弟节点至少拥有一个红色节点，这种情况可以借元素
                if (isBlack(sibling.right)) {
                    //说明兄弟节点右子树是空的  RL 情况 ，兄弟左旋转,转成LL情况
                    //RL
                    leftRotation(sibling);
                    sibling = parent.right;
                }

                //RR
                //新的父节点继承原来父节点的颜色
                color(sibling, colorOf(parent));
                //左右子节点的颜色变成黑色
                blackColor(sibling.right);
                blackColor(parent);
                leftRotation(parent);
                return;
            }

        } else {
            //被删除的节点是右子树，兄弟节点是左子树

            //兄弟节点是红色，那这个红色兄弟节点的子节点一定是黑色（不能有两个连续的红色子节点），那就把兄弟节点的子节点变成兄弟节点，旋转
            if (isRed(sibling)) {
                blackColor(sibling);
                redColor(parent);
                //对父节点进行右旋转
                rightRotation(parent);
                //把兄弟节点的子节点 变成兄弟节点
                sibling = parent.left;
            }

            //这里被删除的节点一定是黑色，它的兄弟节点必然是黑色

            //如果兄弟节点没有子节点，这种情况 不能 借元素，如果父节点是黑色的，父节点向下与兄弟节点合并
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                redColor(sibling);
                if (isRed(parent)) {
                    blackColor(parent);
                } else {
                    afterRemove(parent, null);
                }
            } else {
                //如果兄弟节点至少拥有一个红色节点，这种情况可以借元素
                if (isBlack(sibling.left)) {
                    //说明兄弟节点左子树是空的  LR 情况 ，兄弟左旋转,转成LL情况
                    //LR
                    leftRotation(sibling);
                    sibling = parent.left;
                }

                //LL
                //新的父节点继承原来父节点的颜色
                color(sibling, colorOf(parent));
                //左右子节点的颜色变成黑色
                blackColor(sibling.left);
                blackColor(parent);
                rightRotation(parent);
                return;
            }
        }

    }*/

    @Override
    protected CustTreeNode<E> createNode(E element, CustTreeNode<E> parent) {
        return new RBNode<>(element, parent);
    }

    /**
     * 给指定节点染指定颜色
     *
     * @param node
     * @param color
     * @return
     */
    private CustTreeNode<E> color(CustTreeNode<E> node, boolean color) {
        if (node != null) {
            ((RBNode) node).color = color;
        }
        return node;
    }

    /**
     * 查看节点是什么颜色，空节点默认是黑色
     *
     * @param node
     * @return
     */
    private boolean colorOf(CustTreeNode<E> node) {
        if (node == null) {
            return BLACK;
        }
        return ((RBNode) node).color;
    }

    /**
     * 查看节点是否是黑色
     *
     * @param node
     * @return
     */
    private boolean isBlack(CustTreeNode<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 查看节点是否是红色
     *
     * @param node
     * @return
     */
    private boolean isRed(CustTreeNode<E> node) {
        return colorOf(node) == RED;
    }

    /**
     * 把指定节点染成红色
     *
     * @param node
     * @return
     */
    private CustTreeNode<E> redColor(CustTreeNode<E> node) {
        return color(node, RED);
    }

    /**
     * 把指定节点染成黑色
     *
     * @param node
     * @return
     */
    private CustTreeNode<E> blackColor(CustTreeNode<E> node) {
        return color(node, BLACK);
    }

    @Override
    public Object string(Object node) {
        RBNode<E> myNode = (RBNode<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        String c = myNode.color == RED ? "R" : "";
        return c+"_"+myNode.element + "_p(" + parentString + ")" ;
    }

    private static class RBNode<E> extends CustTreeNode<E> {

        boolean color = RED;


        public RBNode(E element, CustTreeNode<E> parent) {
            super(element, parent);
        }
    }


}
