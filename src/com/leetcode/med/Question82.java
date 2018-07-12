package com.leetcode.med;

/****************
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * @author Walterll
 *
 */
//此题做了好几遍，最后通过后速度很慢，然后发现给的链表是排序的，而我第三次写的算法是可以解决未排序的。。。。。。
public class Question82 {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	//第一次写的版本，想法是将每个节点的上一个节点存到map中，这样发现重复时可以删除节点
	//但是删除节点时会破坏原链表的结构，使得map中的节点信息出错，所以存在bug，且存在重复删除问题
//	public ListNode deleteDuplicates(ListNode head) {
//        Map<Integer, ListNode> map = new HashMap<>();
//        ListNode node = new ListNode(0);
//        node.next = head;
//        ListNode firstNode = node;
//        while (node != null && node.next != null) {
//        	ListNode duplicate = map.get(node.next.val);
//        	//这个版本这样写会存在重复删除，且没有维护删除后的map信息
//        	if (duplicate != null) {
////        		duplicate.next = duplicate.next.next;
////        		node.next = node.next.next;
//        		node.next = node.next.next;
//        		duplicate.next = duplicate.next.next;
//        	} else
//        		map.put(node.next.val, node);
//        	node = node.next;
//        }
//        return firstNode.next;
//    }
	
	//第二次改的版本，想法是将每个不重复的节点存到map，然后没遇到重复的节点存到list，然后分别山list和map
	//但是依然存在问题，当两个重复的节点连续时，删除第一个后保存第二个节点的前一个节点就失效了，导致无法正确删去第二个节点
//	public ListNode deleteDuplicates(ListNode head) {
//        Map<Integer, ListNode> map = new HashMap<>();
//        List<ListNode> list = new ArrayList<>();
//        ListNode node = new ListNode(0);
//        node.next = head;
//        ListNode firstNode = node;
//        while (node != null && node.next != null) {
//        	if (map.containsKey(node.next.val)) {
//        		list.add(node);
//        	} else
//        		map.put(node.next.val, node);
//        	node = node.next;
//        }
//        for (ListNode duplicate : list) {
//        	ListNode firstDuplicate = map.get(duplicate.next.val);
//        	if (firstDuplicate != null) {
//        		firstDuplicate.next = firstDuplicate.next.next;
//        		map.remove(duplicate.next.val);
//        	}
//        	duplicate.next = duplicate.next.next;
//        }
//        return firstNode.next;
//    }
	//第三次重写，使用stack替代list,这样可以从后面开始删除重复节点，且能避免影响存储的重复节点的结构
	//速度较慢，在后10%
//	public ListNode deleteDuplicates(ListNode head) {
//        Map<Integer, ListNode> map = new HashMap<>();
//        Stack<ListNode> stack = new Stack<>();
//        ListNode node = new ListNode(0);
//        node.next = head;
//        ListNode firstNode = node;
//        while (node != null && node.next != null) {
//        	ListNode duplicate = map.get(node.next.val);
//        	if (duplicate != null) {
//        		stack.add(duplicate);
//        		map.put(node.next.val, node);	//stack里存储的是上一个重复的节点，因此map需要更新为此节点
//        	} else
//        		map.put(node.next.val, node);
//        	node = node.next;
//        }
//        while (!stack.isEmpty()) {
//        	ListNode duplicate = stack.pop();
//        	ListNode lastDuplicate = map.get(duplicate.next.val);
//        	if (lastDuplicate != null) {
//        		lastDuplicate.next = lastDuplicate.next.next;
//        		map.remove(duplicate.next.val);
//        	}
//        	duplicate.next = duplicate.next.next;
//        }
//        return firstNode.next;
//    }
	//参考了网上的参考答案写的版本，只需要使用一个指针，有相当高的技巧
	//此题可以重点复习一下
	public ListNode deleteDuplicates(ListNode head) {
		ListNode node = new ListNode(0);
		node.next = head;
		ListNode firstNode = node;
		while (node.next != null && node.next.next != null) {
			if (node.next.val == node.next.next.val) {
				node.next = node.next.next;
				//此内循环是精华所在
				while (node.next.next != null && node.next.val == node.next.next.val) 
//				while (node.next.next != null)
//					if (node.next.val == node.next.next.val)
						node.next = node.next.next;
//					else {
//						node.next = node.next.next;
//						break;
//					}
				//此句是用来跳过重复节点中的最后一个节点，容易漏写
				node.next = node.next.next;
			} else
				node = node.next;
		}
		return firstNode.next;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question82 q = new Question82();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(1);
		ListNode l3 = q.new ListNode(3);
		ListNode l4 = q.new ListNode(3);
		ListNode l5 = q.new ListNode(4);
		ListNode l6 = q.new ListNode(5);
		ListNode l7 = q.new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = l7;
		ListNode node = q.deleteDuplicates(l1);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

}
