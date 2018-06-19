package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************
 * 给定两个数组，写一个方法来计算它们的交集。
 *　例如:
 *　给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
 * 注意：
 *  输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 跟进:
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
 * @author Walterll
 *
 */
public class Question350 {
//	public int[] intersect(int[] nums1, int[] nums2) {
//        if (nums1.length <= nums2.length) {
//        	Arrays.sort(nums2);
//        	return intersectOrdered(nums1, nums2);
//        } else {
//        	Arrays.sort(nums1);
//        	return intersectOrdered(nums2, nums1);
//        }
//    }
//	public int[] intersectOrdered(int[] num, int[] orderedNum) {
//		for (int i = 0; i < num.length; i++) {
//			
//		}
//	}
	//这题没想到什么好方法，网上查了下于是使用了哈希，但是提交成绩很低，还有较大提升空间
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1.length < nums2.length)
			return intersectX(nums2, nums1);
		return intersectX(nums1, nums2);
	}
	
	public int[] intersectX(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums1.length; i++) {
			if (map.containsKey(nums1[i]))
				map.put(nums1[i], (map.get(nums1[i]) + 1 ));
			else 
				map.put(nums1[i], 1);
		}
		List<Integer> intersectSet = new ArrayList<>();
		for (int i = 0; i < nums2.length; i++) {
			if (map.containsKey(nums2[i])) {
				int account = map.get(nums2[i]);
				if (account == 1)
					map.remove(nums2[i]);
				else 
					map.put(nums2[i], (account - 1));
				intersectSet.add(nums2[i]);
			}
		}
		int[] num = new int[intersectSet.size()];
		for (int i = 0; i < num.length; i++) {
			num[i] = intersectSet.remove(0);
		}
		return num;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question350 q = new Question350();
		int[] nums1 = new int[]{1,3,5,5,7};
		int[] nums2 = new int[]{2,3,5,7};
		int[] num = q.intersect(nums1, nums2);
		for (int i = 0; i < num.length; i++)
			System.out.println(num[i]);
	}

}
