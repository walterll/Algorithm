package com.leetcode;

/*****************
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * @author Walterll
 *
 */
public class Question35 {
	public int searchInsert(int[] nums, int target) {
        return searchInsert(nums, target, 0, nums.length - 1);
    }
	public int searchInsert(int[] nums, int target, int lo, int hi) {
//		if (hi == (lo + 1)) { //第一次提交的版本，当数组小于2个时会存在bug
//			if (target == lo) return lo;
//			return hi;
//	}
		if (hi <= (lo + 1)) {
			if (target <= nums[lo]) return lo;
			if (target <= nums[hi]) return hi;
			else return hi + 1;
		}
		int mid = nums[(hi - lo) / 2 + lo];
		if (target == mid) return (hi - lo) / 2 + lo;
		if (target < mid) return searchInsert(nums, target, lo, (hi - lo) / 2 + lo);
		return searchInsert(nums, target, (hi - lo) / 2 + lo, hi);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question35 q = new Question35();
		int[] nums = new int[]{1,3,5,6};
		System.out.println(q.searchInsert(nums, 2));
	}

}
