package com.leetcode.med;

import java.util.Arrays;

/***********************
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的子数组。如果不存在符合条件的子数组，返回 0。
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的子数组。
 *　进阶:
　* 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * @author Walterll
 *
 */
//成绩为40%，具体方法见下面注释
//复习的话查一下是否有改进方法
public class Question209 {
	//第一次写，题目理解错了，以为是数组中元素的和，直接用排序了。其实是子数组的和，所以必须是连续的
//	public int minSubArrayLen(int s, int[] nums) {
//        Arrays.sort(nums);
//        int sum = 0;
//        for (int i = nums.length - 1; i >= 0; i--) {
//        	sum += nums[i];
//        	if (sum >= s)
//        		return nums.length - i;
//        }
//        return 0;
//    }
	//自己又想了个双指针的方法，类似滑动窗口，将左右指针指向数组中的元素，若两个指针间的元素的和大于s，左指针右移，否则右指针右移
	//注意边界处需要谨慎处理
	public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int left = 0, right = 0;
        int sum = nums[0], length = Integer.MAX_VALUE;
        while (right < nums.length - 1 || sum >= s) {	//nums.length-1是因为在循环内有++right操作，若nums.length会数组溢出
        												//||sum>=s是因为当右指针到达最右元素时，循环可能没有结束，还需要移动左指针
        	if (sum >= s) {
        		length = Math.min(length, right - left + 1);
        		sum -= nums[left++];		//此处注意是left++而不是++left，sum减去的是左指针原来指向的元素
        	} else
        		sum += nums[++right];
        	
        }
        if (length > nums.length) return 0;	//注意不能漏写这句，否则若没有找到子数组，会返回Integer.MAX_VALUE
        return length;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question209 q = new Question209();
		System.out.println(q.minSubArrayLen(213, new int[]{12,28,83,4,25,26,25,2,25,25,25,12}));
	}

}
