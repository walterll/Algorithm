package com.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**********************
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * @author Walterll
 *
 */
public class Question169 {
	//自己想的方法，将数组遍历一遍存入HashMap，o(n)复杂度，速度较慢
//	public int majorityElement(int[] nums) {
//		if (nums.length == 1)
//			return nums[0];
//        Map<Integer, Integer> elements = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//        	if (elements.containsKey(nums[i])) {
//        		int count = elements.get(nums[i]);
//        		if ((count + 1) > nums.length / 2)
//        			return nums[i];
//        		elements.put(nums[i], count + 1);
//        	} else
//        		elements.put(nums[i], 1);
//        }
//        return 0;
//    }
	//看了网上的解法，先排序，之后数组中间的数即是众数，o(nlogn)复杂度，不需要额外空间，实际速度反而比上个方法更快，原因不明
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question169 q = new Question169();
		int[] nums = new int[]{3, 2, 3};
		System.out.println(q.majorityElement(nums));
	}

}
