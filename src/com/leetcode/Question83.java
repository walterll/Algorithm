package com.leetcode;

/***********************
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * @author Walterll
 *
 */
public class Question83 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) return head;
        ListNode node = head;
        while (node.next != null) {  
//        while ((node != null) && (node.next != null)) {
        	if (node.next.val == node.val)
        		node.next = node.next.next;
        	else  //第一次没有这行，产生逻辑错误，无法判断连续2个以上相同的节点，
        		  //且while条件中会出现空指针异常,因为将node连续跳了2个节点
        	node = node.next;
        }
        return head;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question83 q = new Question83();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(1);
		ListNode l3 = q.new ListNode(2);
		ListNode l4 = q.new ListNode(3);
		ListNode l5 = q.new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		q.deleteDuplicates(l1);
	}

}
