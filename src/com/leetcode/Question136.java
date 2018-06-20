package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/********************
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * @author Walterll
 *
 */
//异或的运用，比较经典，复习时总结
public class Question136 {
	//这是自己想到的方法，存到HashSet中，但是要使用额外空间，且速度较慢。不使用额外空间的方法网上查了下是使用异或的性质
//	public int singleNumber(int[] nums) {
//        Set<Integer> numberSet = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//        	if (numberSet.contains(nums[i]))
//        		numberSet.remove(nums[i]);
//        	else
//        		numberSet.add(nums[i]);
//        }
//        return numberSet.iterator().next();
//        
//    }
	//网上查了不用额外空间的方法，即使用异或
	public int singleNumber(int[] nums) {
		int singleNum = nums[0];
		for (int i = 1; i < nums.length; i++)
			singleNum ^= nums[i];
		return singleNum;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question136 q = new Question136();
		int[] num = new int[]{4,1,2,1,2};
		System.out.println(q.singleNumber(num));
//		System.out.println(3 ^ 4 ^ 5 ^ 4 ^ 3);
		
	}

}
