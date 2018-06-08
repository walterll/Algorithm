package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/***********************
 * Given an array of integers, return indices of the two numbers
 * such that they add up to a specific target.
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * @author Walterll
 */
public class Question1 {
	/*****************
	 * 第一版，只能解决nums大于零的情况
	 * @param nums
	 * @param target
	 * @return
	 */
//	public int[] twoSum(int[] nums, int target) {
//        int[] numToIndice = new int[target];
//        int[] indice = new int[2];
//        for (int i = 0; i < nums.length; i++) {
//        	if (nums[i] < target) 
//        		numToIndice[nums[i]] = i;
//        }
//        for (int i = 0; i < nums.length; i++) {
//        	if (nums[i] < target)
//        		if (numToIndice[target - nums[i]] != 0) {
//        			indice[0] = i;
//        			indice[1] = numToIndice[target - nums[i]];
//        			break;
//        		}
//        }
//        return indice;
//    }
	/*************************
	 * 第二版，没考虑到target为负的情况
	 * @param nums
	 * @param target
	 * @return
	 */
//	public int[] twoSum(int[] nums, int target) {
//		int[] numToIndice = new int[target];
//        int[] indice = new int[2];
//        Map<Integer, Integer> outRangeNum = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//        	if ((nums[i] > 0) && (nums[i] < target)) 
//        		numToIndice[nums[i]] = i;
//        	else outRangeNum.put(nums[i], i);  
//        }
//        for (int i = 0; i < nums.length; i++) {
//        	if ((nums[i] > 0) && (nums[i] < target)) {
//        		if (numToIndice[target - nums[i]] != 0) {
//        			indice[0] = i;
//        			indice[1] = numToIndice[target - nums[i]];
//        			break;
//        		}
//        	} else {
//        		if (outRangeNum.containsKey(target - nums[i])) {
//        			indice[0] = i;
//        			indice[1] = outRangeNum.get(target - nums[i]);
//        			break;
//        		}
//        			
//        	}
//        }
//        return indice;
//	}
	/**************************
	 * nums为正数且小于target时存到数组，其他情况存到HashMap
	 * o(n)复杂度，数组比HashMap稍快
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		int[] numToIndice;
		if (target > 0)
			numToIndice = new int[target];
		else 
			numToIndice = new int[0];
        int[] indice = new int[2];
        Map<Integer, Integer> outRangeNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
        	if ((nums[i] > 0) && (nums[i] < target)) 
        		numToIndice[nums[i]] = i;
        	else outRangeNum.put(nums[i], i);  
        }
        for (int i = 0; i < nums.length; i++) {
        	if ((nums[i] > 0) && (nums[i] < target)) {
        		if (numToIndice[target - nums[i]] != 0) {
        			indice[0] = i;
        			indice[1] = numToIndice[target - nums[i]];
        			break;
        		}
        	} else {
        		if (outRangeNum.containsKey(target - nums[i])) {
        			indice[0] = i;
        			indice[1] = outRangeNum.get(target - nums[i]);
        			break;
        		}
        			
        	}
        }
        return indice;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question1 q = new Question1();
		int[] nums = new int[]{2,7,-11,15,22,23,26,-32,36,43,57,66,75,78,112};
		int target = 46;
		int[] indice = q.twoSum(nums, target);
		System.out.println(indice[0] + " " + indice[1]);
	}

}
