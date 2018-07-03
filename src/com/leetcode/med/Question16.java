package com.leetcode.med;

import java.util.Arrays;

/********************
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * @author Walterll
 *
 */
public class Question16 {
	//第一次自己写的算法，方法同15题，先排序，然后固定一个数字，从外向内移动两个指针，直到指针相遇才结束循环
	//速度较慢，成绩在70%，复习时查一下是否存在性能更好的代码
	public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int distance = Integer.MAX_VALUE;
        int closestSum = 0;
        for (int i = 0; i < nums.length; i++) {
        	int left = i + 1, right = nums.length - 1;
        	while (left < right) {
        		int sum = nums[i] + nums[left] + nums[right];
        		if (Math.abs(sum - target) < distance) {
        			distance = Math.abs(sum - target);
        			closestSum = sum;
        		}
        		if (sum < target) 
        			left++;
        		else 
        			right--;
        	}
        }
        return closestSum;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question16 q = new Question16();
		System.out.println(q.threeSumClosest(new int[]{-1,2,1,-4}, 1));
	}

}
