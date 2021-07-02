package org.hgq.queue;

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

import java.util.Stack;

/**
 * @description: 232. 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @author: huangguoqiang
 * @create: 2021-07-01 11:18
 **/
public class QueueUsingStack232 {

    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();


    /**
     * Initialize your data structure here.
     */
    public QueueUsingStack232() {

    }

    /**
     * Push element x to the back of queue.
     * 入队 从队尾
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     * 出队 从队头
     */
    public int pop() {
        if (outStack.empty()) {
            int inStackSize = inStack.size();
            for (int i = 0; i < inStackSize; i++) {
                outStack.push(inStack.pop());
            }
            return outStack.pop();
        } else {
            return outStack.pop();
        }
    }

    /**
     * Get the front element.
     * 获取对头元素
     */
    public int peek() {
        if (outStack.empty()) {
            int inStackSize = inStack.size();
            for (int i = 0; i < inStackSize; i++) {
                outStack.push(inStack.pop());
            }
            return outStack.peek();
        } else {
            return outStack.peek();
        }
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

    public static void main(String[] args) {
        QueueUsingStack232 myQueue = new QueueUsingStack232();
        System.out.println(myQueue.empty()); // return true

        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.push(3); // queue is: [1, 2, 3] (leftmost is front of the queue)
        System.out.println(myQueue.pop());// return 1  queue is: [ 2, 3]
        myQueue.push(4); // queue is: [ 2, 3,4] (leftmost is front of the queue)

        System.out.println(myQueue.pop());// return 2
        System.out.println(myQueue.pop());// return 3
        System.out.println(myQueue.pop());// return 4


    }
/*    public static void main(String[] args) {
        QueueUsingStack232 myQueue = new QueueUsingStack232();
        System.out.println(myQueue.empty()); // return true

        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.empty()); // return false
        System.out.println(myQueue.peek());// return 1
        System.out.println(myQueue.pop());// return 1, queue is [2]
        System.out.println(myQueue.pop());// return 2
        System.out.println(myQueue.empty()); // return false
    }*/
}
