package com.leetcode;

import com.leetcode.Question206.ListNode;

/*******************
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * @author Walterll
 *
 */
public class Question203 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	//第一次提交的版本，有个bug，链表中只有错误val的时候返回错误，原因是第一个循环的判断存在bug，没有下一个节点就会跳过循环
//	public ListNode removeElements(ListNode head, int val) {
//        if (head == null) return head;
//        while (head.next != null) {
//        	if (head.val == val)
//        		head = head.next;
//        	else break;
//        }
//        ListNode node = head;
//        while (node.next != null) {
//        	if (node.next.val == val) {
//        		node.next = node.next.next;
//        	} else node = node.next;
//        }
//        return head;
//    }
	//第二次提交的版本，测试通过但是效率不佳，查了网上的代码多为双指针，但测试速度和我的差不多，慢点再考虑提升速度
	public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        while (head != null) {
        	if (head.val == val)
        		head = head.next;
        	else break;
        }
        if (head == null) return head;
        ListNode node = head;
        while (node.next != null) {
        	if (node.next.val == val) {
        		node.next = node.next.next;
        	} else node = node.next;
        }
        return head;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question203 q = new Question203();
		ListNode l1 = q.new ListNode(3);
		ListNode l2 = q.new ListNode(1);
		ListNode l3 = q.new ListNode(3);
		ListNode l4 = q.new ListNode(3);
		ListNode l5 = q.new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		ListNode reverseNode = q.removeElements(l1, 3);
		while (reverseNode != null) {
			System.out.println(reverseNode.val);
			reverseNode = reverseNode.next;
		}
	}

}
