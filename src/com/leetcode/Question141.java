package com.leetcode;

/*******************
 * 给定一个链表，判断链表中是否有环。
 * 进阶：
 *　你能否不使用额外空间解决此题？
 * @author Walterll
 *
 */
public class Question141 {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	//遍历链表，将节点指向头节点，如果遍历中发现有节点指向头结点，则说明存在环
	public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode node = head;
        while (node.next != null) {
        	if (node.next == head)
        		return true;
        	ListNode nextNode = node.next;
        	node.next = head;
        	node = nextNode;
        }
        return false;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
