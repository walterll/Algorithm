package com.leetcode;

/******************
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * @author Walterll
 *
 */
public class Question155 {
/************************
 * 第一次写的时候，尝试用链表自己实现栈，并且用一个变量存最小值
 * 但是存在一个问题就是pop的时候如果删的是最小值的那个节点，需要重新遍历栈来得到新的最小值
 * 成绩只有50%
 * 网上查了下，首先可以使用java自带的stack，不必自己写链表实现，其次可以将最小值同样使用栈来维护
 * 可以使用同一个栈也可以使用两个栈，(不过速度没有提高多少)搞清楚逻辑思想，总结时自己再重写一遍
 * @author Walterll
 *
 */
	private class Node {
		int val;
		Node next;
		Node (int val) {
			this.val = val;
		}
	}
	private int min;
	private Node head;
	public Question155() {	//提交时改成public MinStack()
    }
    
    public void push(int x) {
        if (head == null) {
        	head = new Node(x);
        	min = x;
        } else {
        	Node node = new Node(x);
        	node.next = head;
        	head = node;
        	if (x < min)
        		min = x;
        }
    }
    
    public void pop() {
    	//当栈顶元素即是最小值，遍历栈找到新的最小值
    	if (head.val == min) {
    		if (head.next != null) {
	    		Node node = head.next;
	    		min = node.val;
	    		while (node != null) {//此处注意，第一次写成了node.next!=null，这样无法计算最后一个节点
	    			if (node.val < min)
	    				min = node.val;
	    			node = node.next;
	    		}
    		}
    	}
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return min;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
