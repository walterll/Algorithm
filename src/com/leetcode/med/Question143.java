package com.leetcode.med;

/***********************
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * @author Walterll
 *
 */
//这题有点烦，目前在链表长度为奇数和偶数时的区别还有bug，慢点再改2018、7、6
public class Question143 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	//第一次写的版本，尝试先找出中点然后反转后半部分第三次遍历将后半部分插入前半部分，
	//但是由于需要处理中点和尾节点等多个非常复杂得节点操作，几天都没能成功，看了网上的代码，下面决定将前半部分和后半部分分开
//	public void reorderList(ListNode head) {
//        int length = 1,index = 1;
//        ListNode buffer = head,lastNode = null;
//        while (buffer != null) {
//        	lastNode = buffer;
//        	buffer = buffer.next;
//        	length++;
//        }
//        int halfLength = length / 2 + 1;
//        buffer = head;
//        ListNode inverseNode = null;
//        while (buffer != null) {
//        	if (index < halfLength) {
//        		buffer = buffer.next;
//        		index++;
//        	} else if (index == halfLength){
//        		ListNode next = buffer.next;
//        		buffer.next = lastNode;
//        		inverseNode = buffer;
//        		buffer = next;
//        		index++;
//        	} else if (index == halfLength + 1){
//        		ListNode next = buffer.next;
//        		buffer.next = null;
//        		inverseNode = buffer;
//        		buffer = next;
//        		index++;
//        	} else {
//        		ListNode next = buffer.next;
//        		buffer.next = inverseNode;
//        		inverseNode = buffer;
//        		buffer = next;
//        		index++;
//        	}
//        }
//        buffer = head;
//        index = 1;
//        while (index < halfLength - 1) {
//        	ListNode next = buffer.next, nextLast = lastNode.next;
//        	buffer.next = lastNode;
//        	lastNode.next = next;
//        	buffer = next;
//        	lastNode = nextLast;
//        	index++;
//        }
//        buffer.next = null;
//        buffer = head;
//        while (buffer != null) {
//        	System.out.println(buffer.val);
//        	buffer = buffer.next;
//        }
//    }
	//参考了网上的代码，将链表分成两部分，中间切断，同时改进了倒置链表的方法
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) return;
		//先使用快慢指针找到链表的中间节点
		ListNode fast = head, slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode lastHalf = slow.next;
		//lastHalfHead定义为第二段链表的头部
		ListNode lastHalfHead = new ListNode(0);
		lastHalfHead.next = null;		//关键，这里不设为空的话链表会出现循环
		slow.next = null;
		//反转，方法是将下一个节点插入lastHalfHead的下一个节点
		while (lastHalf != null) {
			ListNode next = lastHalf.next;
			lastHalf.next = lastHalfHead.next;
			lastHalfHead.next = lastHalf;
			lastHalf = next;
		}
		lastHalfHead = lastHalfHead.next;
		//将反转的链表插入前半段链表
		fast = head;
		while (fast != null && lastHalfHead != null) {
			ListNode next = fast.next;
			ListNode lastNext = lastHalfHead.next;
			lastHalfHead.next = fast.next;
			fast.next = lastHalfHead;
			fast = next;
			lastHalfHead = lastNext;
			
		}
//		while (head != null) {
//			System.out.println(head.val);
//			head = head.next;
//		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question143 q = new Question143();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(2);
		ListNode l3 = q.new ListNode(3);
		ListNode l4 = q.new ListNode(4);
		ListNode l5 = q.new ListNode(5);
		ListNode l6 = q.new ListNode(6);
//		ListNode l7 = q.new ListNode(7);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
//		l6.next = l7;
		q.reorderList(l1);
	}

}
