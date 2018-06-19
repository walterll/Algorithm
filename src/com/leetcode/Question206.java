package com.leetcode;

import com.leetcode.Question83.ListNode;

/******************
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * @author Walterll
 *
 */
public class Question206 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	//算法并没有什么难，但是要思路清晰一次写完并不容易，可以在纸上练习下
	public ListNode reverseList(ListNode head) {
        ListNode next,node = head;
        if (node == null) return node;
        next = node.next;
        while (next != null) {
        	ListNode temp = next;
        	next = next.next;
        	temp.next = node;
        	node = temp;
        }
        head.next = null;
        return node;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question206 q = new Question206();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(1);
		ListNode l3 = q.new ListNode(2);
		ListNode l4 = q.new ListNode(3);
		ListNode l5 = q.new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		ListNode reverseNode = q.reverseList(l1);
		while (reverseNode != null) {
			System.out.println(reverseNode.val);
			reverseNode = reverseNode.next;
		}
	}

}
