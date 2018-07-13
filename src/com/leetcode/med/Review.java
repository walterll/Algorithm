package com.leetcode.med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.leetcode.med.Question82.ListNode;

public class Review {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	/****************
	 * Question82
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
	public ListNode deleteDuplicates(ListNode head) {
		ListNode headNode = new ListNode(0);
		headNode.next = head;
		ListNode node = headNode;
		while (node.next != null && node.next.next != null) {
			if (node.next.val == node.next.next.val) {
				node.next = node.next.next;
				//这段和原版写的有细微区别，会导致当最后一个节点是重复节点的话无法正常删除，因为跳过的那一句话包含在循环中了
//				while (node.next != null && node.next.next != null) {
//					if (node.next.val == node.next.next.val)
//						node.next = node.next.next;
//					else {
//						node.next = node.next.next;
//						break;
//					}
//				}
				//可以改写成这样，与原版等价
				while (node.next != null && node.next.next != null) {
					if (node.next.val == node.next.next.val)
						node.next = node.next.next;
					else break;
				}
				node.next = node.next.next;	//关键此句必须在循环外，作用是处理最后一个重复节点
			} else 
				node = node.next;
		}
		return headNode.next;
	}
	
	/*********************
	 * Question11
	 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
	 * 画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
	 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 * 注意：你不能倾斜容器，n 至少是2。
	 * @author Walterll
	 *
	 */
	//已经比较接近参考答案了，不过速度不快，甚至直接将参考答案提交也偶尔会超时
	public int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int area = Math.min(height[left], height[right]) * (right - left);
		//循环还可以更简略，计算area放在if之前
		while (left < right) {
			if (height[left] < height[right]) {
				left++;
				area = Math.max(area, Math.min(height[left], height[right]) * (right - left));
			} else {
				right--;
				area = Math.max(area, Math.min(height[left], height[right]) * (right - left));
			}
		}
		return area;
	}
	
	/**********************
	 * Question15
	 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
	 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
	 * 注意：答案中不可以包含重复的三元组。
	 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	 * 满足要求的三元组集合为：
	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]
	 * @author Walterll
	 *
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (i - 1 >= 0 && nums[i] == nums[i - 1])
				continue;
			int left = i + 1, right = nums.length - 1;
			while (left < right) {
				if (left - 1 >= i + 1 && nums[left] == nums[left - 1]) {
					left++;
					continue;
				}
				//此处判断相比Question15中写的有所优化，先判断<和>比先判断==更快
				if (nums[i] + nums[left] + nums[right] < 0) {
					left++;
					continue;
				} else if (nums[i] + nums[left] + nums[right] > 0) {
					right--;
					continue;
				} else {
					List<Integer> num = new ArrayList<>();
					num.add(nums[i]);
					num.add(nums[left]);
					num.add(nums[right]);
					list.add(num);
					left++;
					right--;
				}
			}
		}
		return list;
	}
	
	/********************
	 * Question16
	 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
	 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
	 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
	 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
	 * @author Walterll
	 *
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int differ = Integer.MAX_VALUE, sum = 0;
		for (int i = 0; i < nums.length; i++) {
			int left = i + 1, right = nums.length - 1;
			while (left < right) {
				int summary = nums[i] + nums[left] + nums[right];
				//这里比Question16里原来写的多了一句，先用个变量存储差，后面可以少计算一次,速度提升50%
				int different = Math.abs(summary - target);	
				if (different < differ) {
					differ = different;
					sum = summary;
				}
				if (summary < target) 
					left++;
				else
					right--;
			}
		}
		return sum;
	}
	
	/****************
	 * Question19
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
	//此方法比原来自己想的方法代码更简洁
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode fast = new ListNode(0), slow = fast;
		ListNode headNode = fast;
		fast.next = head;
		int index = 0;		//此数值注意，第一次写成1结果差了1个节点
		while (fast.next != null) {
			if (index < n) {
				fast = fast.next;
				index++;
			} else {
				fast = fast.next;
				slow = slow.next;
			}
		}
		slow.next = slow.next.next;
		return headNode.next;
	}
	
	/**********************
	 * Question24 此题可多复习
	 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
	 * 示例:
	 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
	 * 说明:
	 * 你的算法只能使用常数的额外空间。
	 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	 * @author Walterll
	 *
	 */
	public ListNode swapPairs(ListNode head) {
		ListNode node = new ListNode(0);
//		ListNode newNode = new ListNode(0), headNode = newNode;
		node.next = head;
		ListNode prev = node, latt = head;
		//这样写同样会破坏链表结构造成回路，然后就无限在第一和第二节点循环
//		while (node.next != null && node.next.next != null) {
//			newNode.next = node.next.next;
//			newNode.next.next = node.next;
//            node = node.next.next;
//			newNode = newNode.next.next;
//		}
		//这是参照了网上的一个答案写的，只需要使用两个辅助节点，有较高的技巧，建议多复习
		while (prev.next != null && prev.next.next != null) {
			prev.next = latt.next;
			prev = prev.next;
			latt.next = prev.next;
			prev.next = latt;
			latt = latt.next;
			prev = prev.next;
			
		}
		return node.next;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Review q = new Review();
		ListNode l1 = q.new ListNode(1);
		ListNode l2 = q.new ListNode(2);
		ListNode l3 = q.new ListNode(3);
		ListNode l4 = q.new ListNode(4);
		ListNode l5 = q.new ListNode(5);
		ListNode l6 = q.new ListNode(6);
//		ListNode l7 = q.new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
//		l6.next = l7;
//		ListNode node = q.deleteDuplicates(l1);
//		while (node != null) {
//			System.out.println(node.val);
//			node = node.next;
//		}
//		int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//		System.out.println(q.threeSum(nums));
		ListNode node = q.swapPairs(l1);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

}
