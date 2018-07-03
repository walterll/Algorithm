package com.leetcode.med;

/****************
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * @author Walterll
 *
 */
//自己想到的，使用数组存储n+1个节点的引用，这样在到达链表尾的时候不需要重新遍历链表可直接根据数组索引得到倒数n个和倒数n+1个节点
//成绩前5%
//网上查了下，有个更简洁的方法是双指针（应该是这题的原意）,用一个指针指向链表头，一个指针指向第n个节点，然后同时移动两个指针，
//直到第二个指针到了链表尾，此时第一个指针就只在倒数n个节点，此方法比我的方法节省了一些数组空间
//复习的话写一下第二种方法
public class Question19 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] nodes = new ListNode[n + 1];	//因为需要获得倒数第n个节点的前一个节点，所以数组比n大1
        										//注意：对象数组的声明方式
        ListNode node = head;
        int index = 0;
        //保存n+1个节点的引用到数组，这样当到达最后一个节点时倒数n+1个节点必然在数组中
        while (node != null) {
        	//数组满了后从头开始覆盖掉n+1之前的节点
        	if (index > n) {	
        		index = 0;
        	}
        	nodes[index] = node;
        	node = node.next;
        	index++;
        }
        index--;	//因为最后一个循环后index加了一，这里需减一使得index表示最后一个节点所在的数组元素的下标
        index = index == n ? 0 : index + 1;	//获得倒数第n+1个节点所在的数组的下标
        //节点为空则表明倒数第n个节点是首节点
        if (nodes[index] == null) 	
        	head = head.next;
        //倒数第n个节点是尾节点
        else if (nodes[index].next.next == null) 
        	nodes[index].next = null;
        else {
        	nodes[index].next = nodes[index].next.next;
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
