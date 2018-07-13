package com.leetcode.med;

/*******************
 * 给定一个链表和一个特定值 x，对链表进行分隔，
 * 使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * @author Walterll
 *
 */
//此题耗了很大功夫，方法看下面注释
public class Question86 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
//	public ListNode partition(ListNode head, int x) {
//        ListNode smaller = new ListNode(0), larger = new ListNode(0), node = head;
//        while (node != null) {
//        	if (node.val < x) {
//        		smaller.next = node;
//        		smaller = node;
//        		larger.next = node.next;
//        	}
//        	else {
//        		larger.next = node;
//        		larger = node;
//        	}
//        	node = node.next;
//        }
//        return head;
//    }
	//第二次改写，试了好多次依然有错误，发现还是需要一个指针指向已知的最后一个大于x的节点，否则会产生1>2>5>4>2>5>4>2>5>4的无限循环
//	public ListNode partition(ListNode head, int x) {
//        ListNode lastSmaller = head, node = head;
//        while (node != null) {
//            ListNode nextNode = node.next;
//        	if (node.val < x) {
//        		if (node == head) {
//        			node = nextNode;
//        			continue;
//        		}
//                node.next = lastSmaller.next;
//        		lastSmaller.next = node;
//                lastSmaller = node;
//        	}
//            node = nextNode;
//        }
//        return head;
//    }
	//第三次写，最终使用的方法是分别建立小于x和大于等于x的两个链表，最后将他们连接起来，同时要注意首节点和尾节点的处理
	//使用了5个指针，成绩为10%，复习可以研究下是否有更简洁的写法
	public ListNode partition(ListNode head, int x) {
        ListNode lastSmaller = new ListNode(0);
        ListNode lastLarger = new ListNode(0);
        ListNode firstLarger = lastLarger,firstSmaller = lastSmaller, node = head;
        while (node != null) {
        	if (node.val < x) {
        		lastSmaller.next = node;
        		lastSmaller = node;
        	} else {
        		lastLarger.next = node;
        		lastLarger = node;
        	}
            node = node.next;
        }
        lastSmaller.next = firstLarger.next;
        lastLarger.next = null; //不能漏写这条，否则最后一个数如果小于x就会形成环型链表无限循环
//        return head;	//这里不能返回head,如果head大于x的话head就不是正确的头部
        return firstSmaller.next;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question86 q = new Question86();
		ListNode l1 = q.new ListNode(2);
		ListNode l2 = q.new ListNode(1);
//		ListNode l3 = q.new ListNode(3);
//		ListNode l4 = q.new ListNode(2);
//		ListNode l5 = q.new ListNode(5);
//		ListNode l6 = q.new ListNode(2);
		l1.next = l2;
//		l2.next = l3;
//		l3.next = l4;
//		l4.next = l5;
//		l5.next = l6;
		ListNode l = q.partition(l1, 2);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
	}

}
