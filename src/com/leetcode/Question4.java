package com.leetcode;

import java.util.Arrays;

/******************************
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 *  should be O(log (m+n)).
 *  
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * 
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * @author Walterll
 *
 */
public class Question4 {
	/*************
	 * 第一版，未看清题意，使用java自带的快排
	 * 复杂度为(m+n)log(m+n)，未达到要求
	 * 其实给的两组数组已经排序了
	 * @param nums1
	 * @param nums2
	 * @return
	 */
//	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        double median = 0;
//        int size = nums1.length + nums2.length;
//        int[] num = new int[size];
//        for (int i = 0; i < size; i++) {
//        	if (i < nums1.length)
//        		num[i] = nums1[i];
//        	else
//        		num[i] = nums2[i - nums1.length];
//        }
//        Arrays.sort(num);
//        if ((size % 2) == 0)
//        	median = (double)(num[size / 2 - 1] + num[size / 2]) / 2;
//        else
//        	median = num[(size) / 2];
//        return median;
//    }
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		int midIndice = 0;		//中位数下标
		if ((size % 2) == 0)
			midIndice = size / 2 - 1;
		else
			midIndice = size / 2;
		int num1Indice, num2Indice = 0;
		double median = 0;
		
		return median;
	}
	
	private double findMedian(int[] nums1, int[] nums2, int midIndice, 
								int num1Indice, int num2Indice) {
		if (???)
			return ??? ;
		if (nums1[num1Indice] > nums2[num2Indice])
			findMedian(nums1, nums2, midIndice, num1Indice, (nums2.length - num2Indice) / 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = new int[]{1,2,4,6,13};
		int[] nums2 = new int[]{3,5,11,22};
		Question4 q = new Question4();
		System.out.println(q.findMedianSortedArrays(nums1, nums2));
	}

}
