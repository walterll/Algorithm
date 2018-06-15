package com.leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * 
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Question21 {
	public class ListNode {
			int val;
			ListNode next;
			ListNode(int x) { val = x; }
	}
	//网上有另一个使用递归的方法，代码要简洁很多，且速度更快，下次尝试自己写一下
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//		if (l1 == null && l2 == null)		
//			return null;
		//参考了网上的代码，遇到空链表的情况完全可以直接返回另一个链表，而无需继续后面的判断
		//同时发现同样代码每次提交运行时间会有较大波动，此处改动提交后运行速度在11ms--28ms之间，成绩波动较大
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		ListNode head,next;
//		if (l1 == null) {
//			head = l2;
//			l2 = l2.next;
//		} else if (l2 == null) {
//			head = l1;
//			l1 = l1.next;
//		} else {		
        if (l1.val < l2.val) {
        	head = l1;
        	l1 = l1.next;
        } else {
        	head = l2;
        	l2 = l2.next;
        }
//		}
        next = head;
        while (true) {
        	if (l1 == null && l2 == null)	//此处判断不能直接写到while条件中，否则性能急剧下降
        									//具体原因暂时不明，疑为编译器优化关系，可查看一下字节码
        		break;
        	if (l1 == null) {
        		next.next = l2;
        		next = l2;	//此句不能漏写，否则next节点不会移动，导致最终链表中只有2个节点
        		l2 = l2.next;
        		break;		//第一次写的时候用了continue，其实当有一个链表为空后就没必要继续遍历下去了，
        					//因为后面必然全是另一个链表的节点，因此改为break，从52%提升到72%
        	}
        	if (l2 == null) {
        		next.next = l1;
        		next = l1;
        		l1 = l1.next;
        		break;
        	}
        	if (l1.val < l2.val) {
        		next.next = l1;
        		next = l1;
        		l1 = l1.next;
        	} else {
        		next.next = l2;
        		next = l2;
        		l2 = l2.next;
        	}
        }
        return head;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
