package com.leetcode;

import java.util.Stack;

/******************
 * 使用栈实现队列的下列操作：
push(x) -- 将一个元素放入队列的尾部。
pop() -- 从队列首部移除元素。
peek() -- 返回队列首部的元素。
empty() -- 返回队列是否为空。
示例:
MyQueue queue = new MyQueue();
queue.push(1);
queue.push(2);  
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
说明:
你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * @author Walterll
 *
 */
//仔细思考一下，就会发现可以使用两个栈来实现队列，将一个栈pop出来的顺序push到另一个栈中，再pop的顺序即为队列的顺序
//关键在于连个栈pop和push的顺序，每次只有第二个栈为空时才将第一个栈pop进去，这点注意
//总结时理清思路
public class Question232 {
	private Stack<Integer> stack;
	private Stack<Integer> queue;
	public Question232() {
		stack = new Stack<>();
		queue = new Stack<>();
	}
	/** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (queue.isEmpty()) {
        	while (!stack.isEmpty())
        		queue.push(stack.pop());
        }
        return queue.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	if (queue.isEmpty()) {
        	while (!stack.isEmpty())
        		queue.push(stack.pop());
        }
    	return queue.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return (stack.isEmpty() && queue.isEmpty());
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
