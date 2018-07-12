package com.leetcode.med;

/******************
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * @author Walterll
 *
 */
//自己想的方法，成绩10%，难点是改变链表结构的同时正确向后遍历
//代码写的有点复杂，看一下网上有没有简洁的代码
public class Question92 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) return head;
        ListNode buffer = new ListNode(0);			//遍历过程中的本节点
        ListNode p1 = buffer;						//p1.next为最后的头结点
        buffer.next = head;
        int index = 1;								//节点的位置，当buffer.next是头结点时index是1
        ListNode mNode = null, inverseNode = null;	//mNode:m位置的节点，需要保存，在循环结束后连接到n后面的节点
        											//inverseNode: 循环中上一个需要反转的节点，此循环中的节点要连接到inverseNode
        while (index < n) {
        	//buffer.next是第一个需要反转的节点
        	if (index == m) {
        		 mNode = buffer.next;
        		 inverseNode = mNode;
        		 buffer.next = buffer.next.next;
        	} else if (index > m) {
        		ListNode node = buffer.next;
        		buffer.next = buffer.next.next;
        		node.next = inverseNode;
        		inverseNode = node;
        	} else
        		buffer = buffer.next;
        	index++;	//别漏了index自增
        }
        mNode.next = buffer.next.next;
        buffer.next.next = inverseNode;
        return p1.next;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question92 q = new Question92();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(2);
		ListNode l3 = q.new ListNode(3);
		ListNode l4 = q.new ListNode(4);
		ListNode l5 = q.new ListNode(5);
		ListNode l6 = q.new ListNode(6);
		ListNode l7 = q.new ListNode(7);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = l7;
		ListNode node = q.reverseBetween(l1, 2, 4);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

}
