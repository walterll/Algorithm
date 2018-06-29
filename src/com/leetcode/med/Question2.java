package com.leetcode.med;

/********************
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @author Walterll
 *
 */
public class Question2 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	//第一次没仔细看题，搞错了链表和数字的对应关系，甚至凑巧能对一部分点。。。
//	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        int n1 = 0, n2 = 0;
//        while (l1 != null || l2 != null) {
//        	if (l1 != null) {
//        		n1 = n1 * 10 + l1.val;
//        		l1 = l1.next;
//        	}
//        	if (l2 != null) {
//        		n2 = n2 * 10 + l2.val;
//        		l2 = l2.next;
//        	}
//        }
//        int sum = n1 + n2;
//        ListNode node = null;
//        while (sum > 0) {
//        	ListNode newNode = new ListNode(sum % 10);
//        	sum /= 10;
//        	newNode.next = node;
//        	node = newNode;
//        }
//        return node;
        //第二次将答案反过来存，由于没仔细看清题目，且例题凑巧可以反过来做，结果改了一下后反反得正还通过了一些点，但是本质上错了
//        int sum = n1 + n2;
//        ListNode node = null, head = null;
//        if (sum == 0) 
//            head = new ListNode(0);
//         while (sum > 0) {
//        	ListNode newNode = new ListNode(sum % 10);
//        	sum /= 10;
//            if (node == null)
//                head = newNode;
//        	else 
//                node.next = newNode;
//        	node = newNode;
//        }
//        return head;
//    }
	//重新写了一个算法，方法同67和415题，看了下参考答案，在进位时有更简洁的处理方法，不需要判断sum>9
	//而是直接sum%10， carry = sum/10;
	//另外参考答案直接定义了个不使用的头结点，避免了后面还需要判断node==null，返回时直接返回head.next即可
	//自己代码还有些多余部分，如ListNode newNode = new ListNode(); node.next = newNode;
	//这个可以直接合并为node.next = new ListNode()
	//但总体成绩是差不多的
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int sum = 0;
		int carry = 0;
		ListNode node = null,head = null;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				sum = l2.val + carry;
				l2 = l2.next;
			} else if (l2 == null) {
				sum = l1.val + carry;
				l1 = l1.next;
			} else {
				sum = l1.val + l2.val + carry;
				l1 = l1.next;
				l2 = l2.next;
			}
			if (sum > 9) {		//可以不做判断，方法见前面注释
				sum -= 10;
				carry = 1;
			} else
				carry = 0;
			ListNode newNode = new ListNode(sum);
			if (node == null)
				head = newNode;
			else
				node.next = newNode;
			node = newNode;
		}
		if (carry == 1) {
			ListNode newNode = new ListNode(1);	//这两句可合并为node.next = new ListNode(1)
			node.next = newNode;
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
