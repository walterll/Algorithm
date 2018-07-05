package com.leetcode.med;

/**********************
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 说明:
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author Walterll
 *
 */
//一遍通过，没有什么技巧，使用了多个辅助节点来交换，代码稍显复杂
//成绩30%，复习时查一下有什么更好的代码
public class Question24 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(0);	//辅助节点，用于遍历链表
        node.next = head;
        ListNode firstNode = node;			//辅助的头结点，.next即是实际的头结点
        while (node.next != null && node.next.next != null) {
        	ListNode first = node.next;	//第一个节点
        	ListNode next = first.next;	//第二个节点
        	first.next = next.next;
        	next.next = first;
        	node.next = next;
        	node = first;
        }
        return firstNode.next;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
