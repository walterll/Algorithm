package com.leetcode.med;

import com.leetcode.med.Question86.ListNode;

/********************
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 说明：不允许修改给定的链表。
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * @author Walterll
 *
 */
//此题自己未能想出解法，查看网上解答使用快慢指针，快指针比慢指针快一倍，若链表有环，两个指针必定会相遇，
//当两个指针相遇时，将一个指针放到链表头，两个指针再按照相同速度移动，再次相遇的点即为环的第一个节点，画图可以清楚看出其中的数学关系
//复习时记忆此方法
public class Question142 {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	//第一次提交的版本，存在bug，当环指向链表头时可能造成无限循环，仔细查看和第二次修改版本的区别
	//原因是这样写fast指针先移动会造成提前追上slow指针带来错误，比如fast移动距离3时slow只移动了1，正确的情况slow应该已经移动了2步
//	public ListNode detectCycle(ListNode head) {
//        if (head == null) return null;
//        ListNode fast = head, slow = head;
//        int timer = 0;
//        fast = fast.next;
//        while (fast != slow) {
//        	if (fast == null)
//        		return null;
//        	if (timer % 2 == 0) 
//        		slow = slow.next;
//        	fast = fast.next;
//        	timer++;
//        }
//        slow = head;
//        while (fast != slow) {
//        	fast = fast.next;
//        	slow = slow.next;
//        }
//        return fast;
//    }
	//修改后的版本，成绩0%
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) return null;
        ListNode fast = head.next.next, slow = head.next;
        int timer = 0;
        while (fast != slow) {
        	if (fast == null)
        		return null;
        	if (timer % 2 == 0) 
        		slow = slow.next;
        	fast = fast.next;
        	timer++;
        }
        slow = head;
        while (fast != slow) {
        	fast = fast.next;
        	slow = slow.next;
        }
        return fast;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question142 q = new Question142();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(2);
		ListNode l3 = q.new ListNode(3);
		ListNode l4 = q.new ListNode(4);
		ListNode l5 = q.new ListNode(5);
		ListNode l6 = q.new ListNode(6);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = l1;
		System.out.println(q.detectCycle(l1).val);
	}

}
